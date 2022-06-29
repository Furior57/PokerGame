package com.java.poker;

import java.util.Locale;

public enum Values {
    TWO("2"), THREE("3"), FOUR("4"), FIVE("5"), SIX("6"), SEVEN("7"),
    EIGHT("8"), NINE("9"), TEN("T"), JACK("J"), QUEEN("Q"), KING("K"), ACE("A");
    public final String value;

    Values(String value) {
        this.value = value;
    }

    public String getValue() {
        return value.toUpperCase(Locale.ROOT);
    }

    public static Values fromString(String s) {
        Values val = null;
        for (Values v : Values.values()) {
            if (v.value.equalsIgnoreCase(s)) {
                val = v;
            }
        }
        if (val != null) {
            return val;
        } else throw new IllegalArgumentException("Value \"" + s + "\" doesn't exist");
    }

}




