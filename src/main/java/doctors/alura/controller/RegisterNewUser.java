package doctors.alura.controller;

import doctors.alura.domain.users.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
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
    @GetMapping
    public ResponseEntity<Page<UserDataList>> list(@PageableDefault(size = 10, sort = {"name"})Pageable pagination){
        var page = repository.findAllByActiveTrue(pagination).map( UserDataList :: new);
        return ResponseEntity.ok(page);
    }
    @PutMapping
    @Transactional
    public ResponseEntity update(@RequestBody @Valid UsersDataUpdate data){
        var users = repository.getReferenceById(data.id());
        users.updateInformation(data);
        return ResponseEntity.ok(new userDetails(users));
    }



}
