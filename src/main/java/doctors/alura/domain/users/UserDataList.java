package doctors.alura.domain.users;

public record UserDataList(Long id, String name, String userType) {

    public UserDataList(Users users) {
        this(users.getId(), users.getName(), users.getUserType());
    }
}
