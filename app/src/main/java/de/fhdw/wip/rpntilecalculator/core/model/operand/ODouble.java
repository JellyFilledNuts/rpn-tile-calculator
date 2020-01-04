package de.fhdw.wip.rpntilecalculator.core.model.operand;

import de.fhdw.wip.rpntilecalculator.core.model.DoubleFormatter;

import org.jetbrains.annotations.NotNull;

/*
 * Summary: Wrapper for the Double Operand
 * Author:  Tim Schwenke
 * Date:    2020/01/04
 */
public class ODouble extends Operand {

    // content to be wrapped
    private double aDouble;

    /*
     * Create a new ODouble from a double
     * @param aDouble content to be wrapped
     */
    public ODouble(double aDouble) {
        this.aDouble = aDouble;
    }

    /*
     * Get the underlying content
     * @return the underlying content
     */
    public double getDouble() {
        return aDouble;
    }

    /*
     * swap the pre Sign (+ -> -; - -> +)
     * @return new double
     */
    @NotNull @Override public ODouble turnAroundSign() {
        return new ODouble(aDouble * -1);
    }

    /*
     * Turn the pre Sign to negative
     * @return new double
     */
    @NotNull @Override public ODouble negateValue() {
        return new ODouble(Math.abs(aDouble) * -1);
    }

    /*
     * Inverse the value
     * @return new double
     */
    @NotNull @Override public ODouble inverseValue() {
        return new ODouble(1 / aDouble);
    }

    /*
     * Format the double to a string
     * @return String representation of the content
     */
    @NotNull @Override public String toString() {
        return DoubleFormatter.format(aDouble);
    }

}
