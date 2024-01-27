package io.github.mjaroslav.sharedjava.tuple.trio;

import io.github.mjaroslav.sharedjava.tuple.pair.SimplePair;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class SimpleTrio<X, Y, Z> extends SimplePair<X, Y> implements Trio<X, Y, Z> {
    protected Z z;

    public SimpleTrio(X x, Y y, Z z) {
        super(x, y);
        setZ(z);
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        return obj instanceof Trio<?, ?, ?> trio ?
            Objects.equals(getX(), trio.getX()) && Objects.equals(getY(), trio.getY()) &&
                Objects.equals(getZ(), trio.getZ()) : super.equals(obj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY(), getZ());
    }
}
