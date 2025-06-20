package example.assertExceptions;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 *
 * @author singh
 */
public class AssertThrowExactly {

    @Test
    void testExpectedExceptionIsThrown() {
        // The following assertion succeeds because the code under assertion throws
        // IllegalArgumentException which is exactly equal to the expected type.
        // The assertion also returns the thrown exception which can be used for
        // further assertions like asserting the exception message.
        IllegalArgumentException exception
                = assertThrowsExactly(IllegalArgumentException.class, () -> {
                    throw new IllegalArgumentException("expected message");
                });
        assertEquals("expected message", exception.getMessage());

        // The following assertion fails because the assertion expects exactly
        // RuntimeException to be thrown, not subclasses of RuntimeException.
        assertThrowsExactly(RuntimeException.class, () -> {
            throw new IllegalArgumentException("expected message");
        });
    }

}
