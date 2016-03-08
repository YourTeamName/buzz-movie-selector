package edu.gatech.buzzmovieselector.controller.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;

import edu.gatech.buzzmovieselector.R;
import edu.gatech.buzzmovieselector.biz.MovieManagementFacade;
import edu.gatech.buzzmovieselector.biz.impl.MovieManager;
import edu.gatech.buzzmovieselector.controller.util.MovieAdapter;
import edu.gatech.buzzmovieselector.entity.Movie;
import edu.gatech.buzzmovieselector.entity.Profile;
import edu.gatech.buzzmovieselector.entity.Review;
import edu.gatech.buzzmovieselector.service.SessionState;

public class RecommendedMoviesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommended_movies);
        ListView recommendations = (ListView) findViewById(R.id.recommendedMovieList);
        final ArrayList<Movie> mList = new ArrayList<>();
        final MovieAdapter movAdapter = new MovieAdapter(this, mList);
        recommendations.setAdapter(movAdapter);
        MovieManagementFacade manager = new MovieManager();
        Collection<Movie> movies = manager.getMovies();

        Profile currentUserProfile = SessionState.getInstance().getSessionUser().getProfile();
        if (currentUserProfile != null && currentUserProfile.getMajor() != null) {
            for (Movie m : movies) {
                double totalPoints = 0.0;
                for (Review r : m.getReviews()) {
                    if (currentUserProfile.getMajor().equals(
                            r.getUser().getProfile().getMajor())) {
                        totalPoints += r.getRating();
                    }
                }
                double average = totalPoints / m.getReviews().size();
                if (average >= 4.0) {
                    mList.add(m);
                }
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
