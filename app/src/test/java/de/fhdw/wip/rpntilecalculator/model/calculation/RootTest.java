package de.fhdw.wip.rpntilecalculator.model.calculation;

import org.junit.Test;
import static org.junit.Assert.*;

import de.fhdw.wip.rpntilecalculator.model.operands.ODouble;
import de.fhdw.wip.rpntilecalculator.model.operands.OFraction;

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
