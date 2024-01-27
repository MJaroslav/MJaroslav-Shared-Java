package io.github.mjaroslav.sharedjava.tuple.pair;

import io.github.mjaroslav.sharedjava.tuple.Pair;
import io.github.mjaroslav.sharedjava.tuple.unit.SimpleUnit;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class SimplePair<X, Y> extends SimpleUnit<X> implements Pair<X, Y> {
    protected Y y;

    public SimplePair(X x, Y y) {
        super(x);
        this.y = y;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        return obj instanceof Pair<?, ?> pair ?
            Objects.equals(getX(), pair.getX()) && Objects.equals(getY(), pair.getY()) : super.equals(obj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY());
    }
}
