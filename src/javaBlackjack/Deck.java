package javaBlackjack;

import java.util.ArrayList;
import java.util.Collections;


//The Deck class represents the deck of 52 standards playing cards
public class Deck {
	
	//ArrayList of card objects that make up the deck
	private ArrayList<Card> deck;
	
	//Constructor that initializes and shuffles the deck when a Deck object is created
	public Deck() {
		buildDeck();
		shuffleDeck();
	}
	
	
	//Creates a standard deck of 52 cards
	private void buildDeck() {
		deck = new ArrayList<>();
		//Card vales
		String[] values = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
		//Card types (Clubs, Diamonds, Hearts, Spades)
		String[] types = {"C", "D", "H", "S"};
		
		
		//Nested loop to create cards and add them to the deck
		for (int i =0; i < types.length; i++) {
			for (int j = 0;j < values.length; j++) {
				Card card = new Card(values[j], types[i]);
				deck.add(card);
			}
		}
	}
	
	//Shuffles the deck of cards to randomize the numbers
	private void shuffleDeck() {
		//Randomly shuffles the deck using java's build-in method
		Collections.shuffle(deck);
	}
	
	//Deals the top card from the deck
	public Card dealCard() {
		//Removes the last card from the deck simulating a top card deal
		return deck.remove(deck.size() -1);
	}
	
}
