package doctors.alura.domain.patients;

public record PatientsDataList(Long id, String name, String email, String phone) {

    public PatientsDataList(Patients patients){
        this(patients.getId(), patients.getname(), patients.getEmail(), patients.getPhone());
    }
}
