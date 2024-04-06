package doctors.alura.controller;
import doctors.alura.domain.patients.Patients;
import doctors.alura.domain.patients.PatientsDataList;
import doctors.alura.domain.patients.PatientsRepository;
import doctors.alura.domain.patients.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("patients")
@SecurityRequirement(name = "bearer-key")
public class RegisterPatients {
    @Autowired
    private PatientsRepository repository;

    @PostMapping
    @Transactional
    public  ResponseEntity register(@RequestBody @Valid PatientsData data, UriComponentsBuilder uriBuilder){
        var patients = new Patients(data);
        repository.save(patients);
        var uri = uriBuilder.path("patients/{id}").buildAndExpand(patients.getId()).toUri();
        return ResponseEntity.created(uri).body(new PatientsDetails(patients));

    }
    @GetMapping
    public ResponseEntity<Page<PatientsDataList>> list(@PageableDefault( size=10, sort ={"name"})Pageable pagination){
        var page =  repository.findAllByActiveTrue(pagination).map(PatientsDataList :: new);
        return ResponseEntity.ok(page);
    }
    @PutMapping
    @Transactional
    public ResponseEntity update(@RequestBody @Valid PatientsDataUpdate data){
        var patients = repository.getReferenceById(data.id());
        patients.updateInformation(data);
        return ResponseEntity.ok(new PatientsDetails(patients));

    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletePatients(@PathVariable Long id){
        var patients = repository.getReferenceById(id);
        patients.delete();
        return ResponseEntity.noContent().build();
    }
}
