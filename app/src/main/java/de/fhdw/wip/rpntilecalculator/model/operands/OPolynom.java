package de.fhdw.wip.rpntilecalculator.model.operands;

import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class OPolynom extends Operand {

    @NotNull private PolynomialFunction polynom;

    public OPolynom(@NotNull PolynomialFunction polynom) {
        this.polynom = polynom;
    }

    public OPolynom(@NotNull double... coefficients) {
        this.polynom = new PolynomialFunction(coefficients);
    }

    public OPolynom(@NotNull String polynom) {
        //4.1x^0 + 2x^1 + -3.1x^2
        String[] vars = polynom.trim().split("(x\\^[0-9])( \\+)*");
        double[] coefficients = new double[vars.length];
        for(int i = 0; i < vars.length; i++) coefficients[i] = Double.valueOf(vars[i].trim());
        for(int i = 0; i < vars.length; i++) System.out.println("AAA" + coefficients[i]);
        this.polynom = new PolynomialFunction(coefficients);
    }

    public @NotNull PolynomialFunction getPolynom() {
        return polynom;
    }

    @NotNull @Override public OPolynom turnAroundSign() {
        double[] doubles = polynom.getCoefficients();
        for (int i = 0; i < doubles.length; i++)
            doubles[i] *= -1;
        return new OPolynom(new PolynomialFunction(doubles));
    }

    @NotNull @Override public OPolynom negateValue() {
        double[] doubles = polynom.getCoefficients();
        for (int i = 0; i < doubles.length; i++)
            doubles[i] = Math.abs(doubles[i]) * -1;
        return new OPolynom(new PolynomialFunction(doubles));
    }

    @Override public @NotNull OPolynom inverseValue() {
        double[] doubles = polynom.getCoefficients();
        for (int i = 0; i < doubles.length; i++)
            doubles[i] = 1 / doubles[i];
        return new OPolynom(new PolynomialFunction(doubles));
    }

    @Override
    public boolean equalsValue(Operand operand) {
        if (operand == this) return true;
        if (!(operand instanceof OPolynom)) return false;

        return DoubleComparator.isEqual(
                polynom.getCoefficients(),
                ((OPolynom) operand).getPolynom().getCoefficients()
        );
    }

    @NotNull @Override public String toString() {
        StringBuilder builder = new StringBuilder();
        double[] doubles = polynom.getCoefficients();
        for (int i = 0; i < doubles.length; i++) {
            builder.append(DoubleFormatter.format(doubles[i]));
            builder.append("x^");
            builder.append(i);
            builder.append(" + ");
        }
        builder.delete(builder.length() - 3, builder.length());
        return builder.toString();
    }


    @NotNull public OPolynom getDerivative()
    {
        PolynomialFunction polynomialDerivative = polynom.polynomialDerivative();
        return new OPolynom(polynomialDerivative);
    }

}
