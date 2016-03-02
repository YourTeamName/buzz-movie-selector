package edu.gatech.buzzmovieselector.controller.activity;

import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.os.Bundle;
import android.widget.RatingBar;

import edu.gatech.buzzmovieselector.R;

public class MovieRatingActivity extends AppCompatActivity {

    public static final String MOVIE_TO_REVIEW = "movie";

    private TextView titleText;
    private TextView reviewText;

    private RatingBar averageRating;
    private RatingBar userRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_rating); //title of movie.
        titleText = (TextView) findViewById(R.id.movieTitle);
        String movieTitle = getIntent().getStringExtra(MOVIE_TO_REVIEW);

        titleText.setText(movieTitle);

        reviewText = (TextView) findViewById(R.id.textReview);
        userRating = (RatingBar) findViewById(R.id.userRating);
        averageRating = (RatingBar) findViewById(R.id.averageRating);
        averageRating.setFocusable(false);
    }


}
