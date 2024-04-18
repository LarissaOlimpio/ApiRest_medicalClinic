package doctors.alura.domain.users;

import jakarta.validation.constraints.NotNull;

public record UsersDataUpdate(
        @NotNull
        Long id,
        String name,
        String phone,
        String email,
        String password

) {
}
