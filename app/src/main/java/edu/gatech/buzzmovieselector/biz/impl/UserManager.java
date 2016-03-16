package edu.gatech.buzzmovieselector.biz.impl;

import edu.gatech.buzzmovieselector.biz.AuthenticationFacade;
import edu.gatech.buzzmovieselector.biz.UserManagementFacade;
import edu.gatech.buzzmovieselector.dao.DaoFactory;
import edu.gatech.buzzmovieselector.dao.ProfileDao;
import edu.gatech.buzzmovieselector.dao.UserDao;
import edu.gatech.buzzmovieselector.entity.User;
import edu.gatech.buzzmovieselector.service.SessionState;

import java.util.List;

public class UserManager implements AuthenticationFacade, UserManagementFacade {

    public User findUserById(String username) {
        User user = null;
        try {
            UserDao userDao = DaoFactory.getUserDao();
            user = userDao.queryForId(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public void addUser(User user) {
        try {
            ProfileDao profileDao = DaoFactory.getProfileDao();
            profileDao.createOrUpdate(user.getProfile());
            UserDao userDao = DaoFactory.getUserDao();
            userDao.createOrUpdate(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public User getUser(String username, String password) {
        User user = null;
        try {
            UserDao userDao = DaoFactory.getUserDao();
            user = userDao.queryForId(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (user == null || !user.checkPassword(password)) {
            return null;
        }
        return user;
    }

    public User login(String username, String password) {
        return getUser(username, password);
    }

    public boolean userExists(String username) {
        User user = null;
        try {
            user = DaoFactory.getUserDao().queryForId(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user != null;
    }

    public void updateUser(User user) {
        if (SessionState.getInstance().getSessionUser() != null
                && user.getUsername().equals(
                SessionState.getInstance().getSessionUser().getUsername())) {
            SessionState.getInstance().setSessionUser(user);
        }
        try {
            ProfileDao profileDao = DaoFactory.getProfileDao();
            profileDao.createOrUpdate(user.getProfile());
            UserDao userDao = DaoFactory.getUserDao();
            userDao.createOrUpdate(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> findAll() {
        List<User> users = null;
        try {
            UserDao userDao = DaoFactory.getUserDao();
            users = userDao.queryForAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    public UserManager() {
    }
}
