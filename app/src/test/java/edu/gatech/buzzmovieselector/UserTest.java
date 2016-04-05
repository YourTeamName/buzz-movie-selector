package edu.gatech.buzzmovieselector;



import junit.framework.TestCase;

import org.junit.Test;

import edu.gatech.buzzmovieselector.entity.User;
import edu.gatech.buzzmovieselector.entity.Review;
import static org.junit.Assert.*;

/**
 * Created by Jonathan Zhang on 4/5/16.
 * Tests the equals() method for User
 */
public class UserTest {
    @Test
    public void checkEqualsNull() {
        User jon = new User("zhangj", "1234", User.UserStatus.USER);
        User brian = null;
        assertFalse(jon.equals(brian)); //brian is null
    }

    @Test
    public void testReflexiveProperty() {
        User James = new User("J", "hehe", User.UserStatus.USER);
        User Jahar = new User("J", "hehe", User.UserStatus.USER);
        assertTrue(James.equals(Jahar) && Jahar.equals(James));
    }

    @Test
    public void checkWithNotUser() { //checks with object that is not user
        User jon = new User("zhangj", "1234", User.UserStatus.USER);
        Review r = new Review();
        assertFalse(jon.equals(r));
    }

    @Test
    public void checkWithSelf() {
        User ryan = new User("KingJulio", "youtube", User.UserStatus.BANNED);

        assertTrue(ryan.equals(ryan));

        //ray and ryan are same object
        //checks if they are equal.
        //User ray = ryan;
        //assertTrue(ray.equals(ryan));
    }

    @Test
    public void testTransitiveProperty() {
        User user1 = new User("KingJulio", "youtube", User.UserStatus.BANNED);
        User user2 = new User("KingJulio", "youtube", User.UserStatus.BANNED);
        User user3 = new User("KingJulio", "youtube", User.UserStatus.BANNED);
        assertTrue(user1.equals(user2));
        assertTrue(user2.equals(user3));
        assertTrue(user1.equals(user3));
    }

    @Test
    public void checkEqualUsers() {
        //checks the parameters of two equal users for equality
        User ryan = new User("KingJulio", "youtube", User.UserStatus.BANNED);
        User josh = new User("KingJulio", "youtube", User.UserStatus.BANNED);
        assertTrue(ryan.equals(josh));

        //checks userstatus parameter
        ryan = new User("KingJulio", "youtube", User.UserStatus.ADMIN);
        josh = new User("KingJulio", "youtube", User.UserStatus.BANNED);
        assertFalse(ryan.equals(josh));

        //checks for different passwords
        ryan = new User("KingJulio", "hashcode", User.UserStatus.BANNED);
        josh = new User("KingJulio", "youtube", User.UserStatus.BANNED);
        assertFalse(ryan.equals(josh));

        //checks different user names
        ryan = new User("KingBURGERS", "youtube", User.UserStatus.BANNED);
        josh = new User("KingJulio", "youtube", User.UserStatus.BANNED);
        assertFalse(ryan.equals(josh));
    }
}
