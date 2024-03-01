package doctors.alura.domain.doctors;

import doctors.alura.domain.address.AddressData;
import jakarta.validation.constraints.NotNull;

public record DataDoctorsUpdate(
        @NotNull
        Long id,
        String name,
        String phone,

        AddressData address

) {
}
