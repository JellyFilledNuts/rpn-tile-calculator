package de.fhdw.wip.rpntilecalculator.presenter;

public class ClickHandlingException extends Exception {

    public ClickHandlingException(String msg) {
        super(msg);
    }

    public ClickHandlingException() {
        this("A tile click could not be handled.");
    }
}
