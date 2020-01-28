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

        if(indicator.equals("Morestack")) {
            //layout = readFromFile(context, "Example");
            layout = "S_LOADLAYOUT,Load Layout;S_SAVELAYOUT,Save Layout;S_STACK,1,O_DOUBLE,8;S_STACK,2,O_DOUBLE,0;S_STACK,3,O_DOUBLE,2;S_STACK,4,O_DOUBLE,1;S_STACK,5,O_DOUBLE,8;S_STACK,6,O_DOUBLE,17\nO_FRACTION,(1/2);O_MATRIX,[[1.23, 1.32], [0.23, 1.23]];O_SET,[1231, -0.232];O_TUPLE,(2, -1231.3);O_POLYNOM,4.1x^0 + 2x^1 + -3.1x^2;A_PLUS,+;A_PLUS,+;A_PLUS,+\nO_DOUBLE,4;O_DOUBLE,5;O_DOUBLE,6;A_SLASH,/;A_MINUS,-;A_PLUS,+;A_PLUS,+;A_PLUS,+\nO_DOUBLE,7;O_DOUBLE,8;O_DOUBLE,9;A_TIMES,*;A_PLUS,+;A_PLUS,+;A_PLUS,+;A_PLUS,+\nS_AC,AC;O_DOUBLE,0;S_ENTER,Enter;A_PLUS,+;A_PLUS,+;A_PLUS,+;A_PLUS,+;A_PLUS,+\nS_DEL,delete;S_TURNAROUNDSIGN,+/-;S_SWAP,Swap;S_INVERSE,1/x;A_PLUS,+;A_PLUS,+;A_PLUS,+;A_PLUS,+";
        }
        else if(indicator.equals("Standardlayout")) {
            //wenn operand/click nicht verfügbar ist, kommt ein plus dort hin
            layout = "S_STACK,1,O_DOUBLE,0;S_STACK,2,O_DOUBLE,0;S_STACK,3,O_DOUBLE,0;A_PLUS,+;A_SIN,sin;O_DOUBLE,0;O_DOUBLE,0;O_DOUBLE,0\nO_DOUBLE,1;O_DOUBLE,2;O_DOUBLE,3;A_MINUS,-;A_COS,cos;O_DOUBLE,0;O_DOUBLE,0;O_DOUBLE,0\nO_DOUBLE,4;O_DOUBLE,5;O_DOUBLE,6;A_SLASH,/;A_TAN,tan;O_DOUBLE,0;O_DOUBLE,0;O_DOUBLE,0\nO_DOUBLE,7;O_DOUBLE,8;O_DOUBLE,9;A_TIMES,*;A_MODULO,%;O_DOUBLE,0;O_DOUBLE,0;O_DOUBLE,0\nS_AC,AC;O_DOUBLE,0;S_ENTER,Enter;A_POWER,pow;A_ROOT,root;O_DOUBLE,0;O_DOUBLE,0;O_DOUBLE,0\nS_DEL,delete;S_TURNAROUNDSIGN,+/-;S_SWAP,Swap;S_INVERSE,1/x;A_LOG,log;O_DOUBLE,0;S_LOADLAYOUT,Load;S_SAVELAYOUT,Save";
            //layout = "S_LOADLAYOUT,Load Layout;S_SAVELAYOUT,Save Layout;S_STACK,1,O_DOUBLE,3;A_PLUS,+;A_PLUS,+;A_PLUS,+;A_PLUS,+;A_PLUS,+\nO_FRACTION,(1/2);O_MATRIX,[[1.23, 1.32], [0.23, 1.23]];O_SET,[1231, -0.232];O_TUPLE,(2, -1231.3);O_POLYNOM,4.1x^0 + 2x^1 + -3.1x^2;A_PLUS,+;A_PLUS,+;A_PLUS,+\nO_DOUBLE,4;O_DOUBLE,5;O_DOUBLE,6;A_SLASH,-;A_PLUS,+;A_PLUS,+;A_PLUS,+;A_PLUS,+\nO_DOUBLE,7;O_DOUBLE,8;O_DOUBLE,9;A_TIMES,-;A_PLUS,+;A_PLUS,+;A_PLUS,+;A_PLUS,+\nA_PLUS,+;A_PLUS,+;A_PLUS,+;A_PLUS,+;A_PLUS,+;A_PLUS,+;A_PLUS,+;A_PLUS,+\nA_PLUS,+;A_PLUS,+;A_PLUS,+;A_PLUS,+;A_PLUS,+;A_PLUS,+;A_PLUS,+;A_PLUS,+";
        }else{
            layout = readLayout(context, indicator);
        }

        return layout;
    }

    //Callable method to save a certain Layout
    public static boolean saveLayout(@NotNull Context context, @NotNull TileLayout tileLayout) {
        String layoutText = tileLayout.generateLayoutText();
        return writeLayout(context, layoutText, tileLayout.getIndicator());
    }

    //Callable method to clean all saved layouts
    public static void clearLayouts(@NotNull Context context) {
        File dir = context.getFilesDir();
        String[] files = dir.list();
        for(int i = 0; i < files.length; i++) {
            new File(dir, files[i]).delete();
        }
    }


    private static boolean writeLayout(Context context, String layoutText, String indicator) {
        try {
            //The path to the file is defined by its indicator, no doubles allowed!
            //String filePath = FOLDER + indicator + ".txt";
            System.out.println(layoutText);
            OutputStreamWriter out = new OutputStreamWriter(context.openFileOutput(indicator + ".csv", Context.MODE_PRIVATE));
            out.write(layoutText);
            out.close();
            System.out.println("Write: " + layoutText);
            return true;
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
            return false;
        }
    }

    private static String readLayout(Context context, String indicator) {
        //String filepath = FOLDER + indicator + ".txt";
        String ret = "";

        try {
            InputStream inputStream = context.openFileInput(indicator + ".csv");

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString + "\n");
                }

                inputStream.close();
                ret = stringBuilder.toString();
                System.out.println("Read: " + ret);
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
