package edu.gatech.buzzmovieselector.controller.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import edu.gatech.buzzmovieselector.R;
import edu.gatech.buzzmovieselector.controller.fragment.MovieSearchFragment;
import edu.gatech.buzzmovieselector.controller.fragment.RecentDVDsFragment;
import edu.gatech.buzzmovieselector.controller.fragment.RecentMoviesFragment;
import edu.gatech.buzzmovieselector.controller.util.ViewPagerAdapter;
import edu.gatech.buzzmovieselector.service.SessionState;

/**
 * BMSActivity is the main screen for the app. It is only displayed to users
 * that are logged in.
 */
public class BMSActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private TabLayout tabLayout;
    private ViewPager viewPager;

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
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R
                .string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id
                .nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new RecentDVDsFragment(), "DVDs");
        adapter.addFragment(new RecentMoviesFragment(), "Movies");
        adapter.addFragment(new MovieSearchFragment(), "Search");
        viewPager.setAdapter(adapter);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        if (SessionState.getInstance().isLoggedIn()) {
            Log.v("BMS", "we are logged in");
        } else {
            Log.v("BMS", "we are not logged in");
            // we should not be here unless we are logged in
            finish();
        }
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
        } else if (id == R.id.nav_recommended_movies) {
            Intent i = new Intent(this, RecommendedMovies.class);
            startActivity(i);
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
