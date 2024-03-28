package doctors.alura.domain.appointment.validation;

import doctors.alura.domain.CustomValidationException;
import doctors.alura.domain.appointment.ConsultationData;
import doctors.alura.domain.doctors.DoctorsRepository;


public class InactiveDoctorsValidator {
    private DoctorsRepository repository;
    public void validator(ConsultationData data){

       if(data.idDoctor() == null ){
           return;
        }
        boolean doctorIsActive = (boolean) repository.findActiveById(data.idDoctor());
        if(!doctorIsActive){
            throw new CustomValidationException("appointment cannot be scheduled with an inactive doctor");
        }

    }
}
