package edu.gatech.buzzmovieselector;

import android.content.Context;
import android.content.SharedPreferences;

import edu.gatech.buzzmovieselector.model.User;

/**
 * Created by jwpilly on 2/9/16.
 */
public class SessionState {

    // Eagerly instantiated Singleton
    private static SessionState ourInstance = new SessionState();
    private static User sessionUser = null;

    public static final String SESSION_PREFS = "BMS_SESSION_PREFS";

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
        // TODO: write a loading function for User class
        String prefix = "sessionUser_";
        String userName = saveSession.getString(prefix + "name", null);
        String userPass = saveSession.getString(prefix + "password", null);
        String userLevel = saveSession.getString(prefix + "level", null);
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
        // TODO: write a pickling function for User class
        if (sessionUser != null) {
            String prefix = "sessionUser_";
            editor.putString(prefix + "name", sessionUser.getName());
            editor.putString(prefix + "password", sessionUser.getPassword());
            editor.putString(prefix + "level", sessionUser.getUserLevel().toString());
        }
        editor.clear();
        editor.commit();
    }

    /**
     * Clears the stored session state in shared preferences
     * @param context Context of shared preferences
     */
    public static void clearSaveState(Context context) {
        SharedPreferences saveSession = context.getSharedPreferences(SESSION_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = saveSession.edit();
        editor.clear();
        editor.commit();
    }

    public static void login(User user, Context context) {
        sessionUser = user;
        saveState(context);
    }

    /**
     * Logs the user out of current Session state and clears existing save state
     * @param context
     */
    public static void logout(Context context) {
        sessionUser = null;
        clearSaveState(context);
    }
    /**
     * private constructor for Singleton design pattern
     */
    private SessionState() {
    }
}
