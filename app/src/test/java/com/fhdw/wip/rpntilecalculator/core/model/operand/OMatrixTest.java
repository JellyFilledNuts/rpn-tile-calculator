package com.fhdw.wip.rpntilecalculator.core.model.operand;

import org.apache.commons.math3.linear.MatrixUtils;
import org.junit.Test;

import static org.junit.Assert.*;

public class OMatrixTest {

    @Test public void testToString() {
        OMatrix matrix = new OMatrix(MatrixUtils.createRealDiagonalMatrix(
                new double[]{1, 2, 3})
        );
        assertEquals("[[1, 0, 0], [0, 2, 0], [0, 0, 3]]", matrix.toString());
    }
}