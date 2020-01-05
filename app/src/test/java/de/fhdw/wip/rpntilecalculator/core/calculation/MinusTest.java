package de.fhdw.wip.rpntilecalculator.core.calculation;

import org.junit.Test;

import de.fhdw.wip.rpntilecalculator.core.model.operand.ODouble;
import de.fhdw.wip.rpntilecalculator.core.model.operand.OFraction;
import de.fhdw.wip.rpntilecalculator.core.model.operand.OMatrix;
import de.fhdw.wip.rpntilecalculator.core.model.operand.OPolynom;
import de.fhdw.wip.rpntilecalculator.core.model.operand.OSet;
import de.fhdw.wip.rpntilecalculator.core.model.operand.OTuple;

import static org.junit.Assert.*;

@SuppressWarnings("ResultOfMethodCallIgnored")
public class MinusTest {

    private Minus MINUS = Minus.getInstance();

    @Test public void on_DoubleDouble_isCorrect() {
        System.out.println(MINUS.on(
                new ODouble(5),
                new ODouble(5)
        ));

        assertTrue(MINUS.on(
                new ODouble(5),
                new ODouble(5)
                ).equalsValue(new ODouble(0)));
    }

    @Test public void on_DoubleFraction_isCorrect() {
        assertTrue(MINUS.on(
                new ODouble(5),
                new OFraction(1, 2)
        ).equalsValue(new ODouble(4.5)));
    }

    @Test public void on_FractionFraction_isCorrect() {
        assertTrue(MINUS.on(
                new OFraction(1, 1),
                new OFraction(1, 1)
        ).equalsValue(new OFraction(0)));
    }

    @Test public void on_FractionDouble_isCorrect() {
        assertTrue(MINUS.on(
                new OFraction(1, 1),
                new ODouble(1)
        ).equalsValue(new ODouble(0)));
    }

    @Test public void on_SetDouble_isCorrect() {
        assertTrue(MINUS.on(
                new OSet(1, 2),
                new ODouble(1)
        ).equalsValue(new OSet(0, 1)));
    }

    @Test public void on_SetFraction_isCorrect() {
        assertTrue(MINUS.on(
                new OSet(1, 2),
                new OFraction(1, 1)
        ).equalsValue(new OSet(0, 1)));
    }

    @Test public void on_MatrixMatrix_isCorrect() {
        assertTrue(MINUS.on(
                new OMatrix(new double[][]{
                        {1d, 2d, 3d},
                        {1d, 2d, 3d},
                        {1d, 2d, 3d}
                }),
                new OMatrix(new double[][]{
                        {1d, 2d, 3d},
                        {1d, 2d, 3d},
                        {1d, 2d, 3d}
                })
        ).equalsValue(new OMatrix(new double[][]{
                {0d, 0d, 0d},
                {0d, 0d, 0d},
                {0d, 0d, 0d}
        })));
    }

    @Test public void on_MatrixMatrix_isWrongDimension() {
        try {
            MINUS.on(
                    new OMatrix(new double[][]{
                            {1d, 2d, 3d},
                            {1d, 2d, 3d},
                            {1d, 2d, 3d}
                    }),
                    new OMatrix(new double[][]{
                            {1d, 1d},
                            {2d, 3d}
                    })
            );
            fail();
        } catch (RuntimeException e) {
            assertTrue("Should always fail", true);
        }
    }

    @Test public void on_MatrixDouble_isCorrect() {
        assertTrue(MINUS.on(
               new OMatrix(new double[][]{
                       {1, 2, 3},
                       {1, 2, 3}
               }),
               new ODouble(1)
        ).equalsValue(new OMatrix(new double[][]{
                {0, 1, 2},
                {0, 1, 2}
        })));
    }

    @Test public void on_MatrixFraction_isCorrect() {
        assertTrue(MINUS.on(
                new OMatrix(new double[][]{
                        {1, 2, 3},
                        {1, 2, 3}
                }),
                new OFraction(1, 1)
        ).equalsValue(new OMatrix(new double[][]{
                {0, 1, 2},
                {0, 1, 2}
        })));
    }

    @Test public void onPolynomPolynom_isCorrect() {
        assertTrue(MINUS.on(
                new OPolynom(1, 2, 3),
                new OPolynom(2, 0, 5, 6)
        ).equalsValue(new OPolynom(-1, 2, -2, -6)));
    }

    @Test public void on_PolynomFraction_isCorrect() {
        assertTrue(MINUS.on(
                new OPolynom(1, 2, 3),
                new OFraction(1, 1)
        ).equalsValue(new OPolynom(0, 2, 3)));
    }

    @Test public void on_PolynomDouble_isCorrect() {
        assertTrue(MINUS.on(
                new OPolynom(1, 2, 3),
                new ODouble(1)
        ).equalsValue(new OPolynom(0, 2, 3)));
    }

    @Test public void on_TupleTuple_isCorrect() {
        assertTrue(MINUS.on(
                new OTuple(1, 2),
                new OTuple(1, 2)
        ).equalsValue(new OTuple(0, 0)));
    }

    @Test public void on_TupleDouble_isCorrect() {
        assertTrue(MINUS.on(
                new OTuple(1, 2),
                new ODouble(1)
        ).equalsValue(new OTuple(0, 1)));
    }

    @Test public void on_TupleFraction_isCorrect() {
        assertTrue(MINUS.on(
                new OTuple(1, 2),
                new OFraction(1, 1)
        ).equalsValue(new OTuple(0, 1)));
    }
}