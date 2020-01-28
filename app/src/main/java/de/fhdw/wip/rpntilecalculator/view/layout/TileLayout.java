package de.fhdw.wip.rpntilecalculator.view.layout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.core.widget.TextViewCompat;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import de.fhdw.wip.rpntilecalculator.MainActivity;
import de.fhdw.wip.rpntilecalculator.controller.Controller;
import de.fhdw.wip.rpntilecalculator.model.operands.Operand;
import de.fhdw.wip.rpntilecalculator.model.stack.OperandStack;
import de.fhdw.wip.rpntilecalculator.view.Tile;
import de.fhdw.wip.rpntilecalculator.view.TileMapping;
import de.fhdw.wip.rpntilecalculator.view.events.StackUpdateListener;
import de.fhdw.wip.rpntilecalculator.view.layout.schemes.HistoryTileScheme;
import de.fhdw.wip.rpntilecalculator.view.layout.schemes.StackTileScheme;
import de.fhdw.wip.rpntilecalculator.view.layout.schemes.TileScheme;

public class TileLayout implements StackUpdateListener {

    //Margin between tiles
    private static final int TILE_MARGIN = 3;

    private SparseArray<Tile> stack = new SparseArray<>();
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
     * Listens to stack updates
     */
    @Override
    public void updateStack(OperandStack operandStack) {
        @NotNull List<Operand> stackOperands = operandStack.peek(stack.size());
        operandStack.print();

        for(int i = 0; i < stack.size(); i++) {
            Tile stackTile = stack.valueAt(i);
            Operand operand = null;
            if(i < stackOperands.size()) operand = stackOperands.get(i);
            stackTile.update(TileScheme.createTileScheme(TileMapping.S_STACK, operand, stack.keyAt(i)));
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

    public TableLayout createView(@NotNull Context context) {
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
                Tile tile = new Tile(context, tileScheme);

                drawTile(tile);

                rowView.addView(tile);
                tileRow.add(tile);

                if(tile.getScheme() instanceof StackTileScheme &&
                        !(tile.getScheme() instanceof HistoryTileScheme)) {
                    stack.append(((StackTileScheme) tileScheme).getRank(), tile);
                }
            }
            tableView.addView(rowView);
            tileLayout.add(tileRow);
        }
        Controller.OPERAND_STACK.clear();
        for(int i = stack.size()-1; i >= 0; i--) {
            Controller.OPERAND_STACK.push(((StackTileScheme)stack.valueAt(i).getScheme()).getOperand());
        }
        return tableView;
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