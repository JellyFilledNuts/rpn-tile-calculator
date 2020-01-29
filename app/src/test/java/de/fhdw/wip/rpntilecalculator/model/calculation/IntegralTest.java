package de.fhdw.wip.rpntilecalculator.model.calculation;

import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;
import org.junit.Test;

import de.fhdw.wip.rpntilecalculator.model.operands.OPolynom;

import static org.junit.Assert.*;

/*
 * Summary: Test for the class 'Integral'
 * Author:  Getuart Istogu
 * Date:    2020/01/27
 */

public class IntegralTest {
    private Integral INTEGRAL = Integral.getInstance();

    @Test public void getSimpsonIntegrator_isCorrect() {
        double[] inputCoefficients = new double[]{17, -8, 1};
        OPolynom inputFunction = new OPolynom(new PolynomialFunction(inputCoefficients));

        assertEquals(INTEGRAL.calculateIntegralSimpsons
                (inputFunction, 2, 5).getDouble(), 6, 0.001);
    }

    @Test
    public void getAntiderivative_isCorrect() {

        OPolynom antiderivative = INTEGRAL.getAntiderivative(new OPolynom(new double[] {
                7, 3, 0, -3.21
        }));

        double[] expectedCoefficient = new double[] {
                0, 7, 1.5, 0, -0.8025
        };

        double[] coefficient = antiderivative.getPolynom().getCoefficients();

        assertEquals(coefficient[0], expectedCoefficient[0], 0.001);
        assertEquals(coefficient[1], expectedCoefficient[1], 0.001);
        assertEquals(coefficient[2], expectedCoefficient[2], 0.001);
        assertEquals(coefficient[3], expectedCoefficient[3], 0.001);
        assertEquals(coefficient[4], expectedCoefficient[4], 0.001);
    }
}