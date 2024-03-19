package doctors.alura.domain.users;

import jakarta.persistence.*;
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

    public UserCreateAccount(UserCreateAccountData userCreateAccountData) {
        this.name = userCreateAccountData.name();
        this.login = userCreateAccountData.login();
        this.phone = userCreateAccountData.phone();
        this.cpf = userCreateAccountData.cpf();
    }
}
