package io.github.mjaroslav.sharedjava.util;

import io.github.mjaroslav.sharedjava.tuple.Unit;
import io.github.mjaroslav.sharedjava.tuple.unit.DelegatingUnit;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

/**
 * Special realization of {@link Map} that allows delegate equals, hashCode and toString.
 *
 * @param <K> type of Map keys
 * @param <V> type of Map values
 * @author MJaroslav
 * @see DelegatingUnit
 * @see DelegatingSet
 * @since 1.0.0
 */
@RequiredArgsConstructor
@Getter
public class DelegatingMap<K, V> extends AbstractMap<K, V> {
    /**
     * Delegating function for equals, required.
     *
     * @since 1.0.0
     */
    protected final @NotNull BiPredicate<Unit<K>, Unit<?>> equalsFunc;
    /**
     * Delegating function for hashCode, required.
     *
     * @since 1.0.0
     */
    protected final @NotNull ToIntFunction<Unit<K>> hashCodeFunc;
    /**
     * Delegating function for toString; Will use default behavior if null.
     *
     * @since 1.0.0
     */
    protected final @Nullable Function<Unit<K>, String> toStringFunc;
    @Getter(AccessLevel.NONE)
    protected final @NotNull HashMap<DelegatingUnit<K>, V> impl = new HashMap<>();

    @Override
    public int size() {
        return impl.size();
    }

    @Override
    public boolean isEmpty() {
        return impl.isEmpty();
    }

    @Override
    public boolean containsKey(@Nullable Object key) {
        return get(key) != null;
    }

    @Override
    public boolean containsValue(@Nullable Object value) {
        return impl.containsValue(value);
    }

    @SuppressWarnings("unchecked")
    @Override
    public @Nullable V remove(@NotNull Object key) {
        return impl.remove(wrap((K) key));
    }

    @Override
    public void clear() {
        impl.clear();
    }

    @SuppressWarnings("unchecked")
    @Override
    public @Nullable V get(@Nullable Object key) {
        return key != null ? impl.get(wrap((K) key)) : null;
    }

    @NotNull
    @Override
    public Collection<V> values() {
        return impl.values();
    }

    @Override
    public @Nullable V put(@NotNull K key, @Nullable V value) {
        return impl.put(wrap(key), value);
    }

    @Override
    public void putAll(@NotNull Map<? extends K, ? extends V> m) {
        m.forEach((key, value) -> impl.put(wrap(key), value));
    }

    @NotNull
    @Override
    public DelegatingSet<K> keySet() {
        val result = new DelegatingSet<>(getEqualsFunc(), getHashCodeFunc(), getToStringFunc());
        impl.keySet().stream().map(DelegatingUnit::getX).forEach(result::add);
        return result;
    }

    @NotNull
    @Override
    public Set<Entry<K, V>> entrySet() {
        return impl.entrySet().stream().map(entry -> new SimpleEntry<>(entry.getKey().getX(),
            entry.getValue())).collect(Collectors.toSet());
    }

    @Override
    public String toString() {
        return "DelegatingMap[" + impl.entrySet().stream().map(entry -> entry.getKey() + "=" + entry.getValue())
            .collect(Collectors.joining(", ")) + "]";
    }

    @Contract("_ -> new")
    protected @NotNull DelegatingUnit<K> wrap(@NotNull K value) {
        return new DelegatingUnit<>(value).setHashCodeFunc(getHashCodeFunc())
            .setEqualsFunc((unit, obj) -> obj instanceof Unit<?> objUnit && getEqualsFunc().test(unit, objUnit))
            .setToStringFunc(getToStringFunc());
    }
}
