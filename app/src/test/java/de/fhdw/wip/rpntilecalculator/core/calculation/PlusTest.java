package de.fhdw.wip.rpntilecalculator.core.calculation;

import org.junit.Test;

import de.fhdw.wip.rpntilecalculator.core.model.operand.ODouble;
import de.fhdw.wip.rpntilecalculator.core.model.operand.OFraction;
import de.fhdw.wip.rpntilecalculator.core.model.operand.OTuple;

import static org.junit.Assert.*;

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

    @Test
    public void on_FractionDouble_isCorrect() {
    }

    @Test
    public void on_DoubleSet_isCorrect() {
    }

    @Test
    public void on_SetDouble_isCorrect() {
    }

    @Test
    public void on_DoubleMatrix_isCorrect() {
    }

    @Test
    public void on_MatrixDouble_isCorrect() {
    }

    @Test
    public void on_DoublePolynom_isCorrect() {
    }

    @Test
    public void on_PolynomDouble_isCorrect() {
    }

    @Test
    public void on_DoubleTuple_isCorrect() {
    }

    @Test
    public void on_TupleDouble_isCorrect() {
    }

    @Test
    public void on_FractionFraction_isCorrect() {
    }

    @Test
    public void on_FractionSet_isCorrect() {
    }

    @Test
    public void on_SetFraction_isCorrect() {
    }

    @Test
    public void on_FractionMatrix_isCorrect() {
    }

    @Test
    public void on_MatrixFraction_isCorrect() {
    }

    @Test
    public void on_FractionPolynom_isCorrect() {
    }

    @Test
    public void on_PolynomFraction_isCorrect() {
    }

    @Test
    public void on_FractionTuple_isCorrect() {
    }

    @Test
    public void on_TupleFraction_isCorrect() {
    }

    @Test
    public void on_MatrixMatrix_isCorrect() {
    }

    @Test
    public void on_PolynomPolynom_isCorrect() {
    }

    @Test public void on_TupleTuple_isCorrect() {
        assertTrue(PLUS.on(
                new OTuple(1, 2, 3),
                new OTuple(1, 2, 3)
                ).equalsValue(new OTuple(2, 4, 6))
        );
    }

    @Test public void on_TupleTuple_isFalse() {
        try {
            assertTrue(PLUS.on(
                    new OTuple(1, 2, 3),
                    new OTuple(1, 2, 3, 4)
                    ).equalsValue(new OTuple(2, 4, 6))
            );
        } catch (Exception e) {
            assertTrue("Should always fail", true);
        }
    }
}