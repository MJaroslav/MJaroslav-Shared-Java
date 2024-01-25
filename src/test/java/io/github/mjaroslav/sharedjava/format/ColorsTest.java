package io.github.mjaroslav.sharedjava.format;

import lombok.val;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class ColorsTest {
    static final int A = 0xAA;
    static final int R = 0xBB;
    static final int G = 0xCC;
    static final int B = 0xDD;

    private void unpackColorIntShared(int packed, @NotNull ColorFormat format) {
        val actualInt = Colors.unpackColorIntToIntArray(packed, format);
        val expectedInt = new int[]{format.hasAlpha ? A : 0, R, G, B};
        Assertions.assertArrayEquals(expectedInt, actualInt, "int");
        val actualFloat = Colors.unpackColorIntToFloatArray(packed, format);
        val expectedFloat = new float[]{(format.hasAlpha ? A : 0) / 255F, R / 255F, G / 255F, B / 255F};
        Assertions.assertArrayEquals(expectedFloat, actualFloat, 0, "float");
        val actualDouble = Colors.unpackColorIntToDoubleArray(packed, format);
        val expectedDouble = new double[]{(format.hasAlpha ? A : 0) / 255D, R / 255D, G / 255D, B / 255D};
        Assertions.assertArrayEquals(expectedDouble, actualDouble, 0, "double");
    }

    private void packToColorIntSharedArray(int expected, int i0, int i1, int i2, int i3, @NotNull ColorFormat format) {
        var actual = Colors.packToColorInt(new int[]{i0, i1, i2, i3}, format);
        Assertions.assertEquals(expected, actual, "int array");
        actual = Colors.packToColorInt(new float[]{i0 / 255F, i1 / 255F, i2 / 255F, i3 / 255F}, format);
        Assertions.assertEquals(expected, actual, "float array");
        actual = Colors.packToColorInt(new double[]{i0 / 255D, i1 / 255D, i2 / 255D, i3 / 255D}, format);
        Assertions.assertEquals(expected, actual, "double array");
    }

    private void packToColorIntShared(int expected, @NotNull ColorFormat format) {
        var actual = Colors.packToColorInt(A, R, G, B, format);
        Assertions.assertEquals(expected, actual, "int");
        actual = Colors.packToColorInt(A / 255F, R / 255F, G / 255F, B / 255F, format);
        Assertions.assertEquals(expected, actual, "float");
        actual = Colors.packToColorInt(A / 255D, R / 255D, G / 255D, B / 255D, format);
        Assertions.assertEquals(expected, actual, "double");
    }

    @Test
    void packToColorInt$BGRA() {
        val expected = (B << 24) + (G << 16) + (R << 8) + A; // #BBGGRRAA
        packToColorIntShared(expected, ColorFormat.BGRA);
    }

    @Test
    void packToColorInt$BGR() {
        val expected = (B << 16) + (G << 8) + R; // #00BBGGRR
        packToColorIntShared(expected, ColorFormat.BGR);
    }

    @Test
    void packToColorInt$RGBA() {
        val expected = (R << 24) + (G << 16) + (B << 8) + A; // #RRGGBB00
        packToColorIntShared(expected, ColorFormat.RGBA);
    }

    @Test
    void packToColorInt$RGB() {
        val expected = (R << 16) + (G << 8) + B; // #00RRGGBB
        packToColorIntShared(expected, ColorFormat.RGB);
    }

    @Test
    void packToColorInt$ARGB() {
        val expected = (A << 24) + (R << 16) + (G << 8) + B; // #AARRGGBB
        packToColorIntShared(expected, ColorFormat.ARGB);
    }

    @Test
    void packToColorInt$ABGR() {
        val expected = (A << 24) + (B << 16) + (G << 8) + R; // #AABBGGRR
        packToColorIntShared(expected, ColorFormat.ABGR);
    }

    @Test
    void packToColorInt$BGRA$array() {
        val expected = 0xAABBCCDD; // #AARRGGBB
        packToColorIntSharedArray(expected, B, G, R, A, ColorFormat.BGRA);
    }

    @Test
    void packToColorInt$BGR$array() {
        val expected = 0xBBCCDD; // #00RRGGBB
        packToColorIntSharedArray(expected, B, G, R, 0, ColorFormat.BGR);
    }

    @Test
    void packToColorInt$RGBA$array() {
        val expected = 0xAABBCCDD; // #AARRGGBB
        packToColorIntSharedArray(expected, R, G, B, A, ColorFormat.RGBA);
    }

    @Test
    void packToColorInt$RGB$array() {
        val expected = 0x00BBCCDD; // #00RRGGBB
        packToColorIntSharedArray(expected, R, G, B, 0, ColorFormat.RGB);
    }

    @Test
    void packToColorInt$ARGB$array() {
        val expected = 0xAABBCCDD; // #AARRGGBB
        packToColorIntSharedArray(expected, A, R, G, B, ColorFormat.ARGB);
    }

    @Test
    void packToColorInt$ABGR$array() {
        val expected = 0xAABBCCDD; // #AARRGGBB
        packToColorIntSharedArray(expected, A, B, G, R, ColorFormat.ABGR);
    }

    @Test
    void unpackColorInt$BGRA() {
        val packed = (B << 24) + (G << 16) + (R << 8) + A; // #BBGGRRAA
        unpackColorIntShared(packed, ColorFormat.BGRA);
    }

    @Test
    void unpackColorInt$BGR() {
        val packed = (B << 16) + (G << 8) + R; // #00BBGGRR
        unpackColorIntShared(packed, ColorFormat.BGR);
    }

    @Test
    void unpackColorInt$RGBA() {
        val packed = (R << 24) + (G << 16) + (B << 8) + A; // #RRGGBBAA
        unpackColorIntShared(packed, ColorFormat.RGBA);
    }

    @Test
    void unpackColorInt$RGB() {
        val packed = (R << 16) + (G << 8) + B; // #00RRGGBB
        unpackColorIntShared(packed, ColorFormat.RGB);
    }

    @Test
    void unpackColorInt$ARGB() {
        val packed = (A << 24) + (R << 16) + (G << 8) + B; // #AARRGGBB
        unpackColorIntShared(packed, ColorFormat.ARGB);
    }


    @Test
    void unpackColorInt$ABGR() {
        val packed = (A << 24) + (B << 16) + (G << 8) + R; // #AABBGGRR
        unpackColorIntShared(packed, ColorFormat.ABGR);
    }

}
