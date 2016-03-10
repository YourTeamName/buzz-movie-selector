package edu.gatech.buzzmovieselector.controller.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import edu.gatech.buzzmovieselector.R;
import edu.gatech.buzzmovieselector.biz.UserManagementFacade;
import edu.gatech.buzzmovieselector.biz.impl.UserManager;
import edu.gatech.buzzmovieselector.entity.Profile;
import edu.gatech.buzzmovieselector.entity.User;

/**
 * RegisterActivity contains the form for registering a new user
 * Handles registration, contains fields for username, email, and password
 */
public class RegisterActivity extends AppCompatActivity {

    private EditText userField;
    private EditText emailField;
    private EditText passwordField;
    private EditText passwordConfirmField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        userField = (EditText) findViewById(R.id.userText);
        emailField = (EditText) findViewById(R.id.emailText);
        passwordField = (EditText) findViewById(R.id.passwordText);
        passwordConfirmField = (EditText) findViewById(R.id
                .passwordConfirmText);
    }

    /**
     * attemptRegister is called when the Register button is clicked
     * checks whether the registration form is valid and attempts to register
     * a new user
     *
     * @param v Reference to widget firing event
     */
    public void attemptRegister(View v) {
        if (!verifyRegister()) {
            return;
        }
        User newUser = new User(userField.getText().toString(), passwordField
                .getText().toString());
        Profile newProfile = new Profile("", "", "", emailField.getText()
                .toString());
        newUser.setProfile(newProfile);
        UserManagementFacade um = new UserManager();
        um.addUser(newUser);
        finish();
    }

    /**
     * Checks to see if username has already been registered
     *
     * @param username Username to check
     * @return username is already taken
     */
    private boolean usernameExists(String username) {
        UserManagementFacade uf = new UserManager();
        return uf.userExists(username);
    }

    /**
     * Checks to see whether the data entered in the registration form is valid
     *
     * @return the data entered in the registration form is valid
     */
    private boolean verifyRegister() {
        String userName = userField.getText().toString();
        if (userName.equals("")) {
            userField.setError("You must enter a username");
            return false;
        }
        if (usernameExists(userName)) {
            userField.setError("Username is already registered");
            return false;
        }
        String email = emailField.getText().toString();
        if (email.equals("")) {
            emailField.setError("You must enter an email");
            return false;
        }
        if (!email.contains("@")) {
            emailField.setError("You must enter a valid email");
            return false;
        }
        String pass1 = passwordField.getText().toString();
        String pass2 = passwordConfirmField.getText().toString();
        if (pass1.equals("")) {
            passwordField.setError("You must enter a password");
            return false;
        }
        if (!pass1.equals(pass2)) {
            passwordConfirmField.setError("Passwords do not match");
            return false;
        }
        return true;
    }

}
