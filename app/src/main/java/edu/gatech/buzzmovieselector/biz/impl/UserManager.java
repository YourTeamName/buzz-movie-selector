package edu.gatech.buzzmovieselector.biz.impl;

import com.j256.ormlite.dao.CloseableIterator;

import edu.gatech.buzzmovieselector.biz.AuthenticationFacade;
import edu.gatech.buzzmovieselector.biz.UserManagementFacade;
import edu.gatech.buzzmovieselector.dao.DaoFactory;
import edu.gatech.buzzmovieselector.dao.UserDao;
import edu.gatech.buzzmovieselector.entity.User;

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
            UserDao userDao = DaoFactory.getUserDao();
            userDao.createOrUpdate(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean handleLoginRequest(String name, String pass) {
        User u = findUserById(name);
        return u != null && u.checkPassword(pass);
    }

    public boolean userExists(String username) {
        return users.containsKey(username);
    }

    public void updateUser(String id, User user) {
        // should this only work when the user exists?
        users.put(id, user);
        try {
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
