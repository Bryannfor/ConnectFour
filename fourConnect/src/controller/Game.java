package controller;

import model.Bord;

public abstract class Game {
	protected Bord b;

	protected Player player1;
	protected Player player2;
	protected Player currentPlayer;

	public Game(Bord b) {

		this.b = b;

	}

	/**
	 * swaps players
	 */
	protected void swapPlayer() {
		if (currentPlayer == player1) {
			currentPlayer = player2;
		} else {
			currentPlayer = player1;
		}

	}

	/*
	 * starts the game
	 */
	public abstract void doGame(Player player1, Player player2);
}
