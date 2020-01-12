package de.fhdw.wip.rpntilecalculator.core.ui;

import android.content.Context;
import android.view.View;

import androidx.appcompat.widget.AppCompatButton;

import org.jetbrains.annotations.NotNull;

import de.fhdw.wip.rpntilecalculator.MainActivity;
import de.fhdw.wip.rpntilecalculator.core.calculation.Action;
import de.fhdw.wip.rpntilecalculator.core.model.operand.Operand;
import de.fhdw.wip.rpntilecalculator.core.ui.layout.TileScheme;

/*
 * Summary: Tile acts as button and forwards the connected type and action to the handler
 * Author:  Tom Bockhorn
 */

public class Tile extends AppCompatButton {

    private MainActivity context;
    private TileScheme scheme;

    public Tile(@NotNull Context context, @NotNull TileScheme scheme) {
        super(context);
        this.context = (MainActivity) context;
        this.scheme = scheme;
        this.setText(scheme.getContent());

        setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                give();
            }
        });
    }

    private void give() {
        this.context.execute(getText().toString(), scheme);
    }

    public TileScheme getScheme() {
        return scheme;
    }
}