package de.fhdw.wip.rpntilecalculator.core.calculation;

import org.junit.Test;

import de.fhdw.wip.rpntilecalculator.core.model.operand.ODouble;

import static org.junit.Assert.*;

public class PlusTest {

    private Plus PLUS = Plus.getInstance();

    @Test
    public void on_DoubleDouble() {
        assertTrue(
                PLUS.on(new ODouble(5), new ODouble(5)).equalsValue(new ODouble(10))
        );
    }

    @Test
    public void on_DoubleFraction() {
    }

    @Test
    public void on_FractionDouble() {
    }

    @Test
    public void on_DoubleSet() {
    }

    @Test
    public void on_SetDouble() {
    }

    @Test
    public void on_DoubleMatrix() {
    }

    @Test
    public void on_MatrixDouble() {
    }

    @Test
    public void on_DoublePolynom() {
    }

    @Test
    public void on_PolynomDouble() {
    }

    @Test
    public void on_DoubleTuple() {
    }

    @Test
    public void on_TupleDouble() {
    }

    @Test
    public void on_FractionFraction() {
    }

    @Test
    public void on_FractionSet() {
    }

    @Test
    public void on_SetFraction() {
    }

    @Test
    public void on_FractionMatrix() {
    }

    @Test
    public void on_MatrixFraction() {
    }

    @Test
    public void on_FractionPolynom() {
    }

    @Test
    public void on_PolynomFraction() {
    }

    @Test
    public void on_FractionTuple() {
    }

    @Test
    public void on_TupleFraction() {
    }

    @Test
    public void on_MatrixMatrix() {
    }

    @Test
    public void on_PolynomPolynom() {
    }

    @Test
    public void on_TupleTuple() {
    }
}