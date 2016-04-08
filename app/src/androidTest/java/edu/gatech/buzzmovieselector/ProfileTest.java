package edu.gatech.buzzmovieselector;


import edu.gatech.buzzmovieselector.entity.Profile;
import edu.gatech.buzzmovieselector.entity.Review;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Jason Wu on 4/6/16.
 * Tests the Profile equals method
 */
public class ProfileTest {
    @Test
    public void checkEqualsNull() {
        Profile test = new Profile("first", "last", "major", "email");
        Profile test2 = null;
        assertFalse(test.equals(test2));
    }

    @Test
    public void testReflexiveProperty() {
        Profile test = new Profile("first", "last", "major", "email");
        Profile test2 = new Profile("first", "last", "major", "email");
        assertTrue(test.equals(test2) && test.equals(test2));
    }

    @Test
    public void checkWithNotProfile() {
        Profile test = new Profile("first", "last", "major", "email");
        Review r = new Review();
        assertFalse(test.equals(r));
    }

    @Test
    public void checkWithSelf() {
        Profile test = new Profile("first", "last", "major", "email");

        assertTrue(test.equals(test));
    }

    @Test
    public void testNotEqual() {
        Profile test = new Profile("first", "last", "major", "email");
        Profile test2 = new Profile("first", "last", "major", "email2");
        assertFalse(test.equals(test2));
        assertFalse(test2.equals(test));
    }


}
