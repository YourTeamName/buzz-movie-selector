package edu.gatech.buzzmovieselector.controller.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SearchView;
import edu.gatech.buzzmovieselector.R;
import edu.gatech.buzzmovieselector.biz.api.ApiCall;
import edu.gatech.buzzmovieselector.biz.api.ApiCallback;
import edu.gatech.buzzmovieselector.biz.api.impl.rt.RTInvoker;
import edu.gatech.buzzmovieselector.biz.api.impl.rt.command.RTCommandFactory;
import edu.gatech.buzzmovieselector.biz.api.impl.rt.receiver
        .RTMovieListReceiver;
import edu.gatech.buzzmovieselector.controller.util.MovieListAdapter;
import edu.gatech.buzzmovieselector.entity.Movie;

import java.util.ArrayList;
import java.util.Collections;

public class MovieSearchFragment extends Fragment {

    private ListView movieResults;
    private Activity hostActivity;

    private String searchQuery = "";

    public MovieSearchFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
        View fragView = inflater.inflate(R.layout.fragment_movie_search,
            container, false);
        movieResults = (ListView) fragView.findViewById(R.id.movieSearchList);

        SearchView searchBar = (SearchView) fragView.findViewById(R.id
            .searchView);
        searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                searchQuery = s;
                refreshResults();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        return fragView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        hostActivity = getActivity();
    }

    /**
     * Refreshes the search results that are contained in the listview
     */
    private void refreshResults() {
        final ArrayList<Movie> mList = new ArrayList<>();
        final MovieListAdapter movAdapter = new MovieListAdapter(hostActivity,
            mList);
        movieResults.setAdapter(movAdapter);
        final RTInvoker rti = new RTInvoker();
        rti.executeCall(new ApiCall(RTCommandFactory
            .getMovieSearchCommand(searchQuery),
            new ApiCallback<RTMovieListReceiver>() {
                @Override
                public void onReceive(RTMovieListReceiver receiver) {
                    Collections.addAll(mList, receiver.getEntity());
                    if (hostActivity == null) {
                        return;
                    }
                    hostActivity.runOnUiThread(new Runnable() {
                        public void run() {
                            movAdapter.notifyDataSetChanged();
                        }
                    });
                }
            }));
    }
}