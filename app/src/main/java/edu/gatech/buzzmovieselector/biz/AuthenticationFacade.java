package edu.gatech.buzzmovieselector.biz;

import edu.gatech.buzzmovieselector.entity.User;

public interface AuthenticationFacade {

    /**
     * @return the user if the credentials are correct, null if the user does
     * not exit or credentials are incorrect
     */
    User login(String username, String password);
}
