package edu.gatech.buzzmovieselector.controller.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import edu.gatech.buzzmovieselector.R;
import edu.gatech.buzzmovieselector.biz.AuthenticationFacade;
import edu.gatech.buzzmovieselector.biz.UserManagementFacade;
import edu.gatech.buzzmovieselector.biz.impl.UserManager;
import edu.gatech.buzzmovieselector.entity.User;
import edu.gatech.buzzmovieselector.service.SessionState;

/**
 * LoginActivity is the controller for the startSession screen.
 * Handles startSession, contains a startSession form and a cancel button
 */
public class LoginActivity extends Activity {

    // UI references.
    private AutoCompleteTextView userText;
    private EditText passwordText;

    int loginAttempts;
    private static final int LOCK_ATTEMPTS = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginAttempts = 0;
        setContentView(R.layout.activity_login);
        userText = (AutoCompleteTextView) findViewById(R.id.username);
        passwordText = (EditText) findViewById(R.id.password);
        passwordText.setOnEditorActionListener(new TextView
                .OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent
                    keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });
    }

    /**
     * login is called by clicking the Login button, attempts to login using
     * entered credentials
     *
     * @param v Reference to widget firing event
     */
    public void login(View v) {
        if (validateLogin()) {
            attemptLogin();
        }
    }

    /**
     * cancel is called by clicking the Cancel button, closes LoginActivity
     *
     * @param v Reference to widget firing event
     */
    public void cancel(View v) {
        finish();
    }

    /**
     * Validates the startSession form
     *
     * @return the startSession form is valid
     */
    public boolean validateLogin() {
        String userName = userText.getText().toString();
        String userPass = passwordText.getText().toString();
        if (userName.equals("")) {
            userText.setError("You must enter a username");
            return false;
        }
        if (userPass.equals("")) {
            passwordText.setError("You must enter a password");
            return false;
        }
        return true;
    }

    /**
     * Clears the user entered values in the username and password fields
     */
    private void resetFields() {
        userText.setText("");
        passwordText.setText("");
        userText.clearFocus();
        passwordText.clearFocus();
    }

    /**
     * Retrieves user-entered data in fields and validates them, if successful,
     * starts BMSActivity
     */
    private void attemptLogin() {
        UserManager um = new UserManager();
        AuthenticationFacade af = um;
        UserManagementFacade uf = um;
        String userName = userText.getText().toString();
        String userPass = passwordText.getText().toString();
        User attemptUser = af.login(userName, userPass);
        if (attemptUser != null) {
            SessionState.getInstance().startSession(attemptUser,
                    getApplicationContext());
            resetFields();
            switch (attemptUser.getUserStatus()) {
                case USER:
                    startBMS();
                    break;
                case ADMIN:
                    startAdmin();
                    break;
                case BANNED:
                    Toast.makeText(LoginActivity.this, "Sorry, this user is " +
                            "currently banned", Toast
                            .LENGTH_SHORT).show();
                    break;
                case LOCKED:
                    Toast.makeText(LoginActivity.this, "Sorry, this user is " +
                            "currently locked", Toast
                            .LENGTH_SHORT).show();
                    break;

            }
        } else {
            if (!uf.userExists(userName)) {
                userText.setError("Invalid Username");
            } else {
                passwordText.setError("Invalid Password");
                loginAttempts++;
                if (loginAttempts >= 3) {
                    User attemptedUser = uf.findUserById(userName);
                    attemptedUser.setUserStatus(User.UserStatus.LOCKED);
                    uf.updateUser(attemptedUser);
                    Toast.makeText(LoginActivity.this, "Account locked: " +
                            "too many attempts", Toast
                            .LENGTH_SHORT).show();
                }
            }
        }
    }

    /**
     * Creates Intent for the BMSActivity and launches it
     */
    private void startBMS() {
        Intent bmsActivity = new Intent(this, BMSActivity.class);
        finish();
        startActivity(bmsActivity);
    }

    public void startAdmin() {
        Intent adminActivity = new Intent(this, AdminActivity.class);
        finish();
        startActivity(adminActivity);
    }

}

