package edu.gatech.buzzmovieselector.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import edu.gatech.buzzmovieselector.R;
import edu.gatech.buzzmovieselector.entity.User;
import edu.gatech.buzzmovieselector.service.SessionState;
import edu.gatech.buzzmovieselector.biz.UserManagementFacade;
import edu.gatech.buzzmovieselector.biz.impl.UserManager;

/**
 * WelcomeActivity is the controller for the welcome screen
 * Initially loaded activity
 */
public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        initApp();
        if (verifyLogin()) {
            Intent mainActivity = new Intent(this, BMSActivity.class);
            startActivity(mainActivity);
        } else {
            // if not logged in or invalid session state, clear everything
            SessionState.getInstance().endSession(getApplicationContext());
        }
    }

    /**
     * Checks to see if there is a stored state and restores it
     */
    private void restoreState() {
        SessionState.getInstance().restoreState(getApplicationContext());
    }

    /**
     * Method for initializing hard coded values and restoring app state
     */
    private void initApp() {
        // TODO: load user data from persistent storage so that register works
        UserManagementFacade um = new UserManager();
        um.addUser(new User("user", "pass"));
        restoreState();
    }

    /**
     * startLogin is called when the Login button is clicked
     * Creates an intent for LoginActivity and launches it
     * @param v Reference to widget firing event
     */
    public void startLogin(View v) {
        Intent loginActivity = new Intent(this, LoginActivity.class);
        startActivity(loginActivity);
    }

    /**
     * startRegister is called when the Register button is clicked
     * Creates an intent for RegisterActivity and launches it
     * @param v Reference to widget firing event
     */
    public void startRegister(View v) {
        Intent registerActivity = new Intent(this, RegisterActivity.class);
        startActivity(registerActivity);
    }

    /**
     * Checks if the user is already logged in and validates previously stored
     * session
     * @return whether the user has already been validated in a previous
     * session and user data is still valid
     */
    private boolean verifyLogin() {
        return SessionState.getInstance().isLoggedIn() && SessionState.getInstance().verifySession();
    }
}
