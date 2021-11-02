

import java.io.*;

//STUDENTS SHOULD ADD CLASS COMMENTS, METHOD COMMENTS, FIELD COMMENTS 
/**
 * Game class runs the tic-tac-toe game app by setting the
 * name of the players. It connects them to the board and 
 * appoints to a referee to start and run the game.
 * 
 * @author Melissa Liao
 */
public class Game implements Runnable {

	private Board theBoard;
	private Referee theRef;
	
    /**
     * Constructor initializes a new board
     */
    public Game() {
        theBoard  = new Board();
	}
    
    /**
     * Methods sets the referee and appoints it
     * to set the board and its players
     * 
     * @param r represents the referee
     * @throws IOException for any invalid input by the user
     */
    public void appointReferee(Referee r) throws IOException {
        theRef = r;
        theRef.setBoard(theBoard);
        theRef.getxPlayer().setBoard(theBoard);
        theRef.getoPlayer().setBoard(theBoard);    	
    }

	@Override
	public void run() {
		try {// function runs and starts the game
			theRef.runTheGame();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
