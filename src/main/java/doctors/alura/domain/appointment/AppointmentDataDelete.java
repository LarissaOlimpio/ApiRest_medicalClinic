package doctors.alura.domain.appointment;

import jakarta.validation.constraints.NotNull;

public record AppointmentDataDelete(
        @NotNull
        Long idAppointment,

        @NotNull
        ReasonDelete reason
) {

}
