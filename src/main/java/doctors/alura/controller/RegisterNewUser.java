package doctors.alura.controller;

import doctors.alura.domain.users.UserCreateAccount;
import doctors.alura.domain.users.UserCreateAccountData;
import doctors.alura.domain.users.UserCreateAccountRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("account")
public class RegisterNewUser {
    private UserCreateAccountRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity registerNewUser(@RequestBody @Valid UserCreateAccountData userCreateAccountData, UriComponentsBuilder uriBuilder){
        var user = new UserCreateAccount(userCreateAccountData);
        repository.save(user);
        var uri = uriBuilder.path("account/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(user);
    }

}
