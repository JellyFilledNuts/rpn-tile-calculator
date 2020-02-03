package de.fhdw.wip.rpntilecalculator.core.calculation;

import org.junit.Test;

import de.fhdw.wip.rpntilecalculator.core.model.operand.ODouble;

import static org.junit.Assert.assertTrue;

public class ArcCosinusTest {
    private ArcCosinus ARC_COSINUS = ArcCosinus.getInstance();

    @Test
    public void arcCosinusAngle_isCorrect() {
        System.out.println(ARC_COSINUS.on(
                new ODouble(10)));
        assertTrue(ARC_COSINUS.on(
                new ODouble(10)).equalsValue(new ODouble(1.3953649341158527))
        );
    }
}
