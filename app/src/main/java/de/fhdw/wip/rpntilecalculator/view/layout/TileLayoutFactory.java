package de.fhdw.wip.rpntilecalculator.view.layout;

import android.content.Context;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import de.fhdw.wip.rpntilecalculator.view.TileMapping;
import de.fhdw.wip.rpntilecalculator.view.schemes.TileScheme;

public class TileLayoutFactory {

    public final static String VALUE_SEPERATOR = ",";
    public final static String COLUMN_SEPERATOR = ";";
    public final static String ROW_SEPERATOR = "\n";

    private static TileLayoutLoader layoutLoader = new TileLayoutLoader();

    private static ArrayList<ArrayList<TileScheme>> tileLayout;
    private static ScreenOrientation orientation = ScreenOrientation.PORTRAIT;
    private static String layoutText;

    public static TileLayout createLayout(@NotNull Context context, @NotNull String indicator) {
        tileLayout = new ArrayList<>();
        layoutText = layoutLoader.loadLayout(context, indicator);

        loadOrientation();

        loadLayout();

        return new TileLayout(indicator, tileLayout, orientation);
    }

    private static void loadOrientation() {
        //decipher the layout orientation
        orientation = ScreenOrientation.getOrientation(layoutText.charAt(0));
    }

    private static void loadLayout() {

        // Read the string and place it into the arraylist
        String[] rows = layoutText.split(ROW_SEPERATOR);
        for(String row : rows) {
            System.out.println(row);
            String[] columns = row.split(COLUMN_SEPERATOR);
            ArrayList<TileScheme> tileRow = new ArrayList<>();
            for(String column : columns) {
                String[] values = column.split(VALUE_SEPERATOR, 2);

                //Convert string enum to real enum
                TileMapping tileType = null;
                try {
                    tileType = Enum.valueOf(TileMapping.class, values[0]);
                } catch (Exception e) {
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

}
