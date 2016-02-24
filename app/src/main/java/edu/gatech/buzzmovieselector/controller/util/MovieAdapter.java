package edu.gatech.buzzmovieselector.controller.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import edu.gatech.buzzmovieselector.R;
import edu.gatech.buzzmovieselector.biz.api.ApiCall;
import edu.gatech.buzzmovieselector.biz.api.ApiCallback;
import edu.gatech.buzzmovieselector.biz.api.ApiCommand;
import edu.gatech.buzzmovieselector.biz.api.ApiReceiver;
import edu.gatech.buzzmovieselector.biz.api.impl.general.command.GeneralCommandFactory;
import edu.gatech.buzzmovieselector.biz.api.impl.rt.RTInvoker;
import edu.gatech.buzzmovieselector.entity.Movie;
import edu.gatech.buzzmovieselector.service.ApiNetwork;

import java.util.List;

/**
 * Movie Adapter allows Movie POJOs to be displayed in ListViews
 */
public class MovieAdapter extends BaseAdapter {

    private List<Movie> movies;
    private Activity hostActivity;
    private final RTInvoker rti = new RTInvoker();

    private static LayoutInflater inflater = null;

    public MovieAdapter(Activity hostActivity, List<Movie> movies) {
        this.movies = movies;
        this.hostActivity = hostActivity;
        inflater = (LayoutInflater) hostActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        View rowView = inflater.inflate(R.layout.movie_list, null);
        final ImageView movieThumbView = (ImageView) rowView.findViewById(R.id.movieImage);
        final TextView movieTitleView = (TextView) rowView.findViewById(R.id.movieTitleText);
        final Movie movie = movies.get(i);
        movieTitleView.setText(movie.getTitle() + " (" + movie.getYear() + ")");
        ApiCall imageCall = new ApiCall(GeneralCommandFactory.getImageCommand(movie.getImageURL()), new ApiCallback<ApiReceiver<Bitmap, Bitmap>>() {
            @Override
            public void onReceive(final ApiReceiver<Bitmap, Bitmap> receiver) {
                hostActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        movieThumbView.setImageBitmap(receiver.getEntity());
                    }
                });
            }
        });
        rti.executeCall(imageCall);
        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Log.v("movieadapter", "position " + i);
            }
        });
        return rowView;
    }
}
