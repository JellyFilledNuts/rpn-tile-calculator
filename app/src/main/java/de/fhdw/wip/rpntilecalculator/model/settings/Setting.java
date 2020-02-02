package de.fhdw.wip.rpntilecalculator.model.settings;

import org.jetbrains.annotations.Contract;

/**
 * Summary: Framework for defining settings. Each setting works in its own way, but can be addressed similarly
 */
public abstract class Setting {

    @Contract(pure = true)
    public abstract boolean call();
}
