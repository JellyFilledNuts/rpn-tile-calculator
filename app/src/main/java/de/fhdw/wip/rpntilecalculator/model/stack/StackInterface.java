package de.fhdw.wip.rpntilecalculator.model.stack;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@SuppressWarnings("unused")
public interface StackInterface<T> {

    void push(@NotNull T value);
    void push(@NotNull T[] values);

    @Nullable T pop();
    @NotNull List<T> pop(int max);
    @Nullable <G extends T> G pop(Class<G> type);
    @NotNull <G extends T> List<G> pop(int max, Class<G> type);

    @Nullable T peek();
    @NotNull List<T> peek(int max);
    @Nullable <G extends T> G peek(Class<G> type);
    @NotNull <G extends T> List<G> peek(int max, Class<G> type);

    boolean contains(T object);

    void clear();

    @NotNull T[] get();

    @NotNull <G extends T> List<G> get(Class<G> type);

    int size();

}
