package de.fhdw.wip.rpntilecalculator.view.layout.schemes;

import org.jetbrains.annotations.NotNull;

import de.fhdw.wip.rpntilecalculator.model.settings.Setting;
import de.fhdw.wip.rpntilecalculator.view.TileMapping;

public class SettingTileScheme extends TileScheme {

    @NotNull private Setting setting;

    /**
     * Crates an SettingTileScheme connected to a setting in it
     * @param content rank of the stack field
     */
    SettingTileScheme(@NotNull TileMapping tileType, @NotNull String content) {
        super(tileType, tileType.getSettingText());
        this.setting = tileType.getSettingType();
    }

    public Setting getSetting() {
        return setting;
    }
}
