package com.java.poker;

import java.util.*;

public class Hand {
    private final Set<Card> handSet;

    public Hand(Set<Card> handSet) {
        if (handSet.size() != 5) {
            throw new IllegalArgumentException("\"Please enter five different cards separated by space in format \"VS\",\n where V - value and S - suit");
        }
        this.handSet = handSet;
    }

    public Set<Card> getHandSet() {
        return handSet;
    }

    public Hand fromString(String s) {
        Set<Card> hand = new HashSet<>();
        String[] stringArray = s.split(" ");
        for (String element : stringArray) {
            Card card = Card.fromString(element);
            if (hand.contains(card)) {
                throw new IllegalArgumentException("Please enter five different cards");
            } else hand.add(card);
        }
        return new Hand(hand);
    }

    private Map<Values, Integer> determineNumberValues(Set<Card> valuesSet) {

        Map<Values, Integer> counter = new TreeMap<>();
        List<Card> list = new ArrayList<>(valuesSet);

        for (Card c : list) {
            if (!counter.containsKey(c.getValues())) {
                counter.put(c.getValues(), 0);
            }
            if (counter.containsKey(c.getValues())) {
                int count = counter.get(c.getValues()) + 1;
                counter.replace(c.getValues(), count);
            }
        }
        return counter;
    }

    private boolean checkOfStraight(Set<Card> handSet) {

        List<Values> valuesList = new ArrayList<>(List.of(Values.values()));
        List<Values> handList = new ArrayList<>();

        for (Card c : handSet) {
            handList.add(c.getValues());
        }

        Collections.sort(handList);

        return Collections.indexOfSubList(valuesList, handList) != -1;
    }

    private boolean checkOfFlush(Set<Card> handSet) {
        Set<Suit> suit = new HashSet<>();
        for (Card c : handSet) {
            suit.add(c.getSuit());
        }
        return suit.size() == 1;
    }

    public Rank determineRank(Set<Card> handset) {

        List<Values> upperCard = new ArrayList<>();
        for (Card c : handset) {
            upperCard.add(c.getValues());
        }
        Collections.sort(upperCard);

        Map<Values, Integer> valuesMap = determineNumberValues(handset);
        List<Values> keyList = new ArrayList<>(valuesMap.keySet());

        switch (valuesMap.size()) {
            case 2:
                if (valuesMap.get(keyList.get(0)) == 2 || valuesMap.get(keyList.get(1)) == 2) {
                    return Rank.FULL_HOUSE;
                } else return Rank.FOUR_OF_A_KIND;

            case 3:
                if (valuesMap.get(keyList.get(0)) == 3 || valuesMap.get(keyList.get(1)) == 3 || valuesMap.get(keyList.get(2)) == 3) {
                    return Rank.THREE_OF_A_KIND;
                } else return Rank.TWO_PAIR;
            case 4:
                return Rank.ONE_PAIR;
            case 5:
                if (checkOfFlush(handset) && checkOfStraight(handset)) {
                    if (upperCard.get(upperCard.size() - 1) == Values.ACE) {
                        return Rank.ROYAL_FLUSH;
                    } else return Rank.STRAIGHT_FLUSH;
                } else if (checkOfStraight(handset)) {
                    return Rank.STRAIGHT;
                }
            default:
                return Rank.HIGH_CARD;


        }

    }


    @Override
    public String toString() {
        return handSet.toString();
    }

}









