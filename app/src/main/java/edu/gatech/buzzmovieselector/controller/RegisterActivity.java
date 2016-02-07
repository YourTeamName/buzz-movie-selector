package edu.gatech.buzzmovieselector.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import edu.gatech.buzzmovieselector.R;
import edu.gatech.buzzmovieselector.model.UserManagementFacade;
import edu.gatech.buzzmovieselector.model.UserManager;

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

    public void attemptRegister(View v) {
        if (!verifyRegister()) {
            // notify the user than the register was unsuccessful
            // cancel registration
            return;
        }
        registerUser();
        // notify the user that register was successful
    }

    private boolean usernameExists(String username) {
        return false;
    }

    private boolean verifyRegister() {
        if (usernameExists(userField.getText().toString())) {
            // notify the user that the username already exists
            return false;
        }
        String pass1 = passwordField.getText().toString();
        String pass2 = passwordConfirmField.getText().toString();
        if (!pass1.equals(pass2)) {
            // notify the user that the passwords are mismatched
            return false;
        }
        return true;
    }

    private void registerUser(){
        UserManagementFacade um = new UserManager();
    }
}
