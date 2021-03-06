package edu.gatech.buzzmovieselector.dao.impl;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import edu.gatech.buzzmovieselector.dao.UserDao;
import edu.gatech.buzzmovieselector.entity.User;

import java.sql.SQLException;

/**
 * OrmLite Android implementation of the UserDao interface.
 */
public class UserDaoImpl extends BaseDaoImpl<User, String>
    implements UserDao {

    /**
     * Necessary constructor to define
     * @param connectionSource connection source
     * @throws SQLException a database exception
     */
    public UserDaoImpl(ConnectionSource connectionSource)
        throws SQLException {
        super(connectionSource, User.class);
    }

}
