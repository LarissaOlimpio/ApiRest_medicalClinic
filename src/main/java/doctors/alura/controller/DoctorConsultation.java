package doctors.alura.controller;

import doctors.alura.domain.appointment.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("doctor-consultation")
public class DoctorConsultation {
    @Autowired
    private ScheduleAppointment schedule;
    @Autowired
    private ConsultationRepository consultationRepository;
    @PostMapping
    public ResponseEntity schedule(@RequestBody @Valid ConsultationData data){
        schedule.schedule(data);
        return ResponseEntity.ok(new ConsultationDetails(data));
    }
    @DeleteMapping
    @Transactional
    public ResponseEntity deleteAppointment(@RequestBody @Valid ConsultationDataDelete data){
        schedule.delete(data);
        return ResponseEntity.noContent().build();
    }
}
