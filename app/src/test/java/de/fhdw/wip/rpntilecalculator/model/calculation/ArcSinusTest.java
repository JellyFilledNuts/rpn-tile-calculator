package de.fhdw.wip.rpntilecalculator.model.calculation;

import org.junit.Test;

import de.fhdw.wip.rpntilecalculator.model.operands.ODouble;

import static org.junit.Assert.assertTrue;

public class ArcSinusTest {
    private ArcSinus ARC_SINUS = ArcSinus.getInstance();

    @Test
    public void arcSinusAngle_isCorrect() {
        System.out.println(ARC_SINUS.on(
                new ODouble(10)));
        assertTrue(ARC_SINUS.on(
                new ODouble(10)).equalsValue(new ODouble(0.17543139267904395))
        );
    }
}

