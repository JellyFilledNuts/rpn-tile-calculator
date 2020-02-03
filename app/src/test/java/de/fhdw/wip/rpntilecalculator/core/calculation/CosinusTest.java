package de.fhdw.wip.rpntilecalculator.core.calculation;

import org.junit.Test;
import static org.junit.Assert.*;
import de.fhdw.wip.rpntilecalculator.core.model.operand.ODouble;

public class CosinusTest {
    private Cosinus COSINUS = Cosinus.getInstance();

    @Test public void cosinusAngle_isCorrect() {
        System.out.println(COSINUS.on(
                new ODouble(10)));
        assertTrue(COSINUS.on(
                new ODouble(10)).equalsValue(new ODouble(0.984807753012208))
        );
    }
}
