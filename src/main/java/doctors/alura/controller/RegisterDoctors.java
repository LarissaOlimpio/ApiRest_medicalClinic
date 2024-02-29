package doctors.alura.controller;

import doctors.alura.doctors.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("doctors")
public class RegisterDoctors {
    @Autowired
    private DoctorsRepository repository;


    @PostMapping
    @Transactional
    public ResponseEntity register(@RequestBody @Valid DoctorData data, UriComponentsBuilder uriBuilder){
        var doctor = new Doctors(data);
        repository.save(doctor);
        var uri = uriBuilder.path("/doctors/{id}").buildAndExpand(doctor.getId()).toUri();
        return ResponseEntity.created(uri).body(new DoctorDetails(doctor));

    }
    @GetMapping
    public ResponseEntity <Page<DoctorsDataList>> list(@PageableDefault(size = 10, sort = {"name"}) Pageable pagination){
        var page =  repository.findAllByActiveTrue(pagination).map(DoctorsDataList::new);
        return ResponseEntity.ok(page);
    }
    @PutMapping
    @Transactional
    public ResponseEntity update(@RequestBody @Valid DataDoctorsUpdate data){
        var doctor = repository.getReferenceById(data.id());
        doctor.updateInformation(data);
        return ResponseEntity.ok(new DoctorDetails(doctor));

    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteDoctors(@PathVariable Long id){
       var doctor = repository.getReferenceById(id);
       doctor.delete();
       return  ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity getDetailsDoctor(@PathVariable Long id){
        var doctor =  repository.getReferenceById(id);
        return  ResponseEntity.ok(new DoctorDetails(doctor));
    }

}
