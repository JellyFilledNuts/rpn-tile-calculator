package de.fhdw.wip.rpntilecalculator.core.ui.layout;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

import org.jetbrains.annotations.NotNull;

import de.fhdw.wip.rpntilecalculator.core.ui.Tile;

public class TileLayout {

    private TileScheme[][] tileLayout;
    private String indicator;

    TileLayout(String indicator, TileScheme[][] tileLayout) {
        this.tileLayout = tileLayout;
        this.indicator = indicator;
        // TODO Test if two layouts can have the same name
    }

    String getIndicator() {
        return indicator;
    }

    TileScheme[][] getTileLayout() {
        return tileLayout;
    }

    public TableLayout createView(@NotNull Context context) {

        //Create table by first creating one column as TableLayout
        TableLayout columns = new TableLayout(context);
        columns.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        columns.setBackgroundColor(Color.BLUE);

        //Creating the in tileLayout defined amount of rows
        for(int i = 0; i < getTileLayout().length; i++) {

            //Rows are of type TableRow, Layout is important!
            TableRow row = new TableRow(context);
            row.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.MATCH_PARENT, 1.0f));
            row.setGravity(Gravity.CENTER);
            row.setBackgroundColor(Color.BLACK);

            //Creating buttons which amount defines the amount of columns
            for(int j = 0; j < getTileLayout()[i].length; j++) {

                //For the design of the Button TileScheme is used and for the button itself Tile
                TileScheme tileScheme = getTileLayout()[i][j];
                Button tile = new Tile(context, tileScheme.getType(), tileScheme.getContent());
                tile.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT, 1.0f));
                row.addView(tile, j);
            }
            columns.addView(row, i);
        }
        return columns;
    }
}