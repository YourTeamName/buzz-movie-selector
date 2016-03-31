package edu.gatech.buzzmovieselector.dao;

import android.content.Context;
import com.j256.ormlite.android.apptools.OpenHelperManager;

/**
 * The DAO factory that provides access to all DAOs.
 */
public final class DaoFactory {

    private static Context context;

    private static UserDao userDao;
    private static ProfileDao profileDao;
    private static MovieDao movieDao;
    private static ReviewDao reviewDao;

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
     * @throws Exception an exception from the database
     */
    public static UserDao getUserDao() throws Exception {
        if (userDao == null) {
            if (context == null) {
                throw new Exception("Context must be set before calling " +
                        "getXxxDao");
            }
            userDao = (UserDao) OpenHelperManager.getHelper(context,
                    DatabaseHelper.class).getUserDao();
        }
        return userDao;
    }

    /**
     * Returns a ProfileDao object to interface with the database
     *
     * @return A ProfileDao object
     * @throws Exception an exception from the database
     */
    public static ProfileDao getProfileDao() throws Exception {
        if (profileDao == null) {
            if (context == null) {
                throw new Exception("Context must be set before calling " +
                        "getXxxDao");
            }
            profileDao = (ProfileDao) OpenHelperManager.getHelper(context,
                    DatabaseHelper.class).getProfileDao();
        }
        return profileDao;
    }

    /**
     * Returns a MovieDao object to interface with the database
     *
     * @return A MovieDao object
     * @throws Exception an exception from the database
     */
    public static MovieDao getMovieDao() throws Exception {
        if (movieDao == null) {
            if (context == null) {
                throw new Exception("Context must be set before calling " +
                        "getXxxDao");
            }
            movieDao = (MovieDao) OpenHelperManager.getHelper(context,
                    DatabaseHelper.class).getMovieDao();
        }
        return movieDao;
    }

    /**
     * Returns a ReviewDao object to interface with the database
     *
     * @return A ReviewDao object
     * @throws Exception an exception from the database
     */
    public static ReviewDao getReviewDao() throws Exception {
        if (reviewDao == null) {
            if (context == null) {
                throw new Exception("Context must be set before calling " +
                        "getXxxDao");
            }
            reviewDao = (ReviewDao) OpenHelperManager.getHelper(context,
                    DatabaseHelper.class).getReviewDao();
        }
        return reviewDao;
    }
}
