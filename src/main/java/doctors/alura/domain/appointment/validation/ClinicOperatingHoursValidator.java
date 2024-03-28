package doctors.alura.domain.appointment.validation;

import doctors.alura.domain.appointment.AppointmentData;
import jakarta.validation.ValidationException;

import java.time.DayOfWeek;

public class ClinicOperatingHoursValidator {

    public void validator(AppointmentData data){
        var dataAppointment = data.data();
        var isEqualsSunday = dataAppointment.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var hoursAppointmentOpen = dataAppointment.getHour() < 7;
        var hoursAppointmentClose = dataAppointment.getHour() > 18;

        if(isEqualsSunday || hoursAppointmentOpen || hoursAppointmentClose){
            throw  new ValidationException("Medical consultation outside the clinic's opening hours");
        }

    }

}
