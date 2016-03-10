package edu.gatech.buzzmovieselector.biz;

import edu.gatech.buzzmovieselector.entity.Movie;

/**
 * Movie manager that retrieves info on movies
 */
public interface MovieManagementFacade {
    void addMovie(Movie m);

    Movie findMovieById(String id);

    boolean movieExists(String movieTitle);

    void updateMovie(String id, Movie m);
}
