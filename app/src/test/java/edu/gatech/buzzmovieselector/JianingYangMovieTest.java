package edu.gatech.buzzmovieselector;

import edu.gatech.buzzmovieselector.entity.Movie;
import edu.gatech.buzzmovieselector.entity.User;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by yangjianing on 4/5/16.
 */
public class JianingYangMovieTest {

    @Test
    public void checkEqualsNull() {
        Movie testMovie = new Movie("Movie", 1984, 4.5);
        Movie compareMovie = null;
        assertFalse(testMovie.equals(compareMovie)); // compareMovie is null
    }

    @Test
    public void testReflexiveProperty() {
        Movie testMovie1 = new Movie("Movie", 1984, 4.5);
        Movie testMovie2 = new Movie("Movie", 1984, 4.5);
        assertTrue(testMovie1.equals(testMovie2) && testMovie2.equals
            (testMovie1));
    }

    @Test
    public void checkWithNotUser() { //checks with object that is not movie
        Movie testMovie = new Movie("Movie", 1984, 4.5);
        User user = new User();
        assertFalse(testMovie.equals(user));
    }

    @Test
    public void checkWithSelf() {
        Movie testMovie = new Movie("Movie", 1984, 4.5);
        assertTrue(testMovie.equals(testMovie));
    }

    @Test
    public void testTransitiveProperty() {
        Movie testMovie1 = new Movie("Movie", 1984, 4.5);
        Movie testMovie2 = new Movie("Movie", 1984, 4.5);
        Movie testMovie3 = new Movie("Movie", 1984, 4.5);
        assertTrue(testMovie1.equals(testMovie2));
        assertTrue(testMovie2.equals(testMovie3));
        assertTrue(testMovie1.equals(testMovie3));
    }

    @Test
    public void checkEqualMovies() {
        // checks the parameters of two equal users for equality
        Movie testMovie1 = new Movie("Movie", 1984, 4.5);
        Movie testMovie2 = new Movie("Movie", 1984, 4.5);
        assertTrue(testMovie1.equals(testMovie2));

        // checks for different titles
        testMovie1 = new Movie("Movie", 1984, 4.5);
        testMovie2 = new Movie("Movie2", 1984, 4.5);
        assertFalse(testMovie1.equals(testMovie2));

        // checks for different years
        testMovie1 = new Movie("Movie", 1984, 4.5);
        testMovie2 = new Movie("Movie", 1985, 4.5);
        assertFalse(testMovie1.equals(testMovie2));

        // checks different ratings
        testMovie1 = new Movie("Movie", 1984, 4.5);
        testMovie2 = new Movie("Movie", 1985, 4.6);
        assertFalse(testMovie1.equals(testMovie2));
    }
}
