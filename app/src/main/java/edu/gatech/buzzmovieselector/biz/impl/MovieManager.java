package edu.gatech.buzzmovieselector.biz.impl;

import edu.gatech.buzzmovieselector.biz.MovieManagementFacade;
import edu.gatech.buzzmovieselector.entity.Movie;
import edu.gatech.buzzmovieselector.entity.Review;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementation of the MovieManagementFacade
 */
public class MovieManager implements MovieManagementFacade {

    private static Map<String, Movie> movies = null;
    private static List<Review> reviews = null;

    private void loadMovieInfo() {
        movies = new HashMap<>();
        reviews = new ArrayList<>();
    }

    public MovieManager() {
        if (movies == null) {
            loadMovieInfo();
        }
    }

    @Override
    public void addMovie(Movie m) {
        movies.put(m.getTitle(), m);
    }

    @Override
    public Movie findMovieById(String id) {
        return movies.get(id);
    }

    @Override
    public boolean movieExists(String movieTitle) {
        return movies.containsKey(movieTitle);
    }

    @Override
    public void updateMovie(String id, Movie m) {
        // TODO: Implement updateMovie
    }

    @Override
    public List<Review> getReviews(Movie m) {
        // TODO: make this more efficient
        List<Review> reviewList = new ArrayList<>();
        for (Review r : reviews) {
            if (r.getMovie().equals(m)) {
                reviewList.add(r);
            }
        }
        return reviewList;
    }

    @Override
    public void addReview(Review r) {
        Movie m = r.getMovie();
        if (!movieExists(m.getTitle())) {
            addMovie(m);
        }
        reviews.add(r);
    }
}
