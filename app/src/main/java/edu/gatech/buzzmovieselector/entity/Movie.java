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

    @DatabaseField(generatedId = true, allowGeneratedIdInsert = true)
    private Integer id;
    @DatabaseField
    private String title;
    @DatabaseField
    private Integer year;
    @DatabaseField
    private Double rating;
    @ForeignCollectionField(eager = true)
    private Collection<Review> reviews;
    @DatabaseField
    private String imageURL;

    /**
     * Empty constructor for a movie object
     */
    public Movie() {
        reviews = new ArrayList<>();
    }

    /**
     * Creates a movie with the given title, year, and rating
     *
     * @param title  The title of the movie
     * @param year   The year the movie was made
     * @param rating The rating of the movie
     */
    public Movie(String title, int year, double rating) {
        this(title, year, rating, null);
    }

    /**
     * Creates a movie with the given title, rating, year, and image
     *
     * @param title    The title of the movie
     * @param year     The year the movie was made
     * @param rating   The rating of the movie
     * @param imageURL The image of the movie
     */
    public Movie(String title, int year, double rating, String imageURL) {
        this.title = title;
        this.year = year;
        this.rating = rating;
        this.imageURL = imageURL;
        reviews = new ArrayList<>();
    }

    /**
     * Creates a movie with the given information
     *
     * @param id       The integer id of the movie
     * @param title    The movie title
     * @param year     The year the movie was made
     * @param rating   The movie's rating
     * @param imageURL The movie's poster
     */
    public Movie(Integer id, String title, int year, double rating, String
        imageURL) {
        this(title, year, rating, imageURL);
        this.setId(id);
    }

    /**
     * @return A list of reviews for a movie
     */
    public Collection<Review> getReviews() {
        return reviews;
    }

    /**
     * @param reviews Assigns reviews to a movie
     */
    public void setReviews(Collection<Review> reviews) {
        this.reviews = reviews;
    }

    /**
     * @return The name of the movie
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id The name to assign the movie to
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return The title of the movie
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title Sets the movie title to this
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return The year the movie was made
     */
    public Integer getYear() {
        return year;
    }

    /**
     * Gets the rating
     *
     * @return the rating
     */
    public Double getRating() {
        return rating;
    }

    /**
     * Sets the rating
     *
     * @param rating the rating to set
     */
    public void setRating(Double rating) {
        this.rating = rating;
    }

    /**
     * Gets the image url
     *
     * @return the image url
     */
    public String getImageURL() {
        return imageURL;
    }

    /**
     * Adds a review to the list of reviews for the movie
     *
     * @param r The review to add
     */
    public void addReview(Review r) {
        reviews.add(r);
    }

    @Override
    public boolean equals(Object movie) {
        if (movie == null) {
            return false;
        }
        if (!Movie.class.isAssignableFrom(movie.getClass())) {
            return false;
        }
        final Movie m = (Movie) movie;
        return title.equals(m.title) && year.equals(m.year)
            && rating.equals(m.rating);
    }

    /**
     * Creates a hashcode for a movie object
     *
     * @return The hashcode
     */
    public int hashCode() {
        return title.hashCode() + year;
    }

    @Override
    public String toString() {
        return "Movie: " + title + "ID: " + id + " Year: " + year + " Rate: "
            + rating;
    }

    /**
     * Loads the rating for a movie
     *
     * @return the movie's current rating
     */
    public double loadRating() {
        if (reviews.size() == 0) {
            return 0;
        }
        double totalRating = 0;
        for (final Review r : reviews) {
            totalRating += r.getRating();
        }
        return totalRating / reviews.size();
    }
}