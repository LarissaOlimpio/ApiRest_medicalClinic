package doctors.alura.domain.appointment;

import doctors.alura.domain.CustomValidationException;
import doctors.alura.domain.appointment.validation.AllValidators;
import doctors.alura.domain.doctors.Doctors;
import doctors.alura.domain.doctors.DoctorsRepository;
import doctors.alura.domain.patients.PatientsRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleAppointment {
    @Autowired
    private ConsultationRepository consultationRepository;
    @Autowired
    private PatientsRepository patientsRepository;
    @Autowired
    private DoctorsRepository doctorsRepository;

    @Autowired
    private List<AllValidators> validatorsList;

    public void schedule(ConsultationData data){
        if(!patientsRepository.existsById(data.idPatient())){
            throw  new CustomValidationException("ID patient not exist");
        }
        if (data.idDoctor() != null && !doctorsRepository.existsById(data.idDoctor())) {
            throw  new CustomValidationException("ID doctor not exist");
        }
        validatorsList.forEach(v -> v.validator(data));
        var doctor = chooseDoctor(data);
        var patient = patientsRepository.getReferenceById(data.idPatient());

        var appointment = new Consultation( null, doctor, patient, data.data(), null);
        consultationRepository.save(appointment);
        System.out.println(appointment);
    }

    private Doctors chooseDoctor(ConsultationData data) {
        if(data.idDoctor() != null){
            return doctorsRepository.getReferenceById(data.idDoctor());

        }
        if(data.specialty() == null){
            throw new ValidationException("Specialty is mandatory when doctor is not chosen");

        }
        return doctorsRepository.chooseRandomDoctor(data.specialty(), data.data());
    }

    public void delete(ConsultationDataDelete data) {
        if(!consultationRepository.existsById(data.idAppointment())){
            throw new ValidationException("Consultation Id not exist");
        }
        var appointment = consultationRepository.getReferenceById(data.idAppointment());
        appointment.delete(data.reason());
    }
}
