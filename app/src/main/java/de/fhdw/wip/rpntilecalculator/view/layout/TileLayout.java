package de.fhdw.wip.rpntilecalculator.view.layout;

import android.content.Context;
import android.graphics.Color;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import de.fhdw.wip.rpntilecalculator.model.operands.ODouble;
import de.fhdw.wip.rpntilecalculator.presenter.Presenter;
import de.fhdw.wip.rpntilecalculator.model.operands.Operand;
import de.fhdw.wip.rpntilecalculator.model.stack.OperandStack;
import de.fhdw.wip.rpntilecalculator.view.Tile;
import de.fhdw.wip.rpntilecalculator.view.TileMapping;
import de.fhdw.wip.rpntilecalculator.view.layout.schemes.HistoryTileScheme;
import de.fhdw.wip.rpntilecalculator.view.layout.schemes.StackTileScheme;
import de.fhdw.wip.rpntilecalculator.view.layout.schemes.TileScheme;

public class TileLayout {

    //Margin between tiles
    private static final int TILE_MARGIN = 3;

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
            Presenter.HISTORY_STACK.remove(0);
        }
        for(int i = 0; i < operandList.size(); i++) {
            Tile historyTile = historyStack.valueAt(i);
            historyTile.update(TileScheme.createTileScheme(TileMapping.H_HISTORY, operandList.get(i), i));
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
                Tile tile = new Tile(context, tileScheme, presenter);

                drawTile(tile);

                rowView.addView(tile);
                tileRow.add(tile);

                if(tile.getScheme() instanceof StackTileScheme) {
                    if(tile.getScheme() instanceof HistoryTileScheme) {
                        historyStack.append(((HistoryTileScheme) tileScheme).getRank(), tile);
                    } else {
                        stack.append(((StackTileScheme) tileScheme).getRank(), tile);
                    }
                }
            }
            tableView.addView(rowView);
            tileLayout.add(tileRow);
        }

        pushStack();
        pushHistoryStack();

        return tableView;
    }

    /**
     * Sets the Stack of the Presenter
     */
    private void pushStack() {
        Presenter.OPERAND_STACK.clear();
        for(int i = stack.size()-1; i >= 0; i--) {
            Presenter.OPERAND_STACK.push(((StackTileScheme)stack.valueAt(i).getScheme()).getOperand());
        }
    }

    /**
     * Sets the History Stack of the Presenter
     */
    private void pushHistoryStack() {
        Presenter.HISTORY_STACK.clear();
        for(int i = 0; i < historyStack.size(); i++) {
            Operand operand = ((HistoryTileScheme) historyStack.valueAt(i).getScheme()).getOperand();
            if(!operand.equalsValue(new ODouble(0)))
                Presenter.HISTORY_STACK.add(operand);
        }
    }

    public void drawTile(Tile tile) {
            TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT, 1.0f);
            layoutParams.setMargins(TILE_MARGIN, TILE_MARGIN, TILE_MARGIN, TILE_MARGIN);
            tile.setLayoutParams(layoutParams);

        if(tile.getScheme() != null) {
            tile.setBackgroundResource(tile.getScheme().getStyle());
        }else{
            System.out.println("Cloud not draw Tile");
        }
    }

    public ScreenOrientation getOrientation() {
        return orientation;
    }

}