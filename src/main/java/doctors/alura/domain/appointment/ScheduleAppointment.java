package doctors.alura.domain.appointment;

import doctors.alura.domain.CustomValidationException;
import doctors.alura.domain.doctors.DoctorsRepository;
import doctors.alura.domain.patients.PatientsRepository;
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
        var doctor = doctorsRepository.findById(data.idDoctor()).get();
        var patient = patientsRepository.findById(data.idPatient()).get();

        var appointment = new Appointment( null, doctor, patient, data.data());
        repository.save(appointment);
    }
}
