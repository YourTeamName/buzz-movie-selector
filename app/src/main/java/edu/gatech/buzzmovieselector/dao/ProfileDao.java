package edu.gatech.buzzmovieselector.dao;

import com.j256.ormlite.dao.Dao;
import edu.gatech.buzzmovieselector.entity.Profile;

/**
 * Profile DAO which has an Integer id (profile.id)
 */
public interface ProfileDao extends Dao<Profile, Integer> {
}
