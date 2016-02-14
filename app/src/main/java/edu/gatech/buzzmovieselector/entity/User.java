package edu.gatech.buzzmovieselector.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import edu.gatech.buzzmovieselector.dao.impl.UserDaoImpl;

@DatabaseTable(tableName = "users", daoClass = UserDaoImpl.class)
public class User {

    public enum UserLevel {
        USER,
        ADMIN,
        BANNED;

        @Override
        public String toString() {
            switch (this) {
                case USER:
                    return "USER";
                case ADMIN:
                    return "ADMIN";
                case BANNED:
                    return "BANNED";
                default:
                    throw new IllegalArgumentException("Invalid UserLevel " +
                            "enum value");
            }
        }
    }

    @DatabaseField(id = true)
    private String username;
    @DatabaseField
    private String password;
    @DatabaseField
    private UserLevel userLevel;
    @DatabaseField(foreign = true, foreignAutoCreate = true,
            foreignAutoRefresh = true)
    private Profile profile;

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

    public UserLevel getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(UserLevel userLevel) {
        this.userLevel = userLevel;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public User() {
    }

    public User(String username, String password, UserLevel userLevel) {
        this.username = username;
        this.password = password;
        this.userLevel = userLevel;
    }

    public User(String username, String password) {
        this(username, password, UserLevel.USER);
    }

    public User(String username, String password, String userLevel) {
        this.username = username;
        this.password = password;
        UserLevel ul;
        if (userLevel.equalsIgnoreCase("admin")) {
            ul = UserLevel.ADMIN;
        } else if (userLevel.equalsIgnoreCase("user")) {
            ul = UserLevel.USER;
        } else if (userLevel.equalsIgnoreCase("banned")) {
            ul = UserLevel.BANNED;
        } else {
            throw new IllegalArgumentException("String cannot be converted to" +
                    " UserLevel");
        }
        this.userLevel = ul;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (!User.class.isAssignableFrom(o.getClass())) {
            return false;
        }
        final User u = (User) o;
        if (!username.equals(u.username)) {
            return false;
        } else if (!password.equals(u.password)) {
            return false;
        } else if (!userLevel.equals(u.userLevel)) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Checks to see if pass matches the stored user password
     *
     * @param pass Given password to validate
     * @return pass equals the stored password
     */
    public boolean checkPassword(String pass) {
        return password.equals(pass);
    }
}
