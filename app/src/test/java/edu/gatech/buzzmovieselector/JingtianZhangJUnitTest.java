package edu.gatech.buzzmovieselector;

import org.junit.Test;
import static org.junit.Assert.*;
import junit.framework.TestCase;
import edu.gatech.buzzmovieselector.entity.User;
import edu.gatech.buzzmovieselector.entity.Review;

public class JingtianZhangJUnitTest {
    @Test
    public void checkConstructorIgnoreCase() {
        //User a = new User("a", "1234", "admin");
        User a = new User("a", "1234", "ADMIN");
        assertEquals(a.userStatus, UserStatus.ADMIN);

        User b = new User("b", "1234", "aDMIN");
        assertEquals(b.userStatus, UserStatus.ADMIN);
    }

}