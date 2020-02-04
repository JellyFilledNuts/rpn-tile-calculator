package de.fhdw.wip.rpntilecalculator.model.stack;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Summary: Stack interface for stacks in this project
 * Author:  Tim Schwenke
 * Date:    2020/01/04
 */
public interface StackInterface<T> {

    /**
     * Push a single object onto the stack
     * @param value Object
     */
    void push(@NotNull T value);

    /**
     * Push an array of objects onto the stack
     * @param values Array of objects
     */
    void push(@NotNull T[] values);

    /**
     * Pop an object from the stack
     * @return Object
     */
    @Nullable T pop();

    /**
     * Pop a certain amount of objects from the stack
     * @param max Max amount of objects to pop
     * @return List of popped items
     */
    @NotNull List<T> pop(int max);

    /**
     * Pop a element of a certain class from the stack
     * @param type Type of the element to be popped
     * @param <G> Class the type to be popped should extend
     * @return Popped item
     */
    @Nullable <G extends T> G pop(Class<G> type);

    /**
     * Pop a certain amount of elements of a certain type from the stack.
     * @param max Max amount of items to pop
     * @param type Type the items should be
     * @param <G> Class the items should extend
     * @return List of popped items
     */
    @NotNull <G extends T> List<G> pop(int max, Class<G> type);

    /**
     * Peek the first item on the stack
     * @return Item peeked
     */
    @Nullable T peek();

    /**
     * Peek a list of items on the stack.
     * @param max Max amount of items to peek
     * @return A list of items that were peeked
     */
    @NotNull List<T> peek(int max);

    /**
     * Peek the first item of a certain type on the stack.
     * @param type Type the item should be
     * @param <G> Class the item should extend
     * @return Peeked item
     */
    @Nullable <G extends T> G peek(Class<G> type);

    /**
     * Peek a list of items that are all of a certain type on the stack
     * @param max Max amount of items to be peeked from the stack
     * @param type Type the peeked items should be
     * @param <G> Class the peeked items should extend
     * @return List of items that were peeked
     */
    @NotNull <G extends T> List<G> peek(int max, Class<G> type);

    /**
     * Check if a certain object is part of the stack
     * @param object Object to be searched for on the stack
     * @return Boolean
     */
    boolean contains(T object);

    /**
     * Clear the stack
     */
    void clear();

    /**
     * Get the stack as an copy in an array.
     * @return Array
     */
    @NotNull T[] get();

    /**
     * Get all items of a certain type from the stack
     * @param type Type the items should be
     * @param <G> Class items should extend
     * @return List
     */
    @NotNull <G extends T> List<G> get(Class<G> type);

    /**
     * Size of the stack
     * @return number of items on the stack
     */
    int size();

}
