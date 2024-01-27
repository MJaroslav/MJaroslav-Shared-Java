package io.github.mjaroslav.sharedjava.tuple.unit;

import io.github.mjaroslav.sharedjava.tuple.Unit;
import lombok.*;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SimpleUnit<X> implements Unit<X> {
    protected X x;

    @Override
    public boolean equals(@Nullable Object obj) {
        val unpacked = obj instanceof Unit<?> unit ? unit.getX() : obj;
        return Objects.equals(getX(), unpacked);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getX());
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "(" + valueToString() + ")";
    }
}
