package io.github.mjaroslav.sharedjava.tuple.unit;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.BiPredicate;

public class UnitEqualsDummy implements BiPredicate<DelegatingUnit<Object>, Object> {
    @Override
    public boolean test(@NotNull DelegatingUnit<Object> unit, @Nullable Object obj) {
        unit.setEqualsFunc(null);
        return unit.equals(obj);
    }
}
