package de.fhdw.wip.rpntilecalculator.core.model.operand;

import de.fhdw.wip.rpntilecalculator.core.model.operand.Operand;

import org.apache.commons.math3.primes.Primes;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ODouble extends Operand {

    private double aDouble;

    public ODouble(double aDouble) {
        this.aDouble = aDouble;
    }

    public double getDouble() {
        return aDouble;
    }

    @NotNull @Override public ODouble turnAroundSign() {
        return new ODouble(aDouble * -1);
    }

    @NotNull @Override public ODouble negateValue() {
        return new ODouble(Math.abs(aDouble) * -1);
    }

    @Override public @NotNull ODouble inverseValue() {
        return new ODouble(1 / aDouble);
    }

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
