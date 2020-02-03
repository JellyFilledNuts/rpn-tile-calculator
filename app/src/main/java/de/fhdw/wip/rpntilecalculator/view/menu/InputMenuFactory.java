package de.fhdw.wip.rpntilecalculator.view.menu;

import android.view.View;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import de.fhdw.wip.rpntilecalculator.MainActivity;
import de.fhdw.wip.rpntilecalculator.view.Tile;
import de.fhdw.wip.rpntilecalculator.view.TileMapping;
import de.fhdw.wip.rpntilecalculator.view.schemes.TileScheme;

public class InputMenuFactory {

    private Tile displayTile;
    private DialogMenu last;
    private DialogMenu current;

    public InputMenuFactory(@NotNull Tile displayTile,
                            @NotNull DialogMenu last,
                            @NotNull DialogMenu current) {
        this.displayTile = displayTile;
        this.last = last;
        this.current = current;
    }

    /**
     * Chooses the correct handling
     * @return the correct class that handles the click
     */
    public View.OnClickListener createListener(@NotNull final MainActivity context, @NotNull TileMapping mapping) {
        return createListener(context, mapping, 0);
    }
    public View.OnClickListener createListener(@NotNull final MainActivity context, @NotNull TileMapping mapping, int rank) {
        if(mapping.getType().isOperand()) return createOperandListener(context, mapping);
        else if(mapping.getType().isAction() || mapping.getType().isSetting())
            return createAcSeListener(context, mapping);
        else if(mapping.getType().isStack() || mapping.getType().isHistory())
            return createStackListener(context, mapping, rank);
        else return createNotFoundListener(context);
    }

    /**
     * Creates the listeners for Stack and History-Stack by rank
     */
    private View.OnClickListener createStackListener(MainActivity context, final TileMapping mapping, final int rank) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TileScheme scheme = TileScheme.createTileScheme(mapping, null, rank);
                displayTile.update(scheme);
                displayTile.getTileLayout().removeFromStacks(displayTile);
                displayTile.getTileLayout().addToStacks(displayTile);
                current.dismissAll();
            }
        };
    }

    /**
     * Creates the listeners for Actions and Settings by TileMapping
     */
    private View.OnClickListener createAcSeListener(final MainActivity context, final TileMapping mapping) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TileScheme scheme = TileScheme.createTileScheme(mapping, mapping.getMenuText());
                displayTile.update(scheme);
                displayTile.getTileLayout().removeFromStacks(displayTile);
                current.dismissAll();
            }
        };
    }

    /**
     * Creates listeners for Operands by TileMapping
     */
    private View.OnClickListener createOperandListener(final MainActivity context, TileMapping mapping) {
        switch (mapping) {
            case O_FRACTION:
                return new InputFraction(context, displayTile, last);
            case O_POLYNOM:
                return new InputPolynomial(context, displayTile, last);
            default:
                return createNotFoundListener(context);
        }
    }

    /**
     * Creates a listener when no other is found
     */
    private View.OnClickListener createNotFoundListener(final MainActivity context) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Not implemented yet.", Toast.LENGTH_SHORT).show();
            }
        };
    }
}
