package doctors.alura.domain.patients;

import doctors.alura.domain.address.AddressData;
import jakarta.validation.constraints.NotNull;

public record PatientsDataUpdate(
        @NotNull
        Long id,
        String phone,
        String email,
        AddressData address

) {
}
