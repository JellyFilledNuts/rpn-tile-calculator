package de.fhdw.wip.rpntilecalculator.model.calculation;

import org.junit.Test;

import de.fhdw.wip.rpntilecalculator.model.operands.ODouble;

import static org.junit.Assert.assertTrue;

/*
 * Summary: Unit test for the arc cosinus function
 * Author:  Jannis Luca Keienburg
 * Date:    2020/01/22
 */

public class ArcCosinusTest {
    private ArcCosinus ARC_COSINUS = ArcCosinus.getInstance();

    @Test
    public void arcCosinusAngle_isCorrect1() {
        System.out.println(ARC_COSINUS.on(
                new ODouble(10)));
        assertTrue(ARC_COSINUS.on(
                new ODouble(10)).equalsValue(new ODouble(1.3953649341158527))
        );
    }

    @Test
    public void arcCosinusAngle_isCorrect2() {
        System.out.println(ARC_COSINUS.on(
                new ODouble(45)));
        assertTrue(ARC_COSINUS.on(
                new ODouble(45)).equalsValue(new ODouble(0.6674572160283838))
        );
    }

    @Test
    public void arcCosinusAngle_isCorrect3() {
        System.out.println(ARC_COSINUS.on(
                new ODouble(-45)));
        assertTrue(ARC_COSINUS.on(
                new ODouble(-45)).equalsValue(new ODouble(-0.6674572160283838))
        );
    }
}
