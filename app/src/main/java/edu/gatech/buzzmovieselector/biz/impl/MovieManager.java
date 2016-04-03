package edu.gatech.buzzmovieselector.biz.impl;

import edu.gatech.buzzmovieselector.biz.MovieManagementFacade;
import edu.gatech.buzzmovieselector.dao.DaoFactory;
import edu.gatech.buzzmovieselector.dao.MovieDao;
import edu.gatech.buzzmovieselector.entity.Movie;
import edu.gatech.buzzmovieselector.entity.Review;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Implementation of the MovieManagementFacade
 */
public class MovieManager implements MovieManagementFacade {
    public static final int FOUR_RATING = 4;
    /**
     * Default constructor for a movie manager
     */
    public MovieManager() {
    }

    /**
     * Adds a movie to the manager
     *
     * @param movie The movie to add
     */
    @Override
    public void addMovie(Movie movie) {
        final MovieDao movieDao = DaoFactory.getMovieDao();
        try {
            movieDao.createOrUpdate(movie);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Finds movie by its integer id
     *
     * @param id The name of the movie being searched
     * @return The movie
     */
    @Override
    public Movie findMovieById(Integer id) {
        Movie movie = null;
        try {
            movie = DaoFactory.getMovieDao().queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movie;
    }

    @Override
    public boolean movieExists(Integer id) {
        Movie movie = null;
        try {
            movie = DaoFactory.getMovieDao().queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movie != null;
    }

    @Override
    public void updateMovie(Movie movie) {
        try {
            final MovieDao movieDao = DaoFactory.getMovieDao();
            movieDao.createOrUpdate(movie);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Collection<Movie> getMovies() {
        List<Movie> movieList = null;
        try {
            final MovieDao movieDao = DaoFactory.getMovieDao();
            movieList = movieDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movieList;
    }

    @Override
    public Collection<Movie> getRecommendationsByMajor(String major) {
        final Collection<Movie> movieList = getMovies();
        final List<Movie> recommendedList = new ArrayList<Movie>();
        for (final Movie movie : movieList) {
            double totalPoints = 0.0;
            for (final Review review : movie.getReviews()) {
                if (major.equals(review.getUser().getProfile().getMajor())) {
                    totalPoints += review.getRating();
                }
            }
            final double average = totalPoints / movie.getReviews().size();
            if (average >= FOUR_RATING) {
                recommendedList.add(movie);
            }
        }
        return recommendedList;
    }
}
