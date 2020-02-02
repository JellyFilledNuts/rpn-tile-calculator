package de.fhdw.wip.rpntilecalculator.model.calculation;

import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import de.fhdw.wip.rpntilecalculator.model.operands.OPolynom;
import de.fhdw.wip.rpntilecalculator.model.operands.Operand;

/*
 * Summary: A Class that can calculate the derivate of a function
 * Author:  Jannis Luca Keienburg
 * Date:    2020/01/23
 */

public class Derivation extends Action {

    @NotNull public static final Derivation DERIVATION = new Derivation();

    @Contract(pure = true) @NotNull public static Derivation getInstance() { return DERIVATION; }
    public Derivation() {    }

    @NotNull @Override
    public Operand with(@NotNull Operand... operands) throws CalculationException {
        scopedAction = this;
        return super.with(operands);
    }

    @Contract(pure = true) @NotNull OPolynom on(@NotNull OPolynom oPolynom) {
        return derivate(oPolynom);
    }

    // Structure: via the method getCoefficients()
    // coefficients[n] * x^n + ... + coefficients[1] * x + coefficients[0]

    // Sets the Function into the following formatition:
    // [n]* x^10 + [n-1] *x^9 + ... + [1] * x + [0]
    private double[] getFunctionAsDouble(OPolynom oPolynom) {
        return oPolynom.getPolynom().getCoefficients();
    }

    // Calculates the derivation
    // returns it as an OPolynom
    public OPolynom derivate(OPolynom oPolynom)
    {
        double[] function = getFunctionAsDouble(oPolynom);
        double[] derivation = new double[function.length - 1];

        for(int i = function.length - 1; i > 0; i--)
        {
            derivation[i-1] = function[i] * i;
        }
        return new OPolynom(new PolynomialFunction(derivation));
    }
}
