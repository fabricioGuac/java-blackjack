package javaBlackjack;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


//This class is responsible for managing and displaying the GUI of the blackjack game
public class BlackjackGUI {

	//Main window for the game
	private JFrame frame;
	//Panel to display the game (cards...)
	private JPanel gamePanel;
	//Panel to display the buttons
	private JPanel buttonPanel;
	//Button for the player to hit
	private JButton hitButton;
	//Button for the player to stay
	private JButton stayButton;
	//Button to reset the game
	private JButton resetButton;
	//Text field to introduce the bet amount
	private JTextField betInput;
	//Button to confirm the bet
	private JButton confirmBetButton;
	
	//Instance of the BlackjackGame to manage the game logic
	private BlackjackGame game;
	
	//Flag indicating if the game is in the betting stage
	private boolean bettingStage;
	
	//Label to display the player wallet
	private JLabel walletLabel;
	
	
	//Constructor to initialize the GUI and the game
	public BlackjackGUI() {
		//Initializes a new BlackjackGame instance 
		game = new BlackjackGame();
		
		//Initializes the bettingStage flag as true
		bettingStage = true;
		
		//Sets up the main frame (window) with the title "Blackjack"
		frame = new JFrame("Blackjack");
		//Sets the window size 
		frame.setSize(1200, 750);
		//Closes the app when the window is closed
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Centers the window on the screen
		frame.setLocationRelativeTo(null);
		//Makes the size of the window static
		frame.setResizable(false);
		
		
		//Sets up the panel where the game graphics will be drawn
		gamePanel = new JPanel() {
            /**
			 * Unique ID for serialization compatibility (good practice to include)
			 */
			private static final long serialVersionUID = 1L;

			@Override
            public void paintComponent(Graphics g) {
				//Ensures the panel is cleared before drawing
                super.paintComponent(g);
                //Calls the draw game method
                drawGame(g);
            }
        };
        
    //Sets background color of the game panel ( dark green to simulate a blackjack table)
	gamePanel.setBackground(new Color(53, 101,77));
	
	//Creates the text field
	betInput = new JTextField(10);
	
	//Sets up the panels for buttons
	buttonPanel = new JPanel();
	//Creates buttons
	hitButton = new JButton("Hit");
	stayButton =  new JButton("Stay");
	resetButton = new JButton("Play again");
	confirmBetButton = new JButton("Confirm");
	
	//Initiates up the label for the wallet
	walletLabel = new JLabel("Wallet: "+ game.getPlayerWallet());
	
	//Hides the buttons initially 
	resetButton.setVisible(false);
	hitButton.setVisible(false);
	stayButton.setVisible(false);
	
	//Adds action listener for the hit button
	hitButton.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			//Performs the player's hit action (add a card)
			game.playerHit();
			//Checks if the game is over after the move
			checkGameOver();
			//Repaints the panel to update the view
			gamePanel.repaint();
		}
		
	});
	
	
	//Adds action listener for the stay button
	stayButton.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			//Performs the player's stay action (end turn) 
			game.playerStay();
			//Checks if the game is over after the move
			checkGameOver();
			//Repaints the panel to update the view
			gamePanel.repaint();
		}
		
	});
	
	
	//Adds action listener for the reset button
	resetButton.addActionListener(new ActionListener () {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			//Sets the bettingStage flag to true on restarts
			bettingStage = true;
			
			//Hides the buttons
			hitButton.setVisible(false);
			stayButton.setVisible(false);
			resetButton.setVisible(false);
			
			//Enables the hit and stay buttons
			hitButton.setEnabled(true);
			stayButton.setEnabled(true);
			
			//Shows the bet input and confirm button
		    betInput.setVisible(true);
		    confirmBetButton.setVisible(true);
		    
		    //Restarts the game
		    game.restartGame();
		    
		    //Updates the wallet
			System.out.println("Wallet: in reset "+game.getPlayerWallet());
			walletLabel.setText("Wallet: "+game.getPlayerWallet());
			
			//Repaints the panel to update the view
			gamePanel.repaint();
		}
		
	});
	
	
	confirmBetButton.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
	    	System.out.println(betInput.getText());
	        try {
	        	//Parses the bet input
	            int betAmount = Integer.parseInt(betInput.getText());
	            //Validates the bet amount input
	            if (betAmount >= 0 && betAmount <= game.getPlayerWallet()) {
	            	//Sets the bettingStage flag to false once the player confirms the bet
	                bettingStage = false;
	                //Hides the bet input and confirm button
	                betInput.setVisible(false);
	                confirmBetButton.setVisible(false);
	                //Shows the hit and stay buttons to start the game
	                hitButton.setVisible(true);
	                stayButton.setVisible(true);
	                //Starts the game
	                game.startGame(betAmount);
	                //Repaints the panel to update the view
	                gamePanel.repaint();
	                //Updates the wallet label
	    			System.out.println("Wallet: in confirm bet "+game.getPlayerWallet());
	    			walletLabel.setText("Wallet: "+game.getPlayerWallet());
	            } else {
	                //Shows a message to the user about invalid bet
	                JOptionPane.showMessageDialog(frame, "Please enter a valid bet within the wallet range.");
	            }
	        } catch (NumberFormatException ex) {
	            JOptionPane.showMessageDialog(frame, "Invalid bet. Please enter a number.");
	        }
	    }
	});

	//Adds the wallet label to the buttonPanel
	buttonPanel.add(walletLabel);
	//Aligns the wallet label to the left
	
	
	//Adds the text field to the button panel
	buttonPanel.add(betInput);
	
	//Adds buttons to the button panel
	buttonPanel.add(hitButton);
	buttonPanel.add(stayButton);
	buttonPanel.add(resetButton);
	buttonPanel.add(confirmBetButton);
	
	
	
	//Adds the game panel and the button panel to the main frame
	frame.add(gamePanel, BorderLayout.CENTER);
	frame.add(buttonPanel, BorderLayout.SOUTH);
	//Ensures the frame is visible to show the game
	frame.setVisible(true);
	
	}
	
	//Method to draw the game elements (cards, winner message) on the screen
	private void drawGame(Graphics g) {
		
				
		
		//Draws the player's cards
		for (int i = 0; i < game.getPlayerHand().getCards().size(); i++) {
			//Retrieves the card at the current index from the player's hand
			Card card  = game.getPlayerHand().getCards().get(i);
			//Retrieves the image associated with the card using it's image path
			Image cardImage = new ImageIcon(getClass().getResource(card.getImagePath())).getImage();
			//Draws the card image on the screen at the specified position adjusting for the index
			g.drawImage(cardImage, 20 + i * 115, 350, 110, 154, null);
		}
		
		
		
		//Draws the dealer's cards
		if(!stayButton.isEnabled()) {
			//If the player has not stayed show the hidden card
			Card hiddenCard = game.getDealerHand().getCards().get(0);
			Image hiddenCardImage = new ImageIcon(getClass().getResource(hiddenCard.getImagePath())).getImage();
			g.drawImage(hiddenCardImage, 20, 20, 110, 154, null);
		} else {
			//If the player has not stayed show the back of the card
			Image hiddenCardBackImage = new ImageIcon(getClass().getResource("/cards/BACK.png")).getImage();
	        g.drawImage(hiddenCardBackImage, 20, 20, 110, 154, null);
		}
		
		
		for (int i = 1; i < game.getDealerHand().getCards().size(); i++) {
			//Retrieves the card at the current index from the dealer's hand
			Card card = game.getDealerHand().getCards().get(i);
			//Retrieves the image associated with the card using its image path
			Image cardImage = new ImageIcon(getClass().getResource(card.getImagePath())).getImage();
			//Draws the card image on the screen at the specified position adjusting for the index
			g.drawImage(cardImage, 20 + i * 115, 20, 110, 154, null);
		}

		
		//Sets the font style and size for the message
		g.setFont(new Font("Arial", Font.BOLD, 30));
		//Sets the color for the message 
		g.setColor(Color.WHITE);
		//Displays the bet amount only during the game
		if (!game.isGameOver() && !bettingStage) {
	        g.drawString("Bet: $" + game.getPlayerBet(), 50, 650);
	    }
		
		//Displays the winner message if the game is over
		if(game.isGameOver()) {
			//Draws the winner message at the specified position on the screen
			g.drawString(game.checkWinner(), 200, 300);
			
			//Updates the wallet label
			System.out.println("Wallet: in game over "+game.getPlayerWallet());
			walletLabel.setText("Wallet: $"+game.getPlayerWallet());
		}
		
		//Displays the bet message if it is in the betting stage
		if(bettingStage) {
			//Sets the font style and size for the winner message
			g.setFont(new Font("Arial", Font.BOLD, 30));
			//Sets the color for the winner message 
			g.setColor(Color.WHITE);
			//Draws the winner message at the specified position on the screen
			g.drawString("Place a bet between $0 and $" + game.getPlayerWallet(),350, 330);
		}
		
		//Display the number of decks remaining
		g.drawString("Remaining deck: " + String.format("%.2f", game.getRemainingDecks()),850, 50);
		//Displays the running count 
		g.drawString("Running count: " + game.getRunningCount(),850, 100 );
	}
	
	//Method to check if the game is over
	private void checkGameOver() {
		if(game.isGameOver()) {
			//Disables the hit and stay buttons
			hitButton.setEnabled(false);
			stayButton.setEnabled(false);
			//Shows the reset button
			resetButton.setVisible(true);
		}
	}
}