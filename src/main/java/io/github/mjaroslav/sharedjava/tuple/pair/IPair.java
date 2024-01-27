package io.github.mjaroslav.sharedjava.tuple.pair;

import lombok.val;

public class IPair extends SimplePair<Integer, Integer> {
    public IPair() {
        super(0, 0);
    }

    public IPair(int x, int y) {
        super(x, y);
    }

    public int x() {
        val x = getX();
        if (x == null) {
            setX(0);
            return 0;
        }
        return x;
    }

    public int y() {
        val y = getY();
        if (y == null) {
            setY(0);
            return 0;
        }
        return y;
    }
}
