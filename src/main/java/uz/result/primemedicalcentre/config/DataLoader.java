package uz.result.primemedicalcentre.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.result.primemedicalcentre.security.User;
import uz.result.primemedicalcentre.security.UserRepository;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (!userRepository.existsByUsername("qwer")) {
            User user = new User();
            user.setEnabled(true);
            user.setUsername("qwer");
            user.setPassword(passwordEncoder.encode("qwer"));
            userRepository.save(user);
        }
    }
}
