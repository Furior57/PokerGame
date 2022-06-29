package com.java.poker;

import java.util.Locale;

public enum Suit {
    CLUBS("C"), DIAMONDS("D"), HEARTS("H"), SPADES("S");
    public final String suit;

    Suit(String suit) {
        this.suit = suit;
    }

    public String getSuit() {
        return suit.toUpperCase(Locale.ROOT);
    }

    public static Suit fromString(String s) {
        Suit suit = null;
        for (Suit s1 : Suit.values()) {
            if (s1.suit.equalsIgnoreCase(s)) {
                suit = s1;
            }
        }
        if (suit != null) {
            return suit;
        } else throw new IllegalArgumentException("Suit \"" + s + "\" doesn't exist");
    }

}
