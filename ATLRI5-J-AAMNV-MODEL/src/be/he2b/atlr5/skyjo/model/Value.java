package be.he2b.atlr5.skyjo.model;

import java.io.Serializable;

/**
 * The enum Value.
 */
public enum Value implements Serializable {
    /**
     * Neg 2 value.
     */
    NEG_2(-2),
    /**
     * Neg 1 value.
     */
    NEG_1(-1),
    /**
     * Neg 0 value.
     */
    NEG_0(0),
    /**
     * Ace value.
     */
    ACE(1),
    /**
     * Two value.
     */
    TWO(2),
    /**
     * Three value.
     */
    THREE(3),
    /**
     * Four value.
     */
    FOUR(4),
    /**
     * Five value.
     */
    FIVE(5),
    /**
     * Six value.
     */
    SIX(6),
    /**
     * Seven value.
     */
    SEVEN(7),
    /**
     * Eight value.
     */
    EIGHT(8),
    /**
     * Nine value.
     */
    NINE(9),
    /**
     * Ten value.
     */
    TEN(10),
    /**
     * Elven value.
     */
    ELVEN(11),
    /**
     * Twelve value.
     */
    TWELVE(12);
    private final int value;

    Value(int value) {
        this.value = value;
    }

    /**
     * Gets value.
     *
     * @return value
     */
    public int getValue() {
        return value;
    }

}
