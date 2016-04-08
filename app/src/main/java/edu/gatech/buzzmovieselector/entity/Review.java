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

    /**
     * Default no arg constructor
     */
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

    /**
     * Gets id
     * @return the user id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets id
     * @param id id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets user
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the user
     * @param user user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Gets the content of the review
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets the content of a review
     * @param content the review's content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Gets the review rating
     * @return the rating of the review
     */
    public Double getRating() {
        return rating;
    }

    /**
     * Sets the review's rating
     * @param rating the rating
     */
    public void setRating(Double rating) {
        this.rating = rating;
    }

    /**
     * Gets the movie the rating is about
     * @return the movie
     */
    public Movie getMovie() {
        return movie;
    }

    /**
     * Sets the movie of the review
     * @param movie the movie
     */
    public void setMovie(Movie movie) {
        this.movie = movie;
    }

}