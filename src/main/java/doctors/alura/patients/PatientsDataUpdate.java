package doctors.alura.patients;

import doctors.alura.address.AddressData;
import jakarta.validation.constraints.NotNull;

public record PatientsDataUpdate(
        @NotNull
        Long id,
        String phone,
        String email,
        AddressData address

) {
}
