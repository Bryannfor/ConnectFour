package model;

import java.util.ArrayList;
import exception.ColumnFullException;
import exception.IllegalMoveException;
import model.Token.Winner;

public class Bord extends GameObject{
	private static int col = 7;
	private static int row = 6;
	private Token.Color winningColor;
	public static Cell[][] bord = new Cell[row][col];

	/**
	 * initialises board and lager
	 */
	public Bord() {
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				bord[i][j] = null;
			}
		}

		lager = null;
		lager = new ArrayList<Token>();
		for (int i = 0; i < row * col; i++) {
			if (i < 21) {
				lager.add(c.getRed());
			} else {
				lager.add(c.getYellow());
			}
		}

		countSaved = 0;
		countLoaded = 0;
		countRestarted = 0;
		wrongPlayer = 0;
		gameStand = new ArrayList<>();
		test = new ArrayList<String>();
		saved = new Cell[42];

	}

	/**
	 * gets the number of rows of a board
	 * 
	 * @return row
	 */
	public static int getRow() {
		return row;
	}


	/**
	 * checks if there's still a token in Lager
	 * 
	 * @param farbe
	 * @return true/false
	 */
	public boolean hasToken(Token.Color farbe) {
		boolean hasColor = false;
		for (int i = 0; i < lager.size(); i++) {
			if (hasColor == false) {
				if (lager.get(i).getFarbe() == farbe) {
					hasColor = true;
					return hasColor;
				}
			}
		}
		return hasColor;
	}

	/**
	 * gets a token from the Lager
	 * 
	 * @param farbe
	 * @return playedColor
	 */
	public Token getToken(Token.Color farbe) {
		boolean hasToken = hasToken(farbe);
		Token playedColor = null;
		if (hasToken == true) {
			for (int i = 0; i < lager.size(); i++) {
				if (lager.get(i).getFarbe() == farbe) {
					return lager.remove(i);
				}
			}
		}
		return playedColor;
	}

	/**
	 * gets the colour of a cell in the board
	 * 
	 * @param row
	 * @param column
	 * @return colour
	 */
	public Token.Color getTokenColor(int row, int column) {
		if (bord[row][column] != null) {
			return bord[row][column].getTokenColor().getFarbe();
		} else {
			return null;
		}
	}

	/**
	 * test and return winner colour
	 * 
	 * @return winner
	 */
	public Winner testVictory() {

		Winner someoneWon = Winner.NONE;
		if (isColumnVictory(c.getRed().getFarbe()) == true || isRowVictory(c.getRed().getFarbe()) == true
				|| isDiagonalVictory(c.getRed().getFarbe()) == true) {
			if (winningColor.toString() == Winner.RED.toString()) {
				someoneWon = Winner.RED;
				return someoneWon;
			}
		} else if (isColumnVictory(c.getYellow().getFarbe()) == true || isRowVictory(c.getYellow().getFarbe()) == true
				|| isDiagonalVictory(c.getYellow().getFarbe()) == true) {
			if (winningColor.toString() == Winner.YELLOW.toString()) {
				someoneWon = Winner.YELLOW;
				return someoneWon;
			}
		} else if (isTie() == true) {
			someoneWon = Winner.TIE;
			return someoneWon;
		}
		return someoneWon;
	}

	/**
	 * checks if there is a tie
	 * 
	 * @return true/false
	 */
	private boolean isTie() {
		if ((hasToken(c.getRed().getFarbe()) == false && hasToken(c.getYellow().getFarbe()) == false)
				|| lager.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * checks if there is a column winnningStreak.
	 * 
	 * @param color
	 * @return true/false
	 */
	private boolean isColumnVictory(Token.Color color) {
		boolean someoneWon = false;
		for (int i = 0; i < col; i++) {
			int winningStreak = 4;
			for (int j = 0; j < row; j++) {
				if (bord[j][i] == null) {
					winningStreak = 4;
				} else if (bord[j][i] != null) {
					if (bord[j][i].getTokenColor().getFarbe() == color) {
						winningStreak--;
						if (winningStreak == 0) {
							someoneWon = true;
							winningColor = color;
							break;
						}
					} else {
						winningStreak = 4;
					}				
				}
			}

		}

		return someoneWon;
	}
	

	/**
	 * checks if there is a row winningStreak
	 * 
	 * @param color
	 * @return true/false
	 */
	private boolean isRowVictory(Token.Color color) {
		boolean someoneWon = false;
		for (int i = 0; i < row; i++) {
			int winningStreak = 4;
			for (int j = 0; j < col; j++) {
				if (bord[i][j] == null) {
					winningStreak = 4;
				} else if (bord[i][j] != null) {
					if (bord[i][j].getTokenColor().getFarbe() == color) {
						winningStreak--;
						if (winningStreak == 0) {
							someoneWon = true;
							winningColor = color;
							break;
						}
					} else {
						winningStreak = 4;
					}
				}
			}
		}
		return someoneWon;
	}

	/**
	 * checks if there is a diagonal winningStreak
	 * 
	 * @param color
	 * @return true/false
	 */
	private boolean isDiagonalVictory(Token.Color color) {
		boolean someoneWon = false;
		// diagonalRightCheck
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				int winningStreak = 4;
				int rowIndex = i;
				int colIndex = j;
				do {
					if (bord[rowIndex][colIndex] != null) {
						if (bord[rowIndex][colIndex].getTokenColor().getFarbe() == color) {
							winningStreak--;
							if (winningStreak == 0) {
								someoneWon = true;
								winningColor = color;
								break;
							}
						} else {
							winningStreak = 4;
						}
					} else {
						winningStreak = 4;
					}
					colIndex++;
					rowIndex++;
				} while (rowIndex < row && colIndex < col);
			}
		}

//		diagonalLeftCheck
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				int winningStreak = 4;
				int rowIndex = i;
				int colIndex = j;
				do {
					if (bord[rowIndex][colIndex] != null) {
						if (bord[rowIndex][colIndex].getTokenColor().getFarbe() == color) {
							winningStreak--;
							if (winningStreak == 0) {
								someoneWon = true;
								winningColor = color;
								break;
							}
						} else {
							winningStreak = 4;
						}
					} else {
						winningStreak = 4;
					}
					rowIndex++;
					colIndex--;
					if (colIndex < 0) {
						break;
					}
				} while (rowIndex < row && colIndex < col);
			}
		}

		return someoneWon;
	}

	/**
	 * Drops a token in the board
	 * 
	 * @param farbe
	 * @param columnIndex
	 * @exception ColumnFullException, IllegalMoveException
	 * 
	 */
	public void dropToken(Token farbe, int columnIndex) {
		Cell cb = new Cell();
		boolean someoneWon = false;
		try {
			boolean canDrop = canDrop(columnIndex);
			boolean dropped = false; // to make sure it is dropped just once
			if (canDrop(columnIndex) == true) {
				for (int i = row - 1; i >= 0; i--) {
					if (bord[i][columnIndex] == null) {
						if (dropped == false) {
							if (canDrop == true) {
								if (someoneWon == false) {
									if (testVictory() != Winner.NONE) {
										throw new IllegalMoveException("illegal move");
									}

									if (hasToken(farbe.getFarbe()) == true) {

										getToken(farbe.getFarbe());

										someoneWon = true;
										bord[i][columnIndex] = new Cell();
										bord[i][columnIndex].setTokenColor(farbe);
										cb.setCol(columnIndex);
										cb.setRow(i);
										cb.setTokenColor(farbe);
										gameStand.add(cb);
										dropped = true;

									}
								}
							}
						}
					}
				}
			} else {
				throw new ColumnFullException("Column " + (columnIndex + 1) + " is full");
			}
		} catch (ColumnFullException e) {
			System.out.println(e.getMessage());
		} catch (IllegalMoveException e) {
			System.out.println(e.getMessage());
		}

	}

	/**
	 * Checks if a token can be dropped
	 * 
	 * @param columnIndex
	 * @return true/false
	 */
	public boolean canDrop(int columnIndex) {
		boolean canDrop = false;
		for (int i = row - 1; i >= 0; i--) {
			if (bord[i][columnIndex] == null) {
				canDrop = true;
			}
		}
		return canDrop;
	}
	
	/**
	 * checks if a framePlayer is almost winning
	 * 
	 * @param color
	 * @return true/false
	 */
	public boolean isColumnDanger(Token.Color color) {
		boolean isDanger = false;
		boolean isChecked = false; // determines if a column is full
		int counter = 0;

		for (int i = row - 1; i >= 0; i--) {
			if (bord[i][dangerCol] == null) {
				isChecked = true;
				break;
			}
		}

		if (isChecked == true) {
			int winningStreak = 4;
			for (int j = 0; j < row; j++) {
				if (bord[j][dangerCol] == null) {
					winningStreak = 4;
				} else if (bord[j][dangerCol] != null) {
					counter++;
					if (bord[j][dangerCol].getTokenColor().getFarbe() == color) {
						winningStreak--;

						if (winningStreak == 1 && counter<=3) {
							isDanger = true;
							dangerColor = color;
							break;
						}
					} else {
						winningStreak = 4;
					}
				}
			}
		}
		return isDanger;
	}

	/**
	 * checks if a computerPlayer is almost winning
	 * 
	 * @param color
	 * @return true/false
	 */
	public boolean isColumnDanger2(Token.Color color) {
		boolean isDanger = false;
		boolean isChecked = false;
		int counter = 0;
		// TODO check if column is full before dropping
		for (int i = row - 1; i >= 0; i--) {
			if (bord[i][dangerCol2] == null) {
				isChecked = true;
				break;
			}
		}
		if (isChecked == true) {
			int winningStreak = 4;
			for (int j = 0; j < row; j++) {
				if (bord[j][dangerCol2] == null) {
					winningStreak = 4;
				} else if (bord[j][dangerCol2] != null) {
					counter++;
					if (bord[j][dangerCol2].getTokenColor().getFarbe() == color) {
						winningStreak--;

						if (winningStreak == 1 && counter<=3) {
							isDanger = true;
							dangerColor2 = color;
							break;
						}
						
					} else {
						winningStreak = 4;
					}
				}
				
			}
		}
		return isDanger;
	}

}
