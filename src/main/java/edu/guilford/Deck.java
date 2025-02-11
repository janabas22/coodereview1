package edu.guilford;

import java.util.ArrayList;
import java.util.Random;

public class Deck {
    /**
     * The deck of cards.
     * The deck is an ArrayList of Card objects.
     * The deck is shuffled using the shuffle method.
     * The deck is built using the build method.
     * The deck is cleared using the clear method.
     */
    private ArrayList<Card> deck = new ArrayList<Card>();
    private Random rand = new Random();

    public Deck() {
        build();
        shuffle();
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }
    
    public void clear() {
        deck.clear();
    }

    /**
     * Builds the deck of cards.
     * The deck is built by adding all 52 cards to the deck.
     * The deck is built by adding all 13 ranks of each suit to the deck.
     */
    public void build() {
        for (Card.Suit suit : Card.Suit.values()) {
            for (Card.Rank rank : Card.Rank.values()) {
                deck.add(new Card(suit, rank));
            }
        }
    }

    /**
     * Shuffles the deck of cards.
     * The deck is shuffled by randomly selecting a card from the deck and adding it to a temporary deck.
     * The deck is shuffled by removing the card from the deck.
     * The deck is shuffled by setting the deck to the temporary deck.
     * The deck is shuffled by repeating the process until the deck is empty.
     */
    public void shuffle() {
        ArrayList<Card> tempDeck = new ArrayList<Card>();
        while (deck.size() > 0) {
            int loc = rand.nextInt(deck.size());
            tempDeck.add(deck.get(loc));
            deck.remove(loc);
        }
        deck = tempDeck;
    }

    /**
     * Picks a card from the deck.
     * The card is picked by removing the card from the deck.
     * @param i
     * @return
     */
    public Card pick(int i) {
        Card picked = deck.remove(i);
        return picked;
    }

    /**
     * Deals a card from the deck.
     * The card is dealt by removing the card from the deck.
     * @return
     */
    public Card deal() {
        return deck.remove(0);
    }

    /**
     * The size of the deck.
     * @return
     */
    public int size() {
        return deck.size();
    }

    /**
     * The string representation of the deck.
     * @return
     */
    public String toString() {
        String deckString = "";
        for (Card card : deck) {
            deckString += card.toString() + "\n";
        }
        return deckString;
    }
}
