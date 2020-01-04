package de.fhdw.wip.rpntilecalculator.core.calculation;

public class CalculationException extends Exception {

    public CalculationException(String msg) {
        super(msg);
    }

    public CalculationException() {
        this("Not supported");
    }

}
