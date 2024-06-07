package dao;

import model.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UserDAO {
    private Map<String, User> users = new HashMap<>();

    public void addUser(User user) {
        users.put(user.getUsername(), user);
    }

    public Optional<User> getUserByUsername(String username) {
        return Optional.ofNullable(users.get(username));
    }
}
