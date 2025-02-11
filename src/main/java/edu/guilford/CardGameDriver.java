package edu.guilford;

public class CardGameDriver {
    /**
     * The main method for the CardGameDriver class. This method plays 10,000 games of Blackjack and outputs the results.
     * @param args
     * @return void
     */
    public static void main(String[] args) {

        /**
         * The number of games to play.
         * The number of games the dealer wins.
         * The number of games the player wins.
         */
        final int NGAMES = 10000;
        int dealerWins = 0;
        int playerWins = 0;
        Blackjack game = new Blackjack();
        game.deal();
        // System.out.println(game);
        int iGame = 0;
        /**
         * Play 10,000 games of Blackjack.
         * If the player has 21, the player wins.
         * If the dealer has 21, the dealer wins.
         * If the player busts, the dealer wins.
         * If the dealer busts, the player wins.
         * If the player has a higher hand than the dealer, the player wins.
         * If the dealer has a higher hand than the player, the dealer wins.
         * If the player and dealer have the same hand, it is a push.
         * If the player has a blackjack and the dealer does not, the player wins.
         */
        while (iGame < NGAMES) {
            game.deal();
            if (game.getPlayerHand().getTotalValue() == 21) {
                playerWins++;
            } else if (game.getDealerHand().getTotalValue() == 21) {
                dealerWins++;
            } else {
                boolean playerResult = game.playerTurn();
                boolean dealerResult = game.dealerTurn();
                if (!playerResult) {
                    dealerWins++;
                } else if (!dealerResult) {
                    playerWins++;
                } else if (game.getPlayerHand().getTotalValue() < game.getDealerHand().getTotalValue()) {
                    dealerWins++;
                } else if (game.getPlayerHand().getTotalValue() > game.getDealerHand().getTotalValue()) {
                    playerWins++;
                }
            }
            if (game.getDeck().size() < 10) {
                game.reset(true);
            }

            /**
             * Increment the game counter.
             * Print the number of games the dealer has won.
             * Print the number of games the player has won.
             */
            iGame++;
        }
        System.out.println("Dealer wins: " + dealerWins);
        System.out.println("Player wins: " + playerWins);
        System.out.println("Pushes: " + (NGAMES - dealerWins - playerWins));

        /**
         * Play Lamarckian Poker.
         * Deal the cards.
         * Play the game until it is done.
         * Print the final hands.
         * Print the winner.
         * @return void
         */

        LamarckianPoker lmpGame = new LamarckianPoker();
        lmpGame.deal();
        System.out.println("\nInitial Lamarckian hands\n" + lmpGame);

        boolean gameDone = false;
        while (!gameDone) {
        //    System.out.println(lmpGame);
            gameDone = !lmpGame.turn();
        }
  
        System.out.println("Final Lamarckian hands\n" + lmpGame);   
        System.out.println(lmpGame.evaluateHands()); // Determine the winner
    }
}