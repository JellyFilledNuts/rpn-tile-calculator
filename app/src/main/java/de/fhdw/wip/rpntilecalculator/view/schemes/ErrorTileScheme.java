package de.fhdw.wip.rpntilecalculator.view.schemes;

import org.jetbrains.annotations.NotNull;

import de.fhdw.wip.rpntilecalculator.view.TileMapping;

/**
 * Summary: TileScheme for wrongly loaded Tiles
 * Author:  Dennis Gentges
 * Date:    2020/01/06
 */
public class ErrorTileScheme extends TileScheme {

    ErrorTileScheme(@NotNull TileMapping tileType, @NotNull String content) {
        super(tileType, content);
    }

}
