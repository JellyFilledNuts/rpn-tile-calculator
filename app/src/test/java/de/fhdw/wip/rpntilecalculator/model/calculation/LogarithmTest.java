package de.fhdw.wip.rpntilecalculator.model.calculation;

import org.junit.Test;

import de.fhdw.wip.rpntilecalculator.model.operands.ODouble;

import static org.junit.Assert.assertTrue;

public class LogarithmTest {
    private Logarithm LOGARITHM = Logarithm.getInstance();

    @Test
    public void naturalLogarithm_isCorrect1() {
        System.out.println(LOGARITHM.on(
                new ODouble(10)));
        assertTrue(LOGARITHM.on(
                new ODouble(10)).equalsValue(new ODouble(2.302585092994046))
        );
    }

    @Test
    public void naturalLogarithm_isCorrect2() {
        System.out.println(LOGARITHM.on(
                new ODouble(5)));
        assertTrue(LOGARITHM.on(
                new ODouble(5)).equalsValue(new ODouble(1.6094379124341003))
        );
    }

    @Test
    public void naturalLogarithm_isCorrect3() {
        System.out.println(LOGARITHM.on(
                new ODouble(97)));
        assertTrue(LOGARITHM.on(
                new ODouble(97)).equalsValue(new ODouble(4.574710978503383))
        );
    }

    @Test
    public void naturalLogarithm_isCorrect4() {
        System.out.println(LOGARITHM.on(
                new ODouble(Math.exp(1))));
        assertTrue(LOGARITHM.on(
                new ODouble(Math.exp(1))).equalsValue(new ODouble(1))
        );
    }

    @Test
    public void logarithmOfChoosenBase_isCorrect1() {
        System.out.println(LOGARITHM.on(
                new ODouble(5),
                new ODouble(10)));
        assertTrue(LOGARITHM.on(
                new ODouble(5),
                new ODouble(10))
                .equalsValue(new ODouble(1.4306765580733933))
        );
    }

    @Test
    public void logarithmOfChoosenBase_isCorrect2() {
        System.out.println(LOGARITHM.on(
                new ODouble(10),
                new ODouble(10)));
        assertTrue(LOGARITHM.on(
                new ODouble(10),
                new ODouble(10))
                .equalsValue(new ODouble(1.0))
        );
    }

    @Test
    public void logarithmOfChoosenBase_isCorrect3() {
        System.out.println(LOGARITHM.on(
                new ODouble(Math.exp(1)),
                new ODouble(10)));
        assertTrue(LOGARITHM.on(
                new ODouble(Math.exp(1)),
                new ODouble(10))
                .equalsValue(new ODouble(2.302585092994046))
        );
    }
}
