package edu.gatech.buzzmovieselector.biz.impl;

import edu.gatech.buzzmovieselector.biz.AuthenticationFacade;
import edu.gatech.buzzmovieselector.biz.UserManagementFacade;
import edu.gatech.buzzmovieselector.dao.DaoFactory;
import edu.gatech.buzzmovieselector.dao.ProfileDao;
import edu.gatech.buzzmovieselector.dao.UserDao;
import edu.gatech.buzzmovieselector.entity.User;
import edu.gatech.buzzmovieselector.service.SessionState;

import java.sql.SQLException;
import java.util.List;

public class UserManager implements AuthenticationFacade, UserManagementFacade {

    /**
     * No arg constructor
     */
    public UserManager() {
    }

    @Override
    public User findUserById(String username) {
        User user = null;
        try {
            final UserDao userDao = DaoFactory.getUserDao();
            user = userDao.queryForId(username);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void addUser(User user) {
        try {
            final ProfileDao profileDao = DaoFactory.getProfileDao();
            profileDao.createOrUpdate(user.getProfile());
            final UserDao userDao = DaoFactory.getUserDao();
            userDao.createOrUpdate(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User getUser(String username, String password) {
        User user = null;
        try {
            final UserDao userDao = DaoFactory.getUserDao();
            user = userDao.queryForId(username);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (user == null || !user.checkPassword(password)) {
            return null;
        }
        return user;
    }

    /**
     * Logs in a user
     * @param username The username of the user
     * @param password The user's password
     * @return the user object for the user
     */
    public User login(String username, String password) {
        return getUser(username, password);
    }

    @Override
    public boolean userExists(String username) {
        User user = null;
        try {
            user = DaoFactory.getUserDao().queryForId(username);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user != null;
    }

    @Override
    public void updateUser(User user) {
        if (SessionState.getInstance().getSessionUser() != null && user
            .getUsername().equals(SessionState.getInstance().getSessionUser()
                .getUsername())) {
            SessionState.getInstance().setSessionUser(user);
        }
        addUser(user);
    }

    @Override
    public List<User> findAll() {
        List<User> users = null;
        try {
            final UserDao userDao = DaoFactory.getUserDao();
            users = userDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
}
