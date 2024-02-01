package io.github.mjaroslav.sharedjava.tuple.unit;

import org.jetbrains.annotations.NotNull;

import java.util.function.ToIntFunction;

public class UnitHashCodeDummy implements ToIntFunction<DelegatingUnit<Object>> {
    @Override
    public int applyAsInt(@NotNull DelegatingUnit<Object> unit) {
        unit.setHashCodeFunc(null);
        return unit.hashCode();
    }
}
