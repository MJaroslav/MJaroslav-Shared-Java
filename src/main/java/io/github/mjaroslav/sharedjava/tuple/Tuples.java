package io.github.mjaroslav.sharedjava.tuple;

import io.github.mjaroslav.sharedjava.tuple.pair.*;
import io.github.mjaroslav.sharedjava.tuple.trio.*;
import io.github.mjaroslav.sharedjava.tuple.unit.NumberUnit;
import io.github.mjaroslav.sharedjava.tuple.unit.SimpleUnit;
import io.github.mjaroslav.sharedjava.tuple.unit.Unit;
import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.Contract;

// TODO: May be Just create number Unit/Pair/Trio for all primitive numbers and bye bye to generics?
@UtilityClass
public class Tuples {
    @Contract(" -> new")
    public <X> Unit<X> unit() {
        return new SimpleUnit<>();
    }

    @Contract("_ -> new")
    public <X> Unit<X> unit(X x) {
        return new SimpleUnit<>(x);
    }

    @Contract(" -> new")
    public <X, Y> Pair<X, Y> pair() {
        return new SimplePair<>();
    }

    @Contract("_, _ -> new")
    public <X, Y> Pair<X, Y> pair(X x, Y y) {
        return new SimplePair<>(x, y);
    }

    @Contract(" -> new")
    public <T> MonoPair<T> monoPair() {
        return new SimpleMonoPair<>();
    }

    @Contract("_, _ -> new")
    public <T> MonoPair<T> monoPair(T x, T y) {
        return new SimpleMonoPair<>(x, y);
    }

    @Contract(" -> new")
    public <X, Y, Z> Trio<X, Y, Z> trio() {
        return new SimpleTrio<>();
    }

    @Contract("_, _, _ -> new")
    public <X, Y, Z> Trio<X, Y, Z> trio(X x, Y y, Z z) {
        return new SimpleTrio<>(x, y, z);
    }

    @Contract(" -> new")
    public <T> MonoTrio<T> monoTrio() {
        return new SimpleMonoTrio<>();
    }

    @Contract("_, _, _ -> new")
    public <T> MonoTrio<T> monoTrio(T x, T y, T z) {
        return new SimpleMonoTrio<>(x, y, z);
    }

    @Contract(" -> new")
    public Unit<Integer> integerUnit() {
        return new NumberUnit<>(0);
    }

    @Contract("_ -> new")
    public Unit<Integer> integerUnit(int x) {
        return new NumberUnit<>(x);
    }

    @Contract(" -> new")
    public Unit<Short> shortUnit() {
        return new NumberUnit<>((short) 0);
    }

    @Contract("_ -> new")
    public Unit<Short> shortUnit(short x) {
        return new NumberUnit<>(x);
    }

    @Contract(" -> new")
    public Unit<Long> longUnit() {
        return new NumberUnit<>(0L);
    }

    @Contract("_ -> new")
    public Unit<Long> longUnit(long x) {
        return new NumberUnit<>(x);
    }

    @Contract(" -> new")
    public Unit<Float> floatUnit() {
        return new NumberUnit<>(0F);
    }

    @Contract("_ -> new")
    public Unit<Float> floatUnit(float x) {
        return new NumberUnit<>(x);
    }

    @Contract(" -> new")
    public Unit<Double> doubleUnit() {
        return new NumberUnit<>(0D);
    }

    @Contract("_ -> new")
    public Unit<Double> doubleUnit(double x) {
        return new NumberUnit<>(x);
    }

    @Contract(" -> new")
    public MonoPair<Integer> integerPair() {
        return new MonoNumberPair<>(0, 0);
    }

    @Contract("_, _ -> new")
    public MonoPair<Integer> integerPair(int x, int y) {
        return new MonoNumberPair<>(x, y);
    }

    @Contract(" -> new")
    public MonoPair<Short> shortPair() {
        return new MonoNumberPair<>((short) 0, (short) 0);
    }

    @Contract("_, _ -> new")
    public MonoPair<Short> shortPair(short x, short y) {
        return new MonoNumberPair<>(x, y);
    }

    @Contract(" -> new")
    public MonoPair<Long> longPair() {
        return new MonoNumberPair<>(0L, 0L);
    }

    @Contract("_, _ -> new")
    public MonoPair<Long> longPair(long x, long y) {
        return new MonoNumberPair<>(x, y);
    }

    @Contract(" -> new")
    public MonoPair<Float> floatPair() {
        return new MonoNumberPair<>(0F, 0F);
    }

    @Contract("_, _ -> new")
    public MonoPair<Float> floatPair(float x, float y) {
        return new MonoNumberPair<>(x, y);
    }

    @Contract(" -> new")
    public MonoPair<Double> doublePair() {
        return new MonoNumberPair<>(0D, 0D);
    }

    @Contract("_, _ -> new")
    public MonoPair<Double> doublePair(double x, double y) {
        return new MonoNumberPair<>(x, y);
    }

    @Contract(" -> new")
    public MonoTrio<Integer> integerTrio() {
        return new MonoNumberTrio<>(0, 0, 0);
    }

    @Contract("_, _, _ -> new")
    public MonoTrio<Integer> integerTrio(int x, int y, int z) {
        return new MonoNumberTrio<>(x, y, z);
    }

    @Contract(" -> new")
    public MonoTrio<Short> shortTrio() {
        return new MonoNumberTrio<>((short) 0, (short) 0, (short) 0);
    }

    @Contract("_, _, _ -> new")
    public MonoTrio<Short> shortTrio(short x, short y, short z) {
        return new MonoNumberTrio<>(x, y, z);
    }

    @Contract(" -> new")
    public MonoTrio<Long> longPTrio() {
        return new MonoNumberTrio<>(0L, 0L, 0L);
    }

    @Contract("_, _, _ -> new")
    public MonoTrio<Long> longTrio(long x, long y, long z) {
        return new MonoNumberTrio<>(x, y, z);
    }

    @Contract(" -> new")
    public MonoTrio<Float> floatTrio() {
        return new MonoNumberTrio<>(0F, 0F, 0F);
    }

    @Contract("_, _, _ -> new")
    public MonoTrio<Float> floatTrio(float x, float y, float z) {
        return new MonoNumberTrio<>(x, y, z);
    }

    @Contract(" -> new")
    public MonoTrio<Double> doubleTrio() {
        return new MonoNumberTrio<>(0D, 0D, 0D);
    }

    @Contract("_, _, _ -> new")
    public MonoTrio<Double> doubleTrio(double x, double y, double z) {
        return new MonoNumberTrio<>(x, y, z);
    }
}
