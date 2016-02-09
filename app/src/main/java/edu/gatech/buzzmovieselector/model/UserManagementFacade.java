package edu.gatech.buzzmovieselector.model;

public interface UserManagementFacade {
    void addUser(String name, String pass);
    User findUserById(String id);
}
