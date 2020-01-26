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
import java.util.HashMap;
import java.util.List;

import de.fhdw.wip.rpntilecalculator.model.operands.Operand;
import de.fhdw.wip.rpntilecalculator.model.stack.OperandStack;
import de.fhdw.wip.rpntilecalculator.view.Tile;
import de.fhdw.wip.rpntilecalculator.view.TileMapping;
import de.fhdw.wip.rpntilecalculator.view.events.StackUpdateListener;

public class TileLayout implements StackUpdateListener {

    //Margin between tiles
    private static final int TILE_MARGIN = 3;

    private SparseArray<Tile> stack = new SparseArray<>();
    private ArrayList<ArrayList<Tile>> tileLayout = new ArrayList<>();

    private ArrayList<ArrayList<TileScheme>> schemeLayout;
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
        for(int i = 0; i < stack.size(); i++) {
            Tile stackTile = stack.valueAt(i);
            stackTile.update(TileScheme.createTileScheme(TileMapping.S_STACK, stackOperands.get(i), stack.keyAt(i)));
        }
    }

    /**
     * Transforms the layout array into a string
     * @return text of layout
     */
    public String encipher() {
        StringBuilder layoutText = new StringBuilder();
        layoutText.append(orientation.getIndicator());

        for(ArrayList<TileScheme> row : schemeLayout) {
            for(TileScheme columnScheme : row) {
                layoutText.append(columnScheme.toString()).append(TileLayoutFactory.COLUMN_SEPERATOR);
            }
        }
        return layoutText.toString();
    }

    String getIndicator() {
        return indicator;
    }

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

                if(tile.getScheme().getTileType().getType().isStack()) {
                    stack.put(((StackTileScheme) tileScheme).getRank(), tile);
                }
            }
            tableView.addView(rowView);
            tileLayout.add(tileRow);
        }
        return tableView;
    }

    public void drawTile(Tile tile) {
        TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT, 1.0f);
        layoutParams.setMargins(TILE_MARGIN, TILE_MARGIN, TILE_MARGIN, TILE_MARGIN);
        tile.setLayoutParams(layoutParams);
        tile.setBackgroundResource(tile.getScheme().getStyle());
    }

    public ScreenOrientation getOrientation() {
        return orientation;
    }
}