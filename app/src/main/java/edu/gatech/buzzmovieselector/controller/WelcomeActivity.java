package edu.gatech.buzzmovieselector.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import edu.gatech.buzzmovieselector.R;
import edu.gatech.buzzmovieselector.model.User;
import edu.gatech.buzzmovieselector.model.UserManagementFacade;
import edu.gatech.buzzmovieselector.model.UserManager;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        UserManagementFacade um = new UserManager();
        User testUser = new User("user", "pass");
        um.addUser(testUser);
        if (checkLogin()) {
            Toast.makeText(getApplicationContext(), "check login is true", Toast.LENGTH_SHORT).show();
            Intent mainActivity = new Intent(this, BMSActivity.class);
            startActivity(mainActivity);
        }
    }

    private void allowAccess() {
        // user is already logged in, so redirect to real menu
    }

    public void startLogin(View v) {
        Intent loginActivity = new Intent(this, LoginActivity.class);
        startActivity(loginActivity);
    }

    public void startRegister(View v) {
        Intent registerActivity = new Intent(this, RegisterActivity.class);
        startActivity(registerActivity);
    }

    private boolean checkLogin() {
        return false;
    }
}
