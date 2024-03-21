package doctors.alura.controller;

import doctors.alura.domain.users.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("account")
public class RegisterNewUser {
    @Autowired
    private UserRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    @Transactional
    public ResponseEntity registerNewUser(@RequestBody @Valid UserCreateAccountData data, UriComponentsBuilder uriBuilder){
        var userCreated = new Users(data);
        userCreated.encryptAndSetPassword(data.password(), passwordEncoder);
        repository.save(userCreated);
        var uri = uriBuilder.path("/account/{id}").buildAndExpand(userCreated.getId()).toUri();
        return ResponseEntity.created(uri).body(new userDetails(userCreated));
    }



}
