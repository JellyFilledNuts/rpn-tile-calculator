package de.fhdw.wip.rpntilecalculator.core.calculation;

import org.junit.Test;

import de.fhdw.wip.rpntilecalculator.core.model.operand.ODouble;
import de.fhdw.wip.rpntilecalculator.core.model.operand.OFraction;
import de.fhdw.wip.rpntilecalculator.core.model.operand.OMatrix;
import de.fhdw.wip.rpntilecalculator.core.model.operand.OPolynom;
import de.fhdw.wip.rpntilecalculator.core.model.operand.OSet;
import de.fhdw.wip.rpntilecalculator.core.model.operand.OTuple;

import static org.junit.Assert.*;

public class TimesTest {

    private Times TIMES = Times.getInstance();

    @Test public void on_DoubleDouble_isCorrect() {
        assertTrue(TIMES.on(
                        new ODouble(5),
                        new ODouble(5)
        ).equalsValue(new ODouble(25)));
    }

    @Test public void on_DoubleFraction_isCorrect() {
        assertTrue(TIMES.on(
                new ODouble(5),
                new OFraction(5, 1)
        ).equalsValue(new ODouble(25)));
    }

    @Test public void on_FractionDouble_isCorrect() {
        assertTrue(TIMES.on(
                new OFraction(1, 1),
                new ODouble(5)
        ).equalsValue(new ODouble(5)));
    }

    @Test public void on_DoubleSet_isCorrect() {
        assertTrue(TIMES.on(
                new ODouble(2),
                new OSet(1, 2)
        ).equalsValue(new OSet(2, 4)));
    }

    @Test public void on_SetDouble_isCorrect() {
        assertTrue(TIMES.on(
                new OSet(1, 2),
                new ODouble(2)
        ).equalsValue(new OSet(2, 4)));
    }

    @Test public void on_DoubleMatrix_isCorrect() {
        assertTrue(TIMES.on(
                new ODouble(2),
                new OMatrix(new double[][]{{1, 2}})
        ).equalsValue(new OMatrix(new double[][]{{2, 4}})));
    }

    @Test public void on_MatrixDouble_isCorrect() {
        assertTrue(TIMES.on(
                new OMatrix(new double[][]{{1, 2}}),
                new ODouble(2)
        ).equalsValue(new OMatrix(new double[][]{{2, 4}})));
    }

    @Test public void on_DoublePolynom_isCorrect() {
        assertTrue(TIMES.on(
                new ODouble(2),
                new OPolynom(0, 1, 2)
        ).equalsValue(new OPolynom(0, 2, 4)));
    }

    @Test public void on_PolynomDouble_isCorrect() {
        assertTrue(TIMES.on(
                new OPolynom(0, 1, 2),
                new ODouble(2)
        ).equalsValue(new OPolynom(0, 2, 4)));
    }

    @Test public void on_DoubleTuple_isCorrect() {
        assertTrue(TIMES.on(
                new ODouble(2),
                new OTuple(1, 2)
        ).equalsValue(new OTuple(2, 4)));
    }

    @Test public void on_TupleDouble_isCorrect() {
        assertTrue(TIMES.on(
                new OTuple(1, 2),
                new ODouble(2)
        ).equalsValue(new OTuple(2, 4)));
    }

    @Test public void on_FractionFraction_isCorrect() {
        assertTrue(TIMES.on(
                new OFraction(2, 1),
                new OFraction(2, 1)
        ).equalsValue(new OFraction(4, 1)));
    }

    @Test public void on_FractionSet_isCorrect() {
        assertTrue(TIMES.on(
                new OFraction(1, 1),
                new OSet(1, 2)
        ).equalsValue(new OSet(1, 2)));
    }

    @Test public void on_FractionMatrix_isCorrect() {
        assertTrue(TIMES.on(
                new OFraction(1, 1),
                new OMatrix(new double[][]{
                        {1, 2},
                        {1, 2}
                })
        ).equalsValue(new OMatrix(new double[][]{
                {1, 2},
                {1, 2}
        })));
    }

    @Test public void on_FractionPolynom_isCorrect() {
        assertTrue(TIMES.on(
                new OFraction(1, 1),
                new OPolynom(0, 1, 3)
        ).equalsValue(new OPolynom(0, 1, 3)));
    }

    @Test public void on_FractionTuple_isCorrect() {
        assertTrue(TIMES.on(
                new OFraction(1, 1),
                new OTuple(1, 2)
        ).equalsValue(new OTuple(1, 2)));
    }

    @Test public void on_MatrixMatrix_isCorrect() {
        assertTrue(TIMES.on(
                new OMatrix(new double[][]{
                        {1, 2},
                        {1, 2}
                }),
                new OMatrix(new double[][]{
                        {1, 2},
                        {1, 2}
                })
        ).equalsValue(new OMatrix(new double[][]{
                {3, 6},
                {3, 6}
        })));
    }

    @Test public void on_PolynomPolynom_isCorrect() {
        assertTrue(TIMES.on(
                new OPolynom(1, 2),
                new OPolynom(2, 3)
        ).equalsValue(new OPolynom(2, 7, 6)));
    }

    @Test public void on_TupleTuple_isCorrect() {
        assertTrue(TIMES.on(
                new OTuple(1, 2),
                new OTuple(1, 2)
        ).equalsValue(new OTuple(1, 4)));
    }
}