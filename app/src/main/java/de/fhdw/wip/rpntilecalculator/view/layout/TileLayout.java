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
import java.util.HashMap;

import de.fhdw.wip.rpntilecalculator.view.Tile;
import de.fhdw.wip.rpntilecalculator.view.TileMapping;

public class TileLayout {

    final static String VALUE_SEPERATOR = ";";
    private final static String COLUMN_SEPERATOR = ";;";
    private final static String ROW_SEPERATOR = ";;;";

    //Margin between tiles
    private static final int TILE_MARGIN = 3;

    private HashMap<Integer, Tile> layoutStack = new HashMap<>();
    private ArrayList<Tile> layoutTiles = new ArrayList<>();

    private ArrayList<ArrayList<TileScheme>> tileLayout = new ArrayList<>();
    private ScreenOrientation orientation = ScreenOrientation.PORTRAIT;
    private String indicator;

    TileLayout(String indicator, String layoutText) {
        this.indicator = indicator;


        //Format is:
        //hO_DOUBLE;1;;O_DOUBLE;2;;O_DOUBLE;3;;;
        //O_DOUBLE;4;;A_MINUS;-;;A_PLUS;;;

        //decipher the layout orientation first
        if(ScreenOrientation.isOrientation(layoutText.charAt(0))) {
            orientation = ScreenOrientation.getOrientation(layoutText.charAt(0));
            layoutText = layoutText.substring(1);
        }


        // Read the string and place it into the arraylist
        String[] rows = layoutText.split(ROW_SEPERATOR);
        for(String row : rows) {
            String[] columns = row.split(COLUMN_SEPERATOR);
            ArrayList<TileScheme> tileRow = new ArrayList<>();
            for(String column : columns) {
                String[] values = column.split(VALUE_SEPERATOR);

                //Convert string enum to real enum
                TileMapping tileType = null;
                try {
                    tileType = Enum.valueOf(TileMapping.class, values[0]);
                } catch (Exception e) {
                    System.out.println("Exception occured: " + values[0] + ";" + values[1]);
                    tileType = TileMapping.X_ERROR;
                    values[1] = tileType.getActionText();
                    //throw new StorageLoadingException("The value " + values[0] + " could not be deciphered.");
                }

                //Add tilescheme to tilerow
                TileScheme scheme = TileScheme.createTileScheme(tileType, values[1]);
                tileRow.add(scheme);
            }
            //Add tilerow to tilerows
            tileLayout.add(tileRow);
        }
    }

    /**
     * Transforms the layout array into a string
     * @return text of layout
     */
    public String encipherLayout() {
        StringBuilder layoutText = new StringBuilder();
        layoutText.append(orientation.getIndicator());

        for(ArrayList<TileScheme> row : tileLayout) {
            for(TileScheme columnScheme : row) {
                layoutText.append(columnScheme.toString()).append(COLUMN_SEPERATOR);
            }
        }
        return layoutText.toString();
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
                Tile tile = new Tile(context, tileScheme);

                drawTile(tile);

                rowView.addView(tile);

                //For later editing
                layoutTiles.add(tile);
                if(tile.getScheme().getTileType().getType().isStack()) {
                    layoutStack.put(((StackTileScheme) tileScheme).getRank(), tile);
                }
            }
            columns.addView(rowView); //TODO: columns.addView(row, 1)
        }
        return columns;
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