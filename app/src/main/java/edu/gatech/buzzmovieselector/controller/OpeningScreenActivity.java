package edu.gatech.cs2340.basicandroidapp.controllers;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import edu.gatech.cs2340.basicandroidapp.R;
import edu.gatech.cs2340.basicandroidapp.controllers.LoginActivity;
import edu.gatech.cs2340.basicandroidapp.model.UserManagementFacade;
import edu.gatech.cs2340.basicandroidapp.model.UserManager;

public class OpeningScreenActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opening_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        UserManagementFacade um = new UserManager();
        um.addUser("test", "pass");
        um.addUser("sally", "sally");

    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("**MYAPP**", "Pausing the opening screen");
    }

    public void onResume() {
        super.onResume();
        Log.d("**MYAPP**", "Resuming the opening screen");
        System.out.println("resuming");
    }

    public void onLoginButtonClicked(View w) {
        Log.d("**MYAPP**", "Login button pressed!");
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_opening_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onStop() {
        super.onStop();

    }

    public void onRegisterButtonPress(View v ) {
        Log.d("Myapp", "Pressed REgisrter");
    }
}
