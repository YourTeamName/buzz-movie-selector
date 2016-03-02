package edu.gatech.buzzmovieselector.entity;

import com.j256.ormlite.table.DatabaseTable;

/**
 * Class for a movie review that has a rating and text evaluation of the movie
 * Each movie review also contains the movie it pertains to
 */
@DatabaseTable(tableName = "review", daoClass = ReviewDaoImpl.class)
public class Review {

    private Integer id;
    private User user;
    private String content;
    private Double rating;
    private Movie movie;

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

    public Review() {
    }

    public Review(User user, String content, Movie movie) {
        this.setUser(user);
        this.setContent(content);
        this.setMovie(movie);
    }

}