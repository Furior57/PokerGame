package com.java.poker;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        System.out.println("Poker\n");
        Stack<Card> deckOfCards = createDeckOfCards();

        System.out.println("Please enter the number of players:");

        int numberPlayers = numberOfPlayers(scan);

        System.out.println("Shuffle and deal cards");
        Map<String, Hand> listOfPlayers = playersList(deckOfCards, numberPlayers);

        List<String> playerName = new ArrayList<>(listOfPlayers.keySet());
        Collections.sort(playerName);

        for (String s : playerName) {
            System.out.println(s + " hand " + listOfPlayers.get(s));
        }

        System.out.println(determineTheWinner(listOfPlayers));


    }

    private static Map<String, Hand> playersList(Stack<Card> deckOfCards, int numberPlayers) {
        Map<String, Hand> listOfPlayers = new HashMap<>();

        for (int i = 1; i < numberPlayers + 1; i++) {
            Set<Card> set = new HashSet<>();
            for (int j = 0; j < 5; j++) {
                set.add(deckOfCards.pop());
            }
            Hand hand = new Hand(set);
            listOfPlayers.put("Player" + i, hand);
        }
        return listOfPlayers;
    }

    private static int numberOfPlayers(Scanner scan) {
        boolean checkPlayerNumber = false;
        int players = 0;
        while (!checkPlayerNumber) {
            try {
                players = Integer.parseInt(scan.nextLine());
                if (players > 10) {
                    System.out.println("The number of players cannot be more than 10, enter the number again:");
                    continue;
                }
                checkPlayerNumber = true;
            } catch (NumberFormatException e) {
                System.out.println("The number of players must be an integer, enter the number again:");
            }
        }
        return players;
    }

    public static Stack<Card> createDeckOfCards() {
        Stack<Card> deckOfCard = new Stack<>();
        for (Values v : Values.values()) {
            for (Suit s : Suit.values()) {
                deckOfCard.add(new Card(Values.fromString(v.getValue()), Suit.fromString(s.getSuit())));
            }
        }
        Collections.shuffle(deckOfCard);
        return deckOfCard;

    }

    public static String determineTheWinner(Map<String, Hand> listOfPlayers) {

        Map<String, Rank> playersRank = new TreeMap<>();
        for (String s : listOfPlayers.keySet()) {
            Rank r = listOfPlayers.get(s).determineRank(listOfPlayers.get(s).getHandSet());
            playersRank.put(s, r);
        }

        List<Rank> rankList = new ArrayList<>();
        Collections.addAll(rankList, Rank.values());

        Map<String, Integer> indexRankList = new HashMap<>();

        for (String s : playersRank.keySet()) {
            indexRankList.put(s, rankList.indexOf(playersRank.get(s)));
        }

        Map.Entry<String, Integer> maxEntry = null;

        for (Map.Entry<String, Integer> e : indexRankList.entrySet()) {
            if (maxEntry == null || e.getValue().compareTo(maxEntry.getValue()) > 0) {
                maxEntry = e;
            }
        }

        return declareTheWinner(listOfPlayers, playersRank, maxEntry);
    }

    private static String declareTheWinner(Map<String, Hand> listOfPlayers, Map<String, Rank> playersRank, Map.Entry<String, Integer> maxEntry) {
        return "The " + maxEntry.getKey() + "  with the " + playersRank.get(maxEntry.getKey()) + " won, his hand " + listOfPlayers.get(maxEntry.getKey());
    }


}






