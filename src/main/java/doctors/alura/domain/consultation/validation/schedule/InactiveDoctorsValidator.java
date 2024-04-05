package doctors.alura.domain.consultation.validation.schedule;

import doctors.alura.domain.CustomValidationException;
import doctors.alura.domain.consultation.ConsultationData;
import doctors.alura.domain.consultation.validation.schedule.AllValidators;
import doctors.alura.domain.doctors.DoctorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InactiveDoctorsValidator implements AllValidators {
    @Autowired
    private DoctorsRepository repository;
    public void validator(ConsultationData data){

       if(data.doctorId() == null ){
           return;
        }
        boolean doctorIsActive = (boolean) repository.findActiveById(data.doctorId());
        if(!doctorIsActive){
            throw new CustomValidationException("This consultation can't be scheduled with an inactive doctor");
        }

    }
}
