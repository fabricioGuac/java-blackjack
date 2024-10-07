package javaBlackjack;

import java.util.ArrayList;


//The Hand class represents the card hand
public class Hand {
	
	//ArrayList of card objects that make up the hand
	private ArrayList<Card> cards;
	//Variable to keep track of the total hand value
	private int totalValue;
	//Variable to keep track of the ace count
	private int aceCount;
	
	
	//Constructor for the hand class. Initializes the hand
	public Hand() {
		//Initializes the list of cards 
		cards = new ArrayList<>();
		//Initial total value is 0
		totalValue = 0;
		//Initial ace count is 0
		aceCount = 0;
	}
	
	// Adds a card to the hand and tracks the total value
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
	
	//Returns the hand card list
	public ArrayList<Card> getCards(){
		return cards;
	}
	
}
