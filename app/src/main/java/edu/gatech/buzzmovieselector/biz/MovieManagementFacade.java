package edu.gatech.buzzmovieselector.biz;

import edu.gatech.buzzmovieselector.entity.Movie;

import java.util.Collection;

/**
 * Movie manager that retrieves info on movies
 */
public interface MovieManagementFacade {
    /**
     * Adds a movie to the manager
     *
     * @param movie the movie to add
     */
    void addMovie(Movie movie);

    /**
     * Finds the movie corresponding to the title passed in
     *
     * @param id The name of the movie being searched
     * @return The movie object associated with the title
     */
    Movie findMovieById(Integer id);

    /**
     * Finds if a movie exists or not
     *
     * @param movieTitle The title of the movie to search for
     * @return True if the movie is found
     */
    boolean movieExists(Integer movieTitle);

    /**
     * Updates a movie object with another that contains new information
     *
     * @param m The new movie with updated reviews, ratings, etc.
     */
    void updateMovie(Movie m);

    /**
     * Returns the total collection of movies in the map
     *
     * @return The collection of movies
     */
    Collection<Movie> getMovies();

    /**
     * Returns a collection of movies that have a rating by major of >= 4.0
     *
     * @param major The major to filter ratings by
     * @return The collection of movies that meet the rating threshold
     */
    Collection<Movie> getRecommendationsByMajor(String major);
}
