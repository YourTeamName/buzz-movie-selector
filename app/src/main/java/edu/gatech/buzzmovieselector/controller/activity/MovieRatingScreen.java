package edu.gatech.buzzmovieselector.controller.activity;

import android.media.Rating;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.os.Bundle;
import android.widget.RatingBar;
import android.view.View.OnTouchListener;

import edu.gatech.buzzmovieselector.R;

public class MovieRatingScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_rating_screen); //title of movie.
        TextView title = (TextView) findViewById(R.id.textView2);
        title.setText("Title");

        TextView synopsis = (TextView) findViewById(R.id.textReview);
        synopsis.setText("Here we insert the synopsis of the film we clicked on.");

        RatingBar userRating = (RatingBar) findViewById(R.id.ratingBar);

        RatingBar avgRating = (RatingBar) findViewById(R.id.ratingBar2);
        avgRating.setFocusable(false);
        avgRating.setRating(5.0f);


    }
}
