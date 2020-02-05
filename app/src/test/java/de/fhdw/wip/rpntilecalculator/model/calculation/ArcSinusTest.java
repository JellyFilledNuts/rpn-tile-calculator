package de.fhdw.wip.rpntilecalculator.model.calculation;

import org.junit.Test;

import de.fhdw.wip.rpntilecalculator.model.operands.ODouble;

import static org.junit.Assert.assertTrue;

/*
 * Summary: Unit test for the arc sinus function
 * Author:  Jannis Luca Keienburg
 * Date:    2020/01/22
 */

public class ArcSinusTest {
    private ArcSinus ARC_SINUS = ArcSinus.getInstance();

    @Test
    public void arcSinusAngle_isCorrect1() {
        System.out.println(ARC_SINUS.on(
                new ODouble(10)));
        assertTrue(ARC_SINUS.on(
                new ODouble(10)).equalsValue(new ODouble(0.17543139267904395))
        );
    }

    @Test
    public void arcSinusAngle_isCorrect2() {
        System.out.println(ARC_SINUS.on(
                new ODouble(45)));
        assertTrue(ARC_SINUS.on(
                new ODouble(45)).equalsValue(new ODouble(0.9033391107665127))
        );
    }

    @Test
    public void arcSinusAngle_isCorrect3() {
        System.out.println(ARC_SINUS.on(
                new ODouble(-45)));
        assertTrue(ARC_SINUS.on(
                new ODouble(-45)).equalsValue(new ODouble(-0.9033391107665127))
        );
    }
}

