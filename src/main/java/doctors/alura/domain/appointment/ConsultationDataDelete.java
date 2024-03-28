package doctors.alura.domain.appointment;

import jakarta.validation.constraints.NotNull;

public record ConsultationDataDelete(
        @NotNull
        Long idAppointment,

        @NotNull
        ReasonDelete reason
) {

}
