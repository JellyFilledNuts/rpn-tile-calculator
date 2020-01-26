package de.fhdw.wip.rpntilecalculator.controller;

public class ClickHandlingException extends Exception {

    public ClickHandlingException(String msg) {
        super(msg);
    }

    public ClickHandlingException() {
        this("A tile click could not be handled.");
    }
}
