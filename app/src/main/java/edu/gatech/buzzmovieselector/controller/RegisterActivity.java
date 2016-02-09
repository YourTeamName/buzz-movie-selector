package edu.gatech.buzzmovieselector.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import edu.gatech.buzzmovieselector.R;
import edu.gatech.buzzmovieselector.model.User;
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
    private UserManagementFacade um;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        // consider changing the userField id
        userField = (EditText) findViewById(R.id.Username);
        passwordField = (EditText) findViewById(R.id.passwordText);
        passwordConfirmField = (EditText) findViewById(R.id.passwordConfirmText);
        um = new UserManager();
    }

    /**
     * attemptRegister is called when the Register button is clicked
     * checks whether the registration form is valid and attempts to register
     * a new user
     * @param v Reference to widget firing event
     */
    public void attemptRegister(View v) {
        if (!verifyRegister()) {
            Toast.makeText(getApplicationContext(), "Registration unsuccessful", Toast.LENGTH_SHORT).show();
            return;
        }
        User newUser = new User(userField.getText().toString(), passwordField.getText().toString());
        um.addUser(newUser);

        // Registration is successful, change screen to login
        Toast.makeText(getApplicationContext(), "Registration successful, please log in", Toast.LENGTH_SHORT).show();
        Intent loginActivity = new Intent(this, LoginActivity.class);
        startActivity(loginActivity);
    }

    /**
     * Checks to see whether the data entered in the registration form is valid
     * @return the data entered in the registration form is valid
     */
    private boolean verifyRegister() {
        if (um.findUserById(userField.getText().toString()) != null) {
            Toast.makeText(getApplicationContext(), "Username already exists", Toast.LENGTH_SHORT).show();
            return false;
        }
        String pass1 = passwordField.getText().toString();
        String pass2 = passwordConfirmField.getText().toString();
        if (!pass1.equals(pass2)) {
            Toast.makeText(getApplicationContext(), "Passwords don't match", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

}
