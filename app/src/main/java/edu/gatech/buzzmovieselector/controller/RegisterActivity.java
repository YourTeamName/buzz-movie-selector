package edu.gatech.buzzmovieselector.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import edu.gatech.buzzmovieselector.R;
import edu.gatech.buzzmovieselector.model.UserManagementFacade;
import edu.gatech.buzzmovieselector.model.UserManager;

/**
 * RegisterActivity contains the form for registering a new user
 * Handles registration, contains fields for username, email, and password
 */
public class RegisterActivity extends AppCompatActivity {

    private EditText userField;
    private EditText passwordField;
    private EditText passwordConfirmField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        // consider changing the userField id
        userField = (EditText) findViewById(R.id.Username);
        passwordField = (EditText) findViewById(R.id.passwordText);
        passwordConfirmField = (EditText) findViewById(R.id.passwordConfirmText);
    }

    /**
     * attemptRegister is called when the Register button is clicked
     * checks whether the registration form is valid and attempts to register
     * a new user
     * @param v Reference to widget firing event
     */
    public void attemptRegister(View v) {
        if (!verifyRegister()) {
            // notify the user than the register was unsuccessful
            // cancel registration
            return;
        }
        registerUser();
        // notify the user that register was successful
    }

    /**
     * Checks to see if username has already been registered
     * @param username Username to check
     * @return username is already taken
     */
    private boolean usernameExists(String username) {
        UserManagementFacade uf = new UserManager();
        return uf.userExists(username);
    }

    /**
     * Checks to see whether the data entered in the registration form is valid
     * @return the data entered in the registration form is valid
     */
    private boolean verifyRegister() {
        String userName = userField.getText().toString();
        if (usernameExists(userName)) {
            userField.setError("Username is already registered");
            return false;
        }
        String pass1 = passwordField.getText().toString();
        String pass2 = passwordConfirmField.getText().toString();
        if (!pass1.equals(pass2)) {
            passwordConfirmField.setError("Passwords do not match");
            return false;
        }
        return true;
    }

    /**
     * Retrieves the text from the entered fields and adds it as a new user
     */
    private void registerUser(){
        UserManagementFacade um = new UserManager();
    }
}
