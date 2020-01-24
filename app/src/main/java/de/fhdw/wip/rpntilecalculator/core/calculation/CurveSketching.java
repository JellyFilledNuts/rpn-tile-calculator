package de.fhdw.wip.rpntilecalculator.core.calculation;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.integration.SimpsonIntegrator;
import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;
import org.apache.commons.math3.analysis.solvers.LaguerreSolver;

import org.apache.commons.math3.complex.Complex;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;

import de.fhdw.wip.rpntilecalculator.core.model.operand.ODouble;
import de.fhdw.wip.rpntilecalculator.core.model.operand.OPolynom;

/*
 * Summary: Defines methods, which are typically needed for curve sketching
 * Author:  Getuart Istogu
 * Date:    2020/01/04
 */
public class CurveSketching extends Action {

    @NotNull private static final CurveSketching CURVE_SKETCHING = new CurveSketching();

    @Contract(pure = true) @NotNull public static CurveSketching getInstance() { return CURVE_SKETCHING; }
    private CurveSketching() { }


    // Structure: via the method getCoefficients()
    // coefficients[n] * x^n + ... + coefficients[1] * x + coefficients[0]

    /**
     * Berechnet die nächste Ableitung.
     * @return
     */
    @NotNull
    public OPolynom getDerivative(OPolynom oPolynom)
    {
        PolynomialFunction polynomialDerivative = oPolynom.getPolynom().polynomialDerivative();
        return new OPolynom(polynomialDerivative);
    }

    /**
     * Berechnet das Integral für den angegebenen Grenzbereich
     * @param lowerBound untere Grenze
     * @param upperBound übere Grenze
     * @return
     */
    @NotNull public double getSimpsonIntegrator(OPolynom oPolynom, double lowerBound, double upperBound)
    {
        SimpsonIntegrator simpsonIntegrator = new SimpsonIntegrator();
        UnivariateFunction uF = (UnivariateFunction) oPolynom.getPolynom();
        return simpsonIntegrator.integrate(10000, uF, lowerBound, upperBound);
    }

    /**
     * Berechnet die Nullstellen.
     * Die Funktion muss nicht abgeleitet werden!
     * @param lowerBound untere Grenze
     * @param upperBound übere Grenze
     * @return Eine Liste an Nullstellen. Wenn null, dann keine Nullstellen
     */
    public ArrayList<ODouble> getZeroPoints(OPolynom oPolynom, double lowerBound, double upperBound)
    {
        return null;
    }


    public double limit(OPolynom oPolynom, double appraoch) {
        PolynomialFunction polynomialFunction = oPolynom.getPolynom();
        double below = limitFromBelow(polynomialFunction, appraoch);
        double above = limitFromAbove(polynomialFunction, appraoch);
        if(below == above)
            return below;
        else
            return Double.NaN;
    }

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
