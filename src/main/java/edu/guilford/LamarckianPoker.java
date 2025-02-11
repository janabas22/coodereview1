package edu.guilford;

import java.util.ArrayList;
import java.util.Random;

public class LamarckianPoker {
    /**
     * The player's hand, the dealer's hand, the pool, the discard pile, and the deck of cards.
     */
    private Hand player1Hand;
    private Hand player2Hand;
    private Hand pool;
    private Deck discard;
    private Deck deck;
    private Random rand = new Random();
    private int iTurn;

    /**
     * Constructor for the LamarckianPoker class. Initializes the player's hand, the dealer's hand, the pool, the discard pile, and the deck of cards.
     */
    public LamarckianPoker() {
        reset(true);
    }

    public Hand getPlayer1Hand() {
        return player1Hand;
    }

    public Hand getPlayer2Hand() {
        return player2Hand;
    }

    public Hand getPool() {
        return pool;
    }

    /**
     * Resets the game. If newDeck is true, a new deck is created and shuffled.
     * @param newDeck
     */
    public void reset(boolean newDeck) {
        if (newDeck) {
            deck = new Deck();
            discard = new Deck();
            discard.clear();
            deck.shuffle();
        }
        player1Hand = new Hand();
        player2Hand = new Hand();
        pool = new Hand();
        iTurn = 0;
    }

    /**
     * Deals four cards to each player.
     * The player's hand is the first card dealt to the player and the second card dealt to the player.
     */
    public void deal() {
        player1Hand = new Hand();
        player2Hand = new Hand();
        for (int iCard = 0; iCard < 4; iCard++) {
            player1Hand.addCard(deck.deal());
            player2Hand.addCard(deck.deal());
        }
    }

    /**
     * The player's turn. The player hits until the player's hand is 16 or greater.
     */
    public void makePool() {
        pool = new Hand();
        for (int iCard = 0; iCard < 4; iCard++) {
            pool.addCard(deck.deal());
        }
        // System.out.println("Deck size: " + deck.size());
    }

    /**
     * The player's turn. The player hits until the player's hand is 16 or greater.
     * @return boolean
     * @return true
     * @return false
     */
    public boolean turn() {
        if (player1Hand.size() < 7 || player2Hand.size() < 7) {
            makePool();
            // System.out.println("Turn " + iTurn + "\n" + pool);
            Card player1Card = player1Hand.getCard(rand.nextInt(player1Hand.size()));
            Card player2Card = player2Hand.getCard(rand.nextInt(player2Hand.size()));
            Hand firstHand, secondHand;
            Card firstCard, secondCard;
            if (player1Card.getRank().ordinal() > player2Card.getRank().ordinal()) {
                firstHand = player1Hand;
                secondHand = player2Hand;
                firstCard = player1Card;
                secondCard = player2Card;
            } else if (player1Card.getRank().ordinal() < player2Card.getRank().ordinal()) {
                firstHand = player2Hand;
                secondHand = player1Hand;
                firstCard = player2Card;
                secondCard = player1Card;
            } else {
                if (player1Card.getSuit().ordinal() > player2Card.getSuit().ordinal()) {
                    firstHand = player1Hand;
                    secondHand = player2Hand;
                    firstCard = player1Card;
                    secondCard = player2Card;
                } else {
                    firstHand = player2Hand;
                    secondHand = player1Hand;
                    firstCard = player2Card;
                    secondCard = player1Card;
                }

            }

            ArrayList<Card> poolRemove = new ArrayList<Card>();

            for (Card poolCard : pool.getHand()) {
                if (firstCard.getRank().ordinal() == poolCard.getRank().ordinal() ||
                        firstCard.getSuit().ordinal() == poolCard.getSuit().ordinal()) {
                    firstHand.addCard(poolCard);
                    poolRemove.add(poolCard);
                }
            }
            // Remove cards from pool
            for (Card poolCard : poolRemove) {
                pool.removeCard(poolCard);
            }
            poolRemove.clear();
            discard.getDeck().add(firstCard);
            firstHand.removeCard(firstCard);
            for (Card poolCard : pool.getHand()) {
                if (secondCard.getRank().ordinal() == poolCard.getRank().ordinal() ||
                        secondCard.getSuit().ordinal() == poolCard.getSuit().ordinal()) {
                    secondHand.addCard(poolCard);
                    poolRemove.add(poolCard);
                }
            }
            for (Card poolCard : poolRemove) {
                pool.removeCard(poolCard);
            }
            discard.getDeck().add(secondCard);
            secondHand.removeCard(secondCard);
            for (Card poolCard : pool.getHand()) {
                discard.getDeck().add(poolCard);
            }
            pool.getHand().clear();
            // System.out.println("Discard\n" + discard.size());
            if (deck.size() < 4) {
                for (Card card : discard.getDeck()) {
                    deck.getDeck().add(card);
                }
                discard.clear();
                // System.out.println("Discard\n" + discard.size());
            }
            iTurn++;
            
            return true;
        } else {
            return false;
        }

    }

    /**
     * Evaluates the hands of the players.
     * @return a string indicating the winner of the game.
     * If player 1 wins, the string "Player 1 wins!" is returned.
     * If player 2 wins, the string "Player 2 wins!" is returned.
     * If the game is a tie, the string "It's a tie!" is returned.
     */
    public String evaluateHands() {
        // This is a placeholder implementation. You can replace it with actual poker hand evaluation logic.
        int player1Score = player1Hand.getTotalValue();
        int player2Score = player2Hand.getTotalValue();

        if (player1Score > player2Score) {
            return "Player 1 wins!";
        } else if (player2Score > player1Score) {
            return "Player 2 wins!";
        } else {
            return "It's a tie!";
        }
    }

    /**
     * The string representation of the LamarckianPoker game.
     * @return a string representation of the LamarckianPoker game.
     */
    @Override
    public String toString() {
        return "\nPlayer 1: \n" + player1Hand + "\nPlayer 2: \n" + player2Hand + "\nPool: " + pool + "\n";
    }
}
