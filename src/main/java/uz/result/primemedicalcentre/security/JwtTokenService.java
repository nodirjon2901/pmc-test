package uz.result.primemedicalcentre.security;

import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class JwtTokenService {

    private final UserRepository userRepository;

    @Value("${jwt.token.secretKey}")
    private String secretKey;

    @Value("${jwt.token.expireDateInMilliSeconds}")
    private Long expireDate;

    public String generateToken(String subject) {
        Date now = new Date();

        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(now)
                .setExpiration(new Date((now.getTime() + expireDate)))
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts
                    .parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            System.out.println("Muddati o'tgan");
        } catch (MalformedJwtException e) {
            System.out.println("Buzilgan token");
        } catch (SignatureException e) {
            System.out.println("Kalit so'z xato");
        } catch (UnsupportedJwtException e) {
            System.out.println("Qo'llanilmagan token");
        } catch (IllegalArgumentException e) {
            System.out.println("Bo'sh token");
        }
        return false;
    }

    public User getUserFromToken(String token) {
        try {
            String subject = Jwts
                    .parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
            return userRepository.findByUsername(subject).orElseThrow(RuntimeException::new);
        } catch (Exception e) {
            throw new AuthorizationFailedException("Authorization failed. Please login again");
        }
    }
}
