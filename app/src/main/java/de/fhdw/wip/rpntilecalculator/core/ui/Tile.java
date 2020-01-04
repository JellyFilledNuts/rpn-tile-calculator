package de.fhdw.wip.rpntilecalculator.core.ui;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import androidx.appcompat.widget.AppCompatButton;

import de.fhdw.wip.rpntilecalculator.MainActivity;

public class Tile extends AppCompatButton {

    private TileType type;
    private MainActivity context;

    public Tile(Context context) {
        super(context);
        this.context = (MainActivity) context;

        setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                give();
            }
        });
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

    private void give() {
        this.context.give(getText().toString(), type);
    }
}