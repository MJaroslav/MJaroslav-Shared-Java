package io.github.mjaroslav.sharedjava.tuple.triplet;

import org.jetbrains.annotations.NotNull;

import java.util.function.ToIntFunction;

public class TripletHashCodeDummy implements ToIntFunction<DelegatingTriplet<Object, Object, Object>> {
    @Override
    public int applyAsInt(@NotNull DelegatingTriplet<Object, Object, Object> triplet) {
        triplet.setHashCodeFunc(null);
        return triplet.hashCode();
    }
}
