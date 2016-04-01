package edu.gatech.buzzmovieselector.controller.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

    public static final String KEY_PROFILE_USER = "profileUser";

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
     *
     * @param e EditText view to enable
     */
    private void enableTextField(EditText e) {
        e.setFocusable(true);
        e.setClickable(true);
        e.setFocusableInTouchMode(true);
    }

    /**
     * Helper method for disabling editable on an EditText
     *
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
     * Retrieves user parameter from passed bundle and uses it to initialize
     * form
     */
    private void initializeForm() {
        final String bundledUserName = getIntent().getExtras()
            .getString(KEY_PROFILE_USER, null);
        if (bundledUserName == null) {
            if (SessionState.getInstance().isLoggedIn()) {
                profileUser = SessionState.getInstance().getSessionUser();
            } else {
                Log.e("ProfileActivity", "Cannot get a user to view profile");
                finish();
            }
        } else {
            final UserManagementFacade um = new UserManager();
            profileUser = um.findUserById(bundledUserName);
        }
        Log.v("ProfileActivity", "profileUser is " + profileUser);
        userNameLabel.setText(profileUser.getUsername() + " Profile");
        editCheckBox.setOnCheckedChangeListener(new CompoundButton
            .OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean
                isChecked) {
                Log.v("ProfileActivity", "Edit checked: " + (isChecked ?
                    "True" : "False"));
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
        final RelativeLayout formLayout = (RelativeLayout) findViewById(R.id
            .profileLayout);
        formLayout.requestFocus();
    }

    /**
     * Called when the Save button is clicked - verifies proper values are
     * entered
     */
    private boolean validateProfile() {
        final String firstName = firstNameText.getText().toString();
        final String lastName = lastNameText.getText().toString();
        final String userMajor = degreeSpinner.getSelectedItem().toString();
        final String email = emailText.getText().toString();
        if ("".equals(firstName)) {
            firstNameText.setError("Enter a first name");
            return false;
        } else if ("".equals(lastName)) {
            lastNameText.setError("Enter a last name");
            return false;
        } else if ("".equals(userMajor)) {
            final TextView spinnerText = (TextView) degreeSpinner.getSelectedView();
            spinnerText.setError("Enter a valid major");
            return false;
        } else if ("".equals(email) || !email.contains("@")) {
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
        final Profile userProfile = profileUser.getProfile();
        int degreeIndex = 0;
        if (userProfile.getMajor() != null) {
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
     * Retrieves existing user profile information and uses it to populate
     * the fields
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
        if (profileUser.equals(SessionState.getInstance().getSessionUser())
            || profileUser.getUserStatus() == User.UserStatus.ADMIN) {
            editCheckBox.setEnabled(true);
        } else {
            editCheckBox.setEnabled(false);
        }
    }

    /**
     * Populates degreeSpinner with the values from the UserDegree enum
     */
    private void populateSpinner() {
        final ArrayAdapter degreeAdapter = new ArrayAdapter<>(this, R.layout
            .support_simple_spinner_dropdown_item, Profile.USER_DEGREES);
        degreeSpinner.setAdapter(degreeAdapter);
    }

    private void depopulateSpinner() {
        final String[] fakeList = {profileUser.getProfile().getMajor() == null ? ""
                                 : profileUser.getProfile().getMajor()};
        final ArrayAdapter degreeAdapter = new ArrayAdapter<>(this, R.layout
            .support_simple_spinner_dropdown_item, fakeList);
        degreeSpinner.setAdapter(degreeAdapter);
    }

    /**
     * Updates the Profile object associated with the user
     */
    private void updateProfile() {
        // TODO: add persistent save in addition to updating profileUser profile
        if (validateProfile()) {
            final String firstName = firstNameText.getText().toString();
            final String lastName = lastNameText.getText().toString();
            final String userMajor = degreeSpinner.getSelectedItem().toString();
            final String email = emailText.getText().toString();
            final Profile uProfile = profileUser.getProfile();
            uProfile.setFirstName(firstName);
            uProfile.setLastName(lastName);
            uProfile.setMajor(userMajor);
            uProfile.setEmail(email);
            profileUser.setProfile(uProfile);
            final UserManagementFacade um = new UserManager();
            um.updateUser(profileUser);
            finish();
        }
    }

    public void saveProfile(View v) {
        updateProfile();
    }
}
