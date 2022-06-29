package com.java.poker;

public enum Rank implements Comparable<Rank> {
    HIGH_CARD("High Card"), ONE_PAIR("One Pair"), TWO_PAIR("Two Pairs"), THREE_OF_A_KIND("Three of a Kind"),
    STRAIGHT("Straight"), FLUSH("Flush"), FULL_HOUSE("Full House"), FOUR_OF_A_KIND("Four of a Kind"),
    STRAIGHT_FLUSH("Straight Flush"), ROYAL_FLUSH("Royal Flush");

    public final String rank;

    Rank(String rank) {
        this.rank = rank;
    }


    @Override
    public String toString() {
        return "" + rank;
    }
}
