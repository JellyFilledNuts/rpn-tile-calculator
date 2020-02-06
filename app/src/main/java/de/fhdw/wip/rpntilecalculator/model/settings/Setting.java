package de.fhdw.wip.rpntilecalculator.model.settings;

import org.jetbrains.annotations.Contract;

/**
 * Summary: Super class for settings
 * Author:  Tim Jonas Meinerzhagen
 * Date:    2020/01/26
 */
public abstract class Setting {

    @Contract(pure = true)
    public abstract boolean call();
}
