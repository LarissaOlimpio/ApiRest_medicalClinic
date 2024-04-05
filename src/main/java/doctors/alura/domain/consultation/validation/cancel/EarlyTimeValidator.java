package doctors.alura.domain.consultation.validation.cancel;

import doctors.alura.domain.CustomValidationException;
import doctors.alura.domain.consultation.ConsultationDataDelete;
import doctors.alura.domain.consultation.ConsultationRepository;

import java.time.Duration;
import java.time.LocalDateTime;

public class EarlyTimeValidator implements CancellationValidator{
   private ConsultationRepository repository;
    @Override
    public void validator(ConsultationDataDelete data) {
        var consultation = repository.getReferenceById(data.idConsultation());
        var  now = LocalDateTime.now();
        var differenceBetweenHours = Duration.between(now, consultation.getData()).toHours();

        if(differenceBetweenHours < 24){
            throw new CustomValidationException("Consultations must be canceled more than 24 hours in advance");
        }
    }
}
