package de.fhdw.wip.rpntilecalculator.view.events;

import de.fhdw.wip.rpntilecalculator.model.stack.OperandStack;

public interface StackUpdateListener extends DisplayEventListener {
    void updateStack(OperandStack operandStack);
}
