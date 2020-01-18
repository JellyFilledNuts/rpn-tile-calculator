package de.fhdw.wip.rpntilecalculator.core.model.operand;

import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;
import org.jetbrains.annotations.NotNull;

import de.fhdw.wip.rpntilecalculator.core.model.DoubleFormatter;

/*
 * Summary: Wrapper for the Polynom Operand
 * Author:  Tim Schwenke
 * Date:    2020/01/04
 */
public class OPolynom extends Operand {

    @NotNull private PolynomialFunction function;

    public OPolynom(@NotNull PolynomialFunction function) {
        this.function = function;
    }

    public OPolynom(@NotNull double[] coefficients) {
        this.function = new PolynomialFunction(coefficients);
    }

    @NotNull public PolynomialFunction getPolynom() {
        return function;
    }

    @NotNull @Override public OPolynom turnAroundSign() {
        double[] doubles = function.getCoefficients();
        for (int i = 0; i < doubles.length; i++)
            doubles[i] *= -1;
        return new OPolynom(new PolynomialFunction(doubles));
    }

    @NotNull @Override public OPolynom negateValue() {
        double[] doubles = function.getCoefficients();
        for (int i = 0; i < doubles.length; i++)
            doubles[i] = Math.abs(doubles[i]) * -1;
        return new OPolynom(new PolynomialFunction(doubles));
    }

    @NotNull @Override public OPolynom inverseValue() {
        double[] doubles = function.getCoefficients();
        for (int i = 0; i < doubles.length; i++)
            doubles[i] = 1 / doubles[i];
        return new OPolynom(new PolynomialFunction(doubles));
    }

    @NotNull @Override public String toString() {
        StringBuilder builder = new StringBuilder();
        double[] doubles = function.getCoefficients();
        for (int i = 0; i < doubles.length; i++) {
            builder.append(DoubleFormatter.format(doubles[i]));
            builder.append("x^");
            builder.append(i);
            builder.append(" + ");
        }
        builder.delete(builder.length() - 3, builder.length());
        return builder.toString();
    }

}
