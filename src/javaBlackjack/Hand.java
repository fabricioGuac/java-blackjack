package javaBlackjack;

import java.util.ArrayList;


//The Hand class represents the player's card hand
public class Hand {
	
	//ArrayList of card objects that make up the players hand
	private ArrayList<Card> cards;
	//Variable to keep track of the player's total hand value
	private int totalValue;
	//Variable to keep track of the player's ace count
	private int aceCount;
	
	
	//Constructor for the hand class. Initializes the player's hand
	public Hand() {
		//Initializes the list of cards 
		cards = new ArrayList<>();
		//Initial total value is 0
		totalValue = 0;
		//Initial ace count is 0
		aceCount = 0;
	}
	
	// Adds a card to the player's hand and tracks the total value
	public void addCard(Card card) {
		cards.add(card);
		totalValue += card.getValue();
		if(card.isAce()) {
			aceCount++;
		}
		//Adjusts the total value accounting for the aces
		adjustForAces();
	}
	
	//Adjusts the total value accounting for the aces
	private void adjustForAces() {
		// While total value is over 21 and there's an Ace counted as 11, adjust value
		while (totalValue > 21 && aceCount > 0) {
			//Adjusts value from 11 to 1
			totalValue -= 10;
			//Decrement ace count 
			aceCount--;
		}
	}
	
	//Returns the total value
	public int getTotalValue() {
		return totalValue;
	}
	
	//Returns the player's hand
	public ArrayList<Card> getCards(){
		return cards;
	}
	
}
