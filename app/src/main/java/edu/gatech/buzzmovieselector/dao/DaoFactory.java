package edu.gatech.buzzmovieselector.dao;

import android.content.Context;
import android.util.Log;
import com.j256.ormlite.android.apptools.OpenHelperManager;

import java.sql.SQLException;

/**
 * The DAO factory that provides access to all DAOs.
 */
public final class DaoFactory {

    private static Context context;

    private static UserDao userDao;
    private static ProfileDao profileDao;
    private static MovieDao movieDao;
    private static ReviewDao reviewDao;

    public static final String ERROR_MSG = "Context must be set before " +
        "calling getXxxDao";

    public static final String DAO_ERROR = "DAO Error";
    /**
     * Empty constructor
     */
    private DaoFactory() {
    }

    /**
     * Sets the context of the factory
     *
     * @param newContext The context to set
     */
    public static void setContext(Context newContext) {
        context = newContext;
    }

    /**
     * Returns a UserDao object to interface with the database
     *
     * @return A UserDao object
     */
    public static UserDao getUserDao() {
        if (userDao == null) {
            if (context == null) {
                throw new IllegalStateException(ERROR_MSG);
            }
            try {
                userDao = (UserDao) OpenHelperManager.getHelper(context,
                    DatabaseHelper.class)
                    .getUserDao();
            } catch (SQLException e) {
                Log.e(DAO_ERROR, "Can't get User DAO", e);
            }
        }
        return userDao;
    }

    /**
     * Returns a ProfileDao object to interface with the database
     *
     * @return A ProfileDao object
     */
    public static ProfileDao getProfileDao() {
        if (profileDao == null) {
            if (context == null) {
                throw new IllegalStateException(ERROR_MSG);
            }
            try {
                profileDao = (ProfileDao) OpenHelperManager.getHelper(context,
                    DatabaseHelper.class)
                    .getProfileDao();
            } catch (SQLException e) {
                Log.e(DAO_ERROR, "Can't get Profile DAO", e);
            }
        }
        return profileDao;
    }

    /**
     * Returns a MovieDao object to interface with the database
     *
     * @return A MovieDao object
     */
    public static MovieDao getMovieDao() {
        if (movieDao == null) {
            if (context == null) {
                throw new IllegalStateException(ERROR_MSG);
            }
            try {
                movieDao = (MovieDao) OpenHelperManager.getHelper(context,
                    DatabaseHelper
                        .class)
                    .getMovieDao();
            } catch (SQLException e) {
                Log.e(DAO_ERROR, "Can't get Movie DAO", e);
            }
        }
        return movieDao;
    }

    /**
     * Returns a ReviewDao object to interface with the database
     *
     * @return A ReviewDao object
     */
    public static ReviewDao getReviewDao() {
        if (reviewDao == null) {
            if (context == null) {
                throw new IllegalStateException(ERROR_MSG);
            }
            try {
                reviewDao = (ReviewDao) OpenHelperManager.getHelper(context,
                    DatabaseHelper.class)
                    .getReviewDao();
            } catch (SQLException e) {
                Log.e(DAO_ERROR, "Can't get Review DAO", e);
            }
        }
        return reviewDao;
    }
}
