package edu.gatech.buzzmovieselector.controller.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import edu.gatech.buzzmovieselector.R;
import edu.gatech.buzzmovieselector.biz.api.ApiCall;
import edu.gatech.buzzmovieselector.biz.api.ApiCallback;
import edu.gatech.buzzmovieselector.biz.api.impl.general.command
    .GeneralCommandFactory;
import edu.gatech.buzzmovieselector.biz.api.impl.general.receiver.ImageReceiver;
import edu.gatech.buzzmovieselector.biz.api.impl.rt.RTInvoker;
import edu.gatech.buzzmovieselector.controller.activity.MovieRatingActivity;
import edu.gatech.buzzmovieselector.entity.Movie;

import java.util.List;

/**
 * Movie List Adapter allows Movie POJOs to be displayed in ListViews
 */
public class MovieListAdapter extends BaseAdapter {

    private static LayoutInflater inflater = null;
    private final RTInvoker rti = new RTInvoker();
    private List<Movie> movies;
    private Activity hostActivity;

    /**
     * Constructs a new movie adapter to display movies in a list
     *
     * @param hostActivity The activity to host the adapter
     * @param movies       The list of movies to go into the adapter
     */
    public MovieListAdapter(Activity hostActivity, List<Movie> movies) {
        this.movies = movies;
        this.hostActivity = hostActivity;
        inflater = (LayoutInflater) hostActivity.getSystemService(Context
            .LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return movies.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        View newView;
        if (view == null) {
            newView = inflater.inflate(R.layout.movie_list_item, null);
        } else {
            newView = view;
        }
        final ImageView movieThumbView = (ImageView) newView.findViewById(R
            .id.movieImage);
        final TextView movieTitleView = (TextView) newView.findViewById(R.id
            .movieTitleText);
        final Movie movie = movies.get(i);
        movieTitleView.setText(movie.getTitle() + " (" + movie.getYear() + ")");
        final ApiCall imageCall = new ApiCall(GeneralCommandFactory
            .getImageCommand(movie.getImageURL()),
            new ApiCallback<ImageReceiver>() {
                @Override
                public void onReceive(final ImageReceiver receiver) {
                    hostActivity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            movieThumbView.setImageBitmap(receiver
                                .getEntity());
                        }
                    });
                }
            });
        rti.executeCall(imageCall);
        newView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent i = new Intent(hostActivity, MovieRatingActivity
                    .class);
                i.putExtra(MovieRatingActivity.CURRENT_MOVIE, movie);
                hostActivity.startActivity(i);
            }
        });
        return newView;
    }
}
