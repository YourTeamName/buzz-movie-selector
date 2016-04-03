package edu.gatech.buzzmovieselector.biz;

import edu.gatech.buzzmovieselector.entity.User;

import java.util.List;

public interface UserManagementFacade {
    /**
     * Adds a user to the user manager
     * @param user the user to add
     */
    void addUser(User user);

    /**
     * Searches for and returns a user object corresponding to a passed-in id
     *
     * @param id The username for which to search
     * @return The user object from the map
     */
    User findUserById(String id);

    /**
     * Finds if a user exists in the map
     *
     * @param username The id to search for in the map
     * @return True if the user exists
     */
    boolean userExists(String username);

    /**
     * Updates a user in the map of users
     *
     * @param user The updated user object corresponding to the username
     */
    void updateUser(User user);

    /**
     * Gets a user from the manager
     * @param username the name of the user
     * @param password the password of the user
     * @return the user if the credentials are correct, null if the user does
     * not exit or credentials are incorrect
     */
    User getUser(String username, String password);

    /**
     * @return all users
     */
    List<User> findAll();
}
