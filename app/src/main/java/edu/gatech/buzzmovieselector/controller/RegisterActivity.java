package edu.gatech.buzzmovieselector.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import edu.gatech.buzzmovieselector.R;
import edu.gatech.buzzmovieselector.model.UserManagementFacade;
import edu.gatech.buzzmovieselector.model.UserManager;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    private void registerUser(){
        UserManagementFacade um = new UserManager();
    }
}