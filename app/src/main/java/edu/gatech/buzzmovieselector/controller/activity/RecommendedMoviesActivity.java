package edu.gatech.buzzmovieselector.controller.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;
import edu.gatech.buzzmovieselector.R;
import edu.gatech.buzzmovieselector.biz.MovieManagementFacade;
import edu.gatech.buzzmovieselector.biz.impl.MovieManager;
import edu.gatech.buzzmovieselector.controller.util.MovieAdapter;
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
        ListView recommendations = (ListView) findViewById(R.id.recommendedMovieList);
        final ArrayList<Movie> adapterList = new ArrayList<>();
        final MovieAdapter movAdapter = new MovieAdapter(this, adapterList);
        recommendations.setAdapter(movAdapter);
        MovieManagementFacade manager = new MovieManager();

        Profile currentUserProfile = SessionState.getInstance().getSessionUser().getProfile();
        if (currentUserProfile != null && currentUserProfile.getMajor() != null) {
            List<Movie> mList = (List<Movie>) manager.getRecommendationsByMajor(
                        SessionState.getInstance().getSessionUser().getProfile().getMajor());
            for (Movie movie : mList) {
                adapterList.add(movie);
            }
            movAdapter.notifyDataSetChanged();
        } else {
            Context context = getApplicationContext();
            CharSequence text = "You haven't entered a major!";
            int duration = Toast.LENGTH_LONG;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }
}
