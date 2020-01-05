package de.fhdw.wip.rpntilecalculator.core.ui;

import java.util.ArrayList;

public class TileLayout {

    //private ArrayList<TileScheme> tileLayout;
    private TileScheme[][] tileLayout;
    //private int height;
    //private int width;

    public TileLayout() {}

    public TileLayout(String title) {
        tileLayout = new TileScheme[6][8];

        // DEFAULT Layout
        if(title.equals("DEFAULT")) {
            for(int i = 0; i < tileLayout.length; i++) {
                for(int j = 0; j < tileLayout[i].length; j++) {
                    tileLayout[i][j] = new TileScheme(TileType.O_MINUS, TileType.O_MINUS.getText());
                }
            }
        }
        if(title.equals("TEST")) {
            tileLayout = new TileScheme[2][3];

            tileLayout[0][0] = new TileScheme(TileType.OPERATOR, "1");
            tileLayout[0][1] = new TileScheme(TileType.OPERATOR, "2");
            tileLayout[0][2] = new TileScheme(TileType.OPERATOR, "3");
            tileLayout[1][0] = new TileScheme(TileType.OPERATOR, "4");
            tileLayout[1][1] = new TileScheme(TileType.O_MINUS, TileType.O_MINUS.getText());
            tileLayout[1][2] = new TileScheme(TileType.O_PLUS, TileType.O_PLUS.getText());
        }
    }

    public TileScheme[][] getTileLayout() {
        return tileLayout;
    }
}