package de.fhdw.wip.rpntilecalculator.core.calculation;

import org.junit.Test;

import de.fhdw.wip.rpntilecalculator.core.model.operand.ODouble;
import de.fhdw.wip.rpntilecalculator.core.model.operand.OFraction;
import de.fhdw.wip.rpntilecalculator.core.model.operand.OMatrix;
import de.fhdw.wip.rpntilecalculator.core.model.operand.OPolynom;
import de.fhdw.wip.rpntilecalculator.core.model.operand.OSet;
import de.fhdw.wip.rpntilecalculator.core.model.operand.OTuple;

import static org.junit.Assert.*;

@SuppressWarnings({"unused", "ResultOfMethodCallIgnored"})
public class PlusTest {

    private Plus PLUS = Plus.getInstance();

    @Test public void on_DoubleDouble_isCorrect() {
        assertTrue(PLUS.on(
                new ODouble(5),
                new ODouble(5)
                ).equalsValue(new ODouble(10))
        );
    }

    @Test public void on_DoubleFraction_isCorrect() {
        assertTrue(PLUS.on(
                new OFraction(2, 4),
                new OFraction(2, 4)
                ).equalsValue(new OFraction(1, 1))
        );
    }

    @Test public void on_FractionDouble_isCorrect() {
        assertTrue(PLUS.on(
                new OFraction(2, 2),
                new ODouble(5)
        ).equalsValue(new ODouble(6)));
    }

    @Test public void on_DoubleSet_isCorrect() {
        assertTrue(PLUS.on(
                new ODouble(1),
                new OSet(1, 2, 3)
        ).equalsValue(new OSet(2, 3, 4)));
    }

    @Test public void on_SetDouble_isCorrect() {
        assertTrue(PLUS.on(
                new OSet(1, 2, 3),
                new ODouble(1)
        ).equalsValue(new OSet(2, 3, 4)));
    }

    @Test public void on_DoubleMatrix_isCorrect() {
        assertTrue(PLUS.on(
                new ODouble(1),
                new OMatrix(new double[][]{
                        {1, 2, 3},
                        {1, 2, 3}
                })
        ).equalsValue(new OMatrix(new double[][]{
                {2, 3, 4},
                {2, 3, 4}
        })));
    }

    @Test public void on_MatrixDouble_isCorrect() {
        assertTrue(PLUS.on(
                new OMatrix(new double[][]{
                        {1, 2, 3},
                        {1, 2, 3}
                }),
                new ODouble(1)
        ).equalsValue(new OMatrix(new double[][]{
                {2, 3, 4},
                {2, 3, 4}
        })));
    }

    @Test public void on_DoublePolynom_isCorrect() {
        assertTrue(PLUS.on(
                new ODouble(1),
                new OPolynom(1, 2, 3)
        ).equalsValue(new OPolynom(2, 2, 3)));
    }

    @Test public void on_PolynomDouble_isCorrect() {
        assertTrue(PLUS.on(
                new OPolynom(1, 2, 3),
                new ODouble(1)
        ).equalsValue(new OPolynom(2, 2, 3)));
    }

    @Test public void on_DoubleTuple_isCorrect() {
        assertTrue(PLUS.on(
                new ODouble(1),
                new OTuple(1, 2, 3)
        ).equalsValue(new OTuple(2, 3, 4)));
    }

    @Test public void on_TupleDouble_isCorrect() {
        assertTrue(PLUS.on(
                new OTuple(1, 2, 3),
                new ODouble(1)
        ).equalsValue(new OTuple(2, 3, 4)));
    }

    @Test public void on_FractionFraction_isCorrect() {
        assertTrue(PLUS.on(
                new OFraction(1, 1),
                new OFraction(1, 1)
        ).equalsValue(new OFraction(2, 1)));
    }

    @Test public void on_FractionSet_isCorrect() {
        assertTrue(PLUS.on(
                new OFraction(1, 1),
                new OSet(1, 2, 3)
        ).equalsValue(new OSet(2, 3, 4)));
    }

    @Test public void on_SetFraction_isCorrect() {
        assertTrue(PLUS.on(
                new OSet(1, 2, 3),
                new OFraction(1, 1)
        ).equalsValue(new OSet(2, 3, 4)));
    }

    @Test public void on_FractionMatrix_isCorrect() {
        assertTrue(PLUS.on(
                new OFraction(1, 1),
                new OMatrix(new double[][]{
                        {1, 2, 3},
                        {1, 2, 3}
                })
        ).equalsValue(new OMatrix(new double[][]{
                {2, 3, 4},
                {2, 3, 4}
        })));
    }

    @Test public void on_MatrixFraction_isCorrect() {
        assertTrue(PLUS.on(
                new OMatrix(new double[][]{
                        {1, 2, 3},
                        {1, 2, 3}
                }),
                new OFraction(1, 1)
        ).equalsValue(new OMatrix(new double[][]{
                {2, 3, 4},
                {2, 3, 4}
        })));
    }

    @Test public void on_FractionPolynom_isCorrect() {
        assertTrue(PLUS.on(
                new OFraction(1, 1),
                new OPolynom(1, 2, 3)
        ).equalsValue(new OPolynom(2, 2, 3)));
    }

    @Test public void on_PolynomFraction_isCorrect() {
        assertTrue(PLUS.on(
                new OPolynom(1, 2, 3),
                new OFraction(1, 1)
        ).equalsValue(new OPolynom(2, 2, 3)));
    }

    @Test public void on_FractionTuple_isCorrect() {
        assertTrue(PLUS.on(
                new OFraction(1, 1),
                new OTuple(1, 2, 3)
        ).equalsValue(new OTuple(2, 3, 4)));
    }

    @Test public void on_TupleFraction_isCorrect() {
        assertTrue(PLUS.on(
                new OTuple(1, 2, 3),
                new OFraction(1, 1)
        ).equalsValue(new OTuple(2, 3, 4)));
    }

    @Test public void on_MatrixMatrix_isCorrect() {
        assertTrue(PLUS.on(
                new OMatrix(new double[][]{
                        {1, 2, 3},
                        {1, 2, 3}
                }),
                new OMatrix(new double[][]{
                        {1, 2, 3},
                        {1, 2, 3}
                })
        ).equalsValue(new OMatrix(new double[][]{
                {2, 4, 6},
                {2, 4, 6}
        })));
    }

    @Test public void on_MatrixMatrix_isWrongDimension() {
        try {
            PLUS.on(
                    new OMatrix(new double[][]{
                            {1, 2, 3},
                            {1, 2, 3}
                    }),
                    new OMatrix(new double[][]{
                            {1, 2, 3}
                    })
            );
        } catch (RuntimeException e) {
            assertTrue("Should always fail", true);
        }
    }

    @Test public void on_PolynomPolynom_isCorrect() {
        assertTrue(PLUS.on(
                new OPolynom(1, 2, 3),
                new OPolynom(0, 0, 3, 4, 5)
        ).equalsValue(new OPolynom(1, 2, 6, 4 ,5)));
    }

    @Test public void on_TupleTuple_isCorrect() {
        assertTrue(PLUS.on(
                new OTuple(1, 2, 3),
                new OTuple(1, 2, 3)
                ).equalsValue(new OTuple(2, 4, 6))
        );
    }

    @Test public void on_TupleTuple_isWrongDimension() {
        try {
            PLUS.on(
                    new OTuple(1, 2, 3),
                    new OTuple(1, 2, 3, 4)
            );
            fail();
        } catch (RuntimeException e) {
            assertTrue("Should always fail", true);
        }
    }
}