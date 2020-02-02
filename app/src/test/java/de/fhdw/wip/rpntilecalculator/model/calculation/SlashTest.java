package de.fhdw.wip.rpntilecalculator.model.calculation;

import org.junit.Test;

import de.fhdw.wip.rpntilecalculator.model.operands.ODouble;
import de.fhdw.wip.rpntilecalculator.model.operands.OFraction;
import de.fhdw.wip.rpntilecalculator.model.operands.OMatrix;
import de.fhdw.wip.rpntilecalculator.model.operands.OPolynom;
import de.fhdw.wip.rpntilecalculator.model.operands.OSet;
import de.fhdw.wip.rpntilecalculator.model.operands.OTuple;

import static org.junit.Assert.*;

@SuppressWarnings("ResultOfMethodCallIgnored")
public class SlashTest {

    private Slash SLASH = Slash.getInstance();

    @Test public void on_DoubleDouble_isCorrect() {
        assertTrue(SLASH.on(
                new ODouble(4),
                new ODouble(2)
        ).equalsValue(new ODouble(2)));
    }

    @Test public void on_DoubleDouble_isDividedByZero() {
        try {
            SLASH.on(
                    new ODouble(4),
                    new ODouble(0)
            );
            fail();
        } catch (RuntimeException e) {
            assertTrue(true);
        }
    }

    @Test public void on_DoubleFraction_isCorrect() {
        assertTrue(SLASH.on(
                new ODouble(10),
                new OFraction(1, 2)
        ).equalsValue(new ODouble(20)));
    }

    @Test public void on_DoubleFraction_isDividedByZero() {
        try {
            SLASH.on(
                    new ODouble(4),
                    new OFraction(5, 0)
            );
            fail();
        } catch (RuntimeException e) {
            assertTrue(true);
        }
    }

    @Test public void on_FractionFraction_isCorrect() {
        assertTrue(SLASH.on(
                new OFraction(1, 2),
                new OFraction(1, 2)
        ).equalsValue(new OFraction(1, 1)));
    }

    @Test public void on_FractionDouble_isCorrect() {
        assertTrue(SLASH.on(
                new OFraction(1, 1),
                new ODouble(0.5)
        ).equalsValue(new ODouble(2)));
    }

    @Test public void on_FractionDouble_isDividedByZero() {
        try {
            SLASH.on(
                    new OFraction(1, 1),
                    new ODouble(0)
            );
            fail();
        } catch (RuntimeException e) {
            assertTrue(true);
        }
    }

    @Test public void on_SetDouble_isCorrect() {
        assertTrue(SLASH.on(
                new OSet(1, 2),
                new ODouble(2)
        ).equalsValue(new OSet(0.5, 1)));
    }

    @Test public void on_SetFraction_isCorrect() {
        assertTrue(SLASH.on(
                new OSet(1, 2),
                new OFraction(1, 2)
        ).equalsValue(new OSet(2, 4)));
    }

    @Test public void on_MatrixDouble_isCorrect() {
        assertTrue(SLASH.on(
                new OMatrix(new double[][]{
                        {1, 2},
                        {1, 2}
                }),
                new ODouble(2)
        ).equalsValue(new OMatrix(new double[][]{
                {0.5, 1},
                {0.5, 1}
        })));
    }

    @Test public void on_MatrixDouble_isDividedByZero() {
        try {
            SLASH.on(
                    new OMatrix(new double[][]{
                            {1, 2},
                            {1, 2}
                    }),
                    new ODouble(0)
            );
            fail();
        } catch (RuntimeException e) {
            assertTrue(true);
        }
    }

    @Test public void on_MatrixFraction_isCorrect() {
        assertTrue(SLASH.on(
                new OMatrix(new double[][]{
                        {1, 2},
                        {1, 2}
                }),
                new OFraction(2, 1)
        ).equalsValue(new OMatrix(new double[][]{
                {0.5, 1},
                {0.5, 1}
        })));
    }

    @Test public void on_PolynomPolynom_isCorrect() {
        assertTrue(SLASH.on(
                new OPolynom(1, 2, 3),
                new OPolynom(2, 2, 2, 2)
        ).equalsValue(new OPolynom(0.5, 1.5, 3, 3, 2.5, 1.5)));
    }

    @Test public void on_PolynomPolynom_isDividedByZero() {
        try {
            SLASH.on(
                    new OPolynom(1, 2, 3),
                    new OPolynom(0, 2, 0, 2)
            );
            fail();
        } catch (RuntimeException e) {
            assertTrue(true);
        }
    }

    @Test public void on_PolynomDouble_isCorrect() {
        assertTrue(SLASH.on(
                new OPolynom(1, 2),
                new ODouble(1)
        ).equalsValue(new OPolynom(1, 2)));
    }

    @Test public void on_PolynomFraction_isCorrect() {
        assertTrue(SLASH.on(
                new OPolynom(1, 2),
                new OFraction(1)
        ).equalsValue(new OPolynom(1, 2)));
    }

    @Test public void on_TupleTuple_isCorrect() {
        assertTrue(SLASH.on(
                new OTuple(1, 2),
                new OTuple(1, 2)
        ).equalsValue(new OTuple(1, 1)));
    }

    @Test public void on_TupleDouble_isCorrect() {
        assertTrue(SLASH.on(
                new OTuple(1, 2),
                new OTuple(2, 2)
        ).equalsValue(new OTuple(0.5, 1)));
    }

    @Test public void on_TupleFraction_isCorrect() {
        assertTrue(SLASH.on(
                new OTuple(1, 2),
                new OFraction(1, 1)
        ).equalsValue(new OTuple(1, 2)));
    }
}