package de.fhdw.wip.rpntilecalculator.model.calculation;

import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import de.fhdw.wip.rpntilecalculator.model.operands.OPolynom;

/*
 * Summary: A Class that can calculate the high and the low points of a function( up to third grade)
 * Author:  Jannis Luca Keienburg
 * Date:    2020/01/23
 */

public class HighAndLowPoints extends Action {

    @NotNull private static final HighAndLowPoints HIGH_AND_LOW_POINTS= new HighAndLowPoints();

    @Contract(pure = true) @NotNull public static HighAndLowPoints getInstance() { return HIGH_AND_LOW_POINTS; }
    private HighAndLowPoints() { }


    // Structure: via the method getCoefficients()
    // coefficients[n] * x^n + ... + coefficients[1] * x + coefficients[0]

    @NotNull
    public OPolynom getDerivative(OPolynom oPolynom)
    {
        PolynomialFunction polynomialDerivative = oPolynom.getPolynom().polynomialDerivative();
        return new OPolynom(polynomialDerivative);
    }

    // Begin. Returns an double array with the following structure
    //
    public void getHighAndLowPoints(OPolynom oPolynom)
    {
        double[] function = getFunctionAsDouble(oPolynom);

    }

    // Sets the Function into the following formatition:
    // [n]* x^10 + [n-1] *x^9 + ... + [1] * x + [0]
    private double[] getFunctionAsDouble(OPolynom oPolynom) {
        return oPolynom.getPolynom().getCoefficients();
    }

    //
    private void calculateHighAndLowPoints(double[] function)
    {
        Derivation derivation = new Derivation();
        // Aufruf der methode in der Klasse für die Ableitung

        //double[] derivatedFunction = derivation.

        // Aufruf der Methode zur berechnung der NUllstellen
        Zeros zeros = new Zeros();
        // double [] zerosOfFuntion = zeros.calculateZeros(derivatedFunction)

        // Berechnen der Werte der beiden Nullstellen
        // doppelte forschleife für die nullstelle  in zeros
        double result = 0;
        for(int i = function.length -1; i >= 0; i--)
        {
            //result += function[i] * VARAUSDEMDOUBLEDERNULLSTELLEN ^i;
        }
        //ergebnis = VARAUSDEMDOUBLEDERNULLSTELLEN/result als ein Hoch bzw. tiefpunkt
    }


}
