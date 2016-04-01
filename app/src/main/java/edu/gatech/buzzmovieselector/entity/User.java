package edu.gatech.buzzmovieselector.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import edu.gatech.buzzmovieselector.dao.impl.UserDaoImpl;

import java.io.Serializable;

@DatabaseTable(tableName = "user", daoClass = UserDaoImpl.class)
public class User implements Serializable {

    @DatabaseField(id = true)
    private String username;
    @DatabaseField
    private String password;
    @DatabaseField
    private UserStatus userStatus;
    @DatabaseField(foreign = true, foreignAutoCreate = true,
        foreignAutoRefresh = true)
    private Profile profile;

    /**
     * Default constructor for OrmLite
     */
    public User() {
    }

    /**
     * Creates a user with the given information
     *
     * @param username   The name of the user
     * @param password   The password needed to log in
     * @param userStatus The level of the account
     */
    public User(String username, String password, UserStatus userStatus) {
        this.setUsername(username);
        this.setPassword(password);
        this.setUserStatus(userStatus);
    }

    /**
     * Chained constructor without a user level
     *
     * @param username The username of the user
     * @param password The password needed to log in
     */
    public User(String username, String password) {
        this(username, password, UserStatus.USER);
    }

    /**
     * Creates a user with the given information
     *
     * @param username   The username of the user
     * @param password   The password to log in
     * @param userStatus The string representation of the user's level
     */
    public User(String username, String password, String userStatus) {
        this.username = username;
        this.password = password;
        if ("admin".equalsIgnoreCase(userStatus)) {
            this.userStatus = UserStatus.ADMIN;
        } else if ("user".equalsIgnoreCase(userStatus)) {
            this.userStatus = UserStatus.USER;
        } else if ("banned".equalsIgnoreCase(userStatus)) {
            this.userStatus = UserStatus.BANNED;
        } else if ("locked".equalsIgnoreCase(userStatus)) {
            this.userStatus = UserStatus.LOCKED;
        } else {
            throw new IllegalArgumentException("String cannot be converted to" +
                " UserStatus");
        }
    }

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

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    /**
     * Checks to see if two users are the same
     *
     * @param user The other user
     * @return True if the users have the same username, password, and level
     */
    @Override
    public boolean equals(Object user) {
        if (user == null) {
            return false;
        }
        if (!User.class.isAssignableFrom(user.getClass())) {
            return false;
        }
        final User u = (User) user;
        return username.equals(u.username) && password.equals(u.password) &&
            userStatus.equals(u.userStatus);
    }

    /**
     * Checks to see if pass matches the stored user password
     *
     * @param password Given password to validate
     * @return pass equals the stored password
     */
    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    @Override
    public String toString() {
        return "User: " + username + " UserStatus: " + userStatus;
    }

    /**
     * enum containing possible UserStatus values
     */
    public enum UserStatus {
        USER,
        ADMIN,
        BANNED,
        LOCKED;

        @Override
        public String toString() {
            switch (this) {
                case USER:
                    return "USER";
                case ADMIN:
                    return "ADMIN";
                case BANNED:
                    return "BANNED";
                case LOCKED:
                    return "LOCKED";
                default:
                    throw new IllegalArgumentException("Invalid UserStatus " +
                        "enum value");
            }
        }
    }
}
