package model;

import java.awt.Color;

public class Circle {

	private int x;
	private int y;
	private static int width;
	private static int height;
	private Color color;
	Bord b = new Bord();

	/**
	 * initialises the width,height and colour of a circle
	 */
	public Circle() {
		Circle.width = 50;
		Circle.height = 50;
		this.color = Color.WHITE;
	}

	/**
	 * gets the x coordinate of a circle
	 * 
	 * @return x
	 */
	public int getX() {
		return x;
	}

	/**
	 * sets the x coordinate of a circle
	 * 
	 * @param x coordinate
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * gets the y coordinate of a circle
	 * 
	 * @return y coordinate
	 */
	public int getY() {
		return y;
	}

	/**
	 * sets the y coordinate of a circle
	 * 
	 * @param y
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * gets the colour of a circle
	 * 
	 * @return colour
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * sets the colour of a circle
	 * 
	 * @param color
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * gets the width of a circle
	 * 
	 * @return width
	 */
	public static int getWidth() {
		return width;
	}

	/**
	 * sets the height of a circle
	 * 
	 * @return height
	 */
	public static int getHeight() {
		return height;
	}

}
