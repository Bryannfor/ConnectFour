package controller;

import model.Bord;
import model.Token;

public class PlayerExtend extends Player {

	public PlayerExtend(Token playerColor, Bord b) {
		super(playerColor, b);

	}

	@Override
	public void doTurn() {

		System.out.println(this.playerColor.getFarbe() + " has to be dropped");
		if (this.playerColor.getFarbe() == Token.Color.RED) {
			System.out.print("Spieler x, was ist dein naechster Zug" + "\nEingabe: ");
		} else {
			System.out.print("Spieler O, was ist dein naechster Zug" + "\nEingabe: ");
		}
	}
	
}
