package doctors.alura.domain.consultation;

import doctors.alura.domain.doctors.Specialty;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ConsultationData(

        Long doctorId,
        @NotNull
        Long patientId,
        @NotNull
        @Future
        LocalDateTime data,
        Specialty specialty
                              ) {
}
