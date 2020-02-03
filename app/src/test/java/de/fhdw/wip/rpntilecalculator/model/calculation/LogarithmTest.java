package de.fhdw.wip.rpntilecalculator.model.calculation;

import org.junit.Test;

import de.fhdw.wip.rpntilecalculator.model.operands.ODouble;

import static org.junit.Assert.assertTrue;

public class LogarithmTest {
    private Logarithm LOGARITHM = Logarithm.getInstance();

    @Test
    public void naturalLogarithm_isCorrect() {
        System.out.println(LOGARITHM.on(
                new ODouble(10)));
        assertTrue(LOGARITHM.on(
                new ODouble(10)).equalsValue(new ODouble(2.302585092994046))
        );
    }

    @Test
    public void logarithmOfChoosenBase_isCorrect() {
        System.out.println(LOGARITHM.on(
                new ODouble(5),
                new ODouble(10)));
        assertTrue(LOGARITHM.on(
                new ODouble(5),
                new ODouble(10))
                .equalsValue(new ODouble(1.4306765580733933))
        );
    }
}
