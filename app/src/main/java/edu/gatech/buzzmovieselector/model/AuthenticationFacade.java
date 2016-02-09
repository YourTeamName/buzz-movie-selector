package edu.gatech.buzzmovieselector.model;

/**
 * Created by robertwaters on 2/1/16.
 */
public interface AuthenticationFacade {
    boolean handleLoginRequest(String name, String password);
    boolean userExists(String username);
}
