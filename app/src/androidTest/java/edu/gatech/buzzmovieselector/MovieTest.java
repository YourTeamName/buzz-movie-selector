package edu.gatech.buzzmovieselector;

import edu.gatech.buzzmovieselector.entity.Movie;
import edu.gatech.buzzmovieselector.entity.Review;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Nikhil on 4/5/2016.
 */
public class MovieTest {
    @Test
    public void checkSingleReview() throws Exception {
        Movie testMovie = new Movie("test", 2016, 0);
        Review testReview = new Review(null, "", 4.5, testMovie);
        testMovie.addReview(testReview);
        assertEquals(4.5, testMovie.loadRating(), 0.0);
    }

    @Test
    public void checkNoReview() throws Exception {
        Movie testMovie = new Movie("test", 2016, 0.0);
        assertEquals(0.0, testMovie.loadRating(), 0.0);
    }

    @Test
    public void checkMultipleReviews() throws Exception {
        Movie testMovie = new Movie("test", 2016, 0);
        Review testReview = new Review(null, "", 4.5, testMovie);
        testMovie.addReview(testReview);
        Review testReviewTwo = new Review(null, "", 2.5, testMovie);
        testMovie.addReview(testReviewTwo);
        Review testReviewThree = new Review(null, "", 3.5, testMovie);
        testMovie.addReview(testReviewThree);
        assertEquals(3.5, testMovie.loadRating(), 0.1);
    }
}