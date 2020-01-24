package de.fhdw.wip.rpntilecalculator.view.layout;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import de.fhdw.wip.rpntilecalculator.model.calculation.Action;
import de.fhdw.wip.rpntilecalculator.view.TileMapping;

public class ActionTileScheme extends TileScheme {

    @NotNull private Action action;

    /**
     * Creates a TileScheme for an Action
     * @param tileType exact type of the scheme
     * @param content display text (default is derived by type)
     */
    ActionTileScheme(@NotNull TileMapping tileType, @Nullable String content) {
        super(tileType, content);
        this.action = tileType.getActionType();
        System.out.println("Created ActionScheme: <Action " + action.toString() + ">");
    }

    public Action getAction() {
        return action;
    }
}
