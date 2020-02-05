package de.fhdw.wip.rpntilecalculator.model.operands;

import org.apache.commons.math3.primes.Primes;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/*
 * Summary: Wrapper for the Double Operand
 * Author:  Tim Jonas Meinerzhagen
 * Date:    2019/11/12
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
     * Create a new ODouble from a string
     * @param aDouble content to be wrapped
     */
    public ODouble(String aDouble) { this.aDouble = Double.valueOf(aDouble); }

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

    @Override public boolean equalsValue(@Nullable Operand operand) {
        if (operand == this) return true;
        if (!(operand instanceof ODouble)) return false;

        double bDouble = ((ODouble) operand).getDouble();

        return DoubleComparator.isEqual(aDouble, bDouble);
    }

    /*
    If number has a decimal part it returns false
     */
    public boolean isPrime()
    {
        if(this.aDouble % 1 == 0)
        {
            return Primes.isPrime((int) this.aDouble);
        }
        else
        {
            return false;
        }
    }
}
