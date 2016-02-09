package edu.gatech.buzzmovieselector.model;

public interface AuthenticationFacade {
    boolean handleLoginRequest(String name, String password);
    boolean userExists(String username);
}
