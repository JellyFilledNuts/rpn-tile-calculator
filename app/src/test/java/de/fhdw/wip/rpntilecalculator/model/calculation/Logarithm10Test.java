package de.fhdw.wip.rpntilecalculator.model.calculation;

import org.junit.Test;
import de.fhdw.wip.rpntilecalculator.model.operands.ODouble;
import static org.junit.Assert.assertTrue;

/*
 * Summary: Unit test for the logarithm with base 10. 
 * Author:  Jannis Luca Keienburg
 * Date:    2020/01/22
 */

public class Logarithm10Test {
    private Logarithm10 LOGARITHM10 = Logarithm10.getInstance();

    @Test
    public void logarithm10_isCorrect1() {
        System.out.println(LOGARITHM10.on(
                new ODouble(10)));
        assertTrue(LOGARITHM10.on(
                new ODouble(10)).equalsValue(new ODouble(1.0))
        );
    }

    @Test
    public void logarithm10_isCorrect2() {
        System.out.println(LOGARITHM10.on(
                new ODouble(5)));
        assertTrue(LOGARITHM10.on(
                new ODouble(5)).equalsValue(new ODouble(0.6989700043360189))
        );
    }

    @Test
    public void logarithm10_isCorrect3() {
        System.out.println(LOGARITHM10.on(
                new ODouble(97)));
        assertTrue(LOGARITHM10.on(
                new ODouble(97)).equalsValue(new ODouble(1.9867717342662448))
        );
    }
}
