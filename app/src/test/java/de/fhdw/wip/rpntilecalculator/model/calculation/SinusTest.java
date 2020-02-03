package de.fhdw.wip.rpntilecalculator.model.calculation;

import org.junit.Test;

import de.fhdw.wip.rpntilecalculator.model.operands.ODouble;

import static org.junit.Assert.assertTrue;

public class SinusTest {
    private Sinus SINUS = Sinus.getInstance();

    @Test public void sinusAngle_isCorrect() {
        System.out.println(SINUS.on(
                new ODouble(10)));
        assertTrue(SINUS.on(
                new ODouble(10)).equalsValue(new ODouble(0.17364817766693033))
        );
    }
}
