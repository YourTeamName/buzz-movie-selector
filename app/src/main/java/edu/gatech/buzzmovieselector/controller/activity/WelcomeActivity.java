package edu.gatech.buzzmovieselector.controller.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;
import edu.gatech.buzzmovieselector.R;
import edu.gatech.buzzmovieselector.biz.UserManagementFacade;
import edu.gatech.buzzmovieselector.biz.impl.UserManager;
import edu.gatech.buzzmovieselector.dao.DaoFactory;
import edu.gatech.buzzmovieselector.entity.Profile;
import edu.gatech.buzzmovieselector.entity.User;
import edu.gatech.buzzmovieselector.service.ApiNetwork;
import edu.gatech.buzzmovieselector.service.SessionState;

/**
 * WelcomeActivity is the controller for the welcome screen
 * Initially loaded activity
 */
public class WelcomeActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_ASK_PERMISSIONS = 2340;

    /**
     * Sets up the welcome activity
     *
     * @param savedInstanceState The instance state of the activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        initApp();
        if (verifyLogin()) {
            User user = SessionState.getInstance().getSessionUser();
            switch (user.getUserStatus()) {
                case USER:
                    Intent bmsActivity = new Intent(this, BMSActivity.class);
                    startActivity(bmsActivity);
                    return;
                case ADMIN:
                    Intent adminActivity = new Intent(this, AdminActivity
                            .class);
                    startActivity(adminActivity);
                    return;
                case BANNED:
                    Toast.makeText(WelcomeActivity.this, "Your account has " +
                            "been banned since last time. Please try to login" +
                            " again or contact an administrator.", Toast
                            .LENGTH_SHORT).show();
                    break;
                case LOCKED:
                    Toast.makeText(WelcomeActivity.this, "Your account has " +
                            "been locked since last time. Please try to login" +
                            " again or contact an administrator.", Toast
                            .LENGTH_SHORT).show();
                    break;
            }
        }
        // if not logged in or invalid session state, clear everything
        SessionState.getInstance().endSession(getApplicationContext());
    }

    /**
     * Checks to see if there is a stored state and restores it
     */

    /**
     * Restores state of the activity
     */
    private void restoreState() {
        SessionState.getInstance().restoreState(getApplicationContext());
    }

    /**
     * Checks to see if permissions need to be requested
     */
    private void checkPermissions() {
        int permissionCheck = ContextCompat.checkSelfPermission
                (getApplicationContext(), Manifest.permission.INTERNET);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest
                    .permission.INTERNET}, REQUEST_CODE_ASK_PERMISSIONS);
        } else {
        }
    }

    /**
     * Requests permissions
     *
     * @param requestCode  code number
     * @param permissions  the permissions to request
     * @param grantResults check whether to grant the results
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String
            permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS:
                if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    finish();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions,
                        grantResults);
        }
    }

    /**
     * Method for initializing hard coded values and restoring app state
     */
    private void initApp() {

        // Pass context to DaoFactory so that it can work properly later
        DaoFactory.setContext(this);

        // test user
        User testUser = new User("user", "pass", "user");
        testUser.setProfile(new Profile("George", "Burdell", "Computer " +
                "Science", "gp@gatech.edu"));
        UserManagementFacade um = new UserManager();
        um.addUser(testUser);

        // test admin
        User testAdmin = new User("admin", "admin", "admin");
        testUser.setProfile(new Profile("Admin", "Burdell", "Computer " +
                "Science", "gp@gatech.edu"));
        um.addUser(testAdmin);

        checkPermissions();
        restoreState();
        ApiNetwork.getInstance(this);
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
        return SessionState.getInstance().isLoggedIn();
    }
}
