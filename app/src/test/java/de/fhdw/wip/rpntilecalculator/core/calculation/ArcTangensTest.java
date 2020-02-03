package de.fhdw.wip.rpntilecalculator.core.calculation;

import org.junit.Test;

import de.fhdw.wip.rpntilecalculator.core.model.operand.ODouble;

import static org.junit.Assert.assertTrue;

public class ArcTangensTest {
    private ArcTangens ARC_TANGENS = ArcTangens.getInstance();

    @Test
    public void arcTangensAngle_isCorrect() {
        System.out.println(ARC_TANGENS.on(
                new ODouble(10)));
        assertTrue(ARC_TANGENS.on(
                new ODouble(10)).equalsValue(new ODouble(0.1727924348551592))
        );
    }
}
