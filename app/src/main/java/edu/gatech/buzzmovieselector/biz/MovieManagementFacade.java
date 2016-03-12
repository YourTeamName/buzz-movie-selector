package edu.gatech.buzzmovieselector.biz;

import edu.gatech.buzzmovieselector.entity.Movie;
import edu.gatech.buzzmovieselector.entity.Review;

import java.util.Collection;
import java.util.List;

/**
 * Movie manager that retrieves info on movies
 */
public interface MovieManagementFacade {
    void addMovie(Movie m);

    /**
     * Finds the movie corresponding to the title passed in
     * @param id The name of the movie being searched
     * @return The movie object associated with the title
     */
    Movie findMovieById(String id);

    /**
     * Finds if a movie exists or not
     * @param movieTitle The title of the movie to search for
     * @return True if the movie is found
     */
    boolean movieExists(String movieTitle);

    /**
     * Updates a movie object with another that contains new information
     * @param id The name of the movie
     * @param m The new movie with updated reviews, ratings, etc.
     */
    void updateMovie(String id, Movie m);

    /**
     * Returns the total collection of movies in the map
     * @return The collection of movies
     */
    Collection<Movie> getMovies();

    /**
     * Returns a collection of movies that have a rating by major of >= 4.0
     * @param major The major to filter ratings by
     * @return The collection of movies that meet the rating threshold
     */
    Collection<Movie> getRecommendationsByMajor(String major);

    /**
     * Returns a total list of reviews for a movie
     * @param m The movie for which to get the reviews
     * @return The list of reviews for the movie
     */
    List<Review> getReviews(Movie m);

    /**
     * Adds a review to a movie
     * @param r The review to add to a movie
     */
    void addReview(Review r);
}
