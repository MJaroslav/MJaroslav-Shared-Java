package io.github.mjaroslav.sharedjava.reflect;

import io.github.mjaroslav.sharedjava.reflect.TreeDummy.TreeChildDummy;
import io.github.mjaroslav.sharedjava.reflect.TreeDummy.TreeChildDummy.TreeGrandChildDummy;
import io.github.mjaroslav.sharedjava.tuple.triplet.ITriplet;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.HashMap;

class ReflectionHelperTest {
    @Test
    void getEnumFromName() {
        val expected = EnumDummy.FIRST;
        val actual = (EnumDummy) ReflectionHelper.getEnumFromName(EnumDummy.class, "FIRST");
        Assertions.assertEquals(expected, actual, "Enum not match");
        Assertions.assertThrows(IllegalArgumentException.class, () ->
            ReflectionHelper.getEnumFromName(EnumDummy.class, "NON_EXISTS"), "Illegal enum exists");
    }

    @Test
    void getEnumFromNameWithFallback() {
        val expected = EnumDummy.SECOND;
        var actual = (EnumDummy) ReflectionHelper.getEnumFromName(EnumDummy.class, "SECOND", EnumDummy.FIRST);
        Assertions.assertEquals(expected, actual, "Enum not match");
        actual = ReflectionHelper.getEnumFromName(EnumDummy.class, "NON_EXISTS", expected);
        Assertions.assertEquals(expected, actual, "Fallback by illegal enum not match");
        actual = ReflectionHelper.getEnumFromName(null, null, expected);
        Assertions.assertEquals(expected, actual, "Fallback by null enum not match");
    }

    @Test
    void getSimpleClassName() {
        val expected = "EnumDummy";
        var actual = ReflectionHelper.getSimpleClassName(EnumDummy.class);
        Assertions.assertEquals(expected, actual, "Name by class not match");
        actual = ReflectionHelper.getSimpleClassName(EnumDummy.FIRST);
        Assertions.assertEquals(expected, actual, "Name by object not match");
        actual = ReflectionHelper.getSimpleClassName(EnumDummy.class.getName());
        Assertions.assertEquals(expected, actual, "Name by string not match");
    }

    @Test
    void getPackage() {
        val expected = "io.github.mjaroslav.sharedjava.reflect";
        var actual = ReflectionHelper.getPackage(EnumDummy.class);
        Assertions.assertEquals(expected, actual, "Package by class not match");
        actual = ReflectionHelper.getPackage(EnumDummy.FIRST);
        Assertions.assertEquals(expected, actual, "Package by object not match");
        actual = ReflectionHelper.getPackage(EnumDummy.class.getName());
        Assertions.assertEquals(expected, actual, "Package by string not match");
    }

    @Test
    void findDeepField() throws NoSuchFieldException {
        val obj = new TreeGrandChildDummy();
        val expected = TreeDummy.class.getDeclaredField("root");
        val actual = ReflectionHelper.findDeepField(TreeGrandChildDummy.class, null, "root");
        Assertions.assertEquals(expected, actual, "Fields not match");
        Assertions.assertThrows(UnableToFindFieldException.class, () ->
                ReflectionHelper.findDeepField(TreeGrandChildDummy.class, TreeChildDummy.class, "root"),
            "Deep limit ignored");
        Assertions.assertThrows(UnableToFindFieldException.class, () ->
                ReflectionHelper.findDeepField(TreeGrandChildDummy.class, null, "nonExistsField"),
            "Illegal field exists");
    }

    @Test
    void getDeepDeclaredFields() throws NoSuchFieldException {
        val expectedMap = new HashMap<String, Field>();
        expectedMap.put("root", TreeDummy.class.getDeclaredField("root"));
        expectedMap.put("child", TreeChildDummy.class.getDeclaredField("child"));
        expectedMap.put("grandChild", TreeGrandChildDummy.class.getDeclaredField("grandChild"));
        val actualMap = ReflectionHelper.getDeepDeclaredFields(TreeGrandChildDummy.class, null);
        Assertions.assertEquals(expectedMap, actualMap, "Fields not match");
    }

    @Test
    void getDeepPrivateValue() {
        val obj = new TreeGrandChildDummy();
        val expected = -10;
        val actual = (int) ReflectionHelper.getDeepPrivateValue(TreeGrandChildDummy.class, obj, null, "root");
        Assertions.assertEquals(expected, actual, "Deep private value not match");
    }

    @Test
    void setDeepPrivateValue() {
        val obj = new TreeGrandChildDummy();
        val expected = 10;
        ReflectionHelper.setDeepPrivateValue(TreeGrandChildDummy.class, obj, 10, null, "root");
        val actual = (int) ReflectionHelper.getDeepPrivateValue(TreeGrandChildDummy.class, obj, null, "root");
        Assertions.assertEquals(expected, actual, "Changed deep private value not match");
    }

    @Test
    void getGenericType() {
        val genericType = new ITriplet();
        val expected1 = Integer.class;
        val expected2 = Integer.class;
        val expected3 = Integer.class;
        val actual1 = ReflectionHelper.getGenericType(genericType.getClass(), 0);
        val actual2 = ReflectionHelper.getGenericType(genericType.getClass(), 1);
        val actual3 = ReflectionHelper.getGenericType(genericType.getClass(), 2);
        Assertions.assertTrue(expected1.isAssignableFrom(actual1), "First generic type not match");
        Assertions.assertTrue(expected2.isAssignableFrom(actual2), "Second generic type not match");
        Assertions.assertTrue(expected3.isAssignableFrom(actual3), "Third generic type not match");
    }
}
