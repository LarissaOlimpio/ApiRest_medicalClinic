package doctors.alura.domain.consultation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
@Repository
public interface ConsultationRepository extends JpaRepository<Consultation,Long> {
    boolean existsByPatientIdAndDataBetween(Long id, LocalDateTime firstHour, LocalDateTime lastHour);

     boolean existsByDoctorIdAndData(Long id, LocalDateTime data) ;
}
