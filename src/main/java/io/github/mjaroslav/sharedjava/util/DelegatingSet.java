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

import java.util.AbstractSet;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Getter
public class DelegatingSet<T> extends AbstractSet<T> {
    protected final @NotNull BiPredicate<Unit<T>, Unit<?>> equalsFunc;
    protected final @NotNull ToIntFunction<Unit<T>> hashCodeFunc;
    protected final @Nullable Function<Unit<T>, String> toStringFunc;
    @Getter(AccessLevel.NONE)
    protected final @NotNull HashSet<DelegatingUnit<T>> impl = new HashSet<>();

    @Override
    public int size() {
        return impl.size();
    }

    @Override
    public boolean isEmpty() {
        return impl.isEmpty();
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean contains(@Nullable Object o) {
        return o != null && impl.contains(wrap((T) o));
    }

    @Override
    public @NotNull Iterator<T> iterator() {
        return impl.stream().map(DelegatingUnit::getX).iterator();
    }

    @Override
    public @Nullable Object @NotNull [] toArray() {
        return impl.stream().map(DelegatingUnit::getX).toArray();
    }

    @Override
    public <T1> @Nullable T1 @NotNull [] toArray(@Nullable T1 @NotNull [] a) {
        return impl.stream().map(DelegatingUnit::getX).collect(Collectors.toSet()).toArray(a);
    }

    @Override
    public boolean add(@NotNull T t) {
        return impl.add(wrap(t));
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean remove(@Nullable Object o) {
        return o != null && impl.remove(wrap((T) o));
    }

    @Override
    public boolean removeAll(@NotNull Collection<?> c) {
        val size = size();
        c.forEach(this::remove);
        return size() != size;
    }

    @Override
    public void clear() {
        impl.clear();
    }

    @Override
    public boolean containsAll(@NotNull Collection<?> c) {
        return c.stream().map(this::contains).filter(Boolean::valueOf).count() == c.size();
    }

    @Override
    public boolean addAll(@NotNull Collection<? extends T> c) {
        return impl.addAll(c.stream().map(this::wrap).collect(Collectors.toSet()));
    }

    @Override
    public String toString() {
        return "DelegatingSet[" + impl.stream().map(DelegatingUnit::toString).collect(Collectors.joining(", ")) + "]";
    }

    @Contract("_ -> new")
    protected @NotNull DelegatingUnit<T> wrap(@NotNull T value) {
        return new DelegatingUnit<>(value).setHashCodeFunc(getHashCodeFunc())
            .setEqualsFunc((unit, obj) -> obj instanceof Unit<?> objUnit && getEqualsFunc().test(unit, objUnit))
            .setToStringFunc(getToStringFunc());
    }
}
