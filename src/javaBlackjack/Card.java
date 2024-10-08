package javaBlackjack;

//The Card class represents a playing card used in the blackjack game
public class Card {
	String value;
	String type;
	
	
	//Constructor for the Card class
	public Card(String value, String type) {
		this.value = value;
		this.type = type;
	}
	
	//Returns a string representation of the card value
	public String toString() {
		return value + '-' + type;
	}
	
	//Returns the numeric value of the card in the context of jackblack
	public int getValue() {
		
		//Checks if the value is Ace, Queen, Jack or King
		if("AJQK".contains(value)) {
			//If the value is Ace returns 11 otherwise returns 10
			return value.equals("A") ? 11 : 10;
		}
		//Parses the numeric value of the number card
		return Integer.parseInt(value);
	}
	
	
	//Checks if the card is an Ace
	public boolean isAce() {
		return value.equals("A");
	}
	
	//Returns the filepath of the image associated with the card
	public String getImagePath() {
		return "/cards/" + toString() + ".png";
	}
}
