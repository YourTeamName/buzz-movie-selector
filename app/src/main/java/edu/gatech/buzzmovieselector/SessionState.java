package edu.gatech.buzzmovieselector;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import edu.gatech.buzzmovieselector.model.User;
import edu.gatech.buzzmovieselector.model.UserManagementFacade;
import edu.gatech.buzzmovieselector.model.UserManager;

/**
 * Created by jwpilly on 2/9/16.
 */
public class SessionState {

    // Eagerly instantiated Singleton
    private static SessionState ourInstance = new SessionState();
    private static User sessionUser = null;

    public static final String SESSION_PREFS = "BMS_SESSION_PREFS";
    public static final String USER_PREFIX = "sessionUser_";

    /**
     * Global Session State for the application
     * @return instance of global SessionState Singelton
     */
    public static SessionState getInstance() {
        return ourInstance;
    }

    /**
     * Sets a user to be currently logged into the session
     * @param u sets the sessionUser variable of the SessionState
     */
    public static void setSessionUser(User u) {
        sessionUser = u;
    }

    /**
     * User currently logged into the session
     * @return stored sessionUser variable
     */
    public static User getSessionUser() {
        return sessionUser;
    }

    /**
     * Checks to see if a session user has been set
     * @return the session user is not equal to null
     */
    public static boolean isLoggedIn() {
        return sessionUser != null;
    }

    /**
     * Checks to see if existing state was stored and tries to restore it
     * @param context Context of shared preferences
     * @return a saved state exists
     */
    public static boolean restoreState(Context context) {
        SharedPreferences saveSession = context.getSharedPreferences(SESSION_PREFS, Context.MODE_PRIVATE);
        String userName = saveSession.getString(USER_PREFIX +  "name", null);
        String userPass = saveSession.getString(USER_PREFIX +  "password", null);
        String userLevel = saveSession.getString(USER_PREFIX +  "level", null);
        if (userName == null || userPass == null || userLevel == null) {
            return false;
        }
        sessionUser = new User(userName, userPass, userLevel);
        return true;
    }

    /**
     * Saves the current session state into shared preferences
     * @param context Context of shared preferences
     */
    public static void saveState(Context context) {
        SharedPreferences saveSession = context.getSharedPreferences(SESSION_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = saveSession.edit();
        if (sessionUser != null) {
            editor.putString(USER_PREFIX +  "name", sessionUser.getName());
            editor.putString(USER_PREFIX +  "password", sessionUser.getPassword());
            editor.putString(USER_PREFIX +  "level", sessionUser.getUserLevel().toString());
        }
        editor.clear();
        editor.apply();
    }

    /**
     * Clears the stored session state in shared preferences
     * @param context Context of shared preferences
     */
    public static void clearSaveState(Context context) {
        SharedPreferences saveSession = context.getSharedPreferences(SESSION_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = saveSession.edit();
        editor.clear();
        editor.apply();
    }

    public static void login(User user, Context context) {
        sessionUser = user;
        saveState(context);
    }

    /**
     * Logs the user out of current Session state and clears existing save state
     * @param context Context of shared preferences
     */
    public static void logout(Context context) {
        sessionUser = null;
        clearSaveState(context);
    }

    public static boolean verifySession() {
        UserManagementFacade uf = new UserManager();
        String userName = sessionUser.getName();
        if (userName == null || !uf.userExists(userName)) {
            return false;
        }
        User realUser = uf.findUserById(userName);
        return realUser.equals(sessionUser);
    }

    /**
     * private constructor for Singleton design pattern
     */
    private SessionState() {
    }
}
