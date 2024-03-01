package doctors.alura.domain.patients;

import doctors.alura.domain.address.AddressData;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PatientsData(
        @NotBlank
        String name,
        @NotBlank
        String phone,
        @NotBlank
        String email,
         @NotNull @Valid
        AddressData address) {
}
