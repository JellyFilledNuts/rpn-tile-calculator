package de.fhdw.wip.rpntilecalculator.model.calculation;

import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import de.fhdw.wip.rpntilecalculator.model.operands.ODouble;
import de.fhdw.wip.rpntilecalculator.model.operands.OPolynom;
import de.fhdw.wip.rpntilecalculator.model.operands.Operand;


/*
 * Summary: Determine limit
 * Author:  Getuart Istogu
 * Date:    2020/01/04
 */
public class Limes extends Action {

    @NotNull private static final Limes LIMES = new Limes();

    @Contract(pure = true) @NotNull public static Limes getInstance() { return LIMES; }
    private Limes() { requiredNumOfOperands = new int[] {1,2};}

    @NotNull @Override
    public Operand with(@NotNull Operand... operands) throws CalculationException {
        scopedAction = this;
        return super.with(operands);
    }

    @Contract(pure = true) @NotNull ODouble on (@NotNull OPolynom oPolynom, @NotNull ODouble approach) {
        return limit(oPolynom, approach.getDouble());
    }

    /**
     * Calculates the limit of a function at one point
     * @param oPolynom Examined function
     * @param approach Limit point
     * @return Limit value at the defined point
     */
    public ODouble limit(OPolynom oPolynom, double approach) {
        PolynomialFunction polynomialFunction = oPolynom.getPolynom();
        double below = limitFromBelow(polynomialFunction, approach);
        double above = limitFromAbove(polynomialFunction, approach);
        if(below == above)
            return new ODouble(below);
        else
            return new ODouble(Double.NaN);
    }

    /**
     * Calculates the limit of a function at one point from below
     * @param polynomialFunction Examined function
     * @param approach Limit point
     * @return Limit value from below at the defined point
     */
    private double limitFromBelow(PolynomialFunction polynomialFunction, double approach) {

        for (double d = approach - 10; d <= approach; d = approach
                - ((approach - d) / 10)) {
            if (polynomialFunction.value(d) == Double.POSITIVE_INFINITY) {
                return Double.POSITIVE_INFINITY;
            } else if (polynomialFunction.value(d) == Double.NEGATIVE_INFINITY) {
                return Double.NEGATIVE_INFINITY;
            } else if (Double.isNaN(polynomialFunction.value(d))) {
                return polynomialFunction.value(approach + ((approach - d) * 10));
            } else {
                if (d == approach) {
                    return polynomialFunction.value(d);
                } else if (approach - d < 0.00000000001) {
                    d = approach;
                }
            }
        }
        return Double.NaN;
    }

    /**
     * Calculates the limit of a function at one point from above
     * @param polynomialFunction Examined function
     * @param approach Limit point
     * @return Limit value from above at the defined point
     */
    private double limitFromAbove(PolynomialFunction polynomialFunction, double approach) {

        for (double d = approach + 10; d >= approach; d = approach
                - ((approach - d) / 10)) {
            if (polynomialFunction.value(d) == Double.POSITIVE_INFINITY) {
                return Double.POSITIVE_INFINITY;
            } else if (polynomialFunction.value(d) == Double.NEGATIVE_INFINITY) {
                return Double.NEGATIVE_INFINITY;
            } else if (Double.isNaN(polynomialFunction.value(d))) {
                return polynomialFunction.value(approach + ((approach - d) * 10));
            } else {
                if (d == approach) {
                    return polynomialFunction.value(d);
                } else if (d - approach < 0.00000000001) {
                    d = approach;
                }
            }
        }
        return Double.NaN;
    }
}