/*
Micah Alummoottil 3/31/25
*/
import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Collections;
import java.util.Arrays;


interface Shufflable {
    void shuffle();
}

interface PlayableEntity {
    void takeTurn();
}

abstract class GamePiece {
    abstract void play();
}

class Card extends GamePiece implements Comparable<Card> {
    private final char rank;
    private final char suit;

    public Card(char rank, char suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public char getRank() {
        return rank;
    }

    @Override
    public void play() {
        // Implement how a card is played
        System.out.println("Playing " + rank + suit); //message to print card played
    }

    @Override
    public int compareTo(Card other) {
        // Implement comparison logic based on rank
        //create a helper to convert them to ints so we can compare
        int val1 = compareHelper(rank);
        int val2 = compareHelper(other.rank);
        if (val1 > val2) {
            return 1; //return a 1 if frst card is higher
        }
        else if (val1 < val2) {
            return -1; //return a -1 if second card is higher
        }
        else {
            return 0; // 0 will indicate a tie
        }
    }
    public int compareHelper(char rank) { //helper
        switch (rank) {
            case 'A': 
                return 1;
            case 'K': 
                return 13;
            case 'Q': 
                return 11;
            case 'J': 
                return 12;
            case 'T': 
                return 10;
            default: 
                return Character.getNumericValue(rank);
        }
    }


    @Override
    public String toString() {
        return rank + "-" + suit;
    }
}

class Deck implements Shufflable {
    private final List<Card> cards = new ArrayList<>();

    public Deck() {  // Initialize deck with all 52 cards
        char[] suits = {'C', 'D', 'H', 'S'};
//create suits and cases for each one then add
        for (char suit : suits) {
            for (int i = 1; i < 14; i++) {
                switch (i) {
                    case 1:
                        cards.add(new Card('A', suit));
                        break;
                    case 10:
                        cards.add(new Card('T', suit));
                        break;
                    case 11:
                        cards.add(new Card('Q', suit));
                        break;
                    case 12:
                        cards.add(new Card('J', suit));
                        break;
                    case 13:
                        cards.add(new Card('K', suit));
                        break;
                    default:
                        cards.add(new Card((char) ('0' + i), suit)); //converts num to char
                        break;
                }
            }
        }
        shuffle(); //shuffle deck
    }

    @Override
    public void shuffle() {
        // Implement shuffle logic
        Collections.shuffle(cards);
    }

    public List<Card> splitDeck() {
        // Return half of the deck
        ArrayList<Card> half1 = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            half1.add(cards.get(i)); //first half
        }
        return half1;
    }

    public List<Card> getRemainingHalf() {
        ArrayList<Card> half2 = new ArrayList<>();
        for (int i = 26; i < 52; i++) {
            half2.add(cards.get(i)); //second half
        }
        return half2;
    }
}

class Player implements PlayableEntity {
    private final Queue<Card> hand;

    public Player(List<Card> cards) {
        hand = new LinkedList<>(cards);
    }

    public boolean hasCards() {
        return !hand.isEmpty();
    }

    public Card drawCard() {
        // Implement logic to draw a card
        //remove it from their hand then return the card
        if (hasCards() == false) {
            return null;
        }
        Card toReturn = hand.remove();
        return toReturn;
    }

    public void collectCards(List<Card> wonCards) {
        // Implement logic to collect won cards
        hand.addAll(wonCards); //this will add all won cards to the back of our hand
    }

    public int getCardCount() {
        return hand.size();
    }

    @Override
    public void takeTurn() {
        // Placeholder for player actions
    }
}

class WarGame {
    private final Player player1;
    private final Player player2;

    public WarGame() {
        Deck deck = new Deck();
        player1 = new Player(deck.splitDeck());
        player2 = new Player(deck.getRemainingHalf());
    }

    public void playGame() {
        while (player1.hasCards() && player2.hasCards()) {
            playRound();
        }
        declareWinner();
    }

    private void playRound() {
        // Implement round logic
        //check if player has cards
        if (player1.hasCards() == false || player2.hasCards() == false) {
            return;
        }
        //first thing is drawing cards for both players
        Card card1 = player1.drawCard();
        Card card2 = player2.drawCard();
        //print messages for playing cards
        System.out.println("Player 1 ");
        card1.play();
        System.out.println("Player 2 ");
        card2.play();
        //create pile for cards to be won
        List<Card> toAdd = new ArrayList<>();
        toAdd.add(card1);
        toAdd.add(card2);
        //then compare the cards
        int res = card1.compareTo(card2);
        //if our res = 1 then p1 won, -1 p2 won, 0 it was a tie
        if (res == 1) {
            System.out.println("Player 1 wins the round");
            player1.collectCards(toAdd);
        }
        else if (res == -1) {
            System.out.println("Player 2 wins the round");
            player2.collectCards(toAdd);
        }
        else {
            //pass in warpile
            System.out.println("We have a war");
            handleWar(toAdd);
        }

        //for clarity purpose after playing a round
        System.out.println("Player 1 " + player1.getCardCount() + " cards");
        System.out.println("Player 2 " + player2.getCardCount() + " cards");
        System.out.println("------------");
    }

    private void handleWar(List<Card> warPile) {
        // Implement war scenario
        //each player needs to draw 4 cards
        //pick the highest rank and the winner gets all cards
        //if equal war again
        List<Card> warCards1 = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Card toAdd = player1.drawCard(); //create a pile for player 1 of face down cards
            if (toAdd == null) { //if they run out of cards, game over
                declareWinner();
                player2.collectCards(warPile);
                return;
            }
            warCards1.add(toAdd);
            warPile.add(toAdd);
            System.out.println("Player 1 places a face down card ");
        }
        //for player2
        List<Card> warCards2 = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Card toAdd = player2.drawCard();
            if (toAdd == null) { //create a pile for player 2 of face down cards
                declareWinner();//if they run out of cards, game over
                player1.collectCards(warPile);
                return;
            }
            warCards2.add(toAdd);
            warPile.add(toAdd);
            System.out.println("Player 2 places a face down card ");
        }
        //now draw the face up card and compare
        Card faceUp1 = player1.drawCard();
        if (faceUp1 == null) {
            declareWinner();//if they run out of cards, game over
            player2.collectCards(warPile);
            return;
        }
        warPile.add(faceUp1);
        System.out.println("Player 1 ");
        faceUp1.play();
        Card faceUp2 = player2.drawCard();
        if (faceUp2 == null) {//if they run out of cards, game over
            player1.collectCards(warPile);
            declareWinner();
            return;
        }
        warPile.addFirst(faceUp2);
        System.out.println("Player 2 ");
        faceUp2.play();
        //now we can compare the face up cards
        int res = faceUp1.compareTo(faceUp2);
        //shuffle and add the cards to warPile which we will add to the winners hand
        Collections.shuffle(warPile);
        if (res == 1) {
            System.out.println("Player 1 wins war"); //p1 win
            player1.collectCards(warPile);
        }
        else if (res == -1) {
            System.out.println("Player 2 wins war"); //p2 win
            player2.collectCards(warPile);
        }
        else {
            System.out.println("Another war"); //war
            handleWar(warPile);
        }
    }

    private void declareWinner() {
        if (player1.getCardCount() > player2.getCardCount()) {
            System.out.println("Player 1 Wins the game!");
        }
        else { //figure out winner based on who has more cards (snce one of them will only have 1/0)
            System.out.println("Player 2 Wins the game!");
        }
    }
    
    public static void main(String[] args) {
        // there is no need to change the main method. 
        // all work is in the methods above.
        WarGame game = new WarGame();
        game.playGame();
    }
}