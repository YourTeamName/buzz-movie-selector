package edu.gatech.buzzmovieselector.biz.impl;

import edu.gatech.buzzmovieselector.biz.AuthenticationFacade;
import edu.gatech.buzzmovieselector.biz.UserManagementFacade;
import edu.gatech.buzzmovieselector.entity.User;

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

    public boolean userExists(String username) {
        return users.containsKey(username);
    }

    public UserManager() {

    }
}
