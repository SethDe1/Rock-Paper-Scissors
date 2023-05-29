package RockPaperScissors;

import java.awt.*;
import java.io.*;
import java.net.*;
import javax.swing.*;

// Class that the setup and game will played in
public class GameServer {

	private ServerSocket socket;		
	private boolean active;				
	private ServerGUI DisplayServerGUI;
	

	public GameServer() throws Exception {

		// Declaring new ServerGUI Object
		DisplayServerGUI = new ServerGUI();
		DisplayServerGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		// Setting New Socket to port 8083
		socket = new ServerSocket(8083);


		// Determines if the server is running
		if (!socket.isClosed())
			active = true;

		// Display current status of server
		DisplayServerGUI.status.setText("Server started - Ready for Clients to connect!!");
		DisplayServerGUI.status.setForeground(Color.GREEN);

	}

	// Main class to set up the rock paper scissors game and players
	public static void main(String[] args) throws Exception {

		// Displays the GUI and Shows if the server has been connected
		GameServer GameServer = new GameServer();


		try {
			while (true) {
				if (GameServer.active) {

					// Setting Game Object
					Game game = new Game();

					// Sets both players choice to NULL so no duplicate responses
					game.player1choice = "ZERO";
					game.player2choice = "ZERO";

					// Ensure both players are connected
					Game.Player player1 = game.new Player(GameServer.socket.accept(), 1);
					Game.Player player2 = game.new Player(GameServer.socket.accept(), 2);

					// Starts the game for each player
					player1.start();
					player2.start();
				}
			}	
		} 
		finally 
		{
			// Close the socket from any new connections
			GameServer.socket.close();
		}
	}
}

// Class that takes in the choices of each player and determined the winner
class Game {

	// The current player
	Player currentplayer;

	// String setting for the Choice of Player 1 & 2
	public String player1choice;
	public String player2choice;

	// Determine if the game has a winner
	public int getWinner() {

		if ((player1choice.equals("PLAYER_CHOICE_ROCK")  && player2choice.equals("PLAYER_CHOICE_SCISSORS")))
			{
				return 1;
			}
		else if ((player1choice.equals("PLAYER_CHOICE_PAPER") && player2choice.equals("PLAYER_CHOICE_ROCK")))
			{
			 	return 1;
			}
		else if ((player1choice.equals("PLAYER_CHOICE_SCISSORS") && player2choice.equals("PLAYER_CHOICE_PAPER")))
			{
				return 1;
			}

		else if ((player2choice.equals("PLAYER_CHOICE_ROCK") && player1choice.equals("PLAYER_CHOICE_SCISSORS")))
			{
				return 2;
			}
		else if((player2choice.equals("PLAYER_CHOICE_PAPER") && player1choice.equals("PLAYER_CHOICE_ROCK")))
			{
				return 2;
			}
		else if((player2choice.equals("PLAYER_CHOICE_SCISSORS") && player1choice.equals("PLAYER_CHOICE_PAPER"))) 
			{
				return 2;
			}
		else 
			{
				return 0;
			}
	}

	
	// Method that takes in the Choice of Player #1
	public boolean playerOneChoice () {
		return player1choice.startsWith("PLAYER_CHOICE_");
	}
	
	// Method that takes in the Choice of Player #2
	public boolean playerTwoChoice() 
	{
		return player2choice.startsWith("PLAYER_CHOICE_");
	}

	// Begin creating threads for players
	class Player extends Thread {

		// Set up player/opponent values and input/output
		int player;
		Socket socket;
		BufferedReader input;
		PrintWriter output;

		// Method to Start Socket for Player Connection
		public Player(Socket socket, int number) {

			// Set socket and player number values
			this.socket = socket;
			this.player = number;

			// Setting up the input/output stream
			try
			{
				input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				output = new PrintWriter(socket.getOutputStream(), true);
				output.println("NEW_" + number);
			} 

			// Error checking
			catch (Exception e) 
			{
				e.printStackTrace();
			}

		}

		// Determine if players have selected their choice
		public boolean setChoice(Player player, String choice) {

			// Set player 1 choice
			if (player.player== 1)
				player1choice = choice;

			// Set player 2 choice
			else
				player2choice = choice;

			return true;
		}
		// Process for the thread when it is running
		public void run() {
			try {

				// Tells the player to start making a choice
				output.println("START");

				// Gets the choice from the player
				String choice = input.readLine();

				// Gets commands from clients and processes
				while (true) {	
					if (choice != null) {

						// If the choice is not "QUIT"
						if (choice.startsWith("PLAYER_CHOICE_"))
						{

							// Sets the player's choice
							setChoice(this, choice);

							if (playerOneChoice() && playerTwoChoice())
							{

								if (getWinner() > 0) {
									if (getWinner() == this.player) 
									{
										output.println("WIN");
										break;
									}

									else if (getWinner() != this.player)
									{
										output.println("DEFEAT");
										break;
									}
								}
								else 
								{
									output.println("TIE");
									break;
								}
							}
						}

						// Close socket if game ends
						else if (choice.startsWith("QUIT")) 
						{
							this.socket.close();
							return;
						} 
					} 
				}
			} catch (Exception e) 
			{
				e.printStackTrace();
			} finally {
				try {socket.close();} catch (IOException e) {}
			}
		} 
	}
}