package de.fhdw.wip.rpntilecalculator.controller;


import org.jetbrains.annotations.NotNull;

import de.fhdw.wip.rpntilecalculator.model.operands.ODouble;
import de.fhdw.wip.rpntilecalculator.model.operands.Operand;
import de.fhdw.wip.rpntilecalculator.model.stack.OperandStack;
import de.fhdw.wip.rpntilecalculator.view.Tile;
import de.fhdw.wip.rpntilecalculator.view.TileMapping;
import de.fhdw.wip.rpntilecalculator.view.events.DisplayEventListener;
import de.fhdw.wip.rpntilecalculator.view.events.StackUpdateListener;
import de.fhdw.wip.rpntilecalculator.view.layout.OperandTileScheme;
import de.fhdw.wip.rpntilecalculator.view.layout.TileScheme;

/**
 * Used for communication between view and model
 */
public class Controller {

    private static final OperandStack OPERAND_STACK = new OperandStack();
    private static StringBuilder INPUT_TERM = new StringBuilder();

    private DisplayEventListener[] viewListeners = new DisplayEventListener[0];

    /**
     * Handles all tile input and decides on the follow up procedure
     * First decides on the type of input
     * @param tile the tile that has been clicked
     */
    public void click(@NotNull Tile tile) throws ClickHandlingException {
        if(tile.isOperand()) clickOperand(tile);
        else if(tile.isAction()) clickAction(tile);
        else if(tile.isStack()) clickStack(tile);
        else if(tile.isSetting()) clickSetting(tile);
        else throw new ClickHandlingException();
    }

    private void clickOperand(@NotNull Tile tile) {
        Operand operand = ((OperandTileScheme) tile.getScheme()).getOperand();

        if(tryAppending(operand)) {
            // Operand can be added to the input term and replaces the last operand
            OPERAND_STACK.pop();
            operand = readCombinedOperand(INPUT_TERM).getOperand();
        } else {
            INPUT_TERM = new StringBuilder();
        }

        OPERAND_STACK.push(operand);
        callStackUpdateEvent();
        System.out.println("[Operand] " + operand.getClass());
    }

    private void clickAction(@NotNull Tile tile) {

    }

    private void clickStack(@NotNull Tile tile) {

    }

    private void clickSetting(@NotNull Tile tile) {

    }

    /**
     * Checks if the new operand has to be seen as new term and add if it works
     * @param operand operand that is added to the stack
     * @return if the combination has been done or not
     */
    private boolean tryAppending(Operand operand) {
        if(OPERAND_STACK.peek() == null || OPERAND_STACK.peek() instanceof ODouble) {
            if (operand instanceof ODouble) {
                String newTerm = INPUT_TERM.toString() + operand;
                int count = 0;
                for (int i = 0; i < newTerm.length(); i++) if (newTerm.charAt(i) == '.') count++;
                if(count <= 1) {
                    INPUT_TERM.append(operand);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Reads the input term as a new operand (typically double)
     * @param inputTerm current input term
     * @return the operand that will be created as a result
     */
    private OperandTileScheme readCombinedOperand(StringBuilder inputTerm) {
        return (OperandTileScheme) TileScheme.createTileScheme(TileMapping.O_DOUBLE, inputTerm.toString());
    }

    /**
     * Throws a Stack update event to all listeners
     */
    private void callStackUpdateEvent() {
        for(DisplayEventListener listener : viewListeners) {
            if(listener instanceof StackUpdateListener) {
                ((StackUpdateListener) listener).updateStack(OPERAND_STACK);
            }
        }
    }

    /**
     * Sets the listeners for display changes
     */
    public void setDisplayEventListeners(DisplayEventListener... viewListeners) {
        this.viewListeners = viewListeners;
    }
}
