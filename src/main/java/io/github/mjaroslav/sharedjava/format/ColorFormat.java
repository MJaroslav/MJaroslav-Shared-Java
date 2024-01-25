package io.github.mjaroslav.sharedjava.format;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

/**
 * Formats of packed color, contains HEX bit positions and array index for each color chanel.
 *
 * @author MJaroslav
 * @since 0.1.0
 */
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PUBLIC)
public enum ColorFormat {
    /**
     * #RRGGBBAA or [r, g, b, a].
     * @since 0.1.0
     */
    RGBA(3, 0, 1, 2, 0, 24, 16, 8, true),
    /**
     * #AARRGGBB or [a, r, g, b].
     * @since 0.1.0
     */
    ARGB(0, 1, 2, 3, 24, 16, 8, 0, true),
    /**
     * #RRGGBB (#00RRGGBB) or [r, g, b, 0].
     * @since 0.1.0
     */
    RGB(3, 0, 1, 2, 24, 16, 8, 0, false),
    /**
     * #BBGGRR (#00BBGGRR) or [b, g, r, 0].
     * @since 0.1.0
     */
    BGR(3, 2, 1, 0, 24, 0, 8, 16, false),
    /**
     * #BBGGRRAA or [b, g, r, a].
     * @since 0.1.0
     */
    BGRA(3, 2, 1, 0, 0, 8, 16, 24, true),
    /**
     * #AABBGGRR or [a, b, g, r].
     * @since 0.1.0
     */
    ABGR(0, 3, 2, 1, 24, 0, 8, 16, true);

    int aArrayPos;
    int rArrayPos;
    int gArrayPos;
    int bArrayPos;

    int aBytePos;
    int rBytePos;
    int gBytePos;
    int bBytePos;

    boolean hasAlpha;
}
