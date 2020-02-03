package de.fhdw.wip.rpntilecalculator.core.calculation;

import org.junit.Test;
import de.fhdw.wip.rpntilecalculator.core.model.operand.ODouble;
import static org.junit.Assert.assertTrue;

public class TangensTest {
    private Tangens TANGENS = Tangens.getInstance();

    @Test
    public void tangensAngle_isCorrect() {
        System.out.println(TANGENS.on(
                new ODouble(10)));
        assertTrue(TANGENS.on(
                new ODouble(10)).equalsValue(new ODouble(0.17632698070846498))
        );
    }
}