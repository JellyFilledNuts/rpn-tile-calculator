package de.fhdw.wip.rpntilecalculator.view.layout;

import android.content.pm.ActivityInfo;

public enum ScreenOrientation {

    LANDSCAPE(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE, 'h'),
    PORTRAIT(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT, 'v');

    private char indicator;
    private int orientation;
    ScreenOrientation(int orientation, char indicator) {
        this.indicator = indicator;
        this.orientation = orientation;
    }

    public static boolean isOrientation(char indicator) {
        return indicator == LANDSCAPE.getIndicator() || indicator == PORTRAIT.getIndicator();
    }

    public static ScreenOrientation getOrientation(char indicator) {
        if(indicator == PORTRAIT.getIndicator()) return PORTRAIT;
        else return LANDSCAPE;
    }

    public char getIndicator() {
        return indicator;
    }

    public int getOrientation() {
        return orientation;
    }
}
