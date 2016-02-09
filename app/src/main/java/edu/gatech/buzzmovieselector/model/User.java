package edu.gatech.buzzmovieselector.model;

public class User {
    private String username;
    private String password;
    private boolean admin;
    private boolean banned;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() { return admin; }

    public boolean isBanned() {
        return banned;
    }

    public void setBanned(boolean banned) {
        this.banned = banned;
    }

    public User(String n, String p, boolean a) {
        this.username = n;
        this.password = p;
        this.admin = a;
    }

    public User(String n, String p) {
        this(n, p, false);
    }

    public boolean checkPassword(String pass) {
        return password.equals(pass);
    }
}
