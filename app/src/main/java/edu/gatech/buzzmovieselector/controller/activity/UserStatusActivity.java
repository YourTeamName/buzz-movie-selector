package edu.gatech.buzzmovieselector.controller.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import edu.gatech.buzzmovieselector.R;
import edu.gatech.buzzmovieselector.biz.UserManagementFacade;
import edu.gatech.buzzmovieselector.biz.impl.UserManager;
import edu.gatech.buzzmovieselector.entity.User;

public class UserStatusActivity extends AppCompatActivity {

    public static final String CURRENT_USER = "currentUser";

    private TextView usernameLabel;
    private TextView userStatusLabel;
    private Button banButton;
    private Button lockButton;
    private User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_status);

        usernameLabel = (TextView) findViewById(R.id.usernameLabel);
        userStatusLabel = (TextView) findViewById(R.id.userStatusLabel);
        banButton = (Button) findViewById(R.id.banButton);
        lockButton = (Button) findViewById(R.id.lockButton);

        currentUser = (User) getIntent().getSerializableExtra(CURRENT_USER);
        initializeForm();
    }

    /**
     * Retrieve user information and populate forms
     */
    private void initializeForm() {
        usernameLabel.setText(currentUser.getUsername());
        userStatusLabel.setText(currentUser.getUserStatus().toString());
        switch (currentUser.getUserStatus()) {
            case USER:
                banButton.setEnabled(true);
                lockButton.setEnabled(true);
                banButton.setText("BAN");
                lockButton.setText("LOCK");
                break;
            case ADMIN:
                banButton.setEnabled(false);
                lockButton.setEnabled(false);
                banButton.setText("BAN");
                lockButton.setText("LOCK");
                break;
            case BANNED:
                banButton.setEnabled(true);
                lockButton.setEnabled(false);
                banButton.setText("UNBAN");
                lockButton.setText("LOCK");
                break;
            case LOCKED:
                banButton.setEnabled(false);
                lockButton.setEnabled(true);
                banButton.setText("BAN");
                lockButton.setText("UNLOCK");
                break;
            default:
                throw new IllegalArgumentException("Invalid UserStatus");
        }
    }

    /**
     * Handler for banButton
     * @param view the view
     */
    public void onBanButtonClicked(View view) {
        if (currentUser.getUserStatus().equals(User.UserStatus.BANNED)) {
            currentUser.setUserStatus(User.UserStatus.USER);
        } else if (currentUser.getUserStatus().equals(User.UserStatus.USER)) {
            currentUser.setUserStatus(User.UserStatus.BANNED);
        }
        initializeForm();
        Log.v("USER STATUS", currentUser.getUserStatus().toString());
        final UserManagementFacade userManager = new UserManager();
        userManager.updateUser(currentUser);
    }

    /**
     * Handler for lockButton
     * @param view the view
     */
    public void onLockButtonClicked(View view) {
        if (currentUser.getUserStatus().equals(User.UserStatus.LOCKED)) {
            currentUser.setUserStatus(User.UserStatus.USER);
        } else if (currentUser.getUserStatus().equals(User.UserStatus.USER)) {
            currentUser.setUserStatus(User.UserStatus.LOCKED);
        }
        initializeForm();
        Log.v("USER STATUS", currentUser.getUserStatus().toString());
        final UserManagementFacade userManager = new UserManager();
        userManager.updateUser(currentUser);
    }
}
