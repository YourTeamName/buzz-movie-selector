package edu.gatech.buzzmovieselector.entity;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;
import edu.gatech.buzzmovieselector.dao.impl.MovieDaoImpl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Creates a movie object with name, year produced, and rating
 */
@DatabaseTable(tableName = "movie", daoClass = MovieDaoImpl.class)
public class Movie implements Serializable {

    @DatabaseField(generatedId = true)
    private Integer id;
    @DatabaseField
    private String title;
    @DatabaseField
    private Integer year;
    @DatabaseField
    private Double rating;
    @ForeignCollectionField(eager = true)
    private Collection<Review> reviews;
    // TODO: store actual binary image data
    @DatabaseField
    private String imageURL;

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

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public Movie() {
        reviews = new ArrayList<Review>();
    }

    public Movie(String title, int year, double rating) {
        this(title, year, rating, null);
    }

    public Movie(String title, int year, double rating, String imageURL) {
        this.title = title;
        this.year = year;
        this.rating = rating;
        this.imageURL = imageURL;
        reviews = new ArrayList<Review>();
    }

    public void addReview(Review r) {
        reviews.add(r);
    }

    @Override
    public boolean equals(Object o) {
        // TODO: add more criteria
        if (o == null) {
            return false;
        }
        if (!Movie.class.isAssignableFrom(o.getClass())) {
            return false;
        }
        final Movie m = (Movie) o;
        if (!title.equals(m.title)) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public String toString() {
        return "Movie: " + title + " Year: " + year + " Rate: " + rating;
    }

}