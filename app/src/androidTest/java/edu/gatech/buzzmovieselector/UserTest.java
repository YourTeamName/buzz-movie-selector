package edu.gatech.buzzmovieselector;


import edu.gatech.buzzmovieselector.entity.Review;
import edu.gatech.buzzmovieselector.entity.User;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Jonathan Zhang on 4/5/16.
 * Tests the equals() method for User
 */
public class UserTest {
    @Test
    public void checkEqualsNull() {
        User jon = new User("zhangj", "1234", "user");
        User brian = null;
        assertFalse(jon.equals(brian)); //brian is null
    }

    @Test
    public void testReflexiveProperty() {
        User James = new User("J", "hehe", "user");
        User Jahar = new User("J", "hehe", "user");
        assertTrue(James.equals(Jahar) && Jahar.equals(James));
    }

    @Test
    public void checkWithNotUser() { //checks with object that is not user
        User jon = new User("zhangj", "1234", "user");
        Review r = new Review();
        assertFalse(jon.equals(r));
    }

    @Test
    public void checkWithSelf() {
        User ryan = new User("KingJulio", "youtube", "banned");

        assertTrue(ryan.equals(ryan));

        //ray and ryan are same object
        //checks if they are equal.
        //User ray = ryan;
        //assertTrue(ray.equals(ryan));
    }

    @Test
    public void testTransitiveProperty() {
        User user1 = new User("KingJulio", "youtube", "banned");
        User user2 = new User("KingJulio", "youtube", "banned");
        User user3 = new User("KingJulio", "youtube", "banned");
        assertTrue(user1.equals(user2));
        assertTrue(user2.equals(user3));
        assertTrue(user1.equals(user3));
    }

    @Test
    public void checkEqualUsers() {
        //checks the parameters of two equal users for equality
        User ryan = new User("KingJulio", "youtube", "admin");
        User josh = new User("KingJulio", "youtube", "admin");
        assertTrue(ryan.equals(josh));

        //checks userstatus parameter
        ryan = new User("KingJulio", "youtube", "admin");
        josh = new User("KingJulio", "youtube", "banned");
        assertFalse(ryan.equals(josh));

        //checks for different passwords
        ryan = new User("KingJulio", "hashcode", "banned");
        josh = new User("KingJulio", "youtube", "banned");
        assertFalse(ryan.equals(josh));

        //checks different user names
        ryan = new User("KingBURGERS", "youtube", "banned");
        josh = new User("KingJulio", "youtube", "banned");
        assertFalse(ryan.equals(josh));
    }

}
