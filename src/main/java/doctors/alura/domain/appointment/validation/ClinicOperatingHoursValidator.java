package doctors.alura.domain.appointment.validation;

import doctors.alura.domain.appointment.ConsultationData;
import jakarta.validation.ValidationException;

import java.time.DayOfWeek;

public class ClinicOperatingHoursValidator {

    public void validator(ConsultationData data){
        var dataConsultation = data.data();
        var isEqualsSunday = dataConsultation.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var hoursClinicOpen = dataConsultation.getHour() < 7;
        var hoursClinicClose = dataConsultation.getHour() > 18;

        if(isEqualsSunday || hoursClinicOpen || hoursClinicClose){
            throw  new ValidationException("Medical consultation outside the clinic's opening hours");
        }

    }

}
