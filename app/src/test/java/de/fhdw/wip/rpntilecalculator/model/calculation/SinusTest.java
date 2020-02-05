package de.fhdw.wip.rpntilecalculator.model.calculation;

import org.junit.Test;
import de.fhdw.wip.rpntilecalculator.model.operands.ODouble;
import static org.junit.Assert.assertTrue;

/*
 * Summary: Unit test for the sinus function
 * Author:  Jannis Luca Keienburg
 * Date:    2020/01/22
 */

public class SinusTest {
    private Sinus SINUS = Sinus.getInstance();

    @Test public void sinusAngle_isCorrect1() {
        System.out.println(SINUS.on(
                new ODouble(10)));
        assertTrue(SINUS.on(
                new ODouble(10)).equalsValue(new ODouble(0.17364817766693033))
        );
    }

    @Test public void sinusAngle_isCorrect2() {
        System.out.println(SINUS.on(
                new ODouble(45)));
        assertTrue(SINUS.on(
                new ODouble(45)).equalsValue(new ODouble(0.7071067811865475))
        );
    }

    @Test public void sinusAngle_isCorrect3() {
        System.out.println(SINUS.on(
                new ODouble(-45)));
        assertTrue(SINUS.on(
                new ODouble(-45)).equalsValue(new ODouble(-0.7071067811865475))
        );
    }
}
