package com.jbariel.example.calculator;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Calculator {

    private static final Logger LOG = LoggerFactory.getLogger(Calculator.class);

    public static enum OPS {
        ADD(BigDecimal::add), SUBTRACT(BigDecimal::subtract), MULTIPLY(BigDecimal::multiply),
        DIVIDE(BigDecimal::divide);

        private BinaryOperator<BigDecimal> op;

        OPS(BinaryOperator<BigDecimal> op) {
            this.op = op;
        }

        public BinaryOperator<BigDecimal> op() {
            return this.op;
        }
    };

    public static void main(final String[] args) {
        List<String> argStrs = Arrays.asList(args);
        LOG.info("Performing " + argStrs.stream().collect(Collectors.joining(" ")));
        if (argStrs.size() < 3) {
            IllegalArgumentException e = new IllegalArgumentException(
                    "Must provide at least three (3) arguments: an actor and at least 2 digits");
            LOG.debug("Read: " + argStrs.stream().collect(Collectors.joining(" ")));
            LOG.error(e.getLocalizedMessage(), e);
            throw e;
        }
        // Log out result
        LOG.info("RESULT: " + getResultOfAction(argStrs.subList(1, argStrs.size()), getOperation(argStrs.get(0)).op()));

    }

    protected static OPS getOperation(final String actor) {
        OPS op = null;
        switch (actor.toLowerCase()) {
            case "+":
            case "add":
                LOG.info("We're gonna add...");
                op = OPS.ADD;
                break;
            case "-":
            case "subtract":
                LOG.info("We're gonna subtract...");
                op = OPS.SUBTRACT;
                break;
            case "*":
            case "multiply":
                LOG.info("We're gonna multiply...");
                op = OPS.MULTIPLY;
                break;
            case "/":
            case "divide":
                LOG.info("We're gonna divide...");
                op = OPS.DIVIDE;
                break;
            default:
                throw new IllegalArgumentException(
                        "Must provide a supported actor: ['+','-','*','/','add','subtract','multiply','divide'");
        }
        return op;
    }

    protected static BigDecimal getResultOfAction(final List<String> paramsToConvert,
            final BinaryOperator<BigDecimal> op) {
        if (paramsToConvert.size() < 2) {
            throw new IllegalArgumentException(
                    "Must provide at least two values, one to start and one to act on (min)");
        }
        final BigDecimal initValue = new BigDecimal(paramsToConvert.get(0));
        return paramsToConvert.subList(1, paramsToConvert.size()).stream().map(BigDecimal::new).reduce(initValue, op);
    }

}