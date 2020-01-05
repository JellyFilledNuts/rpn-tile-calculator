package de.fhdw.wip.rpntilecalculator.core.ui;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import androidx.appcompat.widget.AppCompatButton;

import de.fhdw.wip.rpntilecalculator.MainActivity;

/*
 * Summary: Tile acts as button and forwards the connected type and action to the handler
 * Author:  Tom Bockhorn
 */

public class Tile extends AppCompatButton {

    private TileType type;
    private MainActivity context;

    public Tile(Context context, TileType type, String text) {
        super(context);
        this.context = (MainActivity) context;
        this.type = type;
        this.setText(text);

        setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                give();
            }
        });
    }

    public TileType getType() {
        return type;
    }

    public void setType(TileType type) {
        this.type = type;
    }

    private void give() {
        this.context.execute(getText().toString(), type);
    }
}