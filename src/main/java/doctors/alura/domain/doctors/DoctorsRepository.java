package doctors.alura.domain.doctors;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
@Repository
public interface DoctorsRepository extends JpaRepository<Doctors, Long> {
    Page<Doctors> findAllByActiveTrue(Pageable pagination);
    @Query("""
            select d from Doctors d
            where
            d.active = true
            and
            d.specialty = :specialty
            and
            d.id not in (
                select c.doctor.id from Consultation c
                where
                c.data = :data
            and
                c.reasonDelete is null
            )
            order by rand()
            limit 1
            """)
    Doctors chooseRandomDoctor(Specialty specialty, LocalDateTime data);

    @Query("""
            select d.active
            from Doctors d
            where
            d.id = :id
            """)
    Boolean findActiveById(Long id);
}
