package doctors.alura.domain.consultation.validation.schedule;

import doctors.alura.domain.CustomValidationException;
import doctors.alura.domain.consultation.ConsultationData;
import doctors.alura.domain.consultation.ConsultationRepository;
import doctors.alura.domain.consultation.validation.schedule.AllValidators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PatientWithOnlyIOneConsultationValidator implements AllValidators {
    @Autowired
    private ConsultationRepository repository;
    public void validator(ConsultationData data){
        var firstHour  = data.data().withHour(7);
        var lastHour = data.data().withHour(18);
        var thereAnotherConsultationThisPatient = repository.existsByPatientIdAndDataBetween(data.patientId(),firstHour,lastHour);
        if(thereAnotherConsultationThisPatient){
            throw  new CustomValidationException("Patients already have an consultation scheduled for that day");
        }

    }
}
