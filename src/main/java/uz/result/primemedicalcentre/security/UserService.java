package uz.result.primemedicalcentre.security;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.result.primemedicalcentre.domain.dto.ApiResponse;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtTokenService jwtTokenService;

    public ResponseEntity<ApiResponse<Token>> login(String username, String password) {
        ApiResponse<Token> response = new ApiResponse<>();
        Optional<User> optional = userRepository.findByUsername(username);
        if (optional.isEmpty()) {
            response.setMessage("Username or password is incorrect");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
        User user = optional.get();
        if (passwordEncoder.matches(password, user.getPassword())) {
            Token token = new Token(jwtTokenService.generateToken(username));
            response.setMessage(String.format(
                    "Successfully logged in as: %s.", user.getUsername()
            ));
            response.setData(token);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            response.setMessage("Username or password is incorrect");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }

}
