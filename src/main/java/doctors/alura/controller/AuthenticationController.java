package doctors.alura.controller;

import doctors.alura.domain.users.DataAuthentication;
import doctors.alura.domain.users.Users;
import doctors.alura.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.transaction.annotation.Transactional;
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
        var token = new UsernamePasswordAuthenticationToken(data.login(),data.password());
        var authentication = manager.authenticate(token);
        return ResponseEntity.ok(tokenService.createToken((Users) (authentication.getPrincipal())));

    }
}
