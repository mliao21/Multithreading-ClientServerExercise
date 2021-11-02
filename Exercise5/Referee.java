

import java.io.IOException;

/**
 * Referee class starts the game by setting the players
 * according to its mark. It sets each player's opponent
 * and connects the players to the same board.
 * 
 * @author Melissa Liao
 */
public class Referee {
	
	private Player xPlayer;
	private Player oPlayer;
	private Board theBoard;

	/**
	 * Method is called in Game class to start the game
	 * by the referee and sets each player's opponent. It
	 * prompts both players to input their names and calls
	 * player X to initiate the game first.
	 * 
	 * @throws IOException when player enters and invalid row
	 * and column number or any non-integer value.
	 */
	public void runTheGame() throws IOException {
		xPlayer.setOpponent(oPlayer);
		oPlayer.setOpponent(xPlayer);
		
		xPlayer.getPlayerName();
		oPlayer.getPlayerName();
		
		xPlayer.initiator();
	}

	/**
	 * Method retrieves xPlayer
	 * 
	 * @return to player X
	 */
	public Player getxPlayer() {
		return xPlayer;
	}

	/**
	 * Method sets xPlayer as player X
	 * 
	 * @param xPlayer represents the player that
	 * uses mark X
	 */
	public void setxPlayer(Player xPlayer) {
		this.xPlayer = xPlayer;
	}

	/**
	 * Method retrieves oPlayer
	 * 
	 * @return to player O
	 */
	public Player getoPlayer() {
		return oPlayer;
	}

	/**
	 * Method sets oPlayer as player O
	 * 
	 * @param oPlayer represents the player that
	 * uses mark O
	 */
	public void setoPlayer(Player oPlayer) {
		this.oPlayer = oPlayer;
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
	 * Method sets the board
	 * 
	 * @param theBoard represents the board
	 */
	public void setBoard(Board theBoard) {
		this.theBoard = theBoard;
	}

}
