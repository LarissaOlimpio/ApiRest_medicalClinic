package doctors.alura.domain.users;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record UserCreateAccountData(
        @NotBlank
        String name,
        @NotBlank
        String login,
        @NotBlank
        String phone,
        @NotBlank
        String password,
        @NotBlank
        String cpf,
        @NotBlank
        String email,
       @NotNull
        TypeUser typeUser){
}
