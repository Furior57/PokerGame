package com.java.poker;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class Card implements Comparable<Card> {
    private final Suit suit;
    private final Values values;

    public Card(Values values, Suit suit) {
        this.suit = suit;
        this.values = values;
    }


    public Suit getSuit() {
        return suit;
    }

    public Values getValues() {
        return values;
    }

    public static Card fromString(String s) {
        String[] input = s.split("");
        return new Card(Values.fromString(input[0]), Suit.fromString(input[1]));
    }

    @Override
    public int hashCode() {
        int result = suit != null ? suit.hashCode() : 0;
        result = 31 * result + (values != null ? values.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (getClass() != obj.getClass()) return false;
        Card card = (Card) obj;
        if (suit != card.getSuit()) return false;
        return values == card.values;
    }

    @Override
    public int compareTo(Card o) {
        Values[] values = Values.values();
        List<Values> listValues = Arrays.asList(values);
        if (listValues.indexOf(this.values) > listValues.indexOf(o.getValues())) {
            return 1;
        } else if (listValues.indexOf(this.values) < listValues.indexOf(o.getValues())) {
            return -1;
        } else if (listValues.indexOf(this.values) == listValues.indexOf(o.getValues())) {
            return String.valueOf(suit).compareTo(String.valueOf(o.getSuit()));
        }
        return 0;
    }

    @Override
    public String toString() {
        return "" + values.value.toUpperCase(Locale.ROOT) + suit.suit.toUpperCase(Locale.ROOT);
    }
}
