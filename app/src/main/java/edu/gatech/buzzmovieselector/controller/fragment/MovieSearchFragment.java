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

public class MovieSearchFragment extends Fragment {

    private ListView movieResults;
    private SearchView searchBar;
    private Activity hostActivity;

    private String searchQuery = "";

    private View fragView;

    public MovieSearchFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragView = inflater.inflate(R.layout.fragment_movie_search,
                container, false);
        movieResults = (ListView) fragView.findViewById(R.id.movieSearchList);

        searchBar = (SearchView) fragView.findViewById(R.id.searchView);
        searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                searchQuery = s;
                refreshResults(fragView);
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
     *
     * @param v View associated with the fragment
     */
    private void refreshResults(View v) {
        final ArrayList<Movie> mList = new ArrayList<>();
        final MovieListAdapter movAdapter = new MovieListAdapter(hostActivity, mList);
        movieResults.setAdapter(movAdapter);
        RTInvoker rti = new RTInvoker();
        rti.executeCall(new ApiCall(RTCommandFactory.getMovieSearchCommand
                (searchQuery)
                , new ApiCallback<RTMovieListReceiver>() {
            @Override
            public void onReceive(RTMovieListReceiver receiver) {
                for (Movie m : receiver.getEntity()) {
                    mList.add(m);
                }
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