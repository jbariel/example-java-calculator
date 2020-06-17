package com.jbariel.example.calculator;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
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
        doAction(argStrs);
    }

    protected static void doAction(final List<String> args) {
        String actor = args.get(0);
        switch (actor.toLowerCase()) {
            case "+":
            case "add":
                LOG.info("We're gonna add...");
                break;
            case "-":
            case "subtract":
                LOG.info("We're gonna subtract...");
                break;
            case "*":
            case "multiply":
                LOG.info("We're gonna multiply...");
                break;
            case "/":
            case "divide":
                LOG.info("We're gonna divide...");
                break;
            default:
                throw new IllegalArgumentException(
                        "Must provide a supported actor: ['+','-','*','/','add','subtract','multiply','divide'");
        }
    }

}