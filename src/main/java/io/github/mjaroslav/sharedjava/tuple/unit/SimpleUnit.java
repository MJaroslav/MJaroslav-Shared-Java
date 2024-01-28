package io.github.mjaroslav.sharedjava.tuple.unit;

import io.github.mjaroslav.sharedjava.tuple.Unit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
public @Data class SimpleUnit<X> implements Unit<X> {
    protected X x;


    @Override
    public boolean equals(@Nullable Object obj) {
        return this == obj || obj instanceof Unit<?> unit && Objects.equals(getX(), unit.getX());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getX());
    }
}
