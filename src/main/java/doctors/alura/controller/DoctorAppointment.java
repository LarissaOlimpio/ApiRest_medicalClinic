package doctors.alura.controller;

import doctors.alura.domain.appointment.AppointmentData;
import doctors.alura.domain.appointment.AppointmentDetails;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("doctor-appointment")
public class DoctorAppointment {
    @PostMapping
    public ResponseEntity appointment(@RequestBody @Valid AppointmentData data){
        return ResponseEntity.ok(new AppointmentDetails(data));
    }
}
