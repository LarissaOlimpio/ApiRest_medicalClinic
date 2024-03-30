package doctors.alura.domain.appointment.validation;

import doctors.alura.domain.CustomValidationException;
import doctors.alura.domain.appointment.ConsultationData;
import org.springframework.stereotype.Component;


import java.time.DayOfWeek;
@Component
public class ClinicOperatingHoursValidator implements   AllValidators{

    public void validator(ConsultationData data){
        var dataConsultation = data.data();
        var isEqualsSunday = dataConsultation.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var hoursClinicOpen = dataConsultation.getHour() < 7;
        var hoursClinicClose = dataConsultation.getHour() > 18;

        if(isEqualsSunday || hoursClinicOpen || hoursClinicClose){
            throw  new CustomValidationException("Medical consultation outside the clinic's opening hours");
        }

    }

}
