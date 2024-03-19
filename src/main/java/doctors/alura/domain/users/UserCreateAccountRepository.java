package doctors.alura.domain.users;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCreateAccountRepository extends JpaRepository<UserCreateAccount, Long> {
}
