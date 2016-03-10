package edu.gatech.buzzmovieselector.biz.impl;

import com.j256.ormlite.dao.CloseableIterator;
import edu.gatech.buzzmovieselector.biz.AuthenticationFacade;
import edu.gatech.buzzmovieselector.biz.UserManagementFacade;
import edu.gatech.buzzmovieselector.dao.DaoFactory;
import edu.gatech.buzzmovieselector.dao.ProfileDao;
import edu.gatech.buzzmovieselector.dao.UserDao;
import edu.gatech.buzzmovieselector.entity.User;
import edu.gatech.buzzmovieselector.service.SessionState;

import java.util.HashMap;
import java.util.Map;

public class UserManager implements AuthenticationFacade, UserManagementFacade {
    private static Map<String, User> users = null;

    public User findUserById(String id) {
        return users.get(id);
    }

    public void addUser(User user) {
        users.put(user.getUsername(), user);
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
        if (user == null || user.checkPassword(password)) {
            return null;
        }
        return user;
    }

    public boolean login(String username, String password) {
        return getUser(username, password) == null;
    }

    public boolean userExists(String username) {
        return users.containsKey(username);
    }

    public void updateUser(String id, User user) {
        // should this only work when the user exists?
        users.put(id, user);
        SessionState.getInstance().setSessionUser(user);
        try {
            ProfileDao profileDao = DaoFactory.getProfileDao();
            profileDao.createOrUpdate(user.getProfile());
            UserDao userDao = DaoFactory.getUserDao();
            userDao.createOrUpdate(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadUsers() {
        users = new HashMap<>();
        try {
            UserDao userDao = DaoFactory.getUserDao();
            CloseableIterator<User> iterator = userDao.closeableIterator();
            try {
                while (iterator.hasNext()) {
                    User u = iterator.next();
                    users.put(u.getUsername(), u);
                }
            } finally {
                iterator.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public UserManager() {
        if (users == null) {
            loadUsers();
        }
    }
}
