package doctors.alura.domain.appointment.validation;

import doctors.alura.domain.CustomValidationException;
import doctors.alura.domain.appointment.ConsultationData;
import doctors.alura.domain.patients.PatientsRepository;
import org.springframework.stereotype.Component;

@Component
public class InactivePatientsValidator implements AllValidators{
    private PatientsRepository repository;
    public void validator(ConsultationData data){
        boolean PatientIsActive = repository.findActiveById(data.idPatient());
        if(!PatientIsActive){
            throw new CustomValidationException("Appointment can't be schedule with Inactive patients");
        }

    }
}
