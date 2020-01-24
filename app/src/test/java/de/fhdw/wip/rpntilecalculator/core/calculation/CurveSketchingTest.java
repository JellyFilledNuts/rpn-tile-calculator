package de.fhdw.wip.rpntilecalculator.core.calculation;

import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

import de.fhdw.wip.rpntilecalculator.core.model.operand.ODouble;
import de.fhdw.wip.rpntilecalculator.core.model.operand.OPolynom;

public class CurveSketchingTest {
    private CurveSketching CURVE_SKETCHING = CurveSketching.getInstance();
    private Root ROOT = Root.getInstance();

    @Test public void getDerivative_isCorrect() {
        double[] inputCoefficients = new double[]{1, 3, 5};
        double[] resultCoefficients = new double[]{3, 10};

        OPolynom inputFunction = new OPolynom(new PolynomialFunction(inputCoefficients));
        OPolynom resultFunction = new OPolynom(new PolynomialFunction(resultCoefficients));

        assertTrue(CURVE_SKETCHING.getDerivative(inputFunction)
                .equalsValue(resultFunction));
    }

    @Test public void getSimpsonIntegrator_isCorrect() {
        double[] inputCoefficients = new double[]{17, -8, 1};
        OPolynom inputFunction = new OPolynom(new PolynomialFunction(inputCoefficients));

        assertEquals(CURVE_SKETCHING.getSimpsonIntegrator
                (inputFunction, 2, 5), 6, 0.001);
    }

    @Test public void getZeroPoints_isCorrect() {

    }
}
