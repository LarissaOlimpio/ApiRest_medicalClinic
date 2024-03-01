package doctors.alura.domain.address;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class Address {
    private String street;
    private String neighborhood;
    private String zip_code;
    private String complement;
    private String city;
    private String uf;

    public Address(AddressData data) {
        this.city = data.city();
        this.uf = data.uf();
        this.complement = data.complement();
        this.neighborhood = data.neighborhood();
        this.zip_code = data.zip_code();
        this.street = data.street();

    }

    public void updateAddress(AddressData data) {
        if(data.street() != null){
            this.street = data.street();
        }
        if(data.neighborhood() != null){
            this.neighborhood = data.neighborhood();
        }
        if(data.zip_code() != null){
            this.zip_code = data.zip_code();
        }
        if(data.complement() != null){
            this.complement = data.complement();
        }
        if(data.city()!= null){
            this.city = data.city();
        }
        if(data.uf() != null){
            this.uf = data.uf();
        }

    }
}
