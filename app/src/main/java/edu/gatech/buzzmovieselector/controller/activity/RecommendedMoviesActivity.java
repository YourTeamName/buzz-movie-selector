package edu.gatech.buzzmovieselector.controller.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;
import edu.gatech.buzzmovieselector.R;
import edu.gatech.buzzmovieselector.biz.MovieManagementFacade;
import edu.gatech.buzzmovieselector.biz.impl.MovieManager;
import edu.gatech.buzzmovieselector.controller.util.MovieListAdapter;
import edu.gatech.buzzmovieselector.entity.Movie;
import edu.gatech.buzzmovieselector.entity.Profile;
import edu.gatech.buzzmovieselector.service.SessionState;

import java.util.ArrayList;
import java.util.List;

public class RecommendedMoviesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommended_movies);
        final ListView recommendations = (ListView) findViewById(R.id
            .recommendedMovieList);
        final ArrayList<Movie> adapterList = new ArrayList<>();
        final MovieListAdapter movAdapter = new MovieListAdapter(this,
            adapterList);
        recommendations.setAdapter(movAdapter);
        final MovieManagementFacade manager = new MovieManager();

        final Profile currentUserProfile = SessionState.getInstance()
            .getSessionUser().getProfile();
        if (currentUserProfile != null && currentUserProfile.getMajor() !=
            null) {
            final List<Movie> mList = (List<Movie>) manager.getRecommendationsByMajor(
                SessionState.getInstance().getSessionUser().getProfile()
                    .getMajor());
            for (final Movie movie : mList) {
                adapterList.add(movie);
            }
            movAdapter.notifyDataSetChanged();
        } else {
            final Context context = getApplicationContext();
            final CharSequence text = "You haven't entered a major!";
            final int duration = Toast.LENGTH_LONG;

            final Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }
}
