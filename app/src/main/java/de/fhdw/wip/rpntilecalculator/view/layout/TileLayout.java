package de.fhdw.wip.rpntilecalculator.view.layout;

import android.content.Context;
import android.graphics.Color;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.TableLayout;
import android.widget.TableRow;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import de.fhdw.wip.rpntilecalculator.model.operands.ODouble;
import de.fhdw.wip.rpntilecalculator.model.operands.OEmpty;
import de.fhdw.wip.rpntilecalculator.presenter.Presenter;
import de.fhdw.wip.rpntilecalculator.model.operands.Operand;
import de.fhdw.wip.rpntilecalculator.model.stack.OperandStack;
import de.fhdw.wip.rpntilecalculator.view.Tile;
import de.fhdw.wip.rpntilecalculator.view.TileMapping;
import de.fhdw.wip.rpntilecalculator.view.schemes.HistoryTileScheme;
import de.fhdw.wip.rpntilecalculator.view.schemes.StackTileScheme;
import de.fhdw.wip.rpntilecalculator.view.schemes.TileScheme;

public class TileLayout {

    private SparseArray<Tile> stack = new SparseArray<>();
    private SparseArray<Tile> historyStack = new SparseArray<>();
    private ArrayList<ArrayList<Tile>> tileLayout = new ArrayList<>();

    private ArrayList<ArrayList<TileScheme>> schemeLayout; //outdated after a single operation
    private ScreenOrientation orientation;
    private String indicator;

    TileLayout(String indicator, ArrayList<ArrayList<TileScheme>> schemeLayout, ScreenOrientation orientation) {
        this.schemeLayout = schemeLayout;
        this.orientation = orientation;
        this.indicator = indicator;
    }

    /**
     * Updates the stack and decides which ones are displayed
     */
    public void updateStack(OperandStack operandStack) {
        @NotNull List<Operand> stackOperands = operandStack.peek(stack.size());

        for(int i = 0; i < stack.size(); i++) {
            Tile stackTile = stack.valueAt(i);
            Operand operand = null;
            if(i < stackOperands.size()) operand = stackOperands.get(i);
            stackTile.update(TileScheme.createTileScheme(TileMapping.S_STACK, operand, stack.keyAt(i)));

            //Sonderfall erster Stack Tile
            if(i == 0 && operand != null) {
                if(operand instanceof ODouble)
                    stackTile.setText(Presenter.INPUT_TERM.toString());
            }
        }
    }

    /**
     * Updates the History Stack. if there are more values, the first ones are cut
     */
    public void updateHistoryStack(List<Operand> operandList) {
        int overflow = operandList.size() - historyStack.size();
        //Adjust to actual history stack size
        for(int i = 0; i < overflow; i++) {
            operandList.remove(0);
        }
        for(int i = 0; i < historyStack.size(); i++) {
            Tile historyTile = historyStack.valueAt(i);
            Operand operand = null;
            if(i < operandList.size()) operand = operandList.get(i);
            historyTile.update(TileScheme.createTileScheme(TileMapping.H_HISTORY, operand, i));
        }
    }

    /**
     * Transforms the layout array into a string
     * @return text of layout
     */
    public String generateLayoutText() {
        StringBuilder layoutText = new StringBuilder();
        //layoutText.append(orientation.getIndicator());

        for(ArrayList<Tile> row : tileLayout) {
            for(Tile t : row) {
                layoutText.append(t.getScheme().toString()).append(TileLayoutFactory.COLUMN_SEPERATOR);
            }
            layoutText.deleteCharAt(layoutText.length()-1);
            layoutText.append(TileLayoutFactory.ROW_SEPERATOR);
        }
        //layoutText.deleteCharAt(layoutText.length()-1);
        return layoutText.toString();
    }

    public String getIndicator() {
        return indicator;
    }
    public void setIndicator(String indicator) { this.indicator = indicator; }

    /**
     * Creates the 2d ArrayList of Tiles, the stack list & the history stack list based on
     * the before created TileScheme Layout
     * @param context the context of the application
     * @param presenter an instance of the presenter to act as listener
     * @return a View object TableLayout that can be added to the screen
     */
    public TableLayout createView(@NotNull Context context, @NotNull Presenter presenter) {
        //Create table by first creating one column as TableLayout
        TableLayout tableView = new TableLayout(context);
        tableView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        tableView.setBackgroundColor(Color.BLACK);

        //Creating the in schemeLayout defined amount of rows
        for(ArrayList<TileScheme> row : schemeLayout) {
            ArrayList<Tile> tileRow = new ArrayList<>();
            TableRow rowView = new TableRow(context);

            rowView.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.MATCH_PARENT, 1.0f));
            rowView.setGravity(Gravity.CENTER);
            rowView.setBackgroundColor(Color.WHITE);

            //Creating buttons which amount defines the amount of columns
            for(TileScheme tileScheme : row) {

                //For the design of the Button TileScheme is used and for the button itself Tile
                Tile tile = new Tile(context, tileScheme, this);
                tile.setOnClickListener(presenter);
                tile.enableMenuListener();

                rowView.addView(tile);
                tileRow.add(tile);

                addToStacks(tile);
            }
            tableView.addView(rowView);
            tileLayout.add(tileRow);
        }

        pushStack2Presenter();
        pushHistoryStack2Presenter();

        showAnimation(Tile.buttonLoad);

        return tableView;
    }

    /**
     * Sets the Stack of the Presenter
     */
    private void pushStack2Presenter() {
        Presenter.OPERAND_STACK.clear();
        for(int i = stack.size()-1; i >= 0; i--) {
            Presenter.OPERAND_STACK.push(((StackTileScheme)stack.valueAt(i).getScheme()).getOperand());
        }
    }

    /**
     * Sets the History Stack of the Presenter
     */
    private void pushHistoryStack2Presenter() {
        Presenter.HISTORY_STACK.clear();
        for(int i = 0; i < historyStack.size(); i++) {
            Operand operand = ((HistoryTileScheme) historyStack.valueAt(i).getScheme()).getOperand();
            if(!operand.equalsValue(new OEmpty()))
                Presenter.HISTORY_STACK.add(operand);
        }
    }

    public ScreenOrientation getOrientation() {
        return orientation;
    }

    /**
     * Adds a tile to stack or history stack
     */
    public void addToStacks(Tile tile) {
        if(tile.getScheme() instanceof HistoryTileScheme) {
            updateStackOrder(tile, historyStack, TileMapping.H_HISTORY);
            pushHistoryStack2Presenter();
        }
        else if(tile.getScheme() instanceof StackTileScheme) {
            updateStackOrder(tile, stack, TileMapping.S_STACK);
            pushStack2Presenter();
        }
    }

    /**
     * Updates the order of a stack and makes free room for a new one
     */
    private void updateStackOrder(Tile tile, SparseArray<Tile> stackType, TileMapping mapping) {
        StackTileScheme scheme = (StackTileScheme) tile.getScheme();
        if(stackType.get(scheme.getRank()) == null) {
            stackType.append(scheme.getRank(), tile);
            return;
        }

        //Test if stack tile is replaced with stack tile
        Tile replaceTile = stackType.get(scheme.getRank());
        if(replaceTile.getScheme() instanceof StackTileScheme) {
            StackTileScheme replaceScheme = (StackTileScheme) replaceTile.getScheme();

            TileScheme replaceScheme2 = TileScheme.createTileScheme(mapping, scheme.getOperand(), replaceScheme.getRank());
            replaceTile.update(replaceScheme2);

            TileScheme scheme2 = TileScheme.createTileScheme(mapping, replaceScheme.getOperand(), scheme.getRank());
            tile.update(scheme2);
        }

        for(int i = stackType.size() -1; i >= scheme.getRank(); i--) {
            Tile hisTile = stackType.get(i);
            StackTileScheme hisScheme = (StackTileScheme) hisTile.getScheme();
            TileScheme newScheme = TileScheme.createTileScheme(mapping, hisScheme.getOperand(), (i + 1));
            hisTile.update(newScheme);
            stackType.append((i+1), hisTile);
        }
        stackType.append(scheme.getRank(), tile);
    }

    /**
     * Removes a tile form the stack or history stack
     */
    public void removeFromStacks(Tile tile) {
        if(historyStack.indexOfValue(tile) >= 0)
            historyStack.remove(historyStack.indexOfValue(tile)+1);
        else if(stack.indexOfValue(tile) >= 0)
            stack.remove(stack.indexOfValue(tile)+1);
    }

    public int getStackSize() {
        return stack.size();
    }

    public int getHistoryStackSize() {
        return historyStack.size();
    }

    /**
     * Lets all tiles display a certain animation
     * @param animation the animation to be displayed
     */
    public void showAnimation(Animation animation) {
        for(ArrayList<Tile> tileRow: tileLayout) {
            for(Tile tile : tileRow) tile.startAnimation(animation);
        }
    }
}