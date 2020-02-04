package de.fhdw.wip.rpntilecalculator.view.schemes;

import org.jetbrains.annotations.NotNull;

import de.fhdw.wip.rpntilecalculator.model.settings.Setting;
import de.fhdw.wip.rpntilecalculator.view.TileMapping;

/**
 * Summary: TileScheme for a setting tile
 * Author:  Dennis Gentges
 * Date:    2020/01/08
 */
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

    @NotNull
    public Setting getSetting() {
        return setting;
    }
}
