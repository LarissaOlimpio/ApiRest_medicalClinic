package doctors.alura.controller;

import doctors.alura.domain.users.UserCreateAccountData;
import doctors.alura.domain.users.UserCreateAccountRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("create-account")
public class RegisterNewUser {
    private UserCreateAccountRepository repositoryy;

    @PostMapping
    @Transactional
    public ResponseEntity registerNewUser(@RequestBody @Valid UserCreateAccountData userCreateAccountData){
        return null;
    }

}
