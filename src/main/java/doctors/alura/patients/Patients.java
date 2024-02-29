package doctors.alura.patients;

import doctors.alura.address.Address;
import doctors.alura.address.AddressData;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name= "patients")
@Entity(name= "Patients")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of ="id")
public class Patients {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    private  String name;
    private String phone;
    private String email;
    @Embedded
    private Address address;
    public Boolean active;

    public Patients(PatientsData data){
        this.active =true;
        this.name = data.name();
        this.email = data.email();
        this.phone = data.phone();
        this.address = new Address(data.address());

    }

    public String getname() {
        return name;
    }
    public String getEmail(){
        return email;
    }

    public Long getId() {
        return id;
    }
    public String getPhone() {
        return phone;
    }


    public void updateInformation(PatientsDataUpdate data) {
        if(data.phone() != null){
            this.phone = data.phone();
        }
        if(data.email() != null){
            this.email = data.email();

        }
        if(data.address() != null){
          this.address.updateAddress(data.address());
        }
    }


    public void delete() {
        this.active =false;
    }
}
