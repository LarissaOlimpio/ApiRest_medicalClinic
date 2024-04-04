package doctors.alura.domain.consultation;

import java.time.LocalDateTime;

public record ConsultationDetails(Long id, Long doctorId, Long patientId, LocalDateTime data) {
    public ConsultationDetails(Consultation consultation) {
        this(consultation.getId(),consultation.getDoctor().getId(), consultation.getPatient().getId(),consultation.getData());
    }
}
