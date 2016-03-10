package edu.gatech.buzzmovieselector.biz;

public interface AuthenticationFacade {
    /**
     * @return true if the credentials are correct, false if the user does
     * not exit or credentials are incorrect
     */
    boolean login(String username, String password);
}
