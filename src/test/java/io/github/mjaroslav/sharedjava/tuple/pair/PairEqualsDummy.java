package io.github.mjaroslav.sharedjava.tuple.pair;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.BiPredicate;

public class PairEqualsDummy implements BiPredicate<DelegatingPair<Object, Object>, Object> {
    @Override
    public boolean test(@NotNull DelegatingPair<Object, Object> pair, @Nullable Object obj) {
        pair.setEqualsFunc(null);
        return pair.equals(obj);
    }
}
