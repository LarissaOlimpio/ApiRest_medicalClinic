package doctors.alura.doctors;

public record DoctorsDataList(Long id,String name, String crm, String email, Specialty specialty) {

    public  DoctorsDataList(Doctors doctors){
        this(doctors.getId(),doctors.getName(),doctors.getCrm(), doctors.getEmail(),doctors.getSpeciality());
    }

}
