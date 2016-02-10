package edu.gatech.buzzmovieselector.model;

public interface UserManagementFacade {
    void addUser(User user);
    User findUserById(String id);
    boolean userExists(String username);
}
