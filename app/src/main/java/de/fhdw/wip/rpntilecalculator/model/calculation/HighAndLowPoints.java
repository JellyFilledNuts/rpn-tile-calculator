package de.fhdw.wip.rpntilecalculator.model.calculation;

import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;

import org.intellij.lang.annotations.JdkConstants;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import de.fhdw.wip.rpntilecalculator.model.operands.OPolynom;
import de.fhdw.wip.rpntilecalculator.model.operands.OTuple;
import de.fhdw.wip.rpntilecalculator.model.operands.Operand;

/*
 * Summary: A Class that can calculate the high and the low points of a function( up to third grade)
 * Author:  Jannis Luca Keienburg
 * Date:    2020/01/16, updated on 2020/01/17
 */

public class HighAndLowPoints extends Action {

    @NotNull private static final HighAndLowPoints HIGH_AND_LOW_POINTS = new HighAndLowPoints();
    @NotNull private static final Derivation DERIVATION = Derivation.getInstance();
    @NotNull private static final Zeros ZEROS = Zeros.getInstance();

    @Contract(pure = true) @NotNull public static HighAndLowPoints getInstance() { return HIGH_AND_LOW_POINTS; }
    private HighAndLowPoints() {
        requiredNumOfOperands = new int[]{1};
    }

    @NotNull @Override
    public Operand with(@NotNull Operand... operands) throws CalculationException {
        scopedAction = this;
        return super.with(operands);
    }

    @Contract(pure = true) @NotNull OTuple on(@NotNull OPolynom oPolynom) {
        return new OTuple(getHighAndLowPoints(oPolynom));
    }

    // Begin. Returns an double array with the following structure
    public double [] getHighAndLowPoints(OPolynom oPolynom)
    {
        double [] result = calculateHighAndLowPoints(oPolynom);
        return result;
    }

    // Sets the Function into the following formatition:
    // [n]* x^10 + [n-1] *x^9 + ... + [1] * x + [0]
    private double[] getFunctionAsDouble(OPolynom oPolynom) {
        return oPolynom.getPolynom().getCoefficients();
    }

    // Calculates the values of the extreme points of a given function.
    // Returns the values as an double array, an uneyual number position stands for the x value,
    // the number at the next position for the y value
    private double[] calculateHighAndLowPoints(OPolynom oPolynom)
    {
        // Calculates the derivation of the funcion.
        OPolynom derivation = DERIVATION.derivate(oPolynom);
        // Calculates the zeros of the function's derivation.
        double [] zeros = ZEROS.calculateZeros(derivation);
        // Gets the funcion as an double array for the calculation.
        double [] functionAsDouble = getFunctionAsDouble(oPolynom);

        // Calculates the y values of the zeros.
        double [] valuesXY = new double[2*zeros.length];
        int position = -1;
        for(int counter = 0; counter < zeros.length; counter++)
        {
            double currentZero = zeros[counter];
            double yValue = 0;
            yValue = functionAsDouble[0] + functionAsDouble[1] * currentZero + Math.pow(currentZero, 2) * functionAsDouble[2];
            position++;
            valuesXY[position] = currentZero;
            position++;
            valuesXY[position] = yValue;
        }
        return valuesXY;
    }
}
