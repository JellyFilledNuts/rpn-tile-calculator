package com.fhdw.wip.rpntilecalculator.core.stack;

import com.fhdw.wip.rpntilecalculator.core.model.operand.ODouble;
import com.fhdw.wip.rpntilecalculator.core.model.operand.OFraction;
import com.fhdw.wip.rpntilecalculator.core.model.operand.OTuple;
import com.fhdw.wip.rpntilecalculator.core.model.operand.Operand;

import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class OperandStackTest {

    private int stackDefaultSize;

    private OperandStack setUp() {
        OperandStack stack = new OperandStack();
        stack.push(new Operand[]{
                new ODouble(1),
                new ODouble(2),
                new ODouble(3),
                new ODouble(4),
                new OFraction(1, 2),
                new OFraction(4, 2),
                new OFraction(3, 2),
                new ODouble(11),
                new ODouble(22),
                new ODouble(33)
        });
        stackDefaultSize = stack.size();
        return stack;
    }

    @Test public void testPushSingle() {
        OperandStack stack = setUp();
        assertEquals(stackDefaultSize, stack.size());
        stack.push(new ODouble(2));
        stack.push(new OTuple(new double[]{1, 2, 3.443}));
        assertEquals(stackDefaultSize + 2, stack.size());
    }

    @Test public void testPushArray() {
        OperandStack stack = setUp();
        assertEquals(stackDefaultSize, stack.size());
        stack.push(new Operand[]{new ODouble(5), new ODouble(4.44)});
        assertEquals(stackDefaultSize + 2, stack.size());
    }

    @Test public void testPopSingle() {
        OperandStack stack = setUp();

        Optional<Operand> optional = stack.pop();
        assertTrue(optional.isPresent());
        assertTrue(optional.get() instanceof ODouble);
        assertEquals("33", optional.get().toString());
        assertEquals(stackDefaultSize - 1, stack.size());

        optional = stack.pop();
        assertTrue(optional.isPresent());
        assertTrue(optional.get() instanceof ODouble);
        assertEquals("22", optional.get().toString());
        assertEquals(stackDefaultSize - 2, stack.size());
    }

    @Test public void testPopList() {
        OperandStack stack = setUp();

        List<Operand> operands = stack.pop(2);
        assertEquals(stackDefaultSize - 2, stack.size());
        for (Operand operand : operands)
            assertTrue(operand instanceof ODouble);
    }

    @Test public void testPopSingleConcrete() {
        OperandStack stack = setUp();

        Optional<? extends Operand> optional = stack.pop(OFraction.class);
        assertTrue(optional.isPresent());
        assertEquals("(3/2)", optional.get().toString());
        assertEquals(stackDefaultSize - 1, stack.size());

        optional = stack.pop(ODouble.class);
        assertTrue(optional.isPresent());
        assertTrue(optional.get() instanceof ODouble);
        assertEquals("33", optional.get().toString());
        assertEquals(stackDefaultSize - 2, stack.size());
    }

    @Test public void testPopListConcrete() {
        OperandStack stack = setUp();

        List<OFraction> oFractions = stack.pop(10, OFraction.class);
        assertEquals(3, oFractions.size());
        assertEquals(stackDefaultSize - 3, stack.size());
    }

    @Test public void testPeekSingle() {
        OperandStack stack = setUp();

        Optional<Operand> optional = stack.peek();
        assertTrue(optional.isPresent());
        assertTrue(optional.get() instanceof ODouble);
        assertEquals("33", optional.get().toString());
        assertEquals(stackDefaultSize, stack.size());

        optional = stack.peek();
        assertTrue(optional.isPresent());
        assertTrue(optional.get() instanceof ODouble);
        assertEquals("33", optional.get().toString());
        assertEquals(stackDefaultSize, stack.size());
    }

    @Test public void testPeekList() {
        OperandStack stack = setUp();

        List<Operand> operands = stack.peek(2);
        assertEquals(stackDefaultSize, stack.size());
        for (Operand operand : operands)
            assertTrue(operand instanceof ODouble);
    }

    @Test public void testPeekSingleConcrete() {
        OperandStack stack = setUp();

        Optional<? extends Operand> optional = stack.peek(OFraction.class);
        assertTrue(optional.isPresent());
        assertEquals("(3/2)", optional.get().toString());
        assertEquals(stackDefaultSize, stack.size());

        optional = stack.peek(ODouble.class);
        assertTrue(optional.isPresent());
        assertTrue(optional.get() instanceof ODouble);
        assertEquals("33", optional.get().toString());
        assertEquals(stackDefaultSize, stack.size());
    }

    @Test public void testPeekListConcrete() {
        OperandStack stack = setUp();

        List<? extends Operand> operands = stack.peek(2, OFraction.class);
        assertEquals(stackDefaultSize, stack.size());
        for (Operand operand : operands)
            assertTrue(operand instanceof OFraction);
    }

    @Test public void contains() {
        OperandStack stack = setUp();
        Optional<Operand> optionalOperand = stack.peek();
        if (optionalOperand.isPresent()) assertTrue(stack.contains(optionalOperand.get()));
        else fail();
    }

    @Test public void clear() {
        OperandStack stack = setUp();
        stack.clear();
        assertEquals(0, stack.size());
    }

    @Test public void testGetAsArray() {
        OperandStack stack = setUp();
        assertEquals(stackDefaultSize, stack.get().length);
    }

    @Test public void testGetAsListConcrete() {
        OperandStack stack = setUp();
        assertEquals(3, stack.get(OFraction.class).size());
    }

}