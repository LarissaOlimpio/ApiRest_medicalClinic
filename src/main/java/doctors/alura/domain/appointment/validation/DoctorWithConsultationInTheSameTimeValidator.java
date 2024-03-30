package doctors.alura.domain.appointment.validation;

import doctors.alura.domain.CustomValidationException;
import doctors.alura.domain.appointment.ConsultationData;
import doctors.alura.domain.appointment.ConsultationRepository;
import org.springframework.stereotype.Component;

@Component
public class DoctorWithConsultationInTheSameTimeValidator implements AllValidators{
    private ConsultationRepository repository;
    public void validator(ConsultationData data){
        var thereConsultationWithThisDoctor = repository.existByDoctorIdAndData(data.idDoctor(),data.data());
        if(thereConsultationWithThisDoctor){
            throw new CustomValidationException("The doctor already have an consultation in this time");
        }

    }
}
