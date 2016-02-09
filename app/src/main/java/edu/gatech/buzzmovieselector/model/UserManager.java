package edu.gatech.buzzmovieselector.model;

import java.util.HashMap;
import java.util.Map;

public class UserManager implements AuthenticationFacade, UserManagementFacade {
    private static Map<String, User> users = new HashMap<>();


    public User findUserById(String id) {
        return users.get(id);
    }

    public void addUser(User user) {
        users.put(user.getUsername(), user);
    }

    public boolean handleLoginRequest(String name, String pass) {
        User u = findUserById(name);
        return u != null && u.checkPassword(pass);
    }

    public UserManager() {

    }
}
