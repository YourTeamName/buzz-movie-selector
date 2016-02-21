package edu.gatech.buzzmovieselector.entity;

/**
 * Class for a movie review that has a rating and text evaluation of the movie
 * Each movie review also contains the movie it pertains to
 */
public class Review {

    private User user;
    private String text;
    private double rating;
    private Movie movie;
    private Integer id;

    public Review(User user, String text, Movie movie) {
        this.text = text;
        this.user = user;
        this.movie = movie;
    }

    public String getText() {
        return text;
    }

    public double getRating() {
        return rating;
    }

    public Movie getMovie() {
        return movie;
    }
}