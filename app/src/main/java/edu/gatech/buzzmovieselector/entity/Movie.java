package edu.gatech.buzzmovieselector.entity;

import java.util.ArrayList;
import java.util.List;

public class Movie {

    private String name;
    private int year;
    private double rating;
    private List<Review> reviews;

    public Movie(String name, int year) {
        this.name = name;
        this.year = year;
        reviews = new ArrayList<Review>();
    }

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }

    public 

    public void addReview(Review review) {
        reviews.add(review);
    }

    public List<Review> getReviews() {
        return reviews;
    }

    private void updateRating() {
        int reviewSum = 0
        for (Review r : reviews) {
            reviewSum += r.getRating();
        }
        if (reviews.size() > 0) {
            rating = reviewSum / reviews.size();
        } else {
            rating = 0;
        }
    }

    public int getRating() {
        updateRating();
        return rating;
    }
}