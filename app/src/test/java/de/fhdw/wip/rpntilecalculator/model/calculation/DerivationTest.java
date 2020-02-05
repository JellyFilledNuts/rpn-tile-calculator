package de.fhdw.wip.rpntilecalculator.model.calculation;

import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;
import org.junit.Test;

import de.fhdw.wip.rpntilecalculator.model.operands.OPolynom;
import de.fhdw.wip.rpntilecalculator.model.operands.OTuple;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/*
 * Summary: Unit test for the derivation
 * Author:  Jannis Luca Keienburg
 * Date:    2020/01/23
 */

public class DerivationTest {
    private Derivation DERIVATION = Derivation.getInstance();

    @Test
    public void derivation_isCorrect1() {
        double[] functionValues = new double[] {2, 4, 6};
        OPolynom function = new OPolynom(new PolynomialFunction(functionValues));
        double[] resultValues = new double[] {4, 4};
        OPolynom derivation = new OPolynom(new PolynomialFunction(resultValues));
        assertEquals(DERIVATION.on(function), derivation);
    }

    @Test
    public void derivation_isCorrect2() {
        double[] functionValues = new double[] {7, 9};
        OPolynom function = new OPolynom(new PolynomialFunction(functionValues));
        double[] resultValues = new double[] {7};
        OPolynom derivation = new OPolynom(new PolynomialFunction(resultValues));
        assertEquals(DERIVATION.on(function), derivation);
    }

    @Test
    public void derivation_isCorrect3() {
        double[] functionValues = new double[] {12, -3, 12};
        OPolynom function = new OPolynom(new PolynomialFunction(functionValues));
        double[] resultValues = new double[] {24, -3};
        OPolynom derivation = new OPolynom(new PolynomialFunction(resultValues));
        assertEquals(DERIVATION.on(function), derivation);
    }

    @Test
    public void derivation_isCorrect5() {
        double[] functionValues = new double[] {1.5, 2, 7};
        OPolynom function = new OPolynom(new PolynomialFunction(functionValues));
        double[] resultValues = new double[] {2,25, 2};
        OPolynom derivation = new OPolynom(new PolynomialFunction(resultValues));
        assertEquals(DERIVATION.on(function), derivation);
    }
}
