package doctors.alura.doctors;

import doctors.alura.address.AddressData;
import jakarta.validation.constraints.NotNull;

public record DataDoctorsUpdate(
        @NotNull
        Long id,
        String name,
        String phone,

        AddressData address

) {
}
