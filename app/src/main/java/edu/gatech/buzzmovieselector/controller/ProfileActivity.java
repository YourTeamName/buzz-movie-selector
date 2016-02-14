package edu.gatech.buzzmovieselector.controller;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RelativeLayout;
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
    private CheckBox editCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        userNameLabel = (TextView) findViewById(R.id.userNameText);
        firstNameText = (EditText) findViewById(R.id.firstNameText);
        lastNameText = (EditText) findViewById(R.id.lastNameText);
        degreeSpinner = (Spinner) findViewById(R.id.degreeSpinner);
        emailText = (EditText) findViewById(R.id.emailText);
        editCheckBox = (CheckBox) findViewById(R.id.editCheckBox);
        initializeForm();
    }

    /**
     * Helper method for enable editable on an EditText
     * @param e EditText view to enable
     */
    private void enableTextField(EditText e) {
        e.setFocusable(true);
        e.setClickable(true);
        e.setFocusableInTouchMode(true);
    }

    /**
     * Helper method for disabling editable on an EditText
     * @param e EditText view to disable
     */
    private void disableTextField(EditText e) {
        e.setFocusable(false);
        e.setClickable(false);
        e.setFocusableInTouchMode(false);
    }

    /**
     * disables all elements in the profile form
     */
    private void disableForm() {
        disableTextField(firstNameText);
        disableTextField(lastNameText);
        disableSpinner();
        disableTextField(emailText);
        cancelFocus();
    }

    /**
     * enables all elements in the profile form
     */
    private void enableForm() {
        enableTextField(firstNameText);
        enableTextField(lastNameText);
        enableSpinner();
        enableTextField(emailText);
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
                Log.e("ProfileActivity", "Cannot get a user to view profile");
                finish();
            }
        } else {
            UserManagementFacade um = new UserManager();
            profileUser = um.findUserById(bundledUserName);
        }
        Log.v("ProfileActivity", "profileUser is " + profileUser);
        userNameLabel.setText(profileUser.getUsername() + " Profile");
        editCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.v("ProfileActivity", "Edit checked: " + (isChecked ? "True" : "False"));
                if (isChecked) {
                    enableForm();
                } else {
                    disableForm();
                }
            }
        });
        cancelFocus();
        populateFields();
        disableForm();
    }

    /**
     * Cancels focus from all form elements
     */
    private void cancelFocus() {
        RelativeLayout formLayout = (RelativeLayout) findViewById(R.id.profileLayout);
        formLayout.requestFocus();
    }

    /**
     * Called when the Save button is clicked - verifies proper values are entered
     */
    private boolean validateProfile() {
        String firstName = firstNameText.getText().toString();
        String lastName = lastNameText.getText().toString();
        String userMajor = degreeSpinner.getSelectedItem().toString();
        String email = emailText.getText().toString();
        if (firstName.equals("")) {
            firstNameText.setError("Enter a first name");
            return false;
        } else if (lastName.equals("")) {
            lastNameText.setError("Enter a last name");
            return false;
        } else if (userMajor.equals("")) {
            TextView spinnerText = (TextView) degreeSpinner.getSelectedView();
            spinnerText.setError("Enter a valid major");
            return false;
        } else if (email.equals("") || !email.contains("@")) {
            emailText.setError("Enter a valid email address");
            return false;
        } else {
            return true;
        }
    }

    /**
     * enables spinner by showing all of the possible values
     */
    private void enableSpinner() {
        populateSpinner();
        Profile userProfile = profileUser.getProfile();
        int degreeIndex = 0;
        if (userProfile.getMajor() == null) {

        } else {
            for (int i = 0; i < Profile.USER_DEGREES.length; i++) {
                if (userProfile.getMajor().equals(Profile.USER_DEGREES[i])) {
                    degreeIndex = i;
                    break;
                }
            }
        }
        degreeSpinner.setSelection(degreeIndex);
    }

    /**
     * disables spinner by showing only selected value
     */
    private void disableSpinner() {
        depopulateSpinner();
        degreeSpinner.setSelection(0);
    }

    /**
     * Retrieves existing user profile information and uses it to populate the fields
     */
    private void populateFields() {
        Profile userProfile = profileUser.getProfile();
        if (userProfile == null) {
            userProfile = new Profile();
            profileUser.setProfile(userProfile);
        }
        firstNameText.setText(userProfile.getFirstName());
        lastNameText.setText(userProfile.getLastName());
        emailText.setText(userProfile.getEmail());
        if (profileUser.equals(SessionState.getInstance().getSessionUser()) || profileUser.getUserLevel() == User.UserLevel.ADMIN) {
            editCheckBox.setEnabled(true);
        } else {
            editCheckBox.setEnabled(false);
        }
    }

    /**
     * Populates degreeSpinner with the values from the UserDegree enum
     */
    private void populateSpinner() {
        ArrayAdapter degreeAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, Profile.USER_DEGREES);
        degreeSpinner.setAdapter(degreeAdapter);
    }

    private void depopulateSpinner() {
        String[] fakeList = { profileUser.getProfile().getMajor() == null ? "" : profileUser.getProfile().getMajor() };
        ArrayAdapter degreeAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, fakeList);
        degreeSpinner.setAdapter(degreeAdapter);
    }

    /**
     * Updates the Profile object associated with the user
     */
    private void updateProfile() {
        // TODO: add persistent save in addition to updating profileUser profile
        if (validateProfile()) {
            String firstName = firstNameText.getText().toString();
            String lastName = lastNameText.getText().toString();
            String userMajor = degreeSpinner.getSelectedItem().toString();
            String email = emailText.getText().toString();
            Profile newProfile = new Profile(firstName, lastName, userMajor, email);
            profileUser.setProfile(newProfile);
            finish();
        }
    }

    public void saveProfile(View v) {
        updateProfile();
    }
}
