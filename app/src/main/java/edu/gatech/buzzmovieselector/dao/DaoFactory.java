package edu.gatech.buzzmovieselector.dao;

import android.content.Context;
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

    private static final String ERROR_MSG = "Context must be set before " +
        "calling getXxxDao";

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
     * @throws IllegalStateException if context is null
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
                e.printStackTrace();
            }
        }
        return userDao;
    }

    /**
     * Returns a ProfileDao object to interface with the database
     *
     * @return A ProfileDao object
     * @throws IllegalStateException if context is null
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
                e.printStackTrace();
            }
        }
        return profileDao;
    }

    /**
     * Returns a MovieDao object to interface with the database
     *
     * @return A MovieDao object
     * @throws IllegalStateException if context is null
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
                e.printStackTrace();
            }
        }
        return movieDao;
    }

    /**
     * Returns a ReviewDao object to interface with the database
     *
     * @return A ReviewDao object
     * @throws IllegalStateException if context is null
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
                e.printStackTrace();
            }
        }
        return reviewDao;
    }
}
