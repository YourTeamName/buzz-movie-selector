package edu.gatech.buzzmovieselector.biz;

public interface AuthenticationFacade {
    boolean handleLoginRequest(String name, String password);
}
