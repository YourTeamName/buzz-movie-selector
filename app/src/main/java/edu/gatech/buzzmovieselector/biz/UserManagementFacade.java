package edu.gatech.buzzmovieselector.biz;

import edu.gatech.buzzmovieselector.entity.User;

import java.util.List;

public interface UserManagementFacade {
    void addUser(User user);

    User findUserById(String id);

    boolean userExists(String username);

    void updateUser(User user);

    /**
     * @return the user if the credentials are correct, null if the user does
     * not exit or credentials are incorrect
     */
    User getUser(String username, String password);

    /**
     * @return all users
     */
    List<User> findAll();
}
