package de.fhdw.wip.rpntilecalculator.controller;


import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import de.fhdw.wip.rpntilecalculator.model.calculation.Action;
import de.fhdw.wip.rpntilecalculator.model.calculation.CalculationException;
import de.fhdw.wip.rpntilecalculator.model.operands.ODouble;
import de.fhdw.wip.rpntilecalculator.model.operands.Operand;
import de.fhdw.wip.rpntilecalculator.model.settings.Setting;
import de.fhdw.wip.rpntilecalculator.model.stack.OperandStack;
import de.fhdw.wip.rpntilecalculator.view.Tile;
import de.fhdw.wip.rpntilecalculator.view.TileMapping;
import de.fhdw.wip.rpntilecalculator.view.events.DisplayEventListener;
import de.fhdw.wip.rpntilecalculator.view.events.StackUpdateListener;
import de.fhdw.wip.rpntilecalculator.view.layout.schemes.ActionTileScheme;
import de.fhdw.wip.rpntilecalculator.view.layout.schemes.OperandTileScheme;
import de.fhdw.wip.rpntilecalculator.view.layout.schemes.SettingTileScheme;
import de.fhdw.wip.rpntilecalculator.view.layout.schemes.StackTileScheme;
import de.fhdw.wip.rpntilecalculator.view.layout.schemes.TileScheme;

/**
 * Used for communication between view and model
 */
public class Controller {

    public static final OperandStack OPERAND_STACK = new OperandStack();
    public static StringBuilder INPUT_TERM = new StringBuilder();

    private static DisplayEventListener[] viewListeners = new DisplayEventListener[0];
    public static final String INPUT_FINALIZED = "final";

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

    /**
     * Handles all stack operation (similar to clickOperand)
     */
    private void clickStack(@NotNull Tile tile) {
        StackTileScheme stackTile = (StackTileScheme) tile.getScheme();
        if(stackTile.hasOperand()) clickOperand(stackTile.getOperand());
    }

    /**
     * Handles operand inputs coming from operand tiles
     * @param tile operand tile
     */
    private void clickOperand(@NotNull Tile tile) {
        Operand operand = ((OperandTileScheme) tile.getScheme()).getOperand();
        clickOperand(operand);
    }

    /**
     * Handles operand inputs coming from any tile
     * @param operand operand that is added to stack
     */
    private void clickOperand(@NotNull Operand operand) {

        if(tryAppending(operand)) {
            // Operand can be added to the input term and replaces the last operand
            OPERAND_STACK.pop();
            operand = readCombinedOperand(INPUT_TERM).getOperand();
        } else {
            resetInputTerm(operand);
        }

        OPERAND_STACK.push(operand);
        callStackUpdateEvent();
        //System.out.println("[Operand] " + operand.getClass());
    }

    /**
     * Handles all action inputs
     * @param tile the action tile itself
     */
    private void clickAction(@NotNull Tile tile) {
        Action action = ((ActionTileScheme) tile.getScheme()).getAction();
        int[] requiredNumOfOperands = action.getRequiredNumOfOperands();

        if(action.getRequiredNumOfOperands()[0] != -1) {
            //Create a list of all possible parameter lists
            List<List<Operand>> operands = new ArrayList<>();
            for(int num : requiredNumOfOperands) {
                operands.add(OPERAND_STACK.peek(num));
            }
            //Try calculating using the given list
            try {
                calculate(action, operands);
            } catch (CalculationException e) {
                e.printStackTrace();
            }
        }
        callStackUpdateEvent();
    }

    /**
     * Calculates the result with highest possible number of parameters
     * @param action action that is invoked
     * @param possibleOperands list of lists of operands that will be tested from bottom upwards
     * @return returns if the calculation has been successful
     * @throws CalculationException thrown if no calculation is possible
     */
    private boolean calculate(@NotNull Action action, @NotNull List<List<Operand>> possibleOperands) throws CalculationException {
        if(possibleOperands.size() == 0) {
            // No calculation has been successful
            throw new CalculationException("No calculation could be applied... :(");
        }
        int nextOperandsToTry = possibleOperands.size() - 1;
        try {
            //try the one with the highest number of parameters
            List<Operand> operands = possibleOperands.get(nextOperandsToTry);
            Operand result = action.with(operands);
            OPERAND_STACK.pop(operands.size());
            OPERAND_STACK.push(result);
            resetInputTerm(result);
            return true;
        } catch (CalculationException e) {
            //try the next lower one
            possibleOperands.remove(nextOperandsToTry);
            return calculate(action, possibleOperands);
        }
    }

    /**
     * Handles all Settings (usually do their own thing)
     * @param tile setting tile that has been clicked
     */
    private void clickSetting(@NotNull Tile tile) {
        Setting setting = ((SettingTileScheme) tile.getScheme()).getSetting();
        setting.call();
    }

    /**
     * Checks if the new operand has to be seen as new term and add if it works
     * @param operand operand that is added to the stack
     * @return if the combination has been done or not
     */
    private boolean tryAppending(Operand operand) {
        if(INPUT_TERM.toString().equals(INPUT_FINALIZED)) return false;
        if(OPERAND_STACK.peek() == null || OPERAND_STACK.peek() instanceof ODouble) {
            if (operand instanceof ODouble) {
                ODouble oNew = (ODouble) operand;
                int points = 0;
                if(INPUT_TERM.toString().contains(".")) points++;
                if(oNew.getDouble() % 1 != 0) points++;

                if(points < 2) {
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
     * Clears the current input term
     * @param operand one operand that should remain in the input term
     */
    public static void resetInputTerm(@Nullable Operand operand) {
        INPUT_TERM = new StringBuilder();
        if(operand != null) INPUT_TERM.append(operand);
    }

    /**
     * Throws a Stack update event to all listeners
     */
    public static void callStackUpdateEvent() {
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
