package doctors.alura.controller;

import doctors.alura.domain.appointment.AppointmentData;
import doctors.alura.domain.appointment.AppointmentDetails;
import doctors.alura.domain.appointment.ScheduleAppointment;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("doctor-appointment")
public class DoctorAppointment {
    @Autowired
    private ScheduleAppointment schedule;
    @PostMapping
    public ResponseEntity schedule(@RequestBody @Valid AppointmentData data){
        schedule.schedule(data);
        return ResponseEntity.ok(new AppointmentDetails(data));
    }
}
