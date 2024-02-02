package io.github.mjaroslav.sharedjava.format;

import lombok.experimental.UtilityClass;
import lombok.val;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

import static io.github.mjaroslav.sharedjava.format.ColorFormat.*;

/**
 * Utilities for transferring between different color formats (arrays and packed integers).
 *
 * @author MJaroslav
 * @since 1.0.0
 */
@UtilityClass
public class Colors {
    /**
     * Unpack int color with {@link ColorFormat#ARGB ARGB} to int array with same format.
     *
     * @param packedColor color for unpacking
     * @return int array of unpacked color in {@link ColorFormat#ARGB ARGB} (all channels in 0..255 range)
     * @since 1.0.0
     */
    public int @NotNull [] unpackColorIntToIntArray(int packedColor) {
        return unpackColorIntToIntArray(packedColor, ARGB, ARGB);
    }

    /**
     * Unpack int color with inFormat to int array with {@link ColorFormat#ARGB ARGB} format.
     *
     * @param packedColor color for unpacking
     * @param inFormat    format of int packed color
     * @return int array of unpacked color in {@link ColorFormat#ARGB ARGB} (all channels in 0..255 range)
     * @since 1.0.0
     */
    public int @NotNull [] unpackColorIntToIntArray(int packedColor, @NotNull ColorFormat inFormat) {
        return unpackColorIntToIntArray(packedColor, inFormat, ARGB);
    }

    /**
     * Unpack int color with inFormat to int array with outFormat.
     *
     * @param packedColor color for unpacking
     * @param inFormat    format of int packed color
     * @param outFormat   format for result unpacked color array
     * @return int array of unpacked color in outFormat (all channels in 0..255 range)
     * @since 1.0.0
     */
    public int @NotNull [] unpackColorIntToIntArray(int packedColor, @NotNull ColorFormat inFormat,
                                                    @NotNull ColorFormat outFormat) {
        val result = new int[4];
        if (inFormat.hasAlpha) result[outFormat.aArrayPos] = (packedColor >> inFormat.aBytePos) & 0xFF;
        else result[outFormat.aArrayPos] = 0;
        result[outFormat.rArrayPos] = (packedColor >> inFormat.rBytePos) & 0xFF;
        result[outFormat.gArrayPos] = (packedColor >> inFormat.gBytePos) & 0xFF;
        result[outFormat.bArrayPos] = (packedColor >> inFormat.bBytePos) & 0xFF;
        return result;
    }

    /**
     * Unpack int color with {@link ColorFormat#ARGB ARGB} to double array with same format.
     *
     * @param packedColor color for unpacking
     * @return double array of unpacked color in {@link ColorFormat#ARGB ARGB} (all channels in 0..1 range)
     * @since 1.0.0
     */
    public double @NotNull [] unpackColorIntToDoubleArray(int packedColor) {
        return unpackColorIntToDoubleArray(packedColor, ARGB, ARGB);
    }

    /**
     * Unpack int color with inFormat to double array with {@link ColorFormat#ARGB ARGB} format.
     *
     * @param packedColor color for unpacking
     * @param inFormat    format of int packed color
     * @return double array of unpacked color in {@link ColorFormat#ARGB ARGB} (all channels in 0..1 range)
     * @since 1.0.0
     */
    public double @NotNull [] unpackColorIntToDoubleArray(int packedColor, @NotNull ColorFormat inFormat) {
        return unpackColorIntToDoubleArray(packedColor, inFormat, ARGB);
    }

    /**
     * Unpack int color with inFormat to double array with outFormat.
     *
     * @param packedColor color for unpacking
     * @param inFormat    format of int packed color
     * @param outFormat   format for result unpacked color array
     * @return double array of unpacked color in outFormat (all channels in 0..1 range)
     * @since 1.0.0
     */
    public double @NotNull [] unpackColorIntToDoubleArray(int packedColor, @NotNull ColorFormat inFormat,
                                                          @NotNull ColorFormat outFormat) {
        return Arrays.stream(unpackColorIntToIntArray(packedColor, inFormat, outFormat))
            .mapToDouble(i -> i / 255D).toArray();
    }

    /**
     * Unpack int color with {@link ColorFormat#ARGB ARGB} to float array with same format.
     *
     * @param packedColor color for unpacking
     * @return float array of unpacked color in {@link ColorFormat#ARGB ARGB} (all channels in 0..1 range)
     * @since 1.0.0
     */
    public float @NotNull [] unpackColorIntToFloatArray(int packedColor) {
        return unpackColorIntToFloatArray(packedColor, ARGB, ARGB);
    }

    /**
     * Unpack int color with inFormat to float array with {@link ColorFormat#ARGB ARGB} format.
     *
     * @param packedColor color for unpacking
     * @param inFormat    format of int packed color
     * @return float array of unpacked color in {@link ColorFormat#ARGB ARGB} (all channels in 0..1 range)
     * @since 1.0.0
     */
    public float @NotNull [] unpackColorIntToFloatArray(int packedColor, @NotNull ColorFormat inFormat) {
        return unpackColorIntToFloatArray(packedColor, inFormat, ARGB);
    }

    /**
     * Unpack int color with inFormat to float array with outFormat.
     *
     * @param packedColor color for unpacking
     * @param inFormat    format of int packed color
     * @param outFormat   format for result unpacked color array
     * @return float array of unpacked color in outFormat (all channels in 0..1 range)
     * @since 1.0.0
     */
    public float @NotNull [] unpackColorIntToFloatArray(int packedColor, @NotNull ColorFormat inFormat,
                                                        @NotNull ColorFormat outFormat) {
        val arrayInt = unpackColorIntToIntArray(packedColor, inFormat, outFormat);
        val result = new float[arrayInt.length];
        for (var i = 0; i < arrayInt.length; i++)
            result[i] = arrayInt[i] / 255F;
        return result;
    }

    /**
     * Pack float color channels (in 0..1 range) to color int with {@link ColorFormat#ARGB ARGB} format.
     *
     * @param a alpha channel; 0 if alpha not supported by outFormat
     * @param r red color channel
     * @param g green color channel
     * @param b blue color channel
     * @return packed color integer
     * @see Colors#packToColorInt(float, float, float, float, ColorFormat)
     * @since 1.0.0
     */
    public int packToColorInt(float a, float r, float g, float b) {
        return packToColorInt((double) a, r, g, b, ARGB);
    }

    /**
     * Pack float color channels (in 0..1 range) to color int.
     *
     * @param a         alpha channel; 0 if alpha not supported by outFormat
     * @param r         red color channel
     * @param g         green color channel
     * @param b         blue color channel
     * @param outFormat out color format
     * @return packed color integer
     * @since 1.0.0
     */
    public int packToColorInt(float a, float r, float g, float b, @NotNull ColorFormat outFormat) {
        if (!outFormat.hasAlpha) a = 0;
        val aInt = ((int) (a * 0xFF) & 0xFF) << outFormat.aBytePos;
        val rInt = ((int) (r * 0xFF) & 0xFF) << outFormat.rBytePos;
        val gInt = ((int) (g * 0xFF) & 0xFF) << outFormat.gBytePos;
        val bInt = ((int) (b * 0xFF) & 0xFF) << outFormat.bBytePos;
        return aInt + rInt + gInt + bInt;
    }

    /**
     * Pack double color channels (in 0..1 range) to color int with {@link ColorFormat#ARGB ARGB} format.
     *
     * @param a alpha channel; 0 if alpha not supported by outFormat
     * @param r red color channel
     * @param g green color channel
     * @param b blue color channel
     * @return packed color integer
     * @see Colors#packToColorInt(double, double, double, double, ColorFormat)
     * @since 1.0.0
     */
    public int packToColorInt(double a, double r, double g, double b) {
        return packToColorInt(a, r, g, b, ARGB);
    }

    /**
     * Pack double color channels (in 0..1 range) to color int.
     *
     * @param a         alpha channel; 0 if alpha not supported by outFormat.
     * @param r         red color channel
     * @param g         green color channel
     * @param b         blue color channel
     * @param outFormat out color format
     * @return packed color integer
     * @since 1.0.0
     */
    public int packToColorInt(double a, double r, double g, double b, @NotNull ColorFormat outFormat) {
        if (!outFormat.hasAlpha) a = 0;
        val aInt = ((int) (a * 0xFF) & 0xFF) << outFormat.aBytePos;
        val rInt = ((int) (r * 0xFF) & 0xFF) << outFormat.rBytePos;
        val gInt = ((int) (g * 0xFF) & 0xFF) << outFormat.gBytePos;
        val bInt = ((int) (b * 0xFF) & 0xFF) << outFormat.bBytePos;
        return aInt + rInt + gInt + bInt;
    }

    /**
     * Pack int (in 0..255 range) color channels to color int with {@link ColorFormat#ARGB ARGB} format.
     *
     * @param a alpha channel; 0 if alpha not supported by outFormat
     * @param r red color channel
     * @param g green color channel
     * @param b blue color channel
     * @return packed color integer
     * @see Colors#packToColorInt(int, int, int, int, ColorFormat)
     * @since 1.0.0
     */
    public int packToColorInt(int a, int r, int g, int b) {
        return packToColorInt(a, r, g, b, ARGB);
    }

    /**
     * Pack int (in 0..255 range) color channels to color int.
     *
     * @param a         alpha channel; 0 if alpha not supported by outFormat
     * @param r         red color channel
     * @param g         green color channel
     * @param b         blue color channel
     * @param outFormat out color format
     * @return packed color integer
     * @since 1.0.0
     */
    public int packToColorInt(int a, int r, int g, int b, @NotNull ColorFormat outFormat) {
        if (!outFormat.hasAlpha) a = 0;
        a = (a & 0xFF) << outFormat.aBytePos;
        r = (r & 0xFF) << outFormat.rBytePos;
        g = (g & 0xFF) << outFormat.gBytePos;
        b = (b & 0xFF) << outFormat.bBytePos;
        return a + r + g + b;
    }

    /**
     * Pack int (in 0..255 range) color array to color int.
     *
     * @param unpacked  array with color channels, must contain 4 elements for alpha formats else can be 3
     * @param inFormat  array color format
     * @param outFormat out color format
     * @return packed color integer
     * @since 1.0.0
     */
    public int packToColorInt(int @NotNull [] unpacked, @NotNull ColorFormat inFormat, @NotNull ColorFormat outFormat) {
        if (inFormat.hasAlpha ? unpacked.length != 4 : (unpacked.length != 4 && unpacked.length != 3))
            throw new IllegalArgumentException("unpacked array must be with size 3 or 4 if inFormat has alpha channel");
        val a = (inFormat.hasAlpha ? (unpacked[inFormat.aArrayPos] & 0xFF) : 0) << outFormat.aBytePos;
        val r = (unpacked[inFormat.rArrayPos] & 0xFF) << outFormat.rBytePos;
        val g = (unpacked[inFormat.gArrayPos] & 0xFF) << outFormat.gBytePos;
        val b = (unpacked[inFormat.bArrayPos] & 0xFF) << outFormat.bBytePos;
        return a + r + g + b;
    }

    /**
     * Pack int (in 0..255 range) color array to color int with {@link ColorFormat#ARGB ARGB} format.
     *
     * @param unpacked array with color channels, must contain 4 elements for alpha formats else can be 3
     * @param inFormat array color format
     * @return packed color integer
     * @see Colors#packToColorInt(int[], ColorFormat, ColorFormat)
     * @since 1.0.0
     */
    public int packToColorInt(int @NotNull [] unpacked, @NotNull ColorFormat inFormat) {
        return packToColorInt(unpacked, inFormat, ARGB);
    }

    /**
     * Pack int (in 0..255 range) color array with {@link ColorFormat#ARGB ARGB} format to color int with same format.
     *
     * @param unpacked array with color channels, must contain 4 elements for alpha formats else can be 3
     * @return packed color integer
     * @see Colors#packToColorInt(int[], ColorFormat, ColorFormat)
     * @since 1.0.0
     */
    public int packToColorInt(int @NotNull [] unpacked) {
        return packToColorInt(unpacked, ARGB, ARGB);
    }

    /**
     * Pack float (in 0..1 range) color array to color int.
     *
     * @param unpacked  array with color channels, must contain 4 elements for alpha formats else can be 3
     * @param inFormat  array color format
     * @param outFormat out color format
     * @return packed color integer
     * @since 1.0.0
     */
    public int packToColorInt(float @NotNull [] unpacked, @NotNull ColorFormat inFormat, @NotNull ColorFormat outFormat) {
        if (inFormat.hasAlpha ? unpacked.length != 4 : (unpacked.length != 4 && unpacked.length != 3))
            throw new IllegalArgumentException("unpacked array must be with size 3 or 4 if inFormat has alpha channel");
        val a = (inFormat.hasAlpha ? ((int) (unpacked[inFormat.aArrayPos] * 255F) & 0xFF) : 0) << outFormat.aBytePos;
        val r = ((int) (unpacked[inFormat.rArrayPos] * 255F) & 0xFF) << outFormat.rBytePos;
        val g = ((int) (unpacked[inFormat.gArrayPos] * 255F) & 0xFF) << outFormat.gBytePos;
        val b = ((int) (unpacked[inFormat.bArrayPos] * 255F) & 0xFF) << outFormat.bBytePos;
        return a + r + g + b;
    }

    /**
     * Pack float (in 0..255 range) color array to color int with {@link ColorFormat#ARGB ARGB} format.
     *
     * @param unpacked array with color channels, must contain 4 elements for alpha formats else can be 3
     * @param inFormat array color format
     * @return packed color integer
     * @see Colors#packToColorInt(int[], ColorFormat, ColorFormat)
     * @since 1.0.0
     */
    public int packToColorInt(float @NotNull [] unpacked, @NotNull ColorFormat inFormat) {
        return packToColorInt(unpacked, inFormat, ARGB);
    }

    /**
     * Pack float (in 0..255 range) color array with {@link ColorFormat#ARGB ARGB} format to color int with same format.
     *
     * @param unpacked array with color channels, must contain 4 elements for alpha formats else can be 3
     * @return packed color integer
     * @see Colors#packToColorInt(int[], ColorFormat, ColorFormat)
     * @since 1.0.0
     */
    public int packToColorInt(float @NotNull [] unpacked) {
        return packToColorInt(unpacked, ARGB, ARGB);
    }

    /**
     * Pack double (in 0..1 range) color array to color int.
     *
     * @param unpacked  array with color channels, must contain 4 elements for alpha formats else can be 3
     * @param inFormat  array color format
     * @param outFormat out color format
     * @return packed color integer
     * @since 1.0.0
     */
    public int packToColorInt(double @NotNull [] unpacked, @NotNull ColorFormat inFormat, @NotNull ColorFormat outFormat) {
        if (inFormat.hasAlpha ? unpacked.length != 4 : (unpacked.length != 4 && unpacked.length != 3))
            throw new IllegalArgumentException("unpacked array must be with size 3 or 4 if inFormat has alpha channel");
        val a = (inFormat.hasAlpha ? ((int) (unpacked[inFormat.aArrayPos] * 255D) & 0xFF) : 0) << outFormat.aBytePos;
        val r = ((int) (unpacked[inFormat.rArrayPos] * 255D) & 0xFF) << outFormat.rBytePos;
        val g = ((int) (unpacked[inFormat.gArrayPos] * 255D) & 0xFF) << outFormat.gBytePos;
        val b = ((int) (unpacked[inFormat.bArrayPos] * 255D) & 0xFF) << outFormat.bBytePos;
        return a + r + g + b;
    }

    /**
     * Pack double (in 0..1 range) color array to color int with {@link ColorFormat#ARGB ARGB} format.
     *
     * @param unpacked array with color channels, must contain 4 elements for alpha formats else can be 3
     * @param inFormat array color format
     * @return packed color integer
     * @see Colors#packToColorInt(int[], ColorFormat, ColorFormat)
     * @since 1.0.0
     */
    public int packToColorInt(double @NotNull [] unpacked, @NotNull ColorFormat inFormat) {
        return packToColorInt(unpacked, inFormat, ARGB);
    }

    /**
     * Pack double (in 0..1 range) color array with {@link ColorFormat#ARGB ARGB} format to color int with same format.
     *
     * @param unpacked array with color channels, must contain 4 elements for alpha formats else can be 3
     * @return packed color integer
     * @see Colors#packToColorInt(int[], ColorFormat, ColorFormat)
     * @since 1.0.0
     */
    public int packToColorInt(double @NotNull [] unpacked) {
        return packToColorInt(unpacked, ARGB, ARGB);
    }
}
