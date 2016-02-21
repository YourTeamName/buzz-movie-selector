package edu.gatech.buzzmovieselector.controller;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import edu.gatech.buzzmovieselector.R;
import edu.gatech.buzzmovieselector.biz.UserManagementFacade;
import edu.gatech.buzzmovieselector.biz.impl.UserManager;
import edu.gatech.buzzmovieselector.dao.DaoFactory;
import edu.gatech.buzzmovieselector.entity.User;
import edu.gatech.buzzmovieselector.service.SessionState;
import edu.gatech.buzzmovieselector.service.api.ApiNetwork;

/**
 * WelcomeActivity is the controller for the welcome screen
 * Initially loaded activity
 */
public class WelcomeActivity extends AppCompatActivity {

    private final int REQUEST_CODE_ASK_PERMISSIONS = 2340;

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
     * Checks to see if permissions need to be requested
     */
    private void checkPermissions() {
        int permissionCheck = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.INTERNET);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.INTERNET}, REQUEST_CODE_ASK_PERMISSIONS);
        } else {
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS:
                if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    finish();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    /**
     * Method for initializing hard coded values and restoring app state
     */
    private void initApp() {
        // TODO: load user data from persistent storage so that register works
        // Pass context to DaoFactory so that it can work properly later
        DaoFactory.setContext(this);
        checkPermissions();
        UserManagementFacade um = new UserManager();
        um.addUser(new User("user", "pass"));
        restoreState();
        // ApiNetwork.getInstance(getApplicationContext()).apiString("http://api.rottentomatoes.com/api/public/v1.0/lists/dvds/new_releases.json?page_limit=1&apikey=yedukp76ffytfuy24zsqk7f5");
        ApiNetwork.getInstance(getApplicationContext()).apiString("http://google.com/humans.txt").getSyncRawResponse();
    }

    /**
     * startLogin is called when the Login button is clicked
     * Creates an intent for LoginActivity and launches it
     *
     * @param v Reference to widget firing event
     */
    public void startLogin(View v) {
        Intent loginActivity = new Intent(this, LoginActivity.class);
        startActivity(loginActivity);
    }

    /**
     * startRegister is called when the Register button is clicked
     * Creates an intent for RegisterActivity and launches it
     *
     * @param v Reference to widget firing event
     */
    public void startRegister(View v) {
        Intent registerActivity = new Intent(this, RegisterActivity.class);
        startActivity(registerActivity);
    }

    /**
     * Checks if the user is already logged in and validates previously stored
     * session
     *
     * @return whether the user has already been validated in a previous
     * session and user data is still valid
     */
    private boolean verifyLogin() {
        return SessionState.getInstance().isLoggedIn() && SessionState
                .getInstance().verifySession();
    }
}
