package edu.gatech.buzzmovieselector.model;

/**
 * Created by robertwaters on 1/26/16.
 */
public class User {

    public enum UserLevel {
        USER,
        ADMIN,
        BANNED;

        @Override
        public String toString() {
            switch (this) {
                case USER: return "USER";
                case ADMIN: return "ADMIN";
                case BANNED: return "BANNED";
                default: throw new IllegalArgumentException();
            }
        }
    }

    private String name;
    private String password;
    private UserLevel userLevel;

    public User(String n, String p, UserLevel l) {
        name = n;
        password = p;
        userLevel = l;
    }

    public User(String n, String p) {
        this(n, p, UserLevel.USER);
    }

    public User(String n, String p, String l) {
        this(n, p, (l.equalsIgnoreCase("admin") ? UserLevel.ADMIN : UserLevel.USER));
    }

    /**
     * Gives the username of the user
     * @return username of the User object
     */
    public String getName() {
        return name;
    }

    /**
     * Gives the stored user password
     * @return Stored password of the user
     */
    public String getPassword() {
        return password;
    }
    /**
     * Gives the access level of the user
     * @return Stored UserLevel value of the user
     */
    public UserLevel getUserLevel() {
        return userLevel;
    }

    /**
     * Checks to see if pass matches the stored user password
     * @param pass Given password to validate
     * @return pass equals the stored password
     */
    public boolean checkPassword(String pass) {
        return password.equals(pass);
    }
}
