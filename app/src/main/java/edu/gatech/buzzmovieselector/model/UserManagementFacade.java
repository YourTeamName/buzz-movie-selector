package edu.gatech.buzzmovieselector.model;

/**
 * Created by robertwaters on 2/1/16.
 */
public interface UserManagementFacade {
    void addUser(String name, String pass);
    User findUserById(String id);
}
