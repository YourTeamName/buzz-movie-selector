package edu.gatech.buzzmovieselector.biz;

import com.j256.ormlite.stmt.query.In;
import edu.gatech.buzzmovieselector.entity.Movie;
import edu.gatech.buzzmovieselector.entity.Review;

import java.util.Collection;
import java.util.List;

/**
 * Movie manager that retrieves info on movies
 */
public interface MovieManagementFacade {
    void addMovie(Movie m);

    Movie findMovieById(Integer id);

    boolean movieExists(Integer id);

    void updateMovie(Movie m);

    Collection<Movie> getMovies();

    Collection<Movie> getRecommendationsByMajor(String major);

    List<Review> getReviews(Movie m);

    void addReview(Review r);
}
