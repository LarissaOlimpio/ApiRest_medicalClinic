package doctors.alura.doctors;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;

public interface DoctorsRepository extends JpaRepository<Doctors, Long> {
    Page<Doctors> findAllByActiveTrue(Pageable pagination);
}
