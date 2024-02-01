package io.github.mjaroslav.sharedjava.util;

import io.github.mjaroslav.sharedjava.tuple.Unit;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;

class DelegatingSetTest {
    DelegatingSet<Integer> set =
        new DelegatingSet<>((unit, obj) -> Objects.equals(unit.getX(), obj.getX()),
            Unit::getX, unit -> String.valueOf(unit.getX()));

    @Test
    void size() {
        var expected = 0;
        var actual = set.size();
        Assertions.assertEquals(expected, actual, "New set not empty");
        set.add(3);
        expected = 1;
        actual = set.size();
        Assertions.assertEquals(expected, actual, "Size not match");
    }

    @SuppressWarnings("ConstantValue")
    @Test
    void isEmpty() {
        Assertions.assertTrue(set.isEmpty(), "New set not empty");
        set.add(3);
        Assertions.assertFalse(set.isEmpty(), "Non empty set");
        set.clear();
        Assertions.assertTrue(set.isEmpty(), "Cleared set not empty");
    }

    @Test
    void contains() {
        set.add(30);
        Assertions.assertTrue(set.contains(30), "Not contains existing value");
        Assertions.assertFalse(set.contains(-30), "Contains non existing value");
    }

    @Test
    void iterator() {
        set.addAll(Arrays.asList(1, 5, 3, 6));
        val expected = new HashSet<>(Arrays.asList(1, 5, 3, 6));
        val actual = new HashSet<Integer>();
        set.iterator().forEachRemaining(actual::add);
        Assertions.assertEquals(expected, actual, "Iterator values not match");
    }

    @Test
    void toArray() {
        set.add(4);
        set.add(10);
        val expected = new Object[]{4, 10};
        val actual = set.toArray();
        Assertions.assertArrayEquals(expected, actual, "Array not match");
        val expectedInt = new Integer[]{4, 10};
        val actualInt = set.toArray(new Integer[0]);
        Assertions.assertArrayEquals(expected, actual, "Int array not match");
    }

    @Test
    void add() {
        var expected = new Integer[0];
        var actual = set.toArray(new Integer[0]);
        Assertions.assertArrayEquals(expected, actual, "New set not empty");
        set.add(-30);
        expected = new Integer[]{-30};
        actual = set.toArray(new Integer[0]);
        Assertions.assertArrayEquals(expected, actual, "Non empty set is empty");
    }

    @Test
    void remove() {
        set.add(-30);
        var expected = new Integer[]{-30};
        var actual = set.toArray(new Integer[0]);
        Assertions.assertArrayEquals(expected, actual, "Non empty set is empty");
        set.remove(-30);
        expected = new Integer[0];
        actual = set.toArray(new Integer[0]);
        Assertions.assertArrayEquals(expected, actual, "Empty set is not empty");
    }

    @Test
    void removeAll() {
        set.add(-30);
        set.add(0);
        set.add(30);
        var expected = new Integer[]{0, -30, 30};
        var actual = set.toArray(new Integer[0]);
        Assertions.assertArrayEquals(expected, actual, "Non empty set is empty");
        set.removeAll(Arrays.asList(0, 30));
        expected = new Integer[]{-30};
        actual = set.toArray(new Integer[0]);
        Assertions.assertArrayEquals(expected, actual, "Removed values not match");
    }

    @Test
    void clear() {
        set.add(-30);
        set.add(0);
        set.add(30);
        var expected = new Integer[]{0, -30, 30};
        var actual = set.toArray(new Integer[0]);
        Assertions.assertArrayEquals(expected, actual, "Non empty set is empty");
        set.clear();
        expected = new Integer[]{};
        actual = set.toArray(new Integer[0]);
        Assertions.assertArrayEquals(expected, actual, "Empty set is not empty");
    }

    @Test
    void containsAll() {
        set.add(30);
        set.add(-30);
        Assertions.assertTrue(set.containsAll(Arrays.asList(30, -30)), "Not contains existing value");
        Assertions.assertFalse(set.containsAll(Arrays.asList(30, 0, -30)), "Contains non existing value");
    }

    @Test
    void addAll() {
        var expected = new Integer[0];
        var actual = set.toArray(new Integer[0]);
        Assertions.assertArrayEquals(expected, actual, "New set not empty");
        set.addAll(Arrays.asList(-30, 0, 30));
        expected = new Integer[]{0, -30, 30};
        actual = set.toArray(new Integer[0]);
        Assertions.assertArrayEquals(expected, actual, "Non empty set is empty");
    }

    @Test
    void testToString() {
        set.add(-30);
        set.add(0);
        set.add(30);
        val expected = "DelegatingSet[0, -30, 30]";
        val actual = set.toString();
        Assertions.assertEquals(expected, actual, "String not match");
    }
}
