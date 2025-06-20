package example.classesandmethods;

import static org.junit.jupiter.api.Assertions.assertEquals;

import example.util.Calculator;

import org.junit.jupiter.api.Test;

record MyFirstJUnitJupiterRecordTests() {

    @Test
    void addition() {
        assertEquals(2, new Calculator().add(1, 1));
    }

}
