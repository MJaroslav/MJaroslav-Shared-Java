package io.github.mjaroslav.sharedjava.tuple.pair;

import org.jetbrains.annotations.NotNull;

import java.util.function.ToIntFunction;

public class PairHashCodeDummy implements ToIntFunction<DelegatingPair<Object, Object>> {
    @Override
    public int applyAsInt(@NotNull DelegatingPair<Object, Object> pair) {
        pair.setHashCodeFunc(null);
        return pair.hashCode();
    }
}
