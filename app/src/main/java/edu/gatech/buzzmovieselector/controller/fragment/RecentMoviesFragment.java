package edu.gatech.buzzmovieselector.controller.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import edu.gatech.buzzmovieselector.R;

public class RecentMoviesFragment extends Fragment {

    private ListView movieList;

    public RecentMoviesFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        movieList = (ListView) getView().findViewById(R.id.recentMovieListView);
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