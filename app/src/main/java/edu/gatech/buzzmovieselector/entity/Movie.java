package edu.gatech.buzzmovieselector.entity;

import java.util.Collection;

/**
 * Creates a movie object with name, year produced, and rating
 */
public class Movie {

    private Integer id;
    private String title;
    private Integer year;
    private Double rating;
    private Collection<Review> reviews;

    public Collection<Review> getReviews() {
        return reviews;
    }

    public void setReviews(Collection<Review> reviews) {
        this.reviews = reviews;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Movie() {
    }

    public Movie(String title, int year, double rating) {
        this.title = title;
        this.year = year;
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Movie: " + title + " Year: " + year + " Rate: " + rating;
    }

}