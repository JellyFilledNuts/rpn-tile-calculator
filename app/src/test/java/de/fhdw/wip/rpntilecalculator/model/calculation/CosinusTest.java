package de.fhdw.wip.rpntilecalculator.model.calculation;

import org.junit.Test;

import de.fhdw.wip.rpntilecalculator.model.operands.ODouble;

import static org.junit.Assert.assertTrue;

public class CosinusTest {
    private Cosinus COSINUS = Cosinus.getInstance();

    @Test public void cosinusAngle_isCorrect1() {
        System.out.println(COSINUS.on(
                new ODouble(10)));
        assertTrue(COSINUS.on(
                new ODouble(10)).equalsValue(new ODouble(0.984807753012208))
        );
    }

    @Test public void cosinusAngle_isCorrect2() {
        System.out.println(COSINUS.on(
                new ODouble(45)));
        assertTrue(COSINUS.on(
                new ODouble(45)).equalsValue(new ODouble(0.7071067811865476))
        );
    }

    @Test public void cosinusAngle_isCorrect3() {
        System.out.println(COSINUS.on(
                new ODouble(-45)));
        assertTrue(COSINUS.on(
                new ODouble(-45)).equalsValue(new ODouble(-0.7071067811865476))
        );
    }
}
