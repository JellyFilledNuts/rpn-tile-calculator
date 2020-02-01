package de.fhdw.wip.rpntilecalculator.view.menu.utils;

import android.app.Dialog;
import android.view.View;
import android.widget.Toast;

import de.fhdw.wip.rpntilecalculator.MainActivity;
import de.fhdw.wip.rpntilecalculator.view.Tile;
import de.fhdw.wip.rpntilecalculator.view.TileMapping;
import de.fhdw.wip.rpntilecalculator.view.menu.InputFraction;

public class InputMenuFactory {

    /**
     * Chooses the correct handling
     * @param mapping mapping who's listener should be read
     * @return the correct class that handles the click
     */
    public static View.OnClickListener createListener(MainActivity context, TileMapping mapping, Tile displayTile, Dialog last) {
        if(mapping.getType().isOperand()) return createMenuListener(context, mapping, displayTile, last);
        else return createNotFoundListener(context);
    }

    /**
     * Creates listeners for Operands by TileMapping
     */
    private static View.OnClickListener createMenuListener(final MainActivity context, TileMapping mapping, Tile displayTile, Dialog last) {
        switch (mapping) {
            case O_FRACTION:
                return new InputFraction(context, displayTile, last);
            default:
                return createNotFoundListener(context);
        }
    }

    /**
     * Creates a listener when no other is found
     */
    private static View.OnClickListener createNotFoundListener(final MainActivity context) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Not implemented yet.", Toast.LENGTH_SHORT).show();
            }
        };
    }
}
