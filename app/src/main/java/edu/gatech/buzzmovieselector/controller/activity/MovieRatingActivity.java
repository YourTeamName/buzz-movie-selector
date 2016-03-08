package edu.gatech.buzzmovieselector.controller.activity;

import android.content.pm.PackageInstaller;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.os.Bundle;
import android.widget.RatingBar;

import edu.gatech.buzzmovieselector.R;
import edu.gatech.buzzmovieselector.biz.MovieManagementFacade;
import edu.gatech.buzzmovieselector.biz.impl.MovieManager;
import edu.gatech.buzzmovieselector.entity.Movie;
import edu.gatech.buzzmovieselector.entity.Profile;
import edu.gatech.buzzmovieselector.entity.Review;
import edu.gatech.buzzmovieselector.service.SessionState;

import java.util.Collection;
import java.util.List;

public class MovieRatingActivity extends AppCompatActivity {

    public static final String MOVIE_TITLE = "movieTitle";
    public static final String MOVIE_YEAR = "movieYear";
    public static final String MOVIE_RATING = "movieRating";

    private TextView titleText;
    private TextView reviewText;

    private RatingBar averageRating;
    private RatingBar userRating;

    private EditText contentText;

    private MovieManagementFacade mm;

    private Movie reviewMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_rating); //title of movie.
        titleText = (TextView) findViewById(R.id.movieTitle);
        String movieTitle = getIntent().getStringExtra(MOVIE_TITLE);
        int movieYear = getIntent().getIntExtra(MOVIE_YEAR, 0);
        double movieRating = (double) getIntent().getFloatExtra(MOVIE_RATING, 0.F);
        mm = new MovieManager();
        if (!mm.movieExists(movieTitle)) {
            Log.v("MovieRating", "Movie with that id doesn't exist");
            Movie currentMovie = new Movie(movieTitle, movieYear, movieRating);
            mm.addMovie(currentMovie);
        }
        reviewMovie = mm.findMovieById(movieTitle);
        titleText.setText(reviewMovie.getTitle() + " (" + reviewMovie.getYear() + ")");
        reviewText = (TextView) findViewById(R.id.textReview);
        userRating = (RatingBar) findViewById(R.id.userRating);
        averageRating = (RatingBar) findViewById(R.id.averageRating);
        contentText = (EditText) findViewById(R.id.textReview);
        averageRating.setFocusable(false);
        loadRating();
    }

    private void loadRating() {
        Collection<Review> movieReviews = reviewMovie.getReviews();
        if (movieReviews.size() == 0) {
            return;
        }
        double totalRating = 0;
        for (Review r : movieReviews) {
            totalRating += r.getRating();
        }
        double avgRating = totalRating / movieReviews.size();
        averageRating.setRating((float) avgRating);
    }

    private void submitReview() {
        String reviewContent = contentText.getText().toString();
        double usrRating = (double) userRating.getRating();
        Review userReview = new Review(SessionState.getInstance().getSessionUser(), reviewContent, usrRating, reviewMovie);
        reviewMovie.addReview(userReview);
        finish();
    }

    public void submitClick(View v) {
        submitReview();
    }

}
