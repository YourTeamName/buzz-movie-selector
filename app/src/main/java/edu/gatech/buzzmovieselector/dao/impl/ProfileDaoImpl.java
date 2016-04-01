package edu.gatech.buzzmovieselector.dao.impl;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import edu.gatech.buzzmovieselector.dao.ProfileDao;
import edu.gatech.buzzmovieselector.entity.Profile;

import java.sql.SQLException;

/**
 * OrmLite Android implementation of the ProfileDao interface.
 */
public class ProfileDaoImpl extends BaseDaoImpl<Profile, Integer>
    implements ProfileDao {

    // this constructor must be defined
    public ProfileDaoImpl(ConnectionSource connectionSource) throws
        SQLException {
        super(connectionSource, Profile.class);
    }

}
