package de.fhdw.wip.rpntilecalculator.model.calculation;

import org.junit.Test;

import de.fhdw.wip.rpntilecalculator.model.operands.OPolynom;

import static org.junit.Assert.assertEquals;

/*
 * Summary: Test for the class 'Limes'
 * Author:  Getuart Istogu
 * Date:    2020/01/27
 */

public class LimesTest {
    private Limes LIMES = Limes.getInstance();

    @Test
    public void limes_isCorrect1()
    {
        OPolynom polynom = new OPolynom(new double[]{1, 0, 1});
        assertEquals(
                LIMES.limit(polynom, 5).getDouble(), 26, 0.001);
    }

    @Test
    public void limes_isCorrect2()
    {
        OPolynom polynom = new OPolynom(new double[]{0, 0, 2, -0.333333333  });
        assertEquals(
                LIMES.limit(polynom, Double.POSITIVE_INFINITY).getDouble(), Double.NEGATIVE_INFINITY, 0.001);
    }
}