package io.github.mjaroslav.sharedjava.util;

import io.github.mjaroslav.sharedjava.tuple.Unit;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Objects;

class DelegatingMapTest {
    DelegatingMap<Integer, Object> map =
        new DelegatingMap<>((unit, obj) -> Objects.equals(unit.getX(), obj.getX()),
            Unit::getX, unit -> String.valueOf(unit.getX()));

    @Test
    void size() {
        var expected = 0;
        var actual = map.size();
        Assertions.assertEquals(expected, actual, "New map not empty");
        map.put(3, "test");
        expected = 1;
        actual = map.size();
        Assertions.assertEquals(expected, actual, "Map not match");
    }

    @SuppressWarnings("ConstantValue")
    @Test
    void isEmpty() {
        Assertions.assertTrue(map.isEmpty(), "New map not empty");
        map.put(3, "test");
        Assertions.assertFalse(map.isEmpty(), "Non empty map");
        map.clear();
        Assertions.assertTrue(map.isEmpty(), "Cleared map not empty");
    }

    @Test
    void containsKey() {
        map.put(30, "test");
        Assertions.assertTrue(map.containsKey(30), "Not contains existing value");
        Assertions.assertFalse(map.containsKey(-30), "Contains non existing value");
    }

    @Test
    void containsValue() {
        map.put(30, "test");
        Assertions.assertTrue(map.containsValue("test"), "Not contains existing value");
        Assertions.assertFalse(map.containsValue("nonContains"), "Contains non existing value");
    }

    @Test
    void remove() {
        map.put(-30, "test");
        var expected = Arrays.asList(-30);
        var actual = map.keySet();
        Assertions.assertIterableEquals(expected, actual, "Non empty map is empty");
        map.remove(-30);
        expected = Collections.emptyList();
        actual = map.keySet();
        Assertions.assertIterableEquals(expected, actual, "Empty map is not empty");
    }

    @Test
    void clear() {
        map.put(-30, "-30");
        map.put(0, "0");
        map.put(30, "30");
        var expected = Arrays.asList(0, -30, 30);
        var actual = map.keySet();
        Assertions.assertIterableEquals(expected, actual, "Non empty map is empty");
        map.clear();
        expected = Collections.emptyList();
        actual = map.keySet();
        Assertions.assertIterableEquals(expected, actual, "Empty map is not empty");
    }

    @Test
    void get() {
        val expected = "test";
        map.put(30, expected);
        var actual = map.get(30);
        Assertions.assertEquals(expected, actual, "Value not match");
        actual = map.get(-30);
        Assertions.assertNull(actual, "Non existing key return value");
    }

    @Test
    void values() {
        map.put(-30, "first");
        map.put(30, "second");
        val expected = Arrays.asList("first", "second");
        val actual = map.values();
        Assertions.assertIterableEquals(expected, actual, "Values not match");
    }

    @Test
    void put() {
        var actual = map.get(30);
        Assertions.assertNull(actual, "Non existing key return value");
        val expected = "test";
        map.put(30, expected);
        actual = map.get(30);
        Assertions.assertEquals(expected, actual, "Value not match");
    }

    @Test
    void putAll() {
        val source = new HashMap<Integer, Object>();
        source.put(0, "first");
        source.put(-30, "second");
        source.put(30, "third");
        map.putAll(source);
        val expected = Arrays.asList(0, -30, 30);
        val actual = map.keySet();
        Assertions.assertIterableEquals(expected, actual, "Non empty map is empty");

    }

    @Test
    void keySet() {
        map.put(-30, "first");
        map.put(30, "second");
        val expected = Arrays.asList(-30, 30);
        val actual = map.keySet();
        Assertions.assertIterableEquals(expected, actual, "Keys not match");
    }

    @Test
    void entrySet() {
        map.put(30, "test");
        val actual = map.entrySet().stream().findFirst().get();
        Assertions.assertEquals(30, actual.getKey(), "Key not match");
        Assertions.assertEquals("test", actual.getValue(), "Value not match");
    }

    @Test
    void testToString() {
        map.put(-30, "first");
        map.put(30, "second");
        val expected = "DelegatingMap[-30=first, 30=second]";
        val actual = map.toString();
        Assertions.assertEquals(expected, actual, "String not match");
    }
}
