package edu.gatech.buzzmovieselector.service;

import android.content.Context;
import android.content.SharedPreferences;
import edu.gatech.buzzmovieselector.biz.UserManagementFacade;
import edu.gatech.buzzmovieselector.biz.impl.UserManager;
import edu.gatech.buzzmovieselector.entity.User;

/**
 * Global state that maintains the user logged into the application
 */
public final class SessionState {

    private static final String SESSION_PREFS = "BMS_SESSION_PREFS";
    private static final String USER_PREFIX = "sessionUser_";
    // Lazily instantiated Singleton
    private static SessionState ourInstance = null;
    private static User sessionUser = null;

    /**
     * private constructor for Singleton design pattern
     */
    private SessionState() {
    }

    /**
     * Global Session State for the application
     *
     * @return instance of global SessionState Singleton
     */
    public static SessionState getInstance() {
        if (ourInstance == null) {
            ourInstance = new SessionState();
        }
        return ourInstance;
    }

    /**
     * User currently logged into the session
     *
     * @return stored sessionUser variable
     */
    public User getSessionUser() {
        return sessionUser;
    }

    /**
     * Sets a user to be currently logged into the session
     *
     * @param u sets the sessionUser variable of the SessionState
     */
    public void setSessionUser(User u) {
        sessionUser = u;
    }

    /**
     * Checks to see if a session user has been set
     *
     * @return the session user is not equal to null
     */
    public boolean isLoggedIn() {
        return sessionUser != null;
    }

    /**
     * Checks to see if existing state was stored and tries to restore it
     *
     * @param context Context of shared preferences
     * @return a saved state exists
     */
    public boolean restoreState(Context context) {
        SharedPreferences saveSession = context
            .getSharedPreferences(SESSION_PREFS, Context.MODE_PRIVATE);
        String username = saveSession.getString(USER_PREFIX + "username", null);
        String password = saveSession.getString(USER_PREFIX + "password", null);
        String userStatus = saveSession.getString(USER_PREFIX + "status", null);
        if (username == null || password == null || userStatus == null) {
            return false;
        }
        UserManagementFacade um = new UserManager();
        sessionUser = um.getUser(username, password);
        return true;
    }

    /**
     * Saves the current session state into shared preferences
     *
     * @param context Context of shared preferences
     */

    public void saveState(Context context) {
        SharedPreferences saveSession = context
            .getSharedPreferences(SESSION_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = saveSession.edit();
        if (sessionUser != null) {
            editor.putString(USER_PREFIX + "username", sessionUser
                .getUsername());
            editor.putString(USER_PREFIX + "password", sessionUser
                .getPassword());
            editor.putString(USER_PREFIX + "status", sessionUser.getUserStatus
                ().toString());
        }
        editor.clear();
        editor.apply();
    }

    /**
     * Clears the stored session state in shared preferences
     *
     * @param context Context of shared preferences
     */
    public void clearSaveState(Context context) {
        SharedPreferences saveSession = context
            .getSharedPreferences(SESSION_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = saveSession.edit();
        editor.clear();
        editor.apply();
    }

    /**
     * Creates a session for the user
     *
     * @param user    User to create session for
     * @param context Application context
     */
    public void startSession(User user, Context context) {
        sessionUser = user;
        saveState(context);
    }

    /**
     * Ends the current user session
     *
     * @param context Application context
     */
    public void endSession(Context context) {
        sessionUser = null;
        clearSaveState(context);
    }
}
