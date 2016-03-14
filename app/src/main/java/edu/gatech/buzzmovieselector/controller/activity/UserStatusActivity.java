package edu.gatech.buzzmovieselector.controller.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import edu.gatech.buzzmovieselector.R;
import edu.gatech.buzzmovieselector.entity.User;

public class UserStatusActivity extends AppCompatActivity {

    public static final String CURRENT_USER = "currentUser";

    private TextView usernameLabel;
    private TextView userStatusLabel;
    private User currentUser;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_status);
        usernameLabel = (TextView) findViewById(R.id.usernameLabel);
        userStatusLabel = (TextView) findViewById(R.id.userStatusLabel);
        currentUser = (User) getIntent().getSerializableExtra(CURRENT_USER);
        initializeForm();
    }

    /**
     * Retrieve user information and populate forms
     */
    private void initializeForm() {
        usernameLabel.setText(currentUser.getUsername());
        userStatusLabel.setText(currentUser.getUserLevel().toString());
    }
}
