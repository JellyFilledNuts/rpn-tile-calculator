package de.fhdw.wip.rpntilecalculator.core.ui;

public class TileScheme {

    private TileType type;
    private String content;

    public TileScheme(TileType type, String content) {
        this.type = type;
        this.content = content;
    }

    public TileType getType() {
        return type;
    }

    public void setType(TileType type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
