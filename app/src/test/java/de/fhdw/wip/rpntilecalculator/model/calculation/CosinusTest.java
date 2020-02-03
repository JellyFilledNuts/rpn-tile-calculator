package de.fhdw.wip.rpntilecalculator.model.calculation;

import org.junit.Test;

import de.fhdw.wip.rpntilecalculator.model.operands.ODouble;

import static org.junit.Assert.assertTrue;

public class CosinusTest {
    private Cosinus COSINUS = Cosinus.getInstance();

    @Test public void cosinusAngle_isCorrect() {
        System.out.println(COSINUS.on(
                new ODouble(10)));
        assertTrue(COSINUS.on(
                new ODouble(10)).equalsValue(new ODouble(0.984807753012208))
        );
    }
}
