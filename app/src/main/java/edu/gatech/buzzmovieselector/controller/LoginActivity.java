package edu.gatech.buzzmovieselector.controller;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import edu.gatech.buzzmovieselector.R;
import edu.gatech.buzzmovieselector.SessionState;
import edu.gatech.buzzmovieselector.model.AuthenticationFacade;
import edu.gatech.buzzmovieselector.model.User;
import edu.gatech.buzzmovieselector.model.UserManager;

/**
 * LoginActivity is the controller for the login screen.
 * Handles login, contains a login form and a cancel button
 */
public class LoginActivity extends Activity {

    // UI references.
    private AutoCompleteTextView mUsernameView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mUsernameView = (AutoCompleteTextView) findViewById(R.id.username);
        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        Button loginButton = (Button) findViewById(R.id.login_button);

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
    }

    /**
     * login is called by clicking the Login button, attempts to login using
     * entered credentials
     * @param v Reference to widget firing event
     */
    public void login(View v) {
        attemptLogin();
    }

    /**
     * cancel is called by clicking the Cancel button, closes LoginActivity
     * @param v Reference to widget firing event
     */
    public void cancel(View v) {
        finish();
    }

    /**
     * Retrieves user-entered data in fields and validates them, if successful,
     * starts BMSActivity
     */
    private void attemptLogin() {
        AuthenticationFacade af = new UserManager();
        String userName = mUsernameView.getText().toString();
        String userPass = mPasswordView.getText().toString();
        if (af.handleLoginRequest(userName,
                userPass)) {
            Toast.makeText(getApplicationContext(),
                        "login success", Toast.LENGTH_SHORT).show();
            // TODO: make a matchUser function so we don't have to import User
            User sessionUser = new User(userName, userPass, "USER");
            SessionState.login(sessionUser, getApplicationContext());
            startBMS();
        } else {
            Toast.makeText(getApplicationContext(),
                    "login failure", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Creates Intent for the BMSActivity and launches it
     */
    private void startBMS() {
        Intent mainActivity = new Intent(this, BMSActivity.class);
        startActivity(mainActivity);
    }

}

