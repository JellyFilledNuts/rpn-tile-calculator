package de.fhdw.wip.rpntilecalculator.core.ui;

import java.util.ArrayList;

public class TileLayout {

    private ArrayList<TileScheme> tileLayout;

    public TileLayout() {}

    public TileLayout(String tileLayoutTitle) {
        tileLayout = new ArrayList<>();
        for(int i = 0; i <= 48; i++) {
            tileLayout.add(null);
        }
    }

}