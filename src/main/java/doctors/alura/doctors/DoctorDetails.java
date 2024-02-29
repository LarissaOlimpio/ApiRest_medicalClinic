package doctors.alura.doctors;

import doctors.alura.address.Address;
import jakarta.persistence.Embedded;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public record DoctorDetails(
        Long id,
        String name,
        String email,
        String crm,
        String phone,

        Specialty specialty,

        Address address) {
    public DoctorDetails(Doctors doctor) {
        this(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getPhone(), doctor.getCrm(), doctor.getSpecialty(),doctor.getAddress());
    }
}
