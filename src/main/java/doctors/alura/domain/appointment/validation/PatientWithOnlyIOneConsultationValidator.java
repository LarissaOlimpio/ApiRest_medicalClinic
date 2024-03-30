package doctors.alura.domain.appointment.validation;

import doctors.alura.domain.CustomValidationException;
import doctors.alura.domain.appointment.ConsultationData;
import doctors.alura.domain.appointment.ConsultationRepository;
import org.springframework.stereotype.Component;

@Component
public class PatientWithOnlyIOneConsultationValidator implements  AllValidators{
    private ConsultationRepository repository;
    public void validator(ConsultationData data){
        var firstHour  = data.data().withHour(7);
        var lastHour = data.data().withHour(18);
        var thereAnotherConsultationThisPatient = repository.existsByPatientsIdAndDataBetween(data.idPatient(),firstHour,lastHour);
        if(thereAnotherConsultationThisPatient){
            throw  new CustomValidationException("Patients already have an consultation scheduled for that day");
        }

    }
}
