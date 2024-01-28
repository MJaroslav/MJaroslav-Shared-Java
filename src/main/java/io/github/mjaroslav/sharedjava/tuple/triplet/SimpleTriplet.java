package io.github.mjaroslav.sharedjava.tuple.triplet;

import io.github.mjaroslav.sharedjava.tuple.Triplet;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

@NoArgsConstructor
public @Data class SimpleTriplet<X, Y, Z> implements Triplet<X, Y, Z> {
    protected X x;
    protected Y y;
    protected Z z;

    public SimpleTriplet(X x, Y y, Z z) {
        set(x, y, z);
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        return this == obj || obj instanceof Triplet<?, ?, ?> triplet && Objects.equals(getX(), triplet.getX()) &&
            Objects.equals(getY(), triplet.getY()) && Objects.equals(getZ(), triplet.getZ());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY(), getZ());
    }
}
