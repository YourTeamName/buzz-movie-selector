package edu.gatech.buzzmovieselector.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import edu.gatech.buzzmovieselector.dao.impl.ReviewDaoImpl;

import java.io.Serializable;

/**
 * Class for a movie review that has a rating and text evaluation of the movie
 * Each movie review also contains the movie it pertains to
 */
@DatabaseTable(tableName = "review", daoClass = ReviewDaoImpl.class)
public class Review implements Serializable {

    private Integer id;
    @DatabaseField(foreign = true, foreignAutoCreate = true,
            foreignAutoRefresh = true)
    private User user;
    @DatabaseField
    private String content;
    @DatabaseField
    private Double rating;
    @DatabaseField(foreign = true, foreignAutoCreate = true,
            foreignAutoRefresh = true)
    private Movie movie;

    public Review() {
    }

    /**
     * Creates a review with the given information
     *
     * @param user    The user that wrote the review
     * @param content The string of the review itself
     * @param rating  The rating given to the movie
     * @param movie   The movie the rating is assigned to
     */
    public Review(User user, String content, Double rating, Movie movie) {
        this.setUser(user);
        this.setContent(content);
        this.setRating(rating);
        this.setMovie(movie);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

}