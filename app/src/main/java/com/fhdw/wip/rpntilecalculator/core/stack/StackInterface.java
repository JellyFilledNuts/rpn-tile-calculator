package com.fhdw.wip.rpntilecalculator.core.stack;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("unused")
public interface StackInterface<T> {

    void push(@NotNull T value);
    void push(@NotNull T[] values);

    @NotNull Optional<T> pop();
    @NotNull List<T> pop(int max);
    @NotNull <G extends T> Optional<G> pop(Class<G> type);
    @NotNull <G extends T> List<G> pop(int max, Class<G> type);

    @NotNull Optional<T> peek();
    @NotNull List<T> peek(int max);
    @NotNull <G extends T> Optional<G> peek(Class<G> type);
    @NotNull <G extends T> List<G> peek(int max, Class<G> type);

    boolean contains(T object);

    void clear();

    @NotNull T[] get();

    @NotNull <G extends T> List<G> get(Class<G> type);

    int size();

}
