package controller;

import java.util.Scanner;

import model.Token.Color;
import model.Token.Winner;
import model.Bord;
import model.Token;

public class ConsoleGame extends Game {

	private Token red = new Token();
	private Token yellow = new Token();

	/**
	 * initialises Token red and yellow initialises player1 and player2
	 * 
	 * @param b
	 */
	public ConsoleGame(Bord b) {
		super(b);
		red.setFarbe(Color.RED);
		yellow.setFarbe(Color.YELLOW);

		this.player1 = new PlayerExtend(yellow, b);

		this.player2 = new PlayerExtend(red, b);

	}

	public static void main(String[] args) {
		Bord b = new Bord();
		ConsoleGame cons = new ConsoleGame(b);
		cons.doGame(cons.player1, cons.player2);

	}

	@Override
	public void doGame(Player player1, Player player2) {

		this.currentPlayer = player1;

		System.out.println(b.toString());
		Scanner input = new Scanner(System.in);
		do {

			currentPlayer.doTurn();
			int column = input.nextInt() - 1;
			currentPlayer.doDrop(column);

			if (currentPlayer == player1) {
				currentPlayer = player2;
			} else {
				currentPlayer = player1;
			}
			System.out.println();
			System.out.println(Player.b.toString());
		} while (Player.b.testVictory() == Winner.NONE);
		input.close();

		System.out.println(b.testVictory());

	}
}
