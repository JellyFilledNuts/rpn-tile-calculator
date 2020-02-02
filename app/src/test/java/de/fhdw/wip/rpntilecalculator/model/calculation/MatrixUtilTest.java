package de.fhdw.wip.rpntilecalculator.model.calculation;

import org.junit.Test;

import de.fhdw.wip.rpntilecalculator.model.operands.OMatrix;

import static org.junit.Assert.assertEquals;

/*
 * Summary: Test for the class 'MatrixUtil'
 * Author:  Getuart Istogu
 * Date:    2020/01/27
 */

public class MatrixUtilTest {
    private MatrixUtil MATRIX_UTIL = MatrixUtil.getInstance();

    @Test
    public void solveLinearSystem_isCorrect() {
        double[][] A = {
                {3, -7, 0, -6},
                {2, -8, -1, 4},
                {0, 9, -7, 9},
                {-4, 5, -3, -8}};

        OMatrix aMatrix = new OMatrix(A);
        double[] b = {8, 1, -7, -1};

        double[] exptectedX = {1.1970139565076277, -0.12852969814995122, 0.08276533592989294, -0.5848750405712432};

        double[] x = MATRIX_UTIL.solveLinearSystem(aMatrix, b).getTuple();

        assertEquals(x[0], exptectedX[0], 0.001);
        assertEquals(x[1], exptectedX[1], 0.001);
        assertEquals(x[2], exptectedX[2], 0.001);
        assertEquals(x[3], exptectedX[3], 0.001);
    }

}
