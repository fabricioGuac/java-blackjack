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
	
	
	//Instance of the BlackjackGame to manage the game logic
	private BlackjackGame game;
	
	//Constructor to initialize the GUI and the game
	public BlackjackGUI() {
		//Initializes a new BlackjackGame instance 
		game = new BlackjackGame();
		
		//Sets up the main frame (window) with the title "Blackjack"
		frame = new JFrame("Blackjack");
		//Sets the window size 
		frame.setSize(1200, 750);
		//Closes the app when the window is closed
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Centers the window on the screen
		frame.setLocationRelativeTo(null);
		
		
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
	
	//Sets up the panels for buttons
	buttonPanel = new JPanel();
	//Creates buttons
	hitButton = new JButton("Hit");
	stayButton =  new JButton("Stay");
	resetButton = new JButton("Reset");
	
	//Hides the reset button 
	resetButton.setVisible(false);
	
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
			//Restarts the game
			game.startGame();
			//Enables the hit and stay buttons
			hitButton.setEnabled(true);
			stayButton.setEnabled(true);
			//Hides the reset button
			resetButton.setVisible(false);
			//Repaints the panel to update the view
			gamePanel.repaint();
		}
		
	});
	
	//Adds buttons to the button panel
	buttonPanel.add(hitButton);
	buttonPanel.add(stayButton);
	buttonPanel.add(resetButton);
	
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
		for (int i = 0; i < game.getDealerHand().getCards().size(); i++) {
			//Retrieves the card at the current index from the dealer's hand
			Card card = game.getDealerHand().getCards().get(i);
			//Retrieves the image associated with the card using its image path
			Image cardImage = new ImageIcon(getClass().getResource(card.getImagePath())).getImage();
			//Draws the card image on the screen at the specified position adjusting for the index
			g.drawImage(cardImage, 20 + i * 115, 20, 110, 154, null);
		}
		
		//Displays the winner message if the game is over
		if(game.isGameOver()) {
			//Sets the font style and size for the winner message
			g.setFont(new Font("Arial", Font.BOLD, 30));
			//Sets the color for the winner message 
			g.setColor(Color.WHITE);
			//Draws the winner message at the specified position on the screen
			g.drawString(game.checkWinner(),200, 300);
		}
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