package com.singulariti.os.ephemeris.domain;

/**
 *
 * @author John
 */
public enum Pole {
    NORTH(0),
    SOUTH(1),
    EAST(1),
    WEST(0);

    private final int val;

    private Pole(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }

    public static Pole byValue(int val) {
        for (Pole p : values()) {
            if (p.getVal() == val) {
                return p;
            }
        }

        return null;
    }

}
