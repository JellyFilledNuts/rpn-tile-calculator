package de.fhdw.wip.rpntilecalculator.model.calculation;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.integration.SimpsonIntegrator;
import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import de.fhdw.wip.rpntilecalculator.model.operands.ODouble;
import de.fhdw.wip.rpntilecalculator.model.operands.OPolynom;
import de.fhdw.wip.rpntilecalculator.model.operands.Operand;

/*
 * Summary: Calculate the antiderivative and integral
 * Author:  Getuart Istogu
 * Date:    2020/01/27
 */

public class Integral extends Action {

    @NotNull private static final Integral INTEGRAL = new Integral();

    @Contract(pure = true) @NotNull public static Integral getInstance() { return INTEGRAL; }
    private Integral() {requiredNumOfOperands = new int[] {1, 2, 3};}

    @NotNull @Override
    public Operand with(@NotNull Operand... operands) throws CalculationException {
        scopedAction = this;
        return super.with(operands);
    }

    @Contract(pure = true) @NotNull ODouble on(@NotNull OPolynom oPolynom, @NotNull ODouble lowerBound, @NotNull ODouble upperBound) {
        return calculateIntegralSimpsons(oPolynom, lowerBound.getDouble(), upperBound.getDouble());
    }
    
    /**
     * Calculates the integral for the specified limit range
     * @param oPolynom Normal PolynomialFunction, not its antiderivative
     * @param lowerBound Lower limit
     * @param upperBound Upper limit
     * @return
     */
    @NotNull
    public ODouble calculateIntegralSimpsons(OPolynom oPolynom, double lowerBound, double upperBound)
    {
        SimpsonIntegrator simpsonIntegrator = new SimpsonIntegrator();
        UnivariateFunction uF = (UnivariateFunction) oPolynom.getPolynom();
        return new ODouble(simpsonIntegrator.integrate(10000, uF, lowerBound, upperBound));
    }

    /**
     * Calculates the antiderivative of the passed function
     * @param oPolynom Examined function
     * @return Its antiderivative
     */
    public OPolynom getAntiderivative(OPolynom oPolynom)
    {
        PolynomialFunction polynomialFunction = oPolynom.getPolynom();

        // 3x^2 -2x +5 == 3rd degree, but 4 coefficients
        // Therefore number of coefficient antiderivative = degree + 2
        int degreeForAntiderivative = polynomialFunction.degree() + 2;

        double[] functionCoefficents = polynomialFunction.getCoefficients();

        double[] antiderivativeCoefficents = new double[degreeForAntiderivative];

        // This value can be of any size. It is referred as "C" in the literature.
        antiderivativeCoefficents[0] = 0;
        for(int i = 1; i < degreeForAntiderivative; i++)
        {
            antiderivativeCoefficents[i] = functionCoefficents[i-1]/((double) i);
        }

        return new OPolynom(new PolynomialFunction(antiderivativeCoefficents));
    }
}