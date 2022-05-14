package model;

import model.Token.Color;

public class Cell {

	private int col;
	private int row;
	private Token tokenColor;

	private Token red = new Token();
	private Token yellow = new Token();

	public Cell() {

		red.setFarbe(Color.RED);
		yellow.setFarbe(Color.YELLOW);
	}

	/**
	 * gets column index
	 * 
	 * @return column index
	 */
	public int getCol() {
		return col;
	}

	/**
	 * sets column index
	 * 
	 * @param col
	 */
	public void setCol(int col) {
		this.col = col;
	}

	/**
	 * gets row index
	 * 
	 * @return row index
	 */
	public int getRow() {
		return row;
	}

	/**
	 * sets row index
	 * 
	 * @param row
	 */
	public void setRow(int row) {
		this.row = row;
	}

	/**
	 * gets Token colour
	 * 
	 * @return Token colour
	 */
	public Token getTokenColor() {
		return tokenColor;
	}

	/**
	 * sets Token colour
	 * 
	 * @param tokenColor
	 */
	public void setTokenColor(Token tokenColor) {
		this.tokenColor = tokenColor;
	}

	/**
	 * gets Token red
	 * 
	 * @return Token red
	 */
	public Token getRed() {
		return red;
	}

	/**
	 * gets Token yellow
	 * 
	 * @return Token yellow
	 */
	public Token getYellow() {
		return yellow;
	}

}
