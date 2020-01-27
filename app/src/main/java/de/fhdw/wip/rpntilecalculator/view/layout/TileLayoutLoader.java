package de.fhdw.wip.rpntilecalculator.view.layout;

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

public class TileLayoutLoader {

    /**
     * Loads a saved or hard coded layout
     * @param context activity, from which to access storage
     * @param indicator name of the layout
     * @return TileLayout
     */
    public String loadLayout(@NotNull Context context, @NotNull String indicator) {
        String layout = "";
        if(indicator.equals("Test")) {
            layout = "vO_DOUBLE;1;;O_DOUBLE;2;;O_DOUBLE;3;;;O_DOUBLE;5;;A_MINUS;-;;A_PLUS;+";
        } else if(indicator.equals("Morestack")) {
            //layout = readFromFile(context, "Example");
            layout = "hS_STACK;1;;S_STACK;2;;S_STACK;3;;S_STACK;4;;S_STACK;5;;S_STACK;6;;S_STACK;7;;S_STACK;8;;;O_FRACTION;(1/2);;O_MATRIX;[[1.23, 1.32], [0.23, 1.23]];;O_SET;[1231, -0.232];;O_TUPLE;(2, -1231.3);;O_POLYNOM;4.1x^0 + 2x^1 + -3.1x^2;;A_PLUS;+;;A_PLUS;+;;A_PLUS;+;;;O_DOUBLE;4;;O_DOUBLE;5;;O_DOUBLE;6;;A_SLASH;/;;A_MINUS;-;;A_PLUS;+;;A_PLUS;+;;A_PLUS;+;;;O_DOUBLE;7;;O_DOUBLE;8;;O_DOUBLE;9;;A_TIMES;*;;A_PLUS;+;;A_PLUS;+;;A_PLUS;+;;A_PLUS;+;;;S_AC;AC;;O_DOUBLE;0;;S_ENTER;Enter;;A_PLUS;+;;A_PLUS;+;;A_PLUS;+;;A_PLUS;+;;A_PLUS;+;;;S_DEL;delete;;S_TURNAROUNDSIGN;+/-;;S_SWAP;Swap;;S_INVERSE;1/x;;A_PLUS;+;;A_PLUS;+;;A_PLUS;+;;A_PLUS;+";
        }
        else if(indicator.equals("Standardlayout")) {
            //wenn operand/click nicht verfügbar ist, kommt ein plus dort hin
            //layout = "vA_PLUS;+;;A_PLUS;+;;A_PLUS;+;;A_PLUS;+;;A_PLUS;+;;A_PLUS;+;;A_PLUS;+;;A_PLUS;+;;;O_DOUBLE;1;;O_DOUBLE;2;;O_DOUBLE;3;;A_MINUS;-;;A_PLUS;+;;A_PLUS;+;;A_PLUS;+;;A_PLUS;+;;A_PLUS;+;;O_DOUBLE;4;;O_DOUBLE;5;;O_DOUBLE;6;;A_SLASH;-;;A_PLUS;+;;A_PLUS;+;;A_PLUS;+;;A_PLUS;+;;A_PLUS;+;;O_DOUBLE;7;;O_DOUBLE;8;;O_DOUBLE;9;;A_TIMES;-;;A_PLUS;+;;A_PLUS;+;;A_PLUS;+;;A_PLUS;+;;A_PLUS;+;;A_PLUS;+;;A_PLUS;+;;A_PLUS;+;;A_PLUS;+;;A_PLUS;+;;A_PLUS;+;;A_PLUS;+;;A_PLUS;+;;;A_PLUS;+;;A_PLUS;+;;A_PLUS;+;;A_PLUS;+;;A_PLUS;+;;A_PLUS;+;;A_PLUS;+;;A_PLUS;+";
            layout = "hS_STACK;1;;S_STACK;2;;S_STACK;3;;A_PLUS;+;;A_PLUS;+;;A_PLUS;+;;A_PLUS;+;;A_PLUS;+;;;O_FRACTION;(1/2);;O_MATRIX;[[1.23, 1.32], [0.23, 1.23]];;O_SET;[1231, -0.232];;O_TUPLE;(2, -1231.3);;O_POLYNOM;4.1x^0 + 2x^1 + -3.1x^2;;A_PLUS;+;;A_PLUS;+;;A_PLUS;+;;;O_DOUBLE;4;;O_DOUBLE;5;;O_DOUBLE;6;;A_SLASH;-;;A_PLUS;+;;A_PLUS;+;;A_PLUS;+;;A_PLUS;+;;;O_DOUBLE;7;;O_DOUBLE;8;;O_DOUBLE;9;;A_TIMES;-;;A_PLUS;+;;A_PLUS;+;;A_PLUS;+;;A_PLUS;+;;;A_PLUS;+;;A_PLUS;+;;A_PLUS;+;;A_PLUS;+;;A_PLUS;+;;A_PLUS;+;;A_PLUS;+;;A_PLUS;+;;;A_PLUS;+;;A_PLUS;+;;A_PLUS;+;;A_PLUS;+;;A_PLUS;+;;A_PLUS;+;;A_PLUS;+;;A_PLUS;+";
        }

        return layout;
    }

    //Callable method to save a certain Layout
    public static boolean saveLayout(@NotNull Context context, @NotNull TileLayout tileLayout) {
        String layoutText = tileLayout.encipher();
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
