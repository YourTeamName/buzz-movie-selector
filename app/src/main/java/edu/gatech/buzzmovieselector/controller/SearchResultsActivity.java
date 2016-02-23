package edu.gatech.buzzmovieselector.controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


import android.widget.ArrayAdapter;
import android.widget.ListView;
import edu.gatech.buzzmovieselector.R;
import edu.gatech.buzzmovieselector.biz.api.ApiCall;
import edu.gatech.buzzmovieselector.biz.api.ApiCallback;
import edu.gatech.buzzmovieselector.biz.api.impl.rt.RTInvoker;
import edu.gatech.buzzmovieselector.biz.api.impl.rt.command.RTCommandFactory;
import edu.gatech.buzzmovieselector.biz.api.impl.rt.receiver.RTMovieListReceiver;
import edu.gatech.buzzmovieselector.entity.Movie;

import java.util.ArrayList;
import android.util.Log;

public class SearchResultsActivity extends AppCompatActivity {
    public static final String CURRENT_QUERY = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        //Log.d("MY", "search activity triggered");
        showResults();
    }

    private void showResults() {
        final ArrayList<String> dvdList = new ArrayList<>();
        // TODO: make a custom list adapter to work with POJOs
        ArrayAdapter<String> listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dvdList);
        ListView matchingDVDs = (ListView) findViewById(R.id.searchResults);
        matchingDVDs.setAdapter(listAdapter);
        RTInvoker rti = new RTInvoker();
        String bundledSearchQuery = getIntent().getExtras().getString(CURRENT_QUERY, null);
        rti.executeCall(new ApiCall(RTCommandFactory.getMovieSearchCommand(bundledSearchQuery), new ApiCallback<RTMovieListReceiver>() {
            @Override
            public void onReceive(RTMovieListReceiver receiver) {
                for (Movie m : receiver.getEntity()) {
                    dvdList.add(m.toString());
                }
            }
        }));

        for (String sd : dvdList) {
            Log.v("BMSActivity", sd);
        }
    }
}
