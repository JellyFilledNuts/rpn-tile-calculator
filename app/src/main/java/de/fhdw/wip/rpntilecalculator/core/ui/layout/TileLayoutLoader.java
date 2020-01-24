package de.fhdw.wip.rpntilecalculator.core.ui.layout;

import android.content.Context;
import android.util.Log;
import android.util.Pair;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import de.fhdw.wip.rpntilecalculator.core.ui.TileMapping;

public class TileLayoutLoader {

    final static String VALUE_SEPERATOR = ";";
    private final static String COLUMN_SEPERATOR = ";;";
    private final static String ROW_SEPERATOR = ";;;";

    /**
     * Loads a saved or hard coded layout
     * @param context activity, from which to access storage
     * @param indicator name of the layout
     * @return TileLayout
     */
    public static TileLayout loadLayout(@NotNull Context context, @NotNull String indicator) {
        String layout = "";
        if(indicator.equals("TEST2")) {
            layout = "vO_DOUBLE;1;;O_DOUBLE;2;;O_DOUBLE;3;;;O_DOUBLE;5;;A_MINUS;-;;A_PLUS;+";
        } else if(indicator.equals("TEST3")) {
            layout = readFromFile(context, "TEST3FROMSTRING");
        }
        else if(indicator.equals("Standardlayout")) {
            //wenn operand/action nicht verfügbar ist, kommt ein plus dort hin
            //layout = "vA_PLUS;+;;A_PLUS;+;;A_PLUS;+;;A_PLUS;+;;A_PLUS;+;;A_PLUS;+;;A_PLUS;+;;A_PLUS;+;;;O_DOUBLE;1;;O_DOUBLE;2;;O_DOUBLE;3;;A_MINUS;-;;A_PLUS;+;;A_PLUS;+;;A_PLUS;+;;A_PLUS;+;;A_PLUS;+;;O_DOUBLE;4;;O_DOUBLE;5;;O_DOUBLE;6;;A_SLASH;-;;A_PLUS;+;;A_PLUS;+;;A_PLUS;+;;A_PLUS;+;;A_PLUS;+;;O_DOUBLE;7;;O_DOUBLE;8;;O_DOUBLE;9;;A_TIMES;-;;A_PLUS;+;;A_PLUS;+;;A_PLUS;+;;A_PLUS;+;;A_PLUS;+;;A_PLUS;+;;A_PLUS;+;;A_PLUS;+;;A_PLUS;+;;A_PLUS;+;;A_PLUS;+;;A_PLUS;+;;A_PLUS;+;;;A_PLUS;+;;A_PLUS;+;;A_PLUS;+;;A_PLUS;+;;A_PLUS;+;;A_PLUS;+;;A_PLUS;+;;A_PLUS;+";
            layout = "hA_PLUS;+;;A_PLUS;+;;A_PLUS;+;;A_PLUS;+;;A_PLUS;+;;A_PLUS;+;;A_PLUS;+;;A_PLUS;+;;;O_DOUBLE;1;;O_DOUBLE;2;;O_DOUBLE;3;;A_MINUS;-;;A_PLUS;+;;A_PLUS;+;;A_PLUS;+;;A_PLUS;+;;;O_DOUBLE;4;;O_DOUBLE;5;;O_DOUBLE;6;;A_SLASH;-;;A_PLUS;+;;A_PLUS;+;;A_PLUS;+;;A_PLUS;+;;;O_DOUBLE;7;;O_DOUBLE;8;;O_DOUBLE;9;;A_TIMES;-;;A_PLUS;+;;A_PLUS;+;;A_PLUS;+;;A_PLUS;+;;;A_PLUS;+;;A_PLUS;+;;A_PLUS;+;;A_PLUS;+;;A_PLUS;+;;A_PLUS;+;;A_PLUS;+;;A_PLUS;+;;;A_PLUS;+;;A_PLUS;+;;A_PLUS;+;;A_PLUS;+;;A_PLUS;+;;A_PLUS;+;;A_PLUS;+;;A_PLUS;+";
        }
        try {
            return new TileLayout(indicator, decipherLayout(layout));
        } catch (StorageLoadingException e) {
            // TODO
        }
        return new TileLayout("-", null);
    }

    //Callable method to save a certain Layout
    public static boolean saveLayout(@NotNull Context context, @NotNull TileLayout tileLayout) {
        String layoutText = encipherLayout(tileLayout.getTileLayout(), tileLayout.getOrientation());
        return writeToFile(context, layoutText, tileLayout.getIndicator());
    }

    //Callable method to clean all saved layouts
    public static void clearLayouts(@NotNull Context context) {
        File dir = context.getFilesDir();
        String[] files = dir.list();
        for(int i = 0; i < files.length; i++) {
            new File(dir, files[i]).delete();
        }
    }

    /**
     * Transforms the layout array into a string
     * @param tileLayout layout to be transformed
     * @return text of layout
     */
    private static String encipherLayout(@NotNull ArrayList<ArrayList<TileScheme>> tileLayout, ScreenOrientation orientation) {
        StringBuilder layoutText = new StringBuilder();
        layoutText.append(orientation.getIndicator());

        for(ArrayList<TileScheme> row : tileLayout) {
            for(TileScheme columnScheme : row) {
                layoutText.append(columnScheme.toString()).append(COLUMN_SEPERATOR);
            }
        }
        return layoutText.toString();
    }

    /**
     * Transforms the layout text into a layout array consisting of TileSchemes
     * @param layoutText layoutText from file
     * @return TileScheme[][] layout
     * @throws StorageLoadingException if a tile in the file could not be read
     */
    private static Pair<ScreenOrientation, ArrayList<ArrayList<TileScheme>>> decipherLayout(@NotNull String layoutText) throws StorageLoadingException {
        //Format is:
        //hO_DOUBLE;1;;O_DOUBLE;2;;O_DOUBLE;3;;;
        //O_DOUBLE;4;;A_MINUS;-;;A_PLUS;;;

        //decipher the layout orientation first
        ScreenOrientation orientation = ScreenOrientation.PORTRAIT;
        if(ScreenOrientation.isOrientation(layoutText.charAt(0))) {
            orientation = ScreenOrientation.getOrientation(layoutText.charAt(0));
            layoutText = layoutText.substring(1);
        }

        ArrayList<ArrayList<TileScheme>> tileRows = new ArrayList<>();

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
            tileRows.add(tileRow);
        }
        return new Pair<>(orientation, tileRows);
    }

    private static boolean writeToFile(Context context, String layoutText, String indicator) {
        try {
            //The path to the file is defined by its indicator, no doubles allowed!
            //String filePath = FOLDER + indicator + ".txt";
            OutputStreamWriter out = new OutputStreamWriter(context.openFileOutput(indicator + ".txt", Context.MODE_PRIVATE));
            out.write(layoutText);
            out.close();
            return true;
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
            return false;
        }
    }

    private static String readFromFile(Context context, String indicator) {
        //String filepath = FOLDER + indicator + ".txt";
        String ret = "";

        try {
            InputStream inputStream = context.openFileInput(indicator + ".txt");

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("Exception", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("Exception", "Can not read file: " + e.toString());
        }

        return ret;
    }

}
