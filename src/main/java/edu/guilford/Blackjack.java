package edu.guilford;

public class Blackjack {
    /**
     *  The player's hand, the dealer's hand, and the deck of cards.
     */
    private Hand playerHand;
    private Hand dealerHand;
    private Deck deck;

    /**
     * Constructor for the Blackjack class. Initializes the player's hand, the dealer's hand, and the deck of cards.
     */
    public Blackjack() {
        reset(true);
    }


    public Hand getPlayerHand() {
        return playerHand;
    }


    public Hand getDealerHand() {
        return dealerHand;
    }


    public Deck getDeck() {
        return deck;
    }

    /**
     * Resets the game. If newDeck is true, a new deck is created and shuffled.
     * @param newDeck
     */
    public void reset(boolean newDeck) {
        if (newDeck) {
            deck = new Deck();
            deck.shuffle();
        }
        playerHand = new Hand();
        dealerHand = new Hand();
    }

    /**
     * Deals two cards to the player and two cards to the dealer.
     * The player's hand is the first card dealt to the player and the second card dealt to the player.
     */
    public void deal() {
        playerHand = new Hand();
        dealerHand = new Hand();
        playerHand.addCard(deck.deal());
        dealerHand.addCard(deck.deal());
        playerHand.addCard(deck.deal());
        dealerHand.addCard(deck.deal());
    }

    /**
     * The player's turn. The player hits until the player's hand is 16 or greater.
     * @return true if the player's hand is less than or equal to 21, false otherwise.
     */
    public boolean playerTurn() {
        while (playerHand.getTotalValue() < 16) {
            playerHand.addCard(deck.deal());
        }
        return playerHand.getTotalValue() <= 21;

    }

    /**
     * The dealer's turn. The dealer hits until the dealer's hand is 17 or greater.
     * @return true if the dealer's hand is less than or equal to 21, false otherwise.
     */
    public boolean dealerTurn() {
        while (dealerHand.getTotalValue() < 17) {
            dealerHand.addCard(deck.deal());
        }
        return dealerHand.getTotalValue() <= 21;
    }

    /**
     * Determines the winner of the game.
     * @return a string indicating the winner of the game.
     * If the player wins, the string "Player wins!" is returned.
     * If the dealer wins, the string "Dealer wins!" is returned.
     * If the game is a push, the string "Push!" is returned.
     * If the player busts, the string "Player busts!" is returned.
     * If the dealer busts, the string "Dealer busts!" is returned.
     * If the player's hand is greater than the dealer's hand, the string "Player wins!" is returned.
     * If the dealer's hand is greater than the player's hand, the string "Dealer wins!" is returned.
     * If the player's hand is equal to the dealer's hand, the string "Push!" is returned.
     * If the player has a blackjack, the string "Player has blackjack!" is returned.
     * If the dealer has a blackjack, the string "Dealer has blackjack!" is returned.
     * If the player has a blackjack and the dealer has a blackjack, the string "Push!" is returned.
     */
    // Override toString
    public String toString() {
        String result = "Player's Hand:\n";
        for (int i = 0; i < playerHand.size(); i++) {
            result += playerHand.getCard(i) + "\n";
        }
        result += "Player's Total: " + playerHand.getTotalValue() + "\n\n";
        result += "Dealer's Hand:\n";
        for (int i = 0; i < dealerHand.size(); i++) {
            result += dealerHand.getCard(i) + "\n";
        }
        result += "Dealer's Total: " + dealerHand.getTotalValue() + "\n\n";
        return result;
    }

}
