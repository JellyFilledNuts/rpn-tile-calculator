package com.fhdw.wip.rpntilecalculator.core.model.operand;

import com.fhdw.wip.rpntilecalculator.core.model.operand.Operand;

import org.jetbrains.annotations.NotNull;

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

}
