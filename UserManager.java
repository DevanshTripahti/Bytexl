import java.util.ArrayList;
import java.util.List;

public class UserManager {
    private List<User> users = new ArrayList<>();

    public void addUser(User user) {
        users.add(user);
    }

    public void deleteUser(String userId) {
        users.removeIf(user -> user.getId().equals(userId));
    }

    public User getUser(String userId) {
        return users.stream().filter(user -> user.getId().equals(userId)).findFirst().orElse(null);
    }

    public List<User> getUsers() {
        return users;
    }
}
