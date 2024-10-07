package javaBlackjack;

//This contains the core game logic for the blackjack game 
public class BlackjackGame {
	
	//The deck of cards used in the game
	private Deck deck;
	//The dealer's hand of cards
	private Hand dealerHand;
	//The player's hand of cards
	private Hand playerHand;
	// A flag indicating whether the game is over
	private boolean gameOver;
	
	//Constructor for the BlackjackGame class
	public BlackjackGame() {
		//Initializes a new shuffled deck of cards
		this.deck = new Deck();
		//Initializes the dealer's hand
		this.dealerHand = new Hand();
		//Initializes the player's hand
		this.playerHand = new Hand();
		//The game starts with the game over flag set to false
		this.gameOver = false;
		
		//Deals the initial two cards for the player and the dealer
		startGame();
	}
	
	//Initializes the game by dealing two cards to both the player and the dealer
	public void startGame() {
		//Deals two cards to the player
		playerHand.addCard(deck.dealCard());
		playerHand.addCard(deck.dealCard());
		
		//Deals two cards to the dealer
		dealerHand.addCard(deck.dealCard());
		dealerHand.addCard(deck.dealCard());
	}
	
	//Action for the player to hit (take an additional card)
	public void playerHit() {
		if(!gameOver) {
			//Deals one more card to the player hand
			playerHand.addCard(deck.dealCard());
			//If the player's total exceeds 21 the player busts and the game ends 
			if(playerHand.getTotalValue() > 21) {
				gameOver = true;
			}
		}
	}
	
	//Action for the player to stay (stop taking cards)
	public void playerStay() {
		if(!gameOver) {
			//Dealer keeps drawing cards until their hand value is at least 17
			while (dealerHand.getTotalValue() < 17) {
				dealerHand.addCard(deck.dealCard());
			}
			//After the dealer finishes drawing the game ends
			gameOver = true;
		}
	}
	
	
	//Determines the result of the game by comparing the player's and dealer's hand values
	public String checkWinner() {
		int playerValue = playerHand.getTotalValue();
		int dealerValue = dealerHand.getTotalValue();
		
		//If the player exceeds 21 the player busts and the dealer wins
		if(playerValue > 21) {
			return "Player BUSTS! Dealer WINS";
		}
		//If the dealer exceeds 21 the dealer busts and the player wins
		if(dealerValue > 21) {
			return "Dealer BUSTS! Player WINS";
		}
		
		//If neither busts compare the hand's values to determine the winner (closer to 21)
		if(playerValue > dealerValue) {
			return "Player WINS";
		} else if (playerValue < dealerValue) {
			return "Dealer WINS";
		} else {
			return "It's a Tie";
		}
	}
	
	//Return's the player's hand
	public Hand getPlayerHand() {
		return playerHand;
	}
	
	//Returns the dealer's hand 
	public Hand getDealerHand() {
		return dealerHand;
	}
	
	//Returns whether the game has ended
	public boolean isGameOver() {
		return gameOver;
	}
}
