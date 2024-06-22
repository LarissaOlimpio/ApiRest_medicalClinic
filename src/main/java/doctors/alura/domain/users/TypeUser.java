package doctors.alura.domain.users;

public enum TypeUser {
    ADMIN("admin"),
    DOCTOR("doctor"),
    PATIENT("patient");

    private String typeUser;

    TypeUser(String typeUser){
        this.typeUser = typeUser;
    }

}
