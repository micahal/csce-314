/*
--ToDo: Fill this in:
Micah Alummoottil
3/28
*/

import java.util.ArrayList;
import java.util.List;

public class myCards {
    
    public static ArrayList<String> buildDeck() {
        String[] suits = {"C", "D", "H", "S"};
        ArrayList<String> deck = new ArrayList<>(); //create suits and cases for each one then add
        for (String suit : suits) {
            for (int i = 1; i < 14; i++) {
                switch (i) {
                    case 1:
                        deck.add("A" + suit);
                        break;
                    case 10:
                        deck.add("T-" + suit);
                        break;
                    case 11:
                        deck.add("Q-" + suit);
                        break;
                    case 12:
                        deck.add("J-" + suit);
                        break;
                    case 13:
                        deck.add("K-" + suit);
                        break;
                    default:
                        deck.add(i + "-" + suit);
                        break;
                }
            }
        }
        return deck;
    }
    public static ArrayList<String> buildDeck(int j) {
        ArrayList<String> jokerDeck = buildDeck();
        for (int i = 0; i < j; i++) { //add joker
            jokerDeck.add("Joker");
        }
        return jokerDeck;
        
    }
    public static ArrayList<String> buildDeck(char p) {
        String[] suits = {"C", "D", "H", "S"};
        ArrayList<String> deck = new ArrayList<>(); //create suits and cases for each one then add
        for (String suit : suits) {
            for (int i = 9; i < 15; i++) {
                switch (i) {
                    case 10:
                        deck.add("A" + suit);
                        deck.add("A" + suit);
                        break;
                    case 11:
                        deck.add("T-" + suit);
                        deck.add("T-" + suit);
                        break;
                    case 12:
                        deck.add("Q-" + suit);
                        deck.add("Q-" + suit);
                        break;
                    case 13:
                        deck.add("J-" + suit);
                        deck.add("J-" + suit);
                        break;
                    case 14:
                        deck.add("K-" + suit);
                        deck.add("K-" + suit);
                        break;
                    default:
                        deck.add(i + "-" + suit);
                        deck.add(i + "-" + suit);
                        break;
                }
            }
        }
        return deck;
    }
}
