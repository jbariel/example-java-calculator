package com.jbariel.example.calculator;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Calculator {

    public static final Logger LOG = LoggerFactory.getLogger(Calculator.class);

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
        // get the action out
        String actor = argStrs.get(0);
        final List<String> strValues = argStrs.subList(1, argStrs.size());

        switch (actor.toLowerCase()) {
            case "+":
            case "add":
                LOG.info("We're gonna add...");
                LOG.info("RESULT: " + getResultOfAction(strValues, BigDecimal::add));
                break;
            case "-":
            case "subtract":
                LOG.info("We're gonna subtract...");
                LOG.info("RESULT: " + getResultOfAction(strValues, BigDecimal::subtract));
                break;
            case "*":
            case "multiply":
                LOG.info("We're gonna multiply...");
                LOG.info("RESULT: " + getResultOfAction(strValues, BigDecimal::multiply));
                break;
            case "/":
            case "divide":
                LOG.info("We're gonna divide...");
                LOG.info("RESULT: " + getResultOfAction(strValues, BigDecimal::divide));
                break;
            default:
                throw new IllegalArgumentException(
                        "Must provide a supported actor: ['+','-','*','/','add','subtract','multiply','divide'");
        }
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