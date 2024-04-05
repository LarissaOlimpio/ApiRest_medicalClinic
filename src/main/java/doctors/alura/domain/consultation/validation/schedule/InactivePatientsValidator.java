package doctors.alura.domain.consultation.validation.schedule;

import doctors.alura.domain.CustomValidationException;
import doctors.alura.domain.consultation.ConsultationData;
import doctors.alura.domain.consultation.validation.schedule.AllValidators;
import doctors.alura.domain.patients.PatientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InactivePatientsValidator implements AllValidators {
    @Autowired
    private PatientsRepository repository;
    public void validator(ConsultationData data){
        boolean PatientIsActive = repository.findActiveById(data.patientId());
        if(!PatientIsActive){
            throw new CustomValidationException("This Consultation can't be schedule with Inactive patients");
        }

    }
}
