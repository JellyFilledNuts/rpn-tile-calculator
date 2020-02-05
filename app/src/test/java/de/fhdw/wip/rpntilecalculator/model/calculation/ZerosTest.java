package de.fhdw.wip.rpntilecalculator.model.calculation;

import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;
import org.junit.Test;
import de.fhdw.wip.rpntilecalculator.model.operands.ODouble;
import de.fhdw.wip.rpntilecalculator.model.operands.OPolynom;
import de.fhdw.wip.rpntilecalculator.model.operands.OTuple;
import static org.junit.Assert.assertTrue;

/*
 * Summary: Unit test for the calculation of the zeros
 * Author:  Jannis Luca Keienburg
 * Date:    2020/01/23
 */

public class ZerosTest {
    private Zeros ZEROS = Zeros.getInstance();

    @Test
    public void zeros_isCorrect1() {
        double[] functionValues = new double[] {2, 4};
        OPolynom function = new OPolynom(new PolynomialFunction(functionValues));
        OTuple result = new OTuple(-2);
        System.out.println(ZEROS.on(function));
        assertTrue(ZEROS.on(new OPolynom(new PolynomialFunction(functionValues)))
                .equalsValue(result));
    }

    @Test
    public void zeros_isCorrect2() {
        double[] functionValues = new double[] {2, 4, 0};
        OPolynom function = new OPolynom(new PolynomialFunction(functionValues));
        OTuple result = new OTuple(-2, 0);
        System.out.println(ZEROS.on(function));
        assertTrue(ZEROS.on(new OPolynom(new PolynomialFunction(functionValues)))
                .equalsValue(result));
    }

    @Test
    public void zeros_isCorrect3() {
        double[] functionValues = new double[] {1, 4, -4};
        OPolynom function = new OPolynom(new PolynomialFunction(functionValues));
        OTuple result = new OTuple(-4.828, 0.828);
        System.out.println(ZEROS.on(function));
        assertTrue(ZEROS.on(new OPolynom(new PolynomialFunction(functionValues)))
                .equalsValue(result));
    }
}
