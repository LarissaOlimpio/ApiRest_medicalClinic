package doctors.alura.domain.patients;

import doctors.alura.domain.address.Address;

public record PatientsDetails(String name, String phone, String email, Address address) {
    public PatientsDetails(Patients patients) {
        this(patients.getname(), patients.getPhone(), patients.getEmail(), patients.getAddress());
    }
}
