package edu.gatech.buzzmovieselector.controller;

import android.content.Intent;
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
import edu.gatech.buzzmovieselector.biz.api.impl.rt.receiver.RTMovieListReceiver;
import edu.gatech.buzzmovieselector.entity.Movie;

public class SearchResultsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        displayResults();
        //Log.d("MY", "search activity triggered");
    }

    protected void displayResults() {
        final ArrayList<String> dvdList = new ArrayList<>();
        // TODO: make a custom list adapter to work with POJOs
        RTInvoker rti = new RTInvoker();
        Intent intent = getIntent();
        String search = intent.getStringExtra("search");
        rti.executeCall(new ApiCall(RTCommandFactory.getMovieSearchCommand(search), new ApiCallback<RTMovieListReceiver>() {
            @Override
            public void onReceive(RTMovieListReceiver receiver) {
                for (Movie m : receiver.getEntity()) {
                    dvdList.add(m.toString());
                }
            }
        }));
        ArrayAdapter<String> listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dvdList);
        ListView recentDVDs = (ListView) findViewById(R.id.listView);
        recentDVDs.setAdapter(listAdapter);

        for (String sd : dvdList) {
            Log.v("BMSActivity", sd);
        }
    }
}
