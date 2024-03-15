package doctors.alura.controller;

import doctors.alura.domain.users.DataAuthentication;
import doctors.alura.domain.users.Users;
import doctors.alura.infra.security.DataTokenJwt;
import doctors.alura.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity login(@RequestBody @Valid DataAuthentication data){
        var tokenAuthentication = new UsernamePasswordAuthenticationToken(data.login(),data.password());
        var authentication = manager.authenticate(tokenAuthentication);
        var tokenJWT =tokenService.createToken((Users) (authentication.getPrincipal()));
        return ResponseEntity.ok(new DataTokenJwt(tokenJWT));

    }
}
