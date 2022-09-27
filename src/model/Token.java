package model;

public class Token {

	private Color farbe;

	/**
	 * gets colour of token
	 * 
	 * @return colour of token
	 */
	public Color getFarbe() {
		return farbe;
	}

	/**
	 * sets colour of token
	 * 
	 * @param farbe
	 */
	public void setFarbe(Color farbe) {
		this.farbe = farbe;
	}

	public enum Color {
		RED, YELLOW;
	}

	public enum Winner {
		RED, YELLOW, TIE, NONE
	}
}
