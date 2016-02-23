package edu.gatech.buzzmovieselector.controller;

import edu.gatech.buzzmovieselector.biz.api.impl.rt.receiver.RTMovieListReceiver;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import edu.gatech.buzzmovieselector.R;
import edu.gatech.buzzmovieselector.biz.api.ApiCall;
import edu.gatech.buzzmovieselector.biz.api.ApiCallback;
import edu.gatech.buzzmovieselector.biz.api.impl.rt.RTInvoker;
import edu.gatech.buzzmovieselector.biz.api.impl.rt.command.RTCommandFactory;
import edu.gatech.buzzmovieselector.entity.Movie;

/**
 * Search results activity that displays the results of a search
 * Intent from BMSActivity contains the search text
 */
public class SearchResultsActivity extends AppCompatActivity {

    private static Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handler = new Handler();
        setContentView(R.layout.activity_search_results);
        displayResults();
        //Log.d("MY", "search activity triggered");
    }

    /**
     * Displays the results of the search done from BMSActivity
     */
    private void displayResults() {
        final ArrayList<String> dvdList = new ArrayList<>();
        // TODO: make a custom list adapter to work with POJOs
        final ArrayAdapter<String> listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dvdList);
        final ListView recentDVDs = (ListView) findViewById(R.id.searchResults);
        recentDVDs.setAdapter(listAdapter);
        RTInvoker rti = new RTInvoker();
        Intent intent = getIntent();
        String search = intent.getStringExtra("search"); // Get search query
        rti.executeCall(new ApiCall(RTCommandFactory.getMovieSearchCommand(search), new ApiCallback<RTMovieListReceiver>() {
            @Override
            public void onReceive(RTMovieListReceiver receiver) {
                for (Movie m : receiver.getEntity()) {
                    dvdList.add(m.toString());
                }
                SearchResultsActivity.getHandler().post(new Runnable() {
                    public void run() {
                        listAdapter.notifyDataSetChanged();
                    }
                });
            }
        }));

        for (String sd : dvdList) {
            Log.v("BMSActivity", sd);
        }
    }

    /**
     * Accessor method for the activity's handler
     * @return The handler for the activity on which to execute methods
     */
    private static Handler getHandler() {
        return handler;
    }

}
