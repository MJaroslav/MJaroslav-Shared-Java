package io.github.mjaroslav.sharedjava.tuple.triplet;

import io.github.mjaroslav.sharedjava.tuple.Triplet;
import io.github.mjaroslav.sharedjava.tuple.pair.SimplePair;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class SimpleTriplet<X, Y, Z> extends SimplePair<X, Y> implements Triplet<X, Y, Z> {
    protected Z z;

    public SimpleTriplet(X x, Y y, Z z) {
        super(x, y);
        setZ(z);
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        return obj instanceof Triplet<?, ?, ?> triplet ?
            Objects.equals(getX(), triplet.getX()) && Objects.equals(getY(), triplet.getY()) &&
                Objects.equals(getZ(), triplet.getZ()) : super.equals(obj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY(), getZ());
    }
}
