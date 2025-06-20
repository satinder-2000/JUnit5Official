package example.assumptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

import example.util.Calculator;
import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;

class AssumptionsDemo {

    private final Calculator calculator = new Calculator();

    @Test
    void testOnlyOnCiServer() {
        assumeTrue("CI".equals(System.getenv("ENV")));
        // remainder of test
    }

    @Test
    void testOnlyOnDeveloperWorkstation() {
        assumeTrue("DEV".equals(System.getenv("ENV")),
                () -> "Aborting test: not on developer workstation");
        // remainder of test
    }

    @Test
    void testInAllEnvironments() {
        assumingThat("CI".equals(System.getenv("ENV")),
                () -> {
                    // perform these assertions only on the CI server
                    assertEquals(2, calculator.divide(4, 2));
                });

        // perform these assertions in all environments
        assertEquals(42, calculator.multiply(6, 7));
    }

    @Test
    void failsDueToUncaughtException() {
        // The following throws an ArithmeticException due to division by
        // zero, which causes a test failure.
        calculator.divide(1, 0);
    }

    @Test
    void failsDueToUncaughtAssertionError() {
        // The following incorrect assertion will cause a test failure.
        // The expected value should be 2 instead of 99.
        assertEquals(99, calculator.add(1, 1));
    }

    @Test
    void testExpectedExceptionIsThrown() {
        // The following assertion succeeds because the code under assertion
        // throws the expected IllegalArgumentException.
        // The assertion also returns the thrown exception which can be used for
        // further assertions like asserting the exception message.
        IllegalArgumentException exception =
        assertThrows(IllegalArgumentException.class, () -> {
            throw new IllegalArgumentException("expected message");
        });
        assertEquals("expected message", exception.getMessage());

        // The following assertion also succeeds because the code under assertion
        // throws IllegalArgumentException which is a subclass of RuntimeException.
        assertThrows(RuntimeException.class, () -> {
            throw new IllegalArgumentException("expected message");
        });
    }
}
