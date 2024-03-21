package doctors.alura.domain.users;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record userDetails(String name, String login, String phone, String password, String cpf, String userType) {
    public userDetails(Users user){
        this(user.getName(), user.getLogin(), user.getPhone(), user.getPassword(), user.getCpf(), user.getUserType());

    }


}
