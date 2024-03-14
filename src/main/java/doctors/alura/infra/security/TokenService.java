package doctors.alura.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import doctors.alura.domain.users.Users;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;


@Service
public class TokenService {

    public String createToken(Users users){
        try {
            var algorithm = Algorithm.HMAC256("123456");
            return JWT.create()
                    .withIssuer("api doctors clinic")
                    .withSubject(users.getLogin())
                    .withExpiresAt(dataExpires())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw  new RuntimeException("error: ",exception);
        }
    }

    private Instant dataExpires() {
        return LocalDateTime.now().plusHours(2).atZone(ZoneId.of("America/Sao_Paulo")).toInstant();
    }

}
