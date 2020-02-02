package de.fhdw.wip.rpntilecalculator.presenter;


import android.view.View;

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
import de.fhdw.wip.rpntilecalculator.view.layout.TileLayout;
import de.fhdw.wip.rpntilecalculator.view.layout.schemes.ActionTileScheme;
import de.fhdw.wip.rpntilecalculator.view.layout.schemes.OperandTileScheme;
import de.fhdw.wip.rpntilecalculator.view.layout.schemes.SettingTileScheme;
import de.fhdw.wip.rpntilecalculator.view.layout.schemes.StackTileScheme;
import de.fhdw.wip.rpntilecalculator.view.layout.schemes.TileScheme;

/**
 * Used for communication between view and model
 */
public class Presenter implements View.OnClickListener {

    public static final OperandStack OPERAND_STACK = new OperandStack();
    public static ArrayList<Operand> HISTORY_STACK = new ArrayList<>();
    public static StringBuilder INPUT_TERM = new StringBuilder();

    private static TileLayout layout;

    public static final String INPUT_FINALIZED = "final";

    public Presenter() {
        HISTORY_STACK = new ArrayList<>();
    }

    /**
     * Handles all tile input and decides on the follow up procedure
     * First decides on the type of input
     * @param v the tile view that has been clicked
     */
    @Override
    public void onClick(View v) {
        Tile tile = (Tile) v;
        if(tile.isOperand()) clickOperand(tile);
        else if(tile.isAction()) clickAction(tile);
        else if(tile.isStack() || tile.isHistory()) clickStackLike(tile);
        else if(tile.isSetting()) clickSetting(tile);
        else System.out.println("Handling exception when clicked on " + tile.getScheme());
    }

    /**
     * Handles all stack operation (similar to clickOperand)
     */
    private void clickStackLike(@NotNull Tile tile) {
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
        switch (tryAppending(operand)) {
            case 0: // string finalized
                resetInputTerm(operand);
                break;
            case 1: // not finalized, but pushed ahead
                add2History(operand);
                updateHistoryStack();
                break;
            case 2: //
                OPERAND_STACK.pop();
                operand = readCombinedOperand(INPUT_TERM).getOperand();
        }

        OPERAND_STACK.push(operand);
        updateStack();
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
        updateStack();
        updateHistoryStack();
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
            add2History(result);
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
    private int tryAppending(Operand operand) {
        if(INPUT_TERM.toString().equals(INPUT_FINALIZED)) return 0;
        if(OPERAND_STACK.peek() == null || OPERAND_STACK.peek() instanceof ODouble) {
            if (operand instanceof ODouble) {
                ODouble oNew = (ODouble) operand;
                int points = 0;
                if(INPUT_TERM.toString().contains(".")) points++;
                if(oNew.getDouble() % 1 != 0) points++;

                if(points < 2) {
                    INPUT_TERM.append(operand);
                    return 2;
                }
            }
        }
        return 1;
    }

    /**
     * Try adding an operand to the history stack
     * @param operand the operand ot be added
     */
    public static void add2History(Operand operand) {
        boolean add = true;
        for(Operand op : HISTORY_STACK) {
            if(op.equalsValue(operand)) add = false;
        }
        if(add) HISTORY_STACK.add(operand);
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
     * Set the layout that the controller can edit
     * @param layout current layout
     */
    public void setCurrentLayout(TileLayout layout) {
        Presenter.layout = layout;
    }

    /**
     * Lets the layout update its stack
     */
    public static void updateStack() {
        layout.updateStack(OPERAND_STACK);
    }

    /**
     * Lets the layout update its history stack
     */
    public static void updateHistoryStack() {
        layout.updateHistoryStack(HISTORY_STACK);
    }
}
