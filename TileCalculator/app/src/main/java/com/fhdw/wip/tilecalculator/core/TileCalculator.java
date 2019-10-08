package com.fhdw.wip.tilecalculator.core;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("WeakerAccess")
public class TileCalculator {
    private static final TileCalculator ourInstance = new TileCalculator();

    public static TileCalculator getInstance() {
        return ourInstance;
    }

    private List<Operand> stack;

    private TileCalculator() {
        stack = new ArrayList<>();
    }

    public List<Operand> getStack() {
        return stack;
    }
}
