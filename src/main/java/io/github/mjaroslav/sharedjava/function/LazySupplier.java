package io.github.mjaroslav.sharedjava.function;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * Wrapper for lazy values. Can be null.
 *
 * @param <T> type of lazy value for get method
 * @author MJaroslav
 * @see Supplier
 * @since 0.1.0
 */
@RequiredArgsConstructor
public class LazySupplier<T> implements Supplier<T> {
    /**
     * Functional interface for value initialize.
     *
     * @since 0.1.0
     */
    protected final @NotNull Supplier<T> initializer;
    /**
     * True if value already initialized.
     *
     * @since 0.1.0
     */
    protected @Getter boolean initialized;
    /**
     * Holder for value.
     *
     * @since 0.1.0
     */
    protected @Nullable T value;

    /**
     * Initialize value by initializer function. This method checks for multiple tries of initialize.
     *
     * @since 0.1.0
     */
    public void initialize() {
        if (!isInitialized()) {
            initialized = true;
            value = initializer.get();
        }
    }

    /**
     * Initialize value and return it.
     *
     * @return nullable initialized value
     * @since 0.1.0
     */
    public @Nullable T get() {
        initialize();
        return value;
    }

    /**
     * Initialize value and check for is not null.
     *
     * @return true if initialized value is not null
     * @since 0.1.0
     */
    public boolean isPresent() {
        initialize();
        return value != null;
    }

    /**
     * Initialize value and check for is null.
     *
     * @return true if initialized value is null
     * @since 0.1.0
     */
    public boolean isEmpty() {
        initialize();
        return value == null;
    }

    /**
     * Initialize value and run action if it is not null.
     *
     * @param action action for executing
     * @since 0.1.0
     */
    public void ifPresent(@NotNull Consumer<T> action) {
        initialize();
        if (value != null) action.accept(value);
    }

    /**
     * Initialize value and run action if it is not null or else execute another.
     *
     * @param action      action that will be run if initialized value is not null
     * @param emptyAction action that will be run if initialized value is null
     * @since 0.1.0
     */
    public void ifPresentOrElse(@NotNull Consumer<T> action, @NotNull Runnable emptyAction) {
        initialize();
        if (value != null) action.accept(value);
        else emptyAction.run();
    }

    /**
     * Initialize value and return if it is not null or other else.
     *
     * @param other value that will be return if initialized is null
     * @return initialized non-null or other value
     * @since 0.1.0
     */
    public @NotNull T orElse(@NotNull T other) {
        initialize();
        return value == null ? other : value;
    }

    /**
     * Initialize value and return if it is not null or get other else.
     *
     * @param supplier getter for value that will be used if initialized is null
     * @return initialized non-null or getter other value
     * @since 0.1.0
     */
    public T orElseGet(@NotNull Supplier<T> supplier) {
        initialize();
        return value == null ? supplier.get() : value;
    }

    /**
     * Initialize and return non null-value or fail else.
     *
     * @return initialized value
     * @throws NoSuchElementException if initialized value is null
     * @since 0.1.0
     */
    public @NotNull T orElseThrow() {
        initialize();
        if (value == null) throw new NoSuchElementException("No value present");
        return value;
    }

    /**
     * Initialize and return non-null or throw specified throwable.
     *
     * @param exceptionSupplier getter for throwable that will be thrown if initialized value is null
     * @param <X>               type of throwable
     * @return initialized value
     * @throws X if initialized value is null
     * @since 0.1.0
     */
    public <X extends Throwable> @NotNull T orElseThrow(@NotNull Supplier<? extends X> exceptionSupplier) throws X {
        initialize();
        if (value != null) return value;
        else throw exceptionSupplier.get();
    }

    /**
     * Initialize value and create stream from it.
     *
     * @return stream with initialized value or empty if it is null
     * @since 0.1.0
     */
    public @NotNull Stream<T> stream() {
        initialize();
        return value == null ? Stream.empty() : Stream.of(value);
    }

    @Override
    public String toString() {
        initialize();
        return value != null ? String.format("LazySupplier[%s]", value) : "LazySupplier.empty";
    }
}
