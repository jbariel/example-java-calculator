package com.jbariel.example.calculator;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.stream.Stream;

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
        checkActor("%", false);
        Stream.of("+", "-", "*", "/").forEach(s -> checkActor(s, true));
    }

    @Test
    public void testActorWordArg() {
        checkActor("modulus", false);
        Stream.of("add", "ADD", "subtract", "SUBTRACT", "multiply", "MULTIPLY", "divide", "DIVIDE")
                .forEach(s -> checkActor(s, true));
    }

    protected void checkActor(String actor, boolean isValid) {
        if (isValid) {
            shouldNotThrowIllegalArgumentException(new String[] { actor, "1", "1" });
        } else {
            shouldThrowIllegalArgumentException(new String[] { actor, "1", "1" });
        }
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