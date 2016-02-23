package edu.gatech.buzzmovieselector.controller.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import edu.gatech.buzzmovieselector.R;
import edu.gatech.buzzmovieselector.biz.api.ApiCall;
import edu.gatech.buzzmovieselector.biz.api.ApiCallback;
import edu.gatech.buzzmovieselector.biz.api.impl.rt.RTInvoker;
import edu.gatech.buzzmovieselector.biz.api.impl.rt.command.RTCommandFactory;
import edu.gatech.buzzmovieselector.biz.api.impl.rt.receiver
        .RTMovieListReceiver;
import edu.gatech.buzzmovieselector.controller.util.MovieAdapter;
import edu.gatech.buzzmovieselector.entity.Movie;
import edu.gatech.buzzmovieselector.service.SessionState;

import java.util.ArrayList;

/**
 * BMSActivity is the main screen for the app. It is only displayed to users
 * that are logged in.
 */
public class BMSActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bms);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id
                .fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar
                        .LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        searchView = (SearchView) findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                startResultsActivity(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R
                .string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id
                .nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        if (SessionState.getInstance().isLoggedIn()) {
            Log.v("BMS", "we are logged in");
        } else {
            Log.v("BMS", "we are not logged in");
            // we should not be here unless we are logged in
            finish();
        }

        /*
        // for test purposes only
        final ArrayList<String> dvdList = new ArrayList<>();
        // Make a custom list adapter for list view
        final ArrayAdapter<String> listAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, dvdList);
        ListView recentDVDs = (ListView) findViewById(R.id.recentDVDsList);
        recentDVDs.setAdapter(listAdapter);

        // Call RT api to retrieve recent movies
        RTInvoker rti = new RTInvoker();
        rti.executeCall(new ApiCall(RTCommandFactory.getRecentMoviesCommand()
                , new ApiCallback<RTMovieListReceiver>() {
            @Override
            public void onReceive(RTMovieListReceiver receiver) {
                for (Movie m : receiver.getEntity()) {
                    dvdList.add(m.toString());
                }
                runOnUiThread(new Runnable() {
                    public void run() {
                        listAdapter.notifyDataSetChanged();
                    }
                });
            }
        }));
        */
        final ArrayList<Movie> mList = new ArrayList<>();
        ListView recentDVDs = (ListView) findViewById(R.id.recentDVDsList);
        final MovieAdapter movAdapter = new MovieAdapter(this, mList);
        recentDVDs.setAdapter(movAdapter);
        RTInvoker rti = new RTInvoker();
        rti.executeCall(new ApiCall(RTCommandFactory.getRecentMoviesCommand()
                , new ApiCallback<RTMovieListReceiver>() {
            @Override
            public void onReceive(RTMovieListReceiver receiver) {
                for (Movie m : receiver.getEntity()) {
                    mList.add(m);
                }
                runOnUiThread(new Runnable() {
                    public void run() {
                        movAdapter.notifyDataSetChanged();
                    }
                });
            }
        }));
    }

    /**
     * Helper method to be called in onCreate
     * function that starts the search results screen
     */
    private void startResultsActivity(String keyword) {
        Intent intent = new Intent(this, SearchResultsActivity.class);
        intent.putExtra(SearchResultsActivity.SEARCH_KEYWORD, keyword);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.bm, menu);
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

    /**
     * Starts the profile viewer activity
     */
    private void startProfileActivity() {
        Intent profileIntent = new Intent(this, ProfileActivity.class);
        String profileUser = SessionState.getInstance().getSessionUser()
                .getUsername();
        profileIntent.putExtra(ProfileActivity.PROFILE_USER_KEY, profileUser);
        startActivity(profileIntent);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        } else if (id == R.id.nav_profile) {
            Log.v("BMS", "View ProfileActivity");
            startProfileActivity();
        } else if (id == R.id.nav_logout) {
            SessionState.getInstance().endSession(getApplicationContext());
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
