package doctors.alura.domain.users;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Table(name = "users")
@Entity(name = "Users")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Users implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String login;
    private String phone;
    private String cpf;
    private String email;
    private String password;
    private TypeUser typeUser;
    public boolean active;

    public Users(UserCreateAccountData userCreateAccountData){
        this.name = userCreateAccountData.name();
        this.login = userCreateAccountData.login();
        this.phone = userCreateAccountData.phone();
        this.cpf = userCreateAccountData.cpf();
        this.email = userCreateAccountData.email();
        this.password =userCreateAccountData.password();
        this.typeUser = userCreateAccountData.typeUser();
        this.active = true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        List<GrantedAuthority>authorities = new ArrayList<>();
//        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
//        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
//        return authorities;
        if(this.typeUser == TypeUser.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_DOCTOR"), new SimpleGrantedAuthority("ROLE_PATIENT"));
        else if (this.typeUser == TypeUser.DOCTOR) return  List.of(new SimpleGrantedAuthority("ROLE_DOCTOR"));
        else return List.of(new SimpleGrantedAuthority("ROLE_PATIENT"));

    }


    @Override
    public String getUsername() {
        return login;
    }
    @Override
    public String getPassword(){
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void encryptAndSetPassword(String password, PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(password);
    }

    public void updateInformation(UsersDataUpdate data) {
        if(data.name() != null){
            this.email = data.email();
        }
        if(data.email() != null){
            this.email = data.email();
        }
        if(data.phone() != null){
            this.phone = data.phone();
        }
        if(data.password() != null){
            this.password = data.password();
        }

    }

    public void delete() {
        this.active = false;
    }
}
