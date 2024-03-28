package doctors.alura.domain.appointment.validation;

import doctors.alura.domain.appointment.ConsultationData;
import jakarta.validation.ValidationException;

import java.time.Duration;
import java.time.LocalDateTime;

public class EarlyTimeValidator {
    public void validator(ConsultationData data){
        var consultationHour = data.data();
        var now = LocalDateTime.now();
        var difference = Duration.between(now,consultationHour).toMinutes();
        if(difference < 30){
            throw new ValidationException("A medical appointment must be scheduled at least 30 minutes in advance.");
        }

    }
}
