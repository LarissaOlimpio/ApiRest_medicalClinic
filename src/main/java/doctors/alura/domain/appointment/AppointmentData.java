package doctors.alura.domain.appointment;

import doctors.alura.domain.doctors.Specialty;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record AppointmentData(
        @NotNull
        Long idDoctor,
        @NotNull
        Long idPatient,
        @NotNull
        @Future
        LocalDateTime data,
        Specialty specialty
                              ) {
}
