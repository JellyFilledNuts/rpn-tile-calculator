package de.fhdw.wip.rpntilecalculator.view.layout;

import android.content.Context;
import android.graphics.Color;
import android.util.Pair;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import de.fhdw.wip.rpntilecalculator.view.Tile;

public class TileLayout {

    //Margin between tiles
    private static final int TILE_MARGIN = 3;

    private ArrayList<ArrayList<TileScheme>> tileLayout;
    private ScreenOrientation orientation;
    private String indicator;

    TileLayout(String indicator, Pair<ScreenOrientation, ArrayList<ArrayList<TileScheme>>> layoutData) {
        this(indicator, layoutData.first, layoutData.second);
    }

    TileLayout(String indicator, ScreenOrientation orientation, ArrayList<ArrayList<TileScheme>> tileLayout) {
        this.orientation = orientation;
        this.tileLayout = tileLayout;
        this.indicator = indicator;
        // TODO Test if two layouts can have the same name
    }

    String getIndicator() {
        return indicator;
    }

    ArrayList<ArrayList<TileScheme>> getTileLayout() {
        return tileLayout;
    }

    public TableLayout createView(@NotNull Context context) {
        //Create table by first creating one column as TableLayout
        TableLayout columns = new TableLayout(context);
        columns.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        columns.setBackgroundColor(Color.BLACK);

        //Creating the in tileLayout defined amount of rows
        for(ArrayList<TileScheme> row : tileLayout) {

            //Rows are of type TableRow, Layout is important!
            TableRow rowView = new TableRow(context);
            rowView.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.MATCH_PARENT, 1.0f));
            rowView.setGravity(Gravity.CENTER);
            rowView.setBackgroundColor(Color.WHITE);

            //Creating buttons which amount defines the amount of columns
            for(TileScheme tileScheme : row) {

                //For the design of the Button TileScheme is used and for the button itself Tile
                Button tile = new Tile(context, tileScheme);
                TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT, 1.0f);
                layoutParams.setMargins(TILE_MARGIN, TILE_MARGIN, TILE_MARGIN, TILE_MARGIN);
                tile.setLayoutParams(layoutParams);

                tile.setBackgroundResource(tileScheme.getStyle());
                rowView.addView(tile);
            }
            columns.addView(rowView); //TODO: columns.addView(row, 1)
        }
        return columns;
    }

    public ScreenOrientation getOrientation() {
        return orientation;
    }
}