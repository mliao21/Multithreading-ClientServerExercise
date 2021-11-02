

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
/**
 * Player class defines the element that a tic-tac-toe
 * player should have. A player should have a name, an opponent,
 * the mark it represents and needs a board to play. It also
 * contains methods to make a move and to play.
 * 
 * @author Melissa Liao
 */
public class Player {
	
	private String name;
	private Board theBoard;
	private Player opponent;
	private char mark;
	private Socket gameSocket;
	private PrintWriter socketOut;	
	private BufferedReader socketIn;

	/**
	 * Constructor method sets the name and mark
	 * of each player. There should only be 2 players called
	 * per one game of tic-tac-toe.
	 * 
	 * @param theSocket represents the socket that is going to communicate with the server
	 * @param mark represents the mark the player is going to be using
	 */
	public Player(Socket theSocket, char mark) {
		this.gameSocket = theSocket;
		this.mark = mark;
		try {
			socketIn = new BufferedReader(new InputStreamReader(gameSocket.getInputStream()));
			socketOut = new PrintWriter(gameSocket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Method is called by the referee and initiates the game
	 * with player X to make the first move
	 * 
	 * @throws IOException when the player places incorrect first move
	 */
	public void initiator() throws IOException {
		sendString("Referee started the game...");
		play();
	}

	/**
	 * Method calls in the player to make a move, update its mark in the board and check who is 
	 * the winner.
	 * 
	 * @throws IOException when the player places an incorrect move
	 */
	public void play() throws IOException {
		if (theBoard.xWins() == false && theBoard.oWins() == false && theBoard.isFull() == false) {
			sendString(theBoard.display());
			makeMove();
			theBoard.checkWinner(mark);
			opponent.play();
		}
		else if (theBoard.xWins() == true || theBoard.oWins() == true) {
			sendString(theBoard.display());
			sendString("THE GAME IS OVER: " + opponent.getName() + " is the winner!" + "\nGame Ended ...");
			sendString("QUIT");
			
		}
		else if (theBoard.isFull() == true) {
			sendString(theBoard.display());
			sendString("THE GAME IS OVER: Tied between " + name + " and " + opponent.getName() + "!\nGame Ended ...");
			sendString("QUIT");
		}
		
	}
	
	/**
	 * Method ask the player's desired position in the board. Player should input a 
	 * row and column number in the board to set their mark in the board. Player cannot input
	 * a taken space in the board and must be within the 3 by 3 matrix.
	 * 
	 * @throws IOException when player enters an invalid non-integer value or enters
	 * a taken space in the board.
	 */
	public void makeMove() throws IOException {
		int row = -1, col = -1;
		do {
			try {
				sendString(this.name + ", what row should your next " + this.mark + " be placed in? \0");
				row = Integer.parseInt(socketIn.readLine());
				sendString(this.name + ", what column should your next " + this.mark + " be placed in? \0");
				col = Integer.parseInt(socketIn.readLine());
				if (theBoard.getMark(row, col) != ' ') {
					sendString("\nRow and Column has been taken already. Please try again!\n");
				}
			} catch (NumberFormatException e) {
				sendString("You must enter an integer from 0 to 2. Please try again!");
				e.printStackTrace();
			} 		
		} while (theBoard.getMark(row, col) != ' ');
		theBoard.addMark(row, col, mark);
	}
	
	/**
	 * 
	 * Method prompts the players to enter their names
	 * before starting the game. It is communicated through
	 * the TicTacToeServer class
	 * 
	 */
	public void getPlayerName() {
		try {
			sendString("Please enter the name of the '" + mark + "' player: \0");
			name = socketIn.readLine();
			while (name == null) {
				sendString("Please try again: \0");
				name = socketIn.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * Helper method to send message to the client
	 * through a socket. It communicates with the
	 * TicTacToeServer class
	 * 
	 * @param toSend represents the string of messages that will be sent
	 */
	private void sendString(String toSend) {
		socketOut.println(toSend);
		socketOut.flush();
	}
	
	/**
	 * Method retrieves the name of the player
	 * 
	 * @return the name of the player
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Method sets the name of the player
	 * 
	 * @param name represents the name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Method retrieves the opponent of the player
	 * 
	 * @return the opponent
	 */
	public Player getOpponent() {
		return opponent;
	}
	

	/**
	 * Method sets the opponent of the player
	 * 
	 * @param opponent represents the opponent
	 */
	public void setOpponent(Player opponent) {
		this.opponent = opponent;
	}

	/**
	 * Method retrieves the board
	 * 
	 * @return to the board
	 */
	public Board getBoard() {
		return theBoard;
	}

	/**
	 * Method sets the board that the
	 * player is going to be playing.
	 * 
	 * @param theBoard represents the board
	 */
	public void setBoard(Board theBoard) {
		this.theBoard = theBoard;
	}

	/**
	 * Method retrieves mark of the player
	 * 
	 * @return the mark
	 */
	public char getMark() {
		return mark;
	}

	/**
	 * Method sets the mark of the player.
	 * 
	 * @param mark represents the character mark
	 * of the player, i.e.: 'X' or 'O'.
	 */
	public void setMark(char mark) {
		this.mark = mark;
	}

}
