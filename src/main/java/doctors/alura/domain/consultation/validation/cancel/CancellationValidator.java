package doctors.alura.domain.consultation.validation.cancel;

import doctors.alura.domain.consultation.ConsultationDataDelete;

public interface CancellationValidator {
    void validator(ConsultationDataDelete data);
}
