package edu.gatech.buzzmovieselector;

import org.junit.Test;
import static org.junit.Assert.*;
import junit.framework.TestCase;
import edu.gatech.buzzmovieselector.entity.User;

public class JingtianZhangJUnitTest {
    @Test
    public void checkConstructorIgnoreCaseAdmin() {
        User a = new User("a", "1234", "ADMIN");
        assertEquals(a.userStatus, UserStatus.ADMIN);

        User b = new User("b", "1234", "aDMIN");
        assertEquals(b.userStatus, UserStatus.ADMIN);
    }

    @Test
    public void checkConstructorIgnoreCaseUser() {
        User c = new User("c", "1234", "USER");
        assertEquals(c.userStatus, UserStatus.USER);

        User d = new User("d", "1234", "uSer");
        assertEquals(d.userStatus, UserStatus.USER);
    }

    @Test
    public void checkConstructorIgnoreCaseBanned() {
        User e = new User("e", "1234", "Banned");
        assertEquals(e.userStatus, UserStatus.BANNED);

        User f = new User("f", "1234", "bAnned");
        assertEquals(f.userStatus, UserStatus.BANNED);
    }

    @Test
    public void checkConstructorIgnoreCaseAdmin() {
        User g = new User("g", "1234", "LOCKED");
        assertEquals(g.userStatus, UserStatus.LOCKED);

        User h = new User("h", "1234", "Locked");
        assertEquals(b.userStatus, UserStatus.LOCKED);
    }
}