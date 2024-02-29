package doctors.alura.address;

import doctors.alura.doctors.Specialty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record AddressData(
        @NotBlank
        String street,
        @NotBlank
        String neighborhood,
        @NotBlank
        @Pattern(regexp = "\\d{8}")
        String zip_code,
        String complement,
        @NotBlank
        String city,
        @NotBlank
        String uf) {
}
