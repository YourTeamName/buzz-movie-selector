package edu.gatech.buzzmovieselector.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import edu.gatech.buzzmovieselector.R;
import edu.gatech.buzzmovieselector.biz.api.ApiCall;
import edu.gatech.buzzmovieselector.biz.api.ApiCallback;
import edu.gatech.buzzmovieselector.biz.api.impl.rt.RTInvoker;
import edu.gatech.buzzmovieselector.biz.api.impl.rt.command.RTCommandFactory;
import edu.gatech.buzzmovieselector.biz.api.impl.rt.receiver
        .RTMovieListReceiver;
import edu.gatech.buzzmovieselector.entity.Movie;

import java.util.ArrayList;

/**
 * Search results activity that displays the results of a search
 * Intent from BMSActivity contains the search text
 */
public class SearchResultsActivity extends AppCompatActivity {

    public static final String SEARCH_KEYWORD = "searchKeyword";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        displayResults();
    }

    /**
     * Displays the results of the search done from BMSActivity
     */
    private void displayResults() {
        final ArrayList<String> dvdList = new ArrayList<>();

        // Create customized adapter for list view
        final ArrayAdapter<String> listAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, dvdList);
        ListView recentDVDs = (ListView) findViewById(R.id.searchResults);
        recentDVDs.setAdapter(listAdapter);

        // Call RT api to search for movies
        RTInvoker rti = new RTInvoker();
        Intent intent = getIntent();
        // Get search query
        String search = intent.getStringExtra(SEARCH_KEYWORD);
        // Excute the command and refresh list view
        rti.executeCall(new ApiCall(RTCommandFactory.getMovieSearchCommand
                (search), new ApiCallback<RTMovieListReceiver>() {
            @Override
            public void onReceive(RTMovieListReceiver receiver) {
                for (Movie m : receiver.getEntity()) {
                    dvdList.add(m.toString());
                }
                // NotifyDataSetChanged() must be called on the UI thread
                runOnUiThread(new Runnable() {
                    public void run() {
                        listAdapter.notifyDataSetChanged();
                    }
                });
            }
        }));
    }

}
