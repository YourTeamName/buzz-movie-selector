package edu.gatech.cs2340.basicandroidapp.model;

/**
 * Created by robertwaters on 2/1/16.
 */
public interface UserManagementFacade {
    void addUser(String name, String pass);
    User findUserById(String id);
}
