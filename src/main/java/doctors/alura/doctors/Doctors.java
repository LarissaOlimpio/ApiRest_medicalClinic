package doctors.alura.doctors;

import doctors.alura.address.Address;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "doctors")
@Entity(name = "Doctors")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Doctors {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String crm;
    private String phone;
    @Enumerated(EnumType.STRING)
    private Specialty specialty;
    @Embedded
    private Address address;
    private Boolean active;

    public Doctors(DoctorData data){
        this.active = true;
        this.name = data.name();
        this.address = new Address(data.address());
        this.crm = data.crm();
        this.email = data.email();
        this.specialty = data.specialty();
        this.phone = data.phone();

    }


    public String getName() {
        return  name;
    }

    public String getCrm() {
        return crm;
    }
    public String getEmail(){
        return email;
    }
    public Specialty getSpeciality(){
        return specialty;
    }
    public Long getId(){
        return  id;
    }

    public void updateInformation(DataDoctorsUpdate data) {
        if(data.name() != null){
            this.name = data.name();
        }
        if(data.phone() != null){
            this.phone = data.phone();

        }
        if(data.address() != null){
            this.address.updateAddress(data.address());
        }

    }

    public void delete() {
        this.active = false;
    }
}
