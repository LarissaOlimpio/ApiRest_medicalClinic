package doctors.alura.domain.users;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "user_create_account")
@Entity(name = "UserCreateAccount")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class UserCreateAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String login;
    private String phone;
    private String cpf;
    private String password;
    @Pattern(regexp = "admin/doctor/patient")
    private String userType;

    public UserCreateAccount(UserCreateAccountData userCreateAccountData) {
        this.name = userCreateAccountData.name();
        this.login = userCreateAccountData.login();
        this.phone = userCreateAccountData.phone();
        this.cpf = userCreateAccountData.cpf();
        this.password =userCreateAccountData.password();
        this.userType = userCreateAccountData.userType();
    }
}
