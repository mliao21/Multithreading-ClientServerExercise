

//STUDENTS SHOULD ADD CLASS COMMENTS, METHOD COMMENTS, FIELD COMMENTS 
/**
 * Board class holds the logic of the game and displays
 * the layout of the board for each player's turn.
 * 
 * @author Melissa Liao
 */
public class Board implements Constants {
	
	private char theBoard[][];
	private int markCount;

	/**
	 * The constructor method sets a blank 
	 * tic-tac-toe board (3 by 3 matrix) at
	 * the beginning of the game.
	 */
	public Board() {
		markCount = 0;
		theBoard = new char[3][];
		for (int i = 0; i < 3; i++) {
			theBoard[i] = new char[3];
			for (int j = 0; j < 3; j++)
				theBoard[i][j] = SPACE_CHAR;
		}
	}

	/**
	 * Method retrieves the position mark 
	 * of each player's turn.
	 * 
	 * @param row represents row number from the board.
	 * @param col represents column number from the board.
	 * @return the position of the mark in the board.
	 */
	public char getMark(int row, int col) {
		return theBoard[row][col];
	}

	/**
	 * Method verifies if all 9 positions in the 
	 * board holds a mark.
	 * 
	 * @return whether the markCount has reached to 9.
	 */
	public boolean isFull() {
		return markCount == 9;
	}

	/**
	 * Method verifies if player X has win the game.
	 * It calls in the checkWinner method to verify.
	 * 
	 * @return true if last mark X fulfills the requirement
	 * to win. Otherwise, is false.
	 */
	public boolean xWins() {
		if (checkWinner(LETTER_X) == 1)
			return true;
		else
			return false;
	}

	/**
	 * Method verifies if player O has win the game.
	 * It calls in the checkWinner method to verify.
	 * 
	 * @return true if last mark O fulfills the requirement
	 * to win. Otherwise, is false.
	 */
	public boolean oWins() {
		if (checkWinner(LETTER_O) == 1)
			return true;
		else
			return false;
	}

	/**
	 * Method displays and updates the layout of the 
	 * board for each player's turn.
	 */
	public String display() {
		String display = "";
		display += displayColumnHeaders();
		display += addHyphens();
		for (int row = 0; row < 3; row++) {
			display += addSpaces();
			display += "    row " + row + ' ';
			for (int col = 0; col < 3; col++)
				display += "|  " + getMark(row, col) + "  ";
			display += "|\n";
			display += addSpaces();
			display += addHyphens();
		}
		return display;
	}

	/**
	 * Method sets each player's mark position in the
	 * board for every turn and updates its mark count.
	 * 
	 * @param row represents the row number from the 
	 * board in which the player chose
	 * 
	 * @param col represents the column number from 
	 * the board in which the player chose
	 * 
	 * @param mark represents the mark of the player, 
	 * i.e.: 'O' or 'X'
	 */
	public void addMark(int row, int col, char mark) {
		theBoard[row][col] = mark;
		markCount++;
	}

	/**
	 * Method clears the whole board with empty marks
	 * and clear its count.
	 */
	public void clear() {
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				theBoard[i][j] = SPACE_CHAR;
		markCount = 0;
	}

	/**
	 * Method verifies if there's a winner in each
	 * turn of the game. It checks whether there are
	 * 3 of the same mark across the board horizontally,
	 * vertically and diagonally.
	 * 
	 * @param mark represents the mark of the player
	 * that needs to be verified.
	 * 
	 * @return to the result of the player's according 
	 * to its mark. If the result is 1, then the player 
	 * has won the game.
	 */
	int checkWinner(char mark) {
		int row, col;
		int result = 0;
		
		// verifies if there are 3 consecutive mark across each row
		for (row = 0; result == 0 && row < 3; row++) {
			int row_result = 1;
			for (col = 0; row_result == 1 && col < 3; col++)
				if (theBoard[row][col] != mark)
					row_result = 0;
			if (row_result != 0)
				result = 1;
		}

		// verifies if there are 3 consecutive mark across each column
		for (col = 0; result == 0 && col < 3; col++) {
			int col_result = 1;
			for (row = 0; col_result != 0 && row < 3; row++)
				if (theBoard[row][col] != mark)
					col_result = 0;
			if (col_result != 0)
				result = 1;
		}
		
		// verifies if there are 3 consecutive mark going across
		// diagonally down from left to right
		if (result == 0) {
			int diag1Result = 1;
			for (row = 0; diag1Result != 0 && row < 3; row++)
				if (theBoard[row][row] != mark)
					diag1Result = 0;
			if (diag1Result != 0)
				result = 1;
		}
		
		// verifies if there are 3 consecutive mark going across
		// diagonally up from left to right
		if (result == 0) {
			int diag2Result = 1;
			for (row = 0; diag2Result != 0 && row < 3; row++)
				if (theBoard[row][3 - 1 - row] != mark)
					diag2Result = 0;
			if (diag2Result != 0)
				result = 1;
		}
		return result;
	}

	/**
	 * Method prints out the column header names
	 * of the board
	 */
	String displayColumnHeaders() {
		String display = "          ";
		for (int j = 0; j < 3; j++)
			display += "|col " + j;
		display += "|\n";
		return display;
	}

	/**
	 * Method draws the horizontal lines of the board
	 */
	String addHyphens() {
		String display = "          ";
		for (int j = 0; j < 3; j++)
			display += "+-----";
		display += "+\n";
		return display;
	}

	/**
	 * Method draws the vertical lines of the board
	 */
	String addSpaces() {
		String display = "          ";
		for (int j = 0; j < 3; j++)
			display += "|     ";
		display += "|\n";
		return display;
	}

}
