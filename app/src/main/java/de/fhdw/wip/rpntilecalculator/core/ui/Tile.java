package de.fhdw.wip.rpntilecalculator.core.ui;

import android.content.Context;
import android.view.View;

import androidx.appcompat.widget.AppCompatButton;

import de.fhdw.wip.rpntilecalculator.MainActivity;
import de.fhdw.wip.rpntilecalculator.core.calculation.Action;

/*
 * Summary: Tile acts as button and forwards the connected type and action to the handler
 * Author:  Tom Bockhorn
 */

public class Tile extends AppCompatButton {

    private TileType type;
    private MainActivity context;

    // Depending on the TileType, one of the following is filled
    private Action action;
    private Operand operand;

    public Tile(Context context, TileType type, String text, Operand operand) {
        this(context, type, text, null, operand);
    }

    public Tile(Context context, TileType type, String text, Action action) {
        this(context, type, text, action, null);
    }

    public Tile(Context context, TileType type, String text, Action action, Operand operand) {
        super(context);
        this.context = (MainActivity) context;
        this.type = type;
        this.setText(text);
        this.action = action;
        this.operand = operand;

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