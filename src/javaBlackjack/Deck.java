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
	
	
	//Creates a standard deck with 8 sets of 52 cards to simulate a real casino scenario
	private void buildDeck() {
		deck = new ArrayList<>();
		//Card values
		String[] values = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
		//Card types (Clubs, Diamonds, Hearts, Spades)
		String[] types = {"C", "D", "H", "S"};
		
		//Loop to create 8 decks
		for (int d =0; d < 8; d++) {
		//Nested loops to create cards and add them to the deck
		for (int i =0; i < types.length; i++) {
			for (int j = 0;j < values.length; j++) {
				Card card = new Card(values[j], types[i]);
				deck.add(card);
			}
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
		
		//Checks if the deck is empty
		if(deck.isEmpty()) {
			System.out.println("Using a new deck of cards");
			//Builds a new deck and shuffles it
			buildDeck();
			shuffleDeck();
		}
		
		//Removes the last card from the deck simulating a top card deal
		return deck.remove(deck.size() -1);
	}
	
	//Returns the remaining decks
	public double getRemainingDecks() {
		return (double) deck.size() /52;
	}
	
}
