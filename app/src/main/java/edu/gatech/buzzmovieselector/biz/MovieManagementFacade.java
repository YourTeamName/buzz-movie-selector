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

    Movie findMovieById(String id);

    boolean movieExists(String movieTitle);

    void updateMovie(String id, Movie m);

    Collection<Movie> getMovies();

    Collection<Movie> getRecommendationsByMajor(String major);

    List<Review> getReviews(Movie m);

    void addReview(Review r);
}
