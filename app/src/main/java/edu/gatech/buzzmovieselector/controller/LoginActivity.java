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
import edu.gatech.buzzmovieselector.model.AuthenticationFacade;
import edu.gatech.buzzmovieselector.model.UserManager;

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

    public void login(View v) {
        attemptLogin();
    }

    public void cancel(View v) {
        finish();
    }

    private void attemptLogin() {
        AuthenticationFacade af = new UserManager();
        if (af.handleLoginRequest(mUsernameView.getText().toString(),
                    mPasswordView.getText().toString())) {
            Toast.makeText(getApplicationContext(),
                        "login success", Toast.LENGTH_SHORT).show();
            startBMS();
        } else {
            Toast.makeText(getApplicationContext(),
                    "login failure", Toast.LENGTH_SHORT).show();
        }
    }

    public void startBMS() {
        Toast.makeText(getApplicationContext(), "start BMS activity", Toast.LENGTH_SHORT).show();
        Intent mainActivity = new Intent(this, BMSActivity.class);
        startActivity(mainActivity);
    }

}

