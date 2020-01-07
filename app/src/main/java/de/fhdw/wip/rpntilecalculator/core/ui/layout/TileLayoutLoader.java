package de.fhdw.wip.rpntilecalculator.core.ui.layout;

import android.content.Context;
import android.util.Log;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import de.fhdw.wip.rpntilecalculator.core.ui.TileType;

public class TileLayoutLoader {

    private final static String VALUE_SEPERATOR = ";";
    private final static String COLUMN_SEPERATOR = ";;";
    private final static String ROW_SEPERATOR = ";;;";

    @NotNull
    public static TileLayout loadLayout(@NotNull Context context, @NotNull String indicator) {
        String layout = "";
        if(indicator.equals("TEST2")) {
            layout = "OPERAND;1;;OPERAND;2;;OPERAND;3;;;OPERAND;5;;O_MINUS;-;;O_PLUS;+";
        } else if(indicator.equals("TEST3")) {
            layout = readFromFile(context, "TEST3FROMSTRING");
        }
        try {
            return new TileLayout(indicator, decryptLayout(layout));
        } catch (StorageLoadingException e) {
            // TODO
        }
        return new TileLayout("-", null);
    }

    //Callable method to save a certain Layout
    public static boolean saveLayout(@NotNull Context context, @NotNull TileLayout tileLayout) {
        String layoutText = encryptLayout(tileLayout.getTileLayout());
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

    private static String encryptLayout(@NotNull TileScheme[][] tileLayout) {
        StringBuilder layoutText = new StringBuilder();
        for(int i = 0; i < tileLayout.length; i++) {
            for(int j = 0; j < tileLayout[i].length; j++) {
                layoutText.append(tileLayout[i][j].getType().toString()).append(";");
                layoutText.append(tileLayout[i][j].getContent()).append(";;");
            }
            layoutText.append(";");
        }
        return layoutText.toString();
    }

    private static TileScheme[][] decryptLayout(@NotNull String layoutText) throws StorageLoadingException {
        //Format is:
        //O_DOUBLE;1;;O_DOUBLE;2;;O_DOUBLE;3;;;
        //O_DOUBLE;4;;A_MINUS;-;;A_PLUS;;;

        ArrayList<ArrayList<TileScheme>> tileRows = new ArrayList<>();

        // Read the string and place it into the arraylist
        String[] rows = layoutText.split(ROW_SEPERATOR);
        for(String row : rows) {
            String[] columns = row.split(COLUMN_SEPERATOR);
            ArrayList<TileScheme> tileRow = new ArrayList<>();
            for(String column : columns) {
                String[] values = column.split(VALUE_SEPERATOR);

                //Convert string enum to real enum
                TileMapping mappedTile = null;
                try{
                    mappedTile = Enum.valueOf(TileMapping.class, values[0]);
                } catch (Exception e) {
                    throw new StorageLoadingException("The value " + values[0] + " could not be deciphered.");
                }

                //Add tilescheme to tilerow
                if(TileType.isAction(mappedTile.getType())) {
                    tileRow.add(new TileScheme(mappedTile.getType(), mappedTile.getActionText(), mappedTile.getActionType()));
                } else if(TileType.isOperand(mappedTile.getType())) {
                    //LOAD ENUM AND SHIT
                }
            }
            //Add tilerow to tilerows
            tileRows.add(tileRow);
        }

        //Transform ArrayList into required binary list
        TileScheme[][] tileLayout = new TileScheme[tileRows.size()][tileRows.get(0).size()];
        for(int i = 0; i < tileRows.size(); i++) {
            for(int j = 0; j < tileRows.get(i).size(); j++) {
                tileLayout[i][j] = tileRows.get(i).get(j);
            }
        }
        return tileLayout;
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
