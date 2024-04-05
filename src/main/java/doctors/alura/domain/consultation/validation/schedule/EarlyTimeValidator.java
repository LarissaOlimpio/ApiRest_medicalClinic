package doctors.alura.domain.consultation.validation.schedule;

import doctors.alura.domain.CustomValidationException;
import doctors.alura.domain.consultation.ConsultationData;
import doctors.alura.domain.consultation.validation.schedule.AllValidators;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class EarlyTimeValidator implements AllValidators {
    public void validator(ConsultationData data){
        var consultationHour = data.data();
        var now = LocalDateTime.now();
        var difference = Duration.between(now,consultationHour).toMinutes();
        if(difference < 30){
            throw new CustomValidationException("A medical consultation must be scheduled at least 30 minutes in advance.");
        }

    }
}
