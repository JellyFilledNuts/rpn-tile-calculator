package de.fhdw.wip.rpntilecalculator.model.operands;

import org.apache.commons.math3.primes.Primes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/*
 * Summary: An Empty Operand
 * Author:  Tim Jonas Meinerzhagen
 * Date:    2019/09/21
 */
public class OEmpty extends Operand {

    /*
     * Create a an empty operand
     * @param aDouble content to be wrapped
     */
    public OEmpty() { }



    /*
     * Create a an empty operand
     * @param aDouble content to be wrapped
     */
    public OEmpty(String content) { }


    /*
     * swap the pre Sign
     * @return new empty
     */
    @NotNull @Override public OEmpty turnAroundSign() { return this; }

    /*
     * Turn the pre Sign to negative
     * @return new empty
     */
    @NotNull @Override public OEmpty negateValue()  { return this; }

    /*
     * Inverse the value
     * @return new empty
     */
    @NotNull @Override public OEmpty inverseValue()  { return this; }

    /*
     * Format the empty to a string
     * @return String representation of the content
     */
    @NotNull @Override public String toString() { return " "; }

    @Override public boolean equalsValue(@Nullable Operand operand) {
        if (operand == this) return true;
        return operand instanceof OEmpty;
    }
}
