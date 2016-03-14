package edu.gatech.buzzmovieselector.biz.impl;

import edu.gatech.buzzmovieselector.biz.MovieManagementFacade;
import edu.gatech.buzzmovieselector.dao.DaoFactory;
import edu.gatech.buzzmovieselector.dao.MovieDao;
import edu.gatech.buzzmovieselector.entity.Movie;
import edu.gatech.buzzmovieselector.entity.Review;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Implementation of the MovieManagementFacade
 */
public class MovieManager implements MovieManagementFacade {

    public MovieManager() {
    }

    @Override
    public void addMovie(Movie movie) {
        try {
            MovieDao movieDao = DaoFactory.getMovieDao();
            movieDao.createOrUpdate(movie);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Movie findMovieById(Integer id) {
        Movie movie = null;
        try {
            movie = DaoFactory.getMovieDao().queryForId(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return movie;
    }

    @Override
    public boolean movieExists(Integer id) {
        Movie movie = null;
        try {
            movie = DaoFactory.getMovieDao().queryForId(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return movie != null;
    }

    @Override
    public void updateMovie(Movie movie) {
        try {
            MovieDao movieDao = DaoFactory.getMovieDao();
            movieDao.createOrUpdate(movie);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Review> getReviews(Movie m) {
        // TODO: to be implemented
        return null;
    }

    @Override
    public void addReview(Review r) {
        // TODO: to be implemented
    }

    @Override
    public Collection<Movie> getMovies() {
        List<Movie> movieList = null;
        try {
            MovieDao movieDao = DaoFactory.getMovieDao();
            movieList = movieDao.queryForAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return movieList;
    }

    @Override
    public Collection<Movie> getRecommendationsByMajor(String major) {
        Collection<Movie> movieList = getMovies();
        List<Movie> recommendedList = new ArrayList<Movie>();
        for (Movie movie : movieList) {
            double totalPoints = 0.0;
            for (Review r : movie.getReviews()) {
                if (major.equals(r.getUser().getProfile().getMajor())) {
                    totalPoints += r.getRating();
                }
            }
            double average = totalPoints / movie.getReviews().size();
            if (average >= 4.0) {
                recommendedList.add(movie);
            }
        }
        return recommendedList;
    }
}
