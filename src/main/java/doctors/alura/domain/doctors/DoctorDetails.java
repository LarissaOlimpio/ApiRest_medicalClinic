package doctors.alura.domain.doctors;

import doctors.alura.domain.address.Address;

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
