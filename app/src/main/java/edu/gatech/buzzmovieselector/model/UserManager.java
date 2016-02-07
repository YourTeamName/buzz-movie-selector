package edu.gatech.buzzmovieselector.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by robertwaters on 1/26/16.
 */
public class UserManager implements AuthenticationFacade, UserManagementFacade {
    private static Map<String, User> users = new HashMap<>();


    public User findUserById(String id) {
        return users.get(id);
    }

    public void addUser(String name, String pass) {
        User user = new User(name, pass);
        users.put(name, user);

    }

    public boolean handleLoginRequest(String name, String pass) {
        User u = findUserById(name);
        if (u == null) return false;
        return u.checkPassword(pass);
    }

    public UserManager() {

    }
}
