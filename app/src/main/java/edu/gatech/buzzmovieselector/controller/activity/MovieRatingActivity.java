package edu.gatech.buzzmovieselector.controller.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import edu.gatech.buzzmovieselector.R;
import edu.gatech.buzzmovieselector.biz.MovieManagementFacade;
import edu.gatech.buzzmovieselector.biz.impl.MovieManager;
import edu.gatech.buzzmovieselector.entity.Movie;
import edu.gatech.buzzmovieselector.entity.Review;
import edu.gatech.buzzmovieselector.service.SessionState;

import java.util.Collection;

public class MovieRatingActivity extends AppCompatActivity {

    public static final String CURRENT_MOVIE = "currentMovie";

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
        reviewMovie = (Movie) getIntent().getSerializableExtra(CURRENT_MOVIE);
        mm = new MovieManager();
        if (!mm.movieExists(reviewMovie.getId())) {
            Log.v("MovieRating", "Movie with that id doesn't exist");
            mm.addMovie(reviewMovie);
        }
        reviewMovie = mm.findMovieById(reviewMovie.getId());
        titleText.setText(reviewMovie.getTitle() + " (" + reviewMovie.getYear
                () + ")");
        reviewText = (TextView) findViewById(R.id.textReview);
        userRating = (RatingBar) findViewById(R.id.userRating);
        averageRating = (RatingBar) findViewById(R.id.averageRating);
        contentText = (EditText) findViewById(R.id.textReview);
        averageRating.setFocusable(false);
        loadRating();
    }

    private void loadRating() {
        final Collection<Review> movieReviews = reviewMovie.getReviews();
        if (movieReviews.size() == 0) {
            return;
        }
        double totalRating = 0;
        for (final Review r : movieReviews) {
            totalRating += r.getRating();
        }
        final double avgRating = totalRating / movieReviews.size();
        averageRating.setRating((float) avgRating);
    }

    private void submitReview() {
        final String reviewContent = contentText.getText().toString();
        final double usrRating = (double) userRating.getRating();
        final Review userReview = new Review(SessionState.getInstance()
                .getSessionUser(), reviewContent, usrRating, reviewMovie);
        reviewMovie.addReview(userReview);
        final MovieManagementFacade mm = new MovieManager();
        mm.updateMovie(reviewMovie);
        finish();
    }

    public void submitClick(View v) {
        submitReview();
    }

}
