package edu.gatech.buzzmovieselector.biz;

import edu.gatech.buzzmovieselector.entity.Movie;
import edu.gatech.buzzmovieselector.entity.Review;

import java.util.Collection;
import java.util.List;

/**
 * Movie manager that retrieves info on movies
 */
public interface MovieManagementFacade {
    void addMovie(Movie movie);

    Movie findMovieById(Integer id);

    boolean movieExists(Integer id);

    void updateMovie(Movie movie);

    Collection<Movie> getMovies();

    Collection<Movie> getRecommendationsByMajor(String major);

    List<Review> getReviews(Movie movie);

    void addReview(Review review);
}
