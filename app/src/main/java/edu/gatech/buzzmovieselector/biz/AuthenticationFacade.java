package edu.gatech.buzzmovieselector.biz;

import edu.gatech.buzzmovieselector.entity.User;

/**
 * Interface for implementing an authenticator for the login activity
 */
public interface AuthenticationFacade {

    /**
     * Logs in the user
     * @param username The username of the user
     * @param password The user's password
     * @return the user that has that combination
     */
    User login(String username, String password);
}
