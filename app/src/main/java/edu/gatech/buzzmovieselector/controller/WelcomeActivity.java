package edu.gatech.buzzmovieselector.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import edu.gatech.buzzmovieselector.R;
import edu.gatech.buzzmovieselector.SessionState;
import edu.gatech.buzzmovieselector.model.UserManagementFacade;
import edu.gatech.buzzmovieselector.model.UserManager;

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
        if (checkLogin()) {
            Intent mainActivity = new Intent(this, BMSActivity.class);
            startActivity(mainActivity);
        }
    }

    /**
     * Checks to see if there is a stored state and restores it
     */
    private void restoreState() {
        SessionState.restoreState(getApplicationContext());
        // TODO: also check if the user has been banned since last time app was closed
        if (SessionState.isLoggedIn()) {
            Log.v("BMS", "already logged in, restoring");
            startBMS();
        }
    }

    /**
     * Method for initializing hard coded values and restoring app state
     */
    private void initApp() {
        UserManagementFacade um = new UserManager();
        um.addUser("user", "pass");
        um.addUser("sally", "sally");
        restoreState();
    }

    // TODO: get rid of this replication
    /**
     * Creates Intent for the BMSActivity and launches it
     */
    private void startBMS() {
        Intent mainActivity = new Intent(this, BMSActivity.class);
        startActivity(mainActivity);
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
     * Checks if the user is already logged in
     * @return whether the user has already been validated in a previous
     * session
     */
    private boolean checkLogin() {
        return false;
    }
}
