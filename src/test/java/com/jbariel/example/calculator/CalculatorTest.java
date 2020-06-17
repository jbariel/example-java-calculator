package com.jbariel.example.calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.stream.Stream;

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

    protected void checkActor(final String actor, final boolean isValid) {
        if (isValid) {
            shouldNotThrowIllegalArgumentException(new String[] { actor, "1", "1" });
        } else {
            shouldThrowIllegalArgumentException(new String[] { actor, "1", "1" });
        }
    }

    protected void shouldThrowIllegalArgumentException(final String[] args) {
        try {
            Calculator.main(args);
            fail("Should throw exception");
        } catch (IllegalArgumentException e) {
            // ignore
        }
    }

    protected void shouldNotThrowIllegalArgumentException(final String[] args) {
        try {
            Calculator.main(args);
        } catch (IllegalArgumentException e) {
            fail("Should not throw exception");
        }
    }

    @Test
    public void testAddFxn() {
        final BinaryOperator<BigDecimal> op = BigDecimal::add;
        doTestGetResultOfAction(new BigDecimal("2"), op, "1", "1");
        doTestGetResultOfAction(new BigDecimal("20"), op, "10", "5", "3", "2");
        doTestGetResultOfAction(new BigDecimal("-2"), op, "-1", "-1");
        doTestGetResultOfAction(new BigDecimal("0"), op, "1", "-1");
        doTestGetResultOfAction(new BigDecimal("0"), op, "-1", "1");
        doTestGetResultOfAction(new BigDecimal("5"), op, "1", "1", "1", "1", "1");
    }

    @Test
    public void testSubtractFxn() {
        final BinaryOperator<BigDecimal> op = BigDecimal::subtract;
        doTestGetResultOfAction(new BigDecimal("0"), op, "1", "1");
        doTestGetResultOfAction(new BigDecimal("0"), op, "10", "5", "3", "2");
        doTestGetResultOfAction(new BigDecimal("0"), op, "-1", "-1");
        doTestGetResultOfAction(new BigDecimal("2"), op, "1", "-1");
        doTestGetResultOfAction(new BigDecimal("-2"), op, "-1", "1");
        doTestGetResultOfAction(new BigDecimal("-3"), op, "1", "1", "1", "1", "1");
    }

    @Test
    public void testMultiplyFxn() {
        final BinaryOperator<BigDecimal> op = BigDecimal::multiply;
        doTestGetResultOfAction(new BigDecimal("1"), op, "1", "1");
        doTestGetResultOfAction(new BigDecimal("300"), op, "10", "5", "3", "2");
        doTestGetResultOfAction(new BigDecimal("1"), op, "-1", "-1");
        doTestGetResultOfAction(new BigDecimal("-1"), op, "1", "-1");
        doTestGetResultOfAction(new BigDecimal("-1"), op, "-1", "1");
        doTestGetResultOfAction(new BigDecimal("1"), op, "1", "1", "1", "1", "1");
    }

    @Test
    public void testDivideFxn() {
        final BinaryOperator<BigDecimal> op = BigDecimal::divide;
        doTestGetResultOfAction(new BigDecimal("1"), op, "1", "1");
        doTestGetResultOfAction(new BigDecimal("1"), op, "10", "5", "2");
        doTestGetResultOfAction(new BigDecimal("1"), op, "-1", "-1");
        doTestGetResultOfAction(new BigDecimal("-1"), op, "1", "-1");
        doTestGetResultOfAction(new BigDecimal("-1"), op, "-1", "1");
        doTestGetResultOfAction(new BigDecimal("1"), op, "1", "1", "1", "1", "1");
    }

    protected void doTestGetResultOfAction(final BigDecimal expected, final BinaryOperator<BigDecimal> op,
            final String... vals) {
        BigDecimal res = Calculator.getResultOfAction(toParams(vals), op);
        assertEquals(scaled(expected).intValue(), scaled(res).intValue(), "Int values don't match!");
        assertEquals(scaled(expected).longValue(), scaled(res).longValue(), "Long values don't match!");
        assertEquals(scaled(expected).doubleValue(), scaled(res).doubleValue(), "Double values don't match!");
        assertEquals(scaled(expected).floatValue(), scaled(res).floatValue(), "Float values don't match!");
    }

    protected BigDecimal scaled(final BigDecimal toScale) {
        return toScale.setScale(5, RoundingMode.HALF_UP);
    }

    protected List<String> toParams(final String... vals) {
        return Arrays.asList(vals);
    }
}