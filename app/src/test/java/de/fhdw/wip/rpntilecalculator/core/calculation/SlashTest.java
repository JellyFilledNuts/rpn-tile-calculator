package de.fhdw.wip.rpntilecalculator.core.calculation;

import org.junit.Test;

import de.fhdw.wip.rpntilecalculator.core.model.operand.ODouble;
import de.fhdw.wip.rpntilecalculator.core.model.operand.OFraction;

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
    }

    @Test public void on_FractionDouble_isCorrect() {
    }

    @Test public void on_FractionDouble_isDividedByZero() {
    }

    @Test public void on_SetDouble_isCorrect() {
    }

    @Test public void on_SetFraction_isCorrect() {
    }

    @Test public void on_MatrixDouble_isCorrect() {
    }

    @Test public void on_MatrixDouble_isDividedByZero() {
    }

    @Test public void on_MatrixFraction_isCorrect() {
    }

    @Test public void on_PolynomPolynom_isCorrect() {
    }

    @Test public void on_PolynomDouble_isCorrect() {
    }

    @Test public void on_PolynomFraction_isCorrect() {
    }

    @Test public void on_TupleTuple_isCorrect() {
    }

    @Test public void on_TupleDouble_isCorrect() {
    }

    @Test public void on_TupleFraction_isCorrect() {
    }
}