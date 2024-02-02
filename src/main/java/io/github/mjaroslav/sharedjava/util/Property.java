package io.github.mjaroslav.sharedjava.util;


import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;

/**
 * Value holder that allows listen for value changing and set conventions for null value.
 *
 * @param <T> type of Property
 * @author MJaroslav
 * @since 1.0.0
 */
@SuppressWarnings("UnusedReturnValue") // Builder-like calling of methods
@NoArgsConstructor
@EqualsAndHashCode(of = {"value", "convention", "conventionProperty"})
@ToString(of = {"value", "convention", "conventionProperty"})
public class Property<T> {
    protected final Set<Consumer<@NotNull T>> listeners = new HashSet<>();
    protected final Consumer<Runnable> SHARED = runnable -> {
        val prevValue = get();
        runnable.run();
        val nextValue = get();
        if (!Objects.equals(prevValue, nextValue)) listeners.forEach(listener -> listener.accept(nextValue));
    };

    protected T value;
    protected T convention;
    protected Property<T> conventionProperty;

    public Property(T value) {
        this.value = value;
    }

    /**
     * Gets value with conventions.
     *
     * @return property convention value, own value if property convention is null and convention value otherwise
     * @since 1.0.0
     */
    public T get() {
        return conventionProperty != null ? conventionProperty.get() : value != null ? value : convention;
    }

    /**
     * Sets property value, if property convention not null, set value for it instead
     *
     * @param newValue next value of Property
     * @return this Property
     * @since 1.0.0
     */
    public @NotNull Property<T> set(@NotNull T newValue) {
        SHARED.accept(() -> value = newValue);
        return this;
    }

    /**
     * Sets convention for Property. If value and convention property null then get function will return this value.
     *
     * @param convention Property convention value
     * @return this Property
     * @since 1.0.0
     */
    public @NotNull Property<T> setConvention(@Nullable T convention) {
        SHARED.accept(() -> this.convention = convention);
        return this;
    }

    /**
     * Sets convention property for this Property. get function will return value of convention property.
     *
     * @param conventionProperty Property convention property; use null for use convention or property own value
     * @return this Property
     * @since 1.0.0
     */
    public @NotNull Property<T> setConventionProperty(@Nullable Property<T> conventionProperty) {
        SHARED.accept(() -> {
            if (this.conventionProperty != null) listeners.forEach(this.conventionProperty::unregisterListener);
            this.conventionProperty = conventionProperty;
            if (conventionProperty != null) listeners.forEach(this.conventionProperty::registerListener);
        });
        return this;
    }

    /**
     * Registers new changing value listener for this Property (and for convention property too if presents).
     *
     * @param listener Consumer with new value as parameter
     * @return this Property
     * @since 1.0.0
     */
    public @NotNull Property<T> registerListener(@NotNull Consumer<T> listener) {
        listeners.add(listener);
        if (conventionProperty != null) conventionProperty.registerListener(listener);
        return this;
    }

    /**
     * Unregisters already existing changing value listener for this Property (and for convention property too if presents).
     *
     * @param listener already registered in this Property Consumer
     * @return this Property
     * @since 1.0.0
     */
    public @NotNull Property<T> unregisterListener(@NotNull Consumer<T> listener) {
        listeners.remove(listener);
        if (conventionProperty != null) conventionProperty.unregisterListener(listener);
        return this;
    }
}
