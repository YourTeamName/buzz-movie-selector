package edu.gatech.buzzmovieselector.controller.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ListView;
import android.widget.SearchView;
import edu.gatech.buzzmovieselector.R;

public class MovieSearchFragment extends Fragment {

    private ListView movieResults;
    private SearchView searchBar;

    public MovieSearchFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        movieResults = (ListView) getView().findViewById(R.id.movieSearchList);
        searchBar = (SearchView) getView().findViewById(R.id.searchView);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movie_search, container, false);
    }

    @Override
    public void onAttach(Context context) {
        Activity hostActivity = getActivity();
    }
}