package de.fhdw.wip.rpntilecalculator.core.calculation;

/*
 * Summary: Main Exception for a failed Calculation
 * Author:  Tim Schwenke
 * Date:    2020/01/04
 */
public class CalculationException extends Exception {

    /*
     * Create a new Exception
     * @param msg Error Message
     */
    public CalculationException(String msg) {
        super(msg);
    }

    /*
     * Create a new standard Exception
     */
    public CalculationException() {
        this("Not supported");
    }

}
