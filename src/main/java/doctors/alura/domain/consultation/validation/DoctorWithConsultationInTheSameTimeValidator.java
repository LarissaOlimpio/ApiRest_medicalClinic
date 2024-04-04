package doctors.alura.domain.consultation.validation;

import doctors.alura.domain.CustomValidationException;
import doctors.alura.domain.consultation.ConsultationData;
import doctors.alura.domain.consultation.ConsultationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DoctorWithConsultationInTheSameTimeValidator implements AllValidators{
    @Autowired
    private ConsultationRepository repository;
    public void validator(ConsultationData data){
        var thereConsultationWithThisDoctor = repository.existsByDoctorIdAndData(data.doctorId(),data.data());
        if(thereConsultationWithThisDoctor){
            throw new CustomValidationException("The doctor already have an consultation in this time");
        }

    }
}
