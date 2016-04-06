package edu.gatech.buzzmovieselector.controller.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import edu.gatech.buzzmovieselector.R;
import edu.gatech.buzzmovieselector.biz.UserManagementFacade;
import edu.gatech.buzzmovieselector.biz.impl.UserManager;
import edu.gatech.buzzmovieselector.controller.util.UserListAdapter;
import edu.gatech.buzzmovieselector.entity.User;
import edu.gatech.buzzmovieselector.service.SessionState;

import java.util.List;

public class AdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        final UserManagementFacade um = new UserManager();

        final List<User> userList = um.findAll();

        final UserListAdapter adapter = new UserListAdapter(userList, this);

        final ListView listview = (ListView) findViewById(R.id.userListView);
        listview.setAdapter(adapter);

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        final UserManagementFacade um = new UserManager();

        final List<User> userList = um.findAll();

        final UserListAdapter adapter = new UserListAdapter(userList, this);

        final ListView listview = (ListView) findViewById(R.id.userListView);
        listview.setAdapter(adapter);

    }

    @Override
    public void onBackPressed() {
    }

    /**
     * Handles logout
     *
     * @param view The view of the activity
     */
    public void onLogoutClicked(@SuppressWarnings("UnusedParameters") View view) {
        SessionState.getInstance().endSession(getApplicationContext());
        finish();
    }
}
