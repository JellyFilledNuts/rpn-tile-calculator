package de.fhdw.wip.rpntilecalculator.core.calculation;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.junit.Test;

import java.util.Arrays;

import de.fhdw.wip.rpntilecalculator.core.model.operand.ODouble;
import de.fhdw.wip.rpntilecalculator.core.model.operand.OMatrix;
import de.fhdw.wip.rpntilecalculator.core.model.operand.OTuple;

import static org.junit.Assert.*;

@SuppressWarnings("ResultOfMethodCallIgnored")
public class MinusTest {

    private Minus MINUS = Minus.getInstance();

    @Test public void on_DoubleDouble_isCorrect() {
        assertTrue(MINUS.on(
                new ODouble(5),
                new ODouble(5)
                ).equalsValue(new ODouble(0))
        );
    }

    @Test public void on_DoubleFraction_isCorrect() {
    }

    @Test public void on_FractionFraction_isCorrect() {
    }

    @Test public void on_FractionDouble_isCorrect() {
    }

    @Test public void on_SetDouble_isCorrect() {
    }

    @Test public void on_SetFraction_isCorrect() {
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
    }

    @Test public void on_MatrixFraction_isCorrect() {
    }

    @Test public void onPolynomPolynom_isCorrect() {
    }

    @Test public void on_PolynomFraction_isCorrect() {
    }

    @Test public void on_PolynomDouble_isCorrect() {
    }

    @Test public void on_TupleTuple_isCorrect() {
    }

    @Test public void on_TupleDouble_isCorrect() {
    }

    @Test public void on_TupleFraction_isCorrect() {
    }
}