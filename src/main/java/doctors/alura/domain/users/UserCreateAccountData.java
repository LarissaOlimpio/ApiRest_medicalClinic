package doctors.alura.domain.users;

import jakarta.validation.constraints.NotBlank;

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
        String cpf) {
}
