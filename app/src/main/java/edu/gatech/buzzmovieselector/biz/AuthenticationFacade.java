package edu.gatech.buzzmovieselector.biz;

/**
 * Interface for implementing an authenticator for the login activity
 */
public interface AuthenticationFacade {
    /**
     * @return true if the credentials are correct, false if the user does
     * not exit or credentials are incorrect
     */
    boolean login(String username, String password);
}
