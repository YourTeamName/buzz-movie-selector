package edu.gatech.buzzmovieselector.controller.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import edu.gatech.buzzmovieselector.R;

public class UserStatusActivity extends AppCompatActivity {
    private TextView usernameLabel;
    private TextView userStatusLabel;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_status);
    }
}
