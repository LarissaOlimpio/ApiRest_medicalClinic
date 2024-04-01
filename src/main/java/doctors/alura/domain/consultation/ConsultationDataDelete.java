package doctors.alura.domain.consultation;

import jakarta.validation.constraints.NotNull;

public record ConsultationDataDelete(
        @NotNull
        Long idConsultation,

        @NotNull
        ReasonDelete reason
) {

}
