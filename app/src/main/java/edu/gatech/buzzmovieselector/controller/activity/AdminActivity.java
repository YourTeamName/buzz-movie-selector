package edu.gatech.buzzmovieselector.controller.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import edu.gatech.buzzmovieselector.R;
import edu.gatech.buzzmovieselector.biz.UserManagementFacade;
import edu.gatech.buzzmovieselector.biz.impl.UserManager;
import edu.gatech.buzzmovieselector.controller.util.UserListAdapter;
import edu.gatech.buzzmovieselector.entity.User;

import java.util.List;

public class AdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        UserManagementFacade um = new UserManager();

        List<User> userList = um.findAll();

        Log.v("ADMIN ACTIVITY list", userList.toString());

        UserListAdapter adapter = new UserListAdapter(userList, this);

        ListView listview = (ListView) findViewById(R.id.userListView);
        listview.setAdapter(adapter);

    }
}
