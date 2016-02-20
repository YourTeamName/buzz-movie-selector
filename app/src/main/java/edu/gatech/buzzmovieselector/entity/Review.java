package edu.gatech.buzzmovieselector.entity;

public class Review {

    private User user;
    private String text;
    private double rating;

    public Review(User user, String text) {
        this.text = text;
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public double getRating() {
        return rating;
    }
