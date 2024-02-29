package doctors.alura.controller;

import doctors.alura.patients.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("patients")

public class RegisterPatients {
    @Autowired
    private PatientsRepository repository;

    @PostMapping
    @Transactional
    public  void register(@RequestBody @Valid PatientsData data){
        repository.save(new Patients(data));

    }
    @GetMapping
    public Page<PatientsDataList> list(@PageableDefault( size=10, sort ={"name"})Pageable pagination){
        return repository.findAllByActiveTrue(pagination).map(PatientsDataList :: new);
    }
    @PutMapping
    @Transactional
    public void update(@RequestBody @Valid PatientsDataUpdate data){
        var patients = repository.getReferenceById(data.id());
        patients.updateInformation(data);

    }
    @DeleteMapping("/{id}")
    @Transactional
    public void deletePatients(@PathVariable Long id){
        var patients = repository.getReferenceById(id);
        patients.delete();
    }
}
