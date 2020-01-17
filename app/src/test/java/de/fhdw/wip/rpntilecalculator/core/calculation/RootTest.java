package de.fhdw.wip.rpntilecalculator.core.calculation;

import org.junit.Test;
import static org.junit.Assert.*;

import de.fhdw.wip.rpntilecalculator.core.model.operand.ODouble;
import de.fhdw.wip.rpntilecalculator.core.model.operand.OFraction;

public class RootTest {

    private Root ROOT = Root.getInstance();

    //region Double
    @Test public void onDoubleDouble_isCorrect() {
        assertTrue(ROOT.on(
                new ODouble(36),
                new ODouble(2)
                ).equalsValue(new ODouble((6))));
    }

    @Test public void onDoubleFraction_isCorrect() {
        assertTrue(ROOT.on(
                new ODouble(36),
                new OFraction(0.5)
        ).equalsValue(new ODouble((1296))));
    }
}
