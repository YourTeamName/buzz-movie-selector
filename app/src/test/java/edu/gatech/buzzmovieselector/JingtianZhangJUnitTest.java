package edu.gatech.buzzmovieselector;

import org.junit.Test;
import static org.junit.Assert.*;
import junit.framework.TestCase;
import edu.gatech.buzzmovieselector.entity.User;

public class JingtianZhangJUnitTest {
    @Test
    public void checkConstructorIgnoreCaseAdmin() {
        User a = new User("a", "1234", "ADMIN");
        assertEquals(a.getUserStatus(), User.UserStatus.ADMIN);

        User b = new User("b", "1234", "aDMIN");
        assertEquals(b.getUserStatus(), User.UserStatus.ADMIN);
    }

    @Test
    public void checkConstructorIgnoreCaseUser() {
        User c = new User("c", "1234", "USER");
        assertEquals(c.getUserStatus(), User.UserStatus.USER);

        User d = new User("d", "1234", "uSer");
        assertEquals(d.getUserStatus(), User.UserStatus.USER);
    }

    @Test
    public void checkConstructorIgnoreCaseBanned() {
        User e = new User("e", "1234", "Banned");
        assertEquals(e.getUserStatus(), User.UserStatus.BANNED);

        User f = new User("f", "1234", "bAnned");
        assertEquals(f.getUserStatus(), User.UserStatus.BANNED);
    }

    @Test
    public void checkConstructorIgnoreCaseLocked() {
        User g = new User("g", "1234", "LOCKED");
        assertEquals(g.getUserStatus(), User.UserStatus.LOCKED);

        User h = new User("h", "1234", "Locked");
        assertEquals(h.getUserStatus(), User.UserStatus.LOCKED);
    }

}