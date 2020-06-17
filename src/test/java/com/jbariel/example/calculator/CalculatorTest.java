package com.jbariel.example.calculator;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class CalculatorTest {

    @Test
    @Disabled
    public void testLogArgs() {
        Calculator.main(new String[] { "*", "1", "2", "3" });
        // check logs to validate that "Peforming a b c" is output as info
    }

    @Test
    public void testHasThreeArgs() {
        shouldThrowIllegalArgumentException(new String[] {});
        shouldThrowIllegalArgumentException(new String[] { "foo" });
        shouldThrowIllegalArgumentException(new String[] { "foo", "bar" });
        shouldNotThrowIllegalArgumentException(new String[] { "foo", "bar", "bar" });
    }

    protected void shouldThrowIllegalArgumentException(String[] args) {
        try {
            Calculator.main(args);
            fail("Should throw exception");
        } catch (IllegalArgumentException e) {
            // ignore
        }
    }

    protected void shouldNotThrowIllegalArgumentException(String[] args) {
        try {
            Calculator.main(args);
        } catch (IllegalArgumentException e) {
            fail("Should not throw exception");
        }
    }
}