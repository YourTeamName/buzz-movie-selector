package edu.gatech.buzzmovieselector.controller.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
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

public class RecentDVDsFragment extends Fragment {

    private Activity hostActivity;

    public RecentDVDsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_recent_dvds, container,
            false);
        final ListView movieList = (ListView) v.findViewById(R.id.recentDVDListView);
        final ArrayList<Movie> mList = new ArrayList<>();
        final MovieListAdapter movAdapter = new MovieListAdapter(hostActivity,
            mList);
        movieList.setAdapter(movAdapter);
        final RTInvoker rti = new RTInvoker();
        rti.executeCall(new ApiCall(RTCommandFactory.getRecentDVDsCommand(),
            new ApiCallback<RTMovieListReceiver>() {
                @Override
                public void onReceive(RTMovieListReceiver receiver) {
                    Collections.addAll(mList, receiver.getEntity());
                    if (hostActivity == null) {
                        return;
                    }
                    hostActivity.runOnUiThread(new Runnable() {
                        @Override
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
        Log.v("host activity", hostActivity == null ? "null" : "not null");
    }
}