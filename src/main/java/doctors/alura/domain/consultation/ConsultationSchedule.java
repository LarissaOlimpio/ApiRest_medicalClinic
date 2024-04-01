package doctors.alura.domain.consultation;

import doctors.alura.domain.CustomValidationException;
import doctors.alura.domain.consultation.validation.AllValidators;
import doctors.alura.domain.doctors.Doctors;
import doctors.alura.domain.doctors.DoctorsRepository;
import doctors.alura.domain.patients.PatientsRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultationSchedule {
    @Autowired
    private ConsultationRepository consultationRepository;
    @Autowired
    private PatientsRepository patientsRepository;
    @Autowired
    private DoctorsRepository doctorsRepository;

    @Autowired
    private List<AllValidators> validatorsList;

    public void schedule(ConsultationData data){
        if(!patientsRepository.existsById(data.patientId())){
            throw  new CustomValidationException("ID patient not exist");
        }
        if (data.doctorId() != null && !doctorsRepository.existsById(data.doctorId())) {
            throw  new CustomValidationException("ID doctor not exist");
        }
        validatorsList.forEach(v -> v.validator(data));
        var doctor = chooseDoctor(data);
        var patient = patientsRepository.getReferenceById(data.patientId());

        var consultation = new Consultation( null, doctor, patient, data.data(), null);
        consultationRepository.save(consultation);

    }

    private Doctors chooseDoctor(ConsultationData data) {
        if(data.doctorId() != null){
            return doctorsRepository.getReferenceById(data.doctorId());

        }
        if(data.specialty() == null){
            throw new ValidationException("Specialty is mandatory when doctor is not chosen");

        }
        return doctorsRepository.chooseRandomDoctor(data.specialty(), data.data());
    }

    public void delete(ConsultationDataDelete data) {
        if(!consultationRepository.existsById(data.idConsultation())){
            throw new ValidationException("Consultation Id not exist");
        }
        var consultation = consultationRepository.getReferenceById(data.idConsultation());
        consultation.delete(data.reason());
    }
}
