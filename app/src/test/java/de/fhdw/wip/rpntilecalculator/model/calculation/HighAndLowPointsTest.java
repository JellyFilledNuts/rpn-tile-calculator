package de.fhdw.wip.rpntilecalculator.model.calculation;

import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;
import org.junit.Test;

import de.fhdw.wip.rpntilecalculator.model.operands.OPolynom;
import de.fhdw.wip.rpntilecalculator.model.operands.OTuple;

import static org.junit.Assert.assertEquals;

/*
 * Summary: Unit test for the calculation of high and low points
 * Author:  Jannis Luca Keienburg
 * Date:    2020/01/23
 */

public class HighAndLowPointsTest {
    private HighAndLowPoints HIGHANDLOWPOINTS = HighAndLowPoints.getInstance();

    @Test
    public void highAndLowPoints_isCorrect1() {
        double[] functionValues = new double[]{2, 4, 6};
        OPolynom function = new OPolynom(new PolynomialFunction(functionValues));
        double[] resultValues = new double[]{-1, 4};
        OTuple extremPoints = new OTuple(resultValues);
        assertEquals(HIGHANDLOWPOINTS.on(function), extremPoints);
    }

    @Test
    public void highAndLowPoints_isCorrect2() {
        double[] functionValues = new double[]{3, 6,-4};
        OPolynom function = new OPolynom(new PolynomialFunction(functionValues));
        double[] resultValues = new double[]{-1, -7};
        OTuple extremPoints = new OTuple(resultValues);
        assertEquals(HIGHANDLOWPOINTS.on(function), extremPoints);
    }

    @Test
    public void highAndLowPoints_isCorrect3() {
        double[] functionValues = new double[]{-2, 4, 12};
        OPolynom function = new OPolynom(new PolynomialFunction(functionValues));
        double[] resultValues = new double[]{1, 14};
        OTuple extremPoints = new OTuple(resultValues);
        assertEquals(HIGHANDLOWPOINTS.on(function), extremPoints);
    }
}
