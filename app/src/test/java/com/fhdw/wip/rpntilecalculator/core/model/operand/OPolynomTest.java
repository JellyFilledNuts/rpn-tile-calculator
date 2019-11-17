package com.fhdw.wip.rpntilecalculator.core.model.operand;

import org.junit.Test;

import static org.junit.Assert.*;

public class OPolynomTest {

    @Test public void testToString() {
        Operand operand = new OPolynom(new double[]{0, -33, 3});
        assertEquals("0x^0 + -33x^1 + 3x^2", operand.toString());
    }
}