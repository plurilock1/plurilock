package group1.cpsc319.plurilock_client.Model;

import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * Created by Matas on 2/21/16.
 */
public class UserTest {

    @Test
    public void user_printsString() {
        User test = new User("Matas", "matas@alumni.ubc.ca");

        assertEquals("assert userMessage prints properly", test.userMessage(), "Hello, Matas! Is your email, matas@alumni.ubc.ca still valid?");
    }
}
