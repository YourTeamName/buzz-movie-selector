package edu.gatech.cs2340.basicandroidapp.model;

/**
 * Created by robertwaters on 1/26/16.
 */
public class User {
    String name;
    String password;

    public User(String n, String p) {
        name = n;
        password = p;
    }

    public boolean checkPassword(String pass) {
        return password.equals(pass);
    }
}
