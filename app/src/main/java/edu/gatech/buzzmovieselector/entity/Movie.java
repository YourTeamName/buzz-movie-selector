package edu.gatech.buzzmovieselector.entity;

/**
 * Creates a movie object with name, year produced, and rating
 */ 
public class Movie {

    private String name;
    private int year;
    private double rating;

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

    public int getRating() {
        return rating;
    }

    public boolean equals(Object other) {
        if (!(other instanceof Movie)) {
            return false;
        }
        return this.getName().equals(other.getName())
                && this.getYear() == other.getYear();
    }
}