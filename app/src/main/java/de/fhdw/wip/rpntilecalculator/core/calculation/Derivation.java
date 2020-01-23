package de.fhdw.wip.rpntilecalculator.core.calculation;

import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import de.fhdw.wip.rpntilecalculator.core.model.operand.OPolynom;

/*
 * Summary: A Class that can calculate the derivate of a function
 * Author:  Jannis Luca Keienburg
 * Date:    2020/01/23
*/

public class Derivation extends Action {

    @NotNull private static final Derivation DERIVATION = new Derivation();

    @Contract(pure = true) @NotNull public static Derivation getInstance() { return DERIVATION; }
    private Derivation() { }


    // Structure: via the method getCoefficients()
    // coefficients[n] * x^n + ... + coefficients[1] * x + coefficients[0]

    @NotNull
    public OPolynom getDerivative(OPolynom oPolynom)
    {
        PolynomialFunction polynomialDerivative = oPolynom.getPolynom().polynomialDerivative();
        return new OPolynom(polynomialDerivative);
    }

    // Sets the Function into the following formatition:
    // [n]* x^10 + [n-1] *x^9 + ... + [1] * x + [0]
    private double[] getFunctionAsDouble(OPolynom oPolynom) {
        return oPolynom.getPolynom().getCoefficients();
    }

    // Calculates the derivation
    // returns it as an double array
    public double[] derivate(OPolynom oPolynom)
    {
        double[] function = getFunctionAsDouble(oPolynom);
        double[] derivation = new double[function.length - 1];

        for(int i = function.length - 1; i > 0; i--)
        {
            derivation[i-1] = function[i] * i;
        }
        return derivation;
    }
}
