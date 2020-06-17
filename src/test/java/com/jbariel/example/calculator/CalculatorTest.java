package com.jbariel.example.calculator;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class CalculatorTest {

    @Test
    public void testLogArgs() {
        Calculator.main(new String[] { "*", "1", "2", "3" });
        // check logs to validate that "Peforming a b c" is output as info
    }

    @Test
    public void testHasThreeArgs() {
        shouldThrowIllegalArgumentException(new String[] {});
        shouldThrowIllegalArgumentException(new String[] { "+" });
        shouldThrowIllegalArgumentException(new String[] { "+", "1" });
        shouldNotThrowIllegalArgumentException(new String[] { "+", "1", "1" });
    }

    @Test
    public void testActorSymbolArg() {
        shouldThrowIllegalArgumentException(new String[] { "%", "1", "1" });
        shouldNotThrowIllegalArgumentException(new String[] { "+", "1", "1" });
        shouldNotThrowIllegalArgumentException(new String[] { "-", "1", "1" });
        shouldNotThrowIllegalArgumentException(new String[] { "*", "1", "1" });
        shouldNotThrowIllegalArgumentException(new String[] { "/", "1", "1" });
    }

    @Test
    public void testActorWordArg() {
        shouldThrowIllegalArgumentException(new String[] { "modulus", "1", "1" });
        shouldNotThrowIllegalArgumentException(new String[] { "add", "1", "1" });
        shouldNotThrowIllegalArgumentException(new String[] { "ADD", "1", "1" });
        shouldNotThrowIllegalArgumentException(new String[] { "subtract", "1", "1" });
        shouldNotThrowIllegalArgumentException(new String[] { "SUBTRACT", "1", "1" });
        shouldNotThrowIllegalArgumentException(new String[] { "multiply", "1", "1" });
        shouldNotThrowIllegalArgumentException(new String[] { "MULTIPLY", "1", "1" });
        shouldNotThrowIllegalArgumentException(new String[] { "divide", "1", "1" });
        shouldNotThrowIllegalArgumentException(new String[] { "DIVIDE", "1", "1" });
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