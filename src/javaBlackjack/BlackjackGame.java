package javaBlackjack;

//This contains the core game logic for the blackjack game 
public class BlackjackGame {
	
	//The deck of cards used in the game
	private Deck deck;
	//The dealer's hand of cards
	private Hand dealerHand;
	//The player's hand of cards
	private Hand playerHand;
	//A flag indicating whether the game is over
	private boolean gameOver;
	//The player's current wallet
	private int playerWallet;
	//The player's bet
	private int playerBet;
	
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
		//Initializes the wallet with 100
		this.playerWallet = 100;
		//Initializes the bet with 0
		this.playerBet = 0;
		
		//Deals the initial two cards for the player and the dealer
//		startGame();
	}
	
	//Initializes the game by dealing two cards to both the player and the dealer
	public void startGame(int betAmount) {
		
		playerBet = betAmount;
		playerWallet -= playerBet;
		System.out.println(playerWallet);
		
		//Sets the game over variable to false
		gameOver = false;
		
		//Clears the hands by creating a new Hand instance
		playerHand = new Hand();
		dealerHand = new Hand();
		
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
	
	
	//Restarts the game after each round
	public void restartGame() {
		//Sets the game over variable to false
				gameOver = false;
		//If the player runs out of money refills the wallet		
				if (playerWallet == 0) {
					System.out.println("You have gone bankrupt, play responsibly");
					playerWallet = 100;
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
		
		//Checks if there is a natural blackjack (Ace + 10 value card)
		boolean playerHasBlackjack = playerHand.isBlackjack();
		boolean dealerHasBlackjack = dealerHand.isBlackjack();
		
		//Checks if both have natural blackjacks
		if(playerHasBlackjack && dealerHasBlackjack) {
			//If both have blackjack it is a tie
			playerWallet += playerBet;
			System.out.println("tie player wallet: "+playerWallet);
			return "It's a Tie";
		} else if (playerHasBlackjack) {
			//If the player has a natural blackjack wins 3:2
			playerWallet += playerBet * 2.5;
			System.out.println("Player wins with a blackjack player wallet: "+ playerWallet);
			return "Player WINS with a BLACKJACK!!";
		} else if (dealerHasBlackjack) {
			System.out.println("Player losts player wallet: " + playerWallet);
			return "Dealer WINS with a BLACKJACK!!";
		}
		
		
		//If the player exceeds 21 the player busts and the dealer wins
		if(playerValue > 21) {
			System.out.println("player busts player wallet: " + playerWallet);
			return "Player BUSTS! Dealer WINS";
		}
		//If the dealer exceeds 21 the dealer busts and the player wins
		if(dealerValue > 21) {
			playerWallet += playerBet *2;
			System.out.println("dealer busts player wallet: " + playerWallet);
			return "Dealer BUSTS! Player WINS";
		}
		
		//If neither busts compare the hand's values to determine the winner (closer to 21)
		if(playerValue > dealerValue) {
			 playerWallet += playerBet * 2;
			 System.out.println("player wins player wallet: " + playerWallet);
			return "Player WINS";
		} else if (playerValue < dealerValue) {
			System.out.println("player lost player wallet :"+playerWallet);
			return "Dealer WINS";
		} else {
			playerWallet += playerBet;
			System.out.println("tie player wallet: "+playerWallet);
			return "It's a Tie";
		}
	}
	


	
	
	//Returns the player's hand
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
	
	//Returns the player's wallet
	public int getPlayerWallet() {
		return playerWallet;
	}
	
	//Return the player's bet
	public int getPlayerBet() {
		return playerBet;
	}
	
}
