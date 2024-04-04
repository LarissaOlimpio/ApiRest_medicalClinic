package doctors.alura.controller;

import doctors.alura.domain.consultation.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("consultation")
public class DoctorConsultation {
    @Autowired
    private ConsultationSchedule schedule;
    @Autowired
    private ConsultationRepository consultationRepository;
    @PostMapping
    public ResponseEntity schedule(@RequestBody @Valid ConsultationData data){
        var dto = schedule.schedule(data);
        return ResponseEntity.ok(dto);
    }
    @DeleteMapping
    @Transactional
    public ResponseEntity deleteConsultation(@RequestBody @Valid ConsultationDataDelete data){
        schedule.delete(data);
        return ResponseEntity.noContent().build();
    }
    @GetMapping
    public ResponseEntity <List<ConsultationDetails>> listConsultations(Pageable pagination){
        Page<Consultation> consultationPage = consultationRepository.findAll(pagination);
        List<ConsultationDetails> consultationDetails = consultationPage.getContent().stream()
                .map(ConsultationDetails::new).toList();
        return ResponseEntity.ok(consultationDetails);

    }
}
