package de.fhdw.wip.rpntilecalculator.core.model.operand;

import org.apache.commons.math3.fraction.Fraction;
import org.jetbrains.annotations.NotNull;

public class OFraction extends Operand {

    @NotNull private Fraction fraction;

    public OFraction(@NotNull Fraction fraction) {
        this.fraction = fraction;
    }

    public OFraction(int nom, int den) {
        this.fraction = new Fraction(nom, den);
    }

    public @NotNull Fraction getFraction() {
        return fraction;
    }

    public double getDouble() {
        return fraction.doubleValue();
    }

    @NotNull @Override public OFraction turnAroundSign() {
        return new OFraction(fraction.multiply(-1));
    }

    @NotNull @Override public OFraction negateValue() {
        return new OFraction(new Fraction(
                Math.abs(fraction.getNumerator()) * -1,
                Math.abs(fraction.getDenominator()) * -1
        ));
    }

    @Override public @NotNull OFraction inverseValue() {
        return new OFraction(fraction.reciprocal());
    }

    @Override
    public boolean equalsValue(Operand operand) {
        if (operand == this) return true;
        if (!(operand instanceof OFraction)) return false;

        return ((OFraction) operand).getFraction().compareTo(fraction) == 0;
    }

    @NotNull @Override public String toString() {
        return String.format("(%s/%s)",
                DoubleFormatter.format(fraction.getNumerator()),
                DoubleFormatter.format(fraction.getDenominator())
        );
    }

}
