package io.github.mjaroslav.sharedjava.tuple.pair;

import io.github.mjaroslav.sharedjava.tuple.Pair;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

@NoArgsConstructor
public @Data class SimplePair<X, Y> implements Pair<X, Y> {
    protected X x;
    protected Y y;

    public SimplePair(X x, Y y) {
        set(x, y);
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        return this == obj || obj instanceof Pair<?, ?> pair &&
            Objects.equals(getX(), pair.getX()) && Objects.equals(getY(), pair.getY());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY());
    }
}
