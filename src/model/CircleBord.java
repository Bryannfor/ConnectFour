package model;

import java.awt.Graphics;

import javax.swing.JComponent;

public class CircleBord extends JComponent {

	private static int col = 7;
	private static int row = 6;
	public static Circle[][] bord = new Circle[row][col];
	Bord b = new Bord();

	/**
	 * initialises a circle board
	 */
	public CircleBord() {
		for (int i = 0, x = 24; i < row && x < 300; i++, x += 50) {
			for (int j = 0, y = 0; j < col && y < 350; j++, y += 50) {
				bord[i][j] = new Circle();
				bord[i][j].setX(y);
				bord[i][j].setY(x);
			}
		}
	}

	@Override
	public void paintComponent(Graphics g) {

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				g.setColor(bord[i][j].getColor());
				g.fillOval(bord[i][j].getX(), bord[i][j].getY(), Circle.getWidth(), Circle.getHeight());
			}
		}

	}

}
