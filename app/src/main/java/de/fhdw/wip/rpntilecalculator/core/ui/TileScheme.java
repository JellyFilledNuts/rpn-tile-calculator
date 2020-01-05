package de.fhdw.wip.rpntilecalculator.core.ui;

/*
 * Summary: TileScheme contains the information about tiles and is used to create tiles and save them
 * Author:  Tom Bockhorn
 */


import org.jetbrains.annotations.NotNull;

public class TileScheme {

    @NotNull private TileType type;
    @NotNull private String content;

    TileScheme(@NotNull TileType type, @NotNull String content) {
        this.type = type;
        this.content = content;
    }

    @NotNull
    public TileType getType() {
        return type;
    }

    public void setType(@NotNull TileType type) {
        this.type = type;
    }

    @NotNull
    public String getContent() {
        return content;
    }

    public void setContent(@NotNull String content) {
        this.content = content;
    }
}
