package edu.gatech.buzzmovieselector.dao;

import com.j256.ormlite.dao.Dao;
import edu.gatech.buzzmovieselector.entity.User;

/**
 * User DAO which has a String id (User.username)
 */
public interface UserDao extends Dao<User, String> {
}
