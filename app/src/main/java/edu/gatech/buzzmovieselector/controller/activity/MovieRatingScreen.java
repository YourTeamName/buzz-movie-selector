package edu.gatech.buzzmovieselector.controller.activity;

import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.os.Bundle;
import android.widget.RatingBar;

import edu.gatech.buzzmovieselector.R;

public class MovieRatingScreen extends AppCompatActivity {

    public static final String MOVIE_TO_REVIEW = "movie";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_rating); //title of movie.
        TextView title = (TextView) findViewById(R.id.movieTitle);
        String movieTitle = getIntent().getStringExtra(MOVIE_TO_REVIEW);
        title.setText(movieTitle);

        TextView reviewText = (TextView) findViewById(R.id.textReview);
        reviewText.setText("Enter your review here");

        RatingBar userRating = (RatingBar) findViewById(R.id.ratingBar);

        RatingBar avgRating = (RatingBar) findViewById(R.id.ratingBar2);
        avgRating.setFocusable(false);
        avgRating.setRating(5.0f);
    }


}
