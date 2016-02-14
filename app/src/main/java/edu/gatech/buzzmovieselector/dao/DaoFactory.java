package edu.gatech.buzzmovieselector.dao;

import android.content.Context;
import com.j256.ormlite.android.apptools.OpenHelperManager;

/**
 * The DAO factory that provides access to all DAOs.
 */
public class DaoFactory {

    private static Context context;

    private static UserDao userDao;
    private static ProfileDao profileDao;

    public static void setContext(Context newContext) {
        context = newContext;
    }

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
}
