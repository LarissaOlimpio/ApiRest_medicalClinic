package doctors.alura.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import doctors.alura.domain.users.Users;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;


@Service
public class TokenService {
    @Value("${api.security.token.secret}")
    private String secret;

    public String createToken(Users users){
        try {
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("api doctors clinic")
                    .withSubject(users.getLogin())
                    .withExpiresAt(dataExpires())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw  new RuntimeException("error: ",exception);
        }
    }
    public String getSubject(String tokenJwt){
        try {
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("api doctors clinic")
                    .build()
                    .verify(tokenJwt)
                    .getSubject();

        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Token JWT invalid or expired!");
        }
    }

    private Instant dataExpires() {
        return LocalDateTime.now().plusHours(2).atZone(ZoneId.of("America/Sao_Paulo")).toInstant();
    }

}
