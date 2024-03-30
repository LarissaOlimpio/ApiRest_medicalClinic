package doctors.alura.domain.appointment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface ConsultationRepository extends JpaRepository<Consultation,Long> {
    Boolean existsByPatientsIdAndDataBetween(Long aLong, LocalDateTime firstHour, LocalDateTime lastHour);

    Boolean existByDoctorIdAndData(Long aLong,LocalDateTime data);
}
