# Two card games: Blackjack and Lamarckian Poker

## Utility classes

Both projects require the following classes.

### `Card`

The `Card` class implements the `Comparable<Card>` interface - yes

#### Attributes

* A suit to be chosen from `CLUBS, DIAMONDS, HEARTS, SPADES` - yes
* A rank to be chosen from `ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING`  - yes
* The suit and rank should be declared using `enum` structures named `Suit` and `Rank` - yes

#### Constructors

* `public Card()` should produce a randomly chosen `Card` object - yes
* `public Card(Suit suit, Rank rank)` should produce a `Card` object of the specified suit and rank - yes

#### Methods

* `public void getSuit()` returns the suit of the `Card` object - yes
* `pubilc void getRank()` returns the rank of the `Card` object - yes
* `public String toString()` returns a well-formatted `String` repesentation of this `Card` object - yes
* `public int compareTo(Card otherCard)` compares this `Card` object to `otherCard`. Rank is compared first and then suit. - yes

### `Deck`

A `Deck` object holds a sequence of `Card` objects that can be used in card games

#### Attributes

* `deck` is a list of `Card` objects - yes
* A `Random` object - yes

#### Constructors

* `public Deck()` constructs a standard 52-card deck using the `build()` method - yes

#### Methods

* `public ArrayList<Card> getDeck()` returns `deck` 
* `public void clear()` empties `deck`
* `public void build()` adds all 52 `Card` objects in a standard deck to `deck` in a specified order
* `public void shuffle()` shuffles the order of objects in `deck` using some standard algorithm that ensures a randomly arranged deck. It should operate on any `deck` that contains more than 0 `Card` objects
* `public Card pick (int i)` returns the `Card` object in `deck` at index `i` and removes that `Card` object from `deck`
* `public Card deal()` returns the `Card` object at index 0 (assumed to the the "top" of the deck) and removes that `Card` object from `deck`
* `public int size()` returns the number of `Card` objects in `deck` 
* `public String toString()` returns a well-formatted `String` representation of this `Deck` object

### `Hand`

A `Hand` object holds a sequence of `Card` objects that represent the cards a single player has in a card game

#### Attributes

* `hand` is a list of `Card` objects - yes

#### Constructors

* `public Hand()` assigns an empty list to `hand`

#### Methods

* `public ArrayList<Card> getHand()` returns `hand`
* `public void addCard(Card card)` adds `card` to `hand`
* `public void removeCard(Card card)` removes `card` from `hand`
* `public void reset()` empties `hand` 
* `public Card getCard(int index)` returns the `Card` object at the specified `index`
* `public int getTotalValue()` calculates a total for `hand` according to the rules of Blackjack. Number cards add their face value. Face cards add 10. Aces should count as 1 or 11, whichever provides the higher score without exceeding 21.
* `public String toString()` returns a well-formatted `String` representation of this `Hand` object

## Blackjack

We will implement a simple version of player and dealer actions in the Blackjack game.

Starting the game is when the player and dealer are each dealt two random cards. 

A player turn is when the player draws cards until the hand total is greater than or equal to 16. The player busts if the total is over 21.

A dealer turn is when the dealer draws cards until the hand total is greater than or equal to 17. The dealer busts if the total is over 21. 

### `Blackjack`

The `Blackjack` class implements the actions described above.

#### Attributes

* Two `Hand` objects for the player and dealer hands
* A `Deck` object for the deck to be used in the game

#### Constructor

* `public Blackjack()` uses the `reset()` method to prepare the `Deck` object for a new game


#### Methods
* `public Hand getPlayerHand()` returns the `Hand` object representing the player's hand
* `public Hand getDealerHand()` returns the `Hand` object representing the dealer's hand
* `public Deck getDeck()` retuns the current state of the object's `Deck`
* `public void reset()` instantiates a new `Deck` object and applies its `shuffle()` method
* `public void deal()` instantiates two `Hand` objects, assigning them to the appropriate player and dealer attributes. It then adds two `Card` objects from the `Deck` object to each `Hand` object
* `public boolean playerTurn()` applies the rules described above to the player's hand. It returns `true` if the value of the player's hand is less than or equal to 21 and false otherwise
* `public boolean dealerTurn()` applies the rules described above to the dealer's hand. It returns `true` if the value of the dealer's hand is less than or equal to 21 and false otherwise
* * `public String toString()` returns a well-formatted `String` representation of this `Blackjack` object

## Lamarckian Poker

Lamarckian poker is a two-player game where players attempt to construct the best poker hand by giving up cards in their hand to select what they perceive to be better cards from a pool towards the goal of building the best poker hand. 

The version implemented here is based on rules that are best described [here](https://boardgamegeek.com/blog/743/blogpost/17479/lamarckian-poker-a-surprising-diamond-in-the-rough), but with modifications that lend themselves well to random choices when taking turns.

In essence, each player is dealt four cards to begin with, and four cards are placed face up in a pool. 

At the beginning of each turn, each player chooses a card from their hand that they wish to sacrifice in order to obtain more desirable cards from the pool. The players show their card and the following actions take place:

* The player with the higher sacrificial card selects all cards with the same suit or rank from the pool and adds them to their hand. The sacrificial card is then placed in the discard pile.
* The second player does the same with their sacrificial card.
* Any unselected cards in the pool go into the discard pile.
* A new four-card pool is dealt from the deck.

If the deck does not have enough cards to create a four-card pool, the cards in the deck and discard pile are combined and shuffled, and game play continues.

The game continues until each player has at least 7 cards in their hand. At that point, each hand is evaluated for the best five-card poker hand that can be created, and that determines the winner of the game.

### `LamarckianPoker`

The `LamarckianPoker` class implements the start of a new game and the playing of a single turn.

#### Attributes

* Two `Hand` objects for the player and dealer hands
* A `Deck` object for the deck to be used in the game
* A `Hand` object for the pool
* A `Deck` object for the discard pile
* A `Random` object 

#### Constructors

* `public LamarckianPoker()` creates a new game using the `reset()` method

#### Methods

* `public Hand getPlayer1Hand()` returns the hand of the first player
* `public Hand getPlayer1Hand()` returns the hand of the second player
* `public Hand pool()` returns the hand representing the pool
* `public void reset(boolean newDeck)` creates a game with a new shuffled deck and discard pile
* `public void deal()` instantiates new `Hand` objects for each player and deals 4 `Card` objects from the deck to each player
* `public void makePool()` creates a `Hand` object and deals 4 `Card` objects from the deck to this object
* `public boolean turn()` implements the turn rules described above with the `Card` object from each player chosen randomly. The method returns `true` if the size of both player hand is less than 7 and `false` otherwise
* `public String toString()` returns a well-formatted `String` representation of this `LamarckianPoker` object

## Driver program

The driver program is contained in the `CardGameDriver` program. This program performs tests on both the `Blackjack` and `LamarckianPoker` classes. In doing so, it implicitly tests all other classes.

### Blackjack testing

This testing approach and the `Blackjack` and utility classes are based on [an approach](https://cs.colby.edu/courses/F17/cs231-labs/proj01.php) described by Caitrin Eaton for a Data Structures & Algorithms project at Colby College 

The driver program runs 10000 games of blackjack by instantiating a `Blackjack` object and then using its methods to deal cards, execute player and dealer turns, and evaluate the result. It records for each game whether the dealer wins, the player wins, or there is a tie (which is called a *push*). During the testing, when the deck has fewer than 10 cards, a new deck is used in the game. The number of dealer wins, player wins, and pushes is displayed.

#### Lamarckian Poker testing

The driver program runs one simulated Lamarckian Poker game. It does so by instantiating a `LamarckianPoker` object and dealing hands to the two players. It then has the object execute game turns until the `turn()` method indicates that the game is complete and the hands are ready to be evaluated. The evaluate of the hands and determination of a winner is not yet implemented.