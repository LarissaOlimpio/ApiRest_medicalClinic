package doctors.alura.domain.users;

public record UserDataList(Long id, String name,String email, TypeUser typeUser) {

    public UserDataList(Users users) {
        this(users.getId(), users.getName(), users.getEmail(),users.getTypeUser());
    }
}
