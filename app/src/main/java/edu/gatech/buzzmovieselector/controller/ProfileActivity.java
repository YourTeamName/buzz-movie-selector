package edu.gatech.buzzmovieselector.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import edu.gatech.buzzmovieselector.R;
import edu.gatech.buzzmovieselector.biz.UserManagementFacade;
import edu.gatech.buzzmovieselector.biz.impl.UserManager;
import edu.gatech.buzzmovieselector.entity.Profile;
import edu.gatech.buzzmovieselector.entity.User;
import edu.gatech.buzzmovieselector.service.SessionState;

public class ProfileActivity extends AppCompatActivity {

    public static final String PROFILE_USER_KEY = "profileUser";

    private User profileUser;

    private TextView userNameLabel;
    private EditText firstNameText;
    private EditText lastNameText;
    private Spinner degreeSpinner;
    private EditText emailText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        userNameLabel = (TextView) findViewById(R.id.userNameText);
        firstNameText = (EditText) findViewById(R.id.firstNameText);
        lastNameText = (EditText) findViewById(R.id.lastNameText);
        degreeSpinner = (Spinner) findViewById(R.id.degreeSpinner);
        emailText = (EditText) findViewById(R.id.emailText);
        initializeForm();
    }

    /**
     * Retrieves user parameter from passed bundle and uses it to initialize form
     */
    private void initializeForm() {
        String bundledUserName = getIntent().getExtras().getString(PROFILE_USER_KEY, null);
        if (bundledUserName == null) {
            if (SessionState.getInstance().isLoggedIn()) {
                profileUser = SessionState.getInstance().getSessionUser();
            } else {
                Log.e("ProfileActivity", "Cannot get a user to view");
                finish();
            }
        } else {
            UserManagementFacade um = new UserManager();
            profileUser = um.findUserById(bundledUserName);
        }
        userNameLabel.setText(profileUser.getUsername());
        populateSpinner();
        populateFields();
    }

    /**
     * Called when the Save button is clicked - verifies proper values are entered
     */
    private void validateProfile() {

    }

    /**
     * Retrieves existing user profile information and uses it to populate the fields
     */
    private void populateFields() {
        Profile userProfile = profileUser.getProfile();
        if (userProfile == null) {
            userProfile = new Profile();
        }
        firstNameText.setText(userProfile.getFirstName());
        lastNameText.setText(userProfile.getLastName());
        int enumIndex = 0;
        for (Profile.UserDegree ud : Profile.UserDegree.values()) {
            if (ud == userProfile.getMajor()) {
                break;
            }
            enumIndex++;
        }
        degreeSpinner.setSelection(enumIndex);
        emailText.setText(userProfile.getEmail());
    }

    /**
     * Populates degreeSpinner with the values from the UserDegree enum
     */
    private void populateSpinner() {
        ArrayAdapter degreeAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, Profile.UserDegree.values());
        degreeSpinner.setAdapter(degreeAdapter);
    }

    /**
     * Updates the Profile object associated with the user
     */
    private void updateProfile() {

    }

    public void saveProfile(View v) {
        updateProfile();
        finish();
    }
}
