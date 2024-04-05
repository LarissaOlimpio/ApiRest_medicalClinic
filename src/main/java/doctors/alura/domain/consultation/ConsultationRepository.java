package doctors.alura.domain.consultation;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
@Repository
public interface ConsultationRepository extends JpaRepository<Consultation,Long> {
    boolean existsByPatientIdAndDataBetween(Long id, LocalDateTime firstHour, LocalDateTime lastHour);

     boolean existsByDoctorIdAndDataAndReasonDeleteIsNull(Long id, LocalDateTime data) ;

    Page<Consultation> findAllByReasonDeleteIsNull(Pageable pagination);
}
