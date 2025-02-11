package edu.guilford;

import java.util.Random;

public class Card implements Comparable<Card>{

    /**
     * The suits and ranks of the cards.
     * The suits are CLUBS, DIAMONDS, HEARTS, and SPADES.
     * The ranks are ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, and KING.
     */
    // enum for the suits
    public enum Suit {
        CLUBS, DIAMONDS, HEARTS, SPADES
    }

    // enum for the ranks
    public enum Rank {
        ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN,
        KING
    }

    /**
     * The suit and rank of the card.
     * @param suit
     * @param rank
     */
    // instance variables
    private Suit suit;
    private Rank rank;

    /**
     * Constructor for the Card class.
     * @param suit
     * @param rank
     */
    // constructor
    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    /**
     * Constructor for the Card class.
     * Randomly selects a suit and rank for the card.
     * @param suit
     * @param rank
     */
    public Card() {
        // random Card
        Random rand = new Random();
        int suit = rand.nextInt(Suit.values().length);
        int rank = rand.nextInt(Rank.values().length);
        this.suit = Suit.values()[suit];
        this.rank = Rank.values()[rank];
    }

    /**
     * Compares two cards.
     * @param otherCard
     * @return 1 if this card is greater than otherCard, -1 if this card is less than otherCard, 0 if the cards are equal
     */
    // getters
    public Suit getSuit() {
        return suit;
    }

    public Rank getRank() {
        return rank;
    }

    // toString
    public String toString() {
        return rank + " of " + suit;
    }


    /**
     * Compares two cards.
     * @param otherCard
     * @return 1 if this card is greater than otherCard, -1 if this card is less than otherCard, 0 if the cards are equal
     */
    @Override
    public int compareTo(Card otherCard) {
        // TODO Auto-generated method stub
        if (this.rank.ordinal() > otherCard.rank.ordinal()) {
            return 1;
        }
        else if (this.rank.ordinal() < otherCard.rank.ordinal()) {
            return -1;
        }
        else {
            if (this.suit.ordinal() > otherCard.suit.ordinal()) {
                return 1;
            }
            else if (this.suit.ordinal() < otherCard.suit.ordinal()) {
                return -1;
            }
        }

        return 0;
    }

    
}