package doctors.alura.patients;

import doctors.alura.address.Address;

public record PatientsDetails(String name, String phone, String email, Address address) {
    public PatientsDetails(Patients patients) {
        this(patients.getname(), patients.getPhone(), patients.getEmail(), patients.getAddress());
    }
}
