package doctors.alura.domain.appointment;

import doctors.alura.domain.CustomValidationException;
import doctors.alura.domain.doctors.Doctors;
import doctors.alura.domain.doctors.DoctorsRepository;
import doctors.alura.domain.patients.PatientsRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScheduleAppointment {
    @Autowired
    private AppointmentRepository repository;
    @Autowired
    private PatientsRepository patientsRepository;
    @Autowired
    private DoctorsRepository doctorsRepository;

    public void schedule(AppointmentData data){
        if(!patientsRepository.existsById(data.idPatient())){
            throw  new CustomValidationException("ID patient not exist");
        }
        if (data.idDoctor() != null && !doctorsRepository.existsById(data.idDoctor())) {
            throw  new CustomValidationException("ID doctor not exist");
        }
        var doctor = chooseDoctor(data);
        var patient = patientsRepository.getReferenceById(data.idPatient());

        var appointment = new Appointment( null, doctor, patient, data.data());
        repository.save(appointment);
        System.out.println(appointment);
    }

    private Doctors chooseDoctor(AppointmentData data) {
        if(data.idDoctor() != null){
            return doctorsRepository.getReferenceById(data.idDoctor());

        }
        if(data.specialty() == null){
            throw new ValidationException("Specialty is mandatory when doctor is not chosen");

        }
        return doctorsRepository.chooseRandomDoctor(data.specialty(), data.data());
    }
}
