package de.fhdw.wip.rpntilecalculator.core.ui;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class TileLayout {

    private TileScheme[][] tileLayout;
    private final static String VALUE_SEPERATOR = ";";
    private final static String COLUMN_SEPERATOR = ";;";
    private final static String ROW_SEPERATOR = ";;;";

    public TileLayout() {}

    public TileLayout(String title) {
        tileLayout = new TileScheme[6][8];

        // DEFAULT Layout
        if(title.equals("DEFAULT")) {
            for(int i = 0; i < tileLayout.length; i++) {
                for(int j = 0; j < tileLayout[i].length; j++) {
                    tileLayout[i][j] = new TileScheme(TileType.O_MINUS, TileType.O_MINUS.getDisplayText());
                }
            }
        } else if(title.equals("TEST")) {
            tileLayout = new TileScheme[2][3];

            String operator = "OPERAND";
            String content = "1";

            tileLayout[0][0] = new TileScheme(Enum.valueOf(TileType.class, operator), content);
            tileLayout[0][1] = new TileScheme(TileType.OPERAND, "2");
            tileLayout[0][2] = new TileScheme(TileType.OPERAND, "3");
            tileLayout[1][0] = new TileScheme(TileType.OPERAND, "4");
            tileLayout[1][1] = new TileScheme(TileType.O_MINUS, TileType.O_MINUS.getDisplayText());
            tileLayout[1][2] = new TileScheme(TileType.O_PLUS, TileType.O_PLUS.getDisplayText());
        } else if(title.equals("TEST2")) {

            try {
                tileLayout = loadLayout("OPERAND;1;;OPERAND;2;;OPERAND;3;;;OPERAND;5;;O_MINUS;-;;O_PLUS;+");
            } catch (StorageLoadingException e) {
                // TODO
            }
        }
    }

    private TileScheme[][] loadLayout(@NotNull String layoutText) throws StorageLoadingException {
        //Format is:
        //OPERAND;1;;OPERAND;2;;OPERAND;3;;;
        //OPERAND;4;;O_MINUS;-;;O_PLUS;;;

        ArrayList<ArrayList<TileScheme>> tileRows = new ArrayList<>();

        // Read the string and place it into the arraylist
        String[] rows = layoutText.split(ROW_SEPERATOR);
        for(String row : rows) {
            String[] columns = row.split(COLUMN_SEPERATOR);
            ArrayList<TileScheme> tileRow = new ArrayList<>();
            for(String column : columns) {
                String[] values = column.split(VALUE_SEPERATOR);

                //Convert string enum to real enum
                TileType type = null;
                try{
                    type = Enum.valueOf(TileType.class, values[0]);
                } catch (Exception e) {
                    throw new StorageLoadingException("The value " + values[0] + " could not be deciphered.");
                }

                //Add tilescheme to LayoutMap
                tileRow.add(new TileScheme(type, values[1]));
            }
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



    public TileScheme[][] getTileLayout() {
        return tileLayout;
    }
}