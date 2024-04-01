package doctors.alura.controller;

import doctors.alura.domain.consultation.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("consultation")
public class DoctorConsultation {
    @Autowired
    private ConsultationSchedule schedule;
    @Autowired
    private ConsultationRepository consultationRepository;
    @PostMapping
    public ResponseEntity schedule(@RequestBody @Valid ConsultationData data){
        schedule.schedule(data);
        return ResponseEntity.ok(new ConsultationDetails(data));
    }
    @DeleteMapping
    @Transactional
    public ResponseEntity deleteConsultation(@RequestBody @Valid ConsultationDataDelete data){
        schedule.delete(data);
        return ResponseEntity.noContent().build();
    }
}
