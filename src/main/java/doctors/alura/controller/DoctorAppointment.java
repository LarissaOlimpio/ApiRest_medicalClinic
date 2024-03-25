package doctors.alura.controller;

import doctors.alura.domain.appointment.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("doctor-appointment")
public class DoctorAppointment {
    @Autowired
    private ScheduleAppointment schedule;
    @Autowired
    private AppointmentRepository appointmentRepository;
    @PostMapping
    public ResponseEntity schedule(@RequestBody @Valid AppointmentData data){
        schedule.schedule(data);
        return ResponseEntity.ok(new AppointmentDetails(data));
    }
    @DeleteMapping ("{/id}")
    @Transactional
    public ResponseEntity deleteAppointment(@RequestBody @Valid AppointmentDataDelete data){
        schedule.delete(data);
        return ResponseEntity.noContent().build();
    }
}
