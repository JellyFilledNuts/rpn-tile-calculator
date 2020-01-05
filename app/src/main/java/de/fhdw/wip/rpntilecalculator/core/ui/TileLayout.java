package de.fhdw.wip.rpntilecalculator.core.ui;

import java.util.ArrayList;

public class TileLayout {

    private ArrayList<TileScheme> tileLayout;
    private int height;
    private int width;

    public TileLayout() {}

    public TileLayout(String tileLayoutTitle) {
        tileLayout = new ArrayList<>();
        for(int i = 0; i <= 48; i++) {
            tileLayout.add(null);
        }
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}