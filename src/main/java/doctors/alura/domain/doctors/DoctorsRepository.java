package doctors.alura.domain.doctors;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface DoctorsRepository extends JpaRepository<Doctors, Long> {
    Page<Doctors> findAllByActiveTrue(Pageable pagination);
    @Query("""
            select d from Doctor d
            where
            d.active = true
            and
            d.specialty = :specialty
            and
            d.id not in (
                select c.doctor.id from consultation c
                where
                c.data = :data
            )
            order by rand()
            limit 1
            """)
    Doctors chooseRandomDoctor(Specialty specialty, LocalDateTime data);
}
