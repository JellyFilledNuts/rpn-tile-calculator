package de.fhdw.wip.rpntilecalculator.model.calculation;

import org.junit.Test;

import de.fhdw.wip.rpntilecalculator.model.operands.ODouble;

import static org.junit.Assert.assertTrue;

/*
 * Summary: Unit test for the arc tangens function
 * Author:  Jannis Luca Keienburg
 * Date:    2020/01/22
 */

public class ArcTangensTest {
    private ArcTangens ARC_TANGENS = ArcTangens.getInstance();

    @Test
    public void arcTangensAngle_isCorrect1() {
        System.out.println(ARC_TANGENS.on(
                new ODouble(10)));
        assertTrue(ARC_TANGENS.on(
                new ODouble(10)).equalsValue(new ODouble(0.1727924348551592))
        );
    }

    @Test
    public void arcTangensAngle_isCorrect2() {
        System.out.println(ARC_TANGENS.on(
                new ODouble(45)));
        assertTrue(ARC_TANGENS.on(
                new ODouble(45)).equalsValue(new ODouble(0.6657737500283538))
        );
    }

    @Test
    public void arcTangensAngle_isCorrect3() {
        System.out.println(ARC_TANGENS.on(
                new ODouble(-45)));
        assertTrue(ARC_TANGENS.on(
                new ODouble(-45)).equalsValue(new ODouble(-0.6657737500283538))
        );
    }
}
