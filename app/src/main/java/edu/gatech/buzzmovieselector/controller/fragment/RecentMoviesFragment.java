package edu.gatech.buzzmovieselector.controller.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import edu.gatech.buzzmovieselector.R;
import edu.gatech.buzzmovieselector.biz.api.ApiCall;
import edu.gatech.buzzmovieselector.biz.api.ApiCallback;
import edu.gatech.buzzmovieselector.biz.api.impl.rt.RTInvoker;
import edu.gatech.buzzmovieselector.biz.api.impl.rt.command.RTCommandFactory;
import edu.gatech.buzzmovieselector.biz.api.impl.rt.receiver.RTMovieListReceiver;
import edu.gatech.buzzmovieselector.controller.util.MovieListAdapter;
import edu.gatech.buzzmovieselector.entity.Movie;

import java.util.ArrayList;

public class RecentMoviesFragment extends Fragment {

    private ListView movieList;
    private Activity hostActivity;

    public RecentMoviesFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_recent_movies, container,
                false);
        movieList = (ListView) v.findViewById(R.id.recentMovieListView);
        final ArrayList<Movie> mList = new ArrayList<>();
        final MovieListAdapter movAdapter = new MovieListAdapter
                (hostActivity, mList);
        movieList.setAdapter(movAdapter);
        RTInvoker rti = new RTInvoker();
        rti.executeCall(new ApiCall(RTCommandFactory.getRecentMoviesCommand()
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
        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        hostActivity = getActivity();
    }
}