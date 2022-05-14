package controller;

import java.awt.Color;
import javax.swing.JOptionPane;

import exception.ColumnFullException;
import exception.IllegalMoveException;
import model.Bord;
import model.Cell;
import model.CircleBord;
import model.Token;
import model.Token.Winner;
import observer_observable.ObservableBord;
import view.JOptionFrame;

public abstract class Player extends ObservableBord {

	protected Token playerColor;
	protected static Bord b;

	public Player(Token playerColor, Bord b) {
		Player.b = b;
		this.playerColor = playerColor;
	}

	/**
	 * Drops a token in the board
	 * 
	 * @param columnIndex
	 * @exception ColumnFullException, IllegalMoveException
	 * 
	 */
	protected void doDrop(int columnIndex) {
		Cell cb = new Cell();
		boolean someoneWon = false;
		try {
			boolean canDrop = b.canDrop(columnIndex);
			boolean dropped = false; // to make sure it is dropped just once
			if (b.canDrop(columnIndex) == true) {
				for (int i = Bord.getRow() - 1; i >= 0; i--) {
					if (Bord.bord[i][columnIndex] == null) {
						if (dropped == false) {
							if (canDrop == true) {
								if (someoneWon == false) {
									if (b.testVictory() != Winner.NONE) {
										throw new IllegalMoveException("Illegal Move");
									}

									if (b.hasToken(playerColor.getFarbe()) == true) {

										b.getToken(playerColor.getFarbe());

										someoneWon = true;
										Bord.bord[i][columnIndex] = new Cell();
										Bord.bord[i][columnIndex].setTokenColor(playerColor);
										CircleBord.bord[i][columnIndex].setColor(b.tokenToSwing(playerColor)); 
										cb.setCol(columnIndex);						
										cb.setRow(i);									
										cb.setTokenColor(playerColor);			
										Bord.gameStand.add(cb);						
										notifyObserver();
										dropped = true;

									}
								}
							}
						}
					}
				}
			} else {
				throw new ColumnFullException("Column " + (columnIndex) + " is full");
			}
		} catch (ColumnFullException e) {
			notifyObserver();
			JOptionPane.showMessageDialog(new JOptionFrame(), "         " + e.getMessage());
			System.out.println(e.getMessage());
		} catch (IllegalMoveException e) {
			JOptionPane.showMessageDialog(new JOptionFrame(), "            " + e.getMessage());
			System.out.println(e.getMessage());
		}

		if (b.testVictory() != null && b.testVictory() != Winner.NONE && b.testVictory() != Winner.TIE) {
			System.out.println("Winner is " + b.testVictory());
		} else if (b.testVictory() == Winner.TIE) {
			System.out.println("There is a " + b.testVictory());
		}

	}

	/**
	 * displays each colours turn to play
	 */
	public abstract void doTurn();
}
