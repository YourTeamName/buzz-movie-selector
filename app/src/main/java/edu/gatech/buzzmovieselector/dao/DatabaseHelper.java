package edu.gatech.buzzmovieselector.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import edu.gatech.buzzmovieselector.entity.Movie;
import edu.gatech.buzzmovieselector.entity.Profile;
import edu.gatech.buzzmovieselector.entity.Review;
import edu.gatech.buzzmovieselector.entity.User;

import java.sql.SQLException;

/**
 * Database helper class used to manage the creation and upgrading of your
 * database. This class also usually provides
 * the DAOs used by the other classes.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    // name of the database file for your application -- change to something
    // appropriate for your app
    private static final String DATABASE_NAME = "bms.db";
    // any time you make changes to your database objects, you may have to
    // increase the database version
    private static final int DATABASE_VERSION = 4;

    // the DAO objects
    private Dao<User, String> userDao;
    private Dao<Profile, Integer> profileDao;
    private Dao<Movie, Integer> movieDao;
    private Dao<Review, Integer> reviewDao;


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * This is called when the database is first created. Usually you should
     * call createTable statements here to create
     * the tables that will store your data.
     */
    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
        try {
            Log.i(DatabaseHelper.class.getName(), "onCreate");
            TableUtils.createTable(connectionSource, User.class);
            TableUtils.createTable(connectionSource, Profile.class);
            TableUtils.createTable(connectionSource, Movie.class);
            TableUtils.createTable(connectionSource, Review.class);
        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Can't create database", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * This is called when your application is upgraded and it has a higher
     * version number. This allows you to adjust
     * the various data to match the new version number.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource
            connectionSource, int oldVersion, int newVersion) {
        try {
            Log.i(DatabaseHelper.class.getName(), "onUpgrade");
            TableUtils.dropTable(connectionSource, User.class, true);
            TableUtils.dropTable(connectionSource, Profile.class, true);
            TableUtils.dropTable(connectionSource, Movie.class, true);
            TableUtils.dropTable(connectionSource, Review.class, true);
            // after we drop the old databases, we create the new ones
            onCreate(db, connectionSource);
        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Can't drop databases", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Returns the DAO for our User class. It
     * will create it or just give the cached
     * value.
     */
    public Dao<User, String> getUserDao() throws SQLException {
        if (userDao == null) {
            userDao = getDao(User.class);
        }
        return userDao;
    }

    /**
     * Returns the DAO for our Profile class. It
     * will create it or just give the cached
     * value.
     */
    public Dao<Profile, Integer> getProfileDao() throws SQLException {
        if (profileDao == null) {
            profileDao = getDao(Profile.class);
        }
        return profileDao;
    }

    /**
     * Returns the DAO for our Movie class. It
     * will create it or just give the cached
     * value.
     */
    public Dao<Movie, Integer> getMovieDao() throws SQLException {
        if (movieDao == null) {
            movieDao = getDao(Movie.class);
        }
        return movieDao;
    }

    /**
     * Returns the DAO for our Movie class. It
     * will create it or just give the cached
     * value.
     */
    public Dao<Review, Integer> getReviewDao() throws SQLException {
        if (reviewDao == null) {
            reviewDao = getDao(Review.class);
        }
        return reviewDao;
    }

    /**
     * Close the database connections and clear any cached DAOs.
     */
    @Override
    public void close() {
        super.close();
        userDao = null;
        profileDao = null;
    }
}