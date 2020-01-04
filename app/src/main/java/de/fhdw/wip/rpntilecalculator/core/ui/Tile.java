package de.fhdw.wip.rpntilecalculator.core.ui;

import android.content.Context;

import androidx.appcompat.widget.AppCompatButton;

public class Tile extends AppCompatButton {

    private TileType type;

    public Tile(Context context) {
        super(context);
    }

    public void setText(String text) {
        this.setText(text);
    }

    public TileType getType() {
        return type;
    }

    public void setType(TileType type) {
        this.type = type;
    }
}