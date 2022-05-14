package controller;

import java.util.Random;

import model.Bord;
import model.GameObject;
import observer_observable.BordObserver;
import observer_observable.ObservableBord;
import view.VierGewinntFrame;

public class FrameGame extends Game implements BordObserver {

	private VierGewinntFrame frame;
	private ObservableBord obs = new ObservableBord();

	/**
	 * initialises Token red and yellow
	 * 
	 * @param b
	 * @param frame
	 */
	public FrameGame(Bord b, VierGewinntFrame frame) {
		super(b);
		this.frame = frame;
		obs.addObserver(this);
	}
	@Override
	public void update(ObservableBord bord) {
		frame.repaint();
		System.out.println("\n");
		System.out.println(currentPlayer.playerColor.getFarbe());
		System.out.println(b.toString());

		swapPlayer();
		currentPlayer.doTurn();
		System.out.println("\n");
		GameObject.countLoaded = 0; // for load notification to show once
		GameObject.countSaved = 0; // for save notification to show once
		GameObject.countRestarted = 0; // for restart notification to show once
		GameObject.wrongPlayer = 0; // for wrong player notification
//		frame.repaint();  //not neccessary					
	}

	@Override
	public void doGame(Player player1, Player player2) {
		Random player = new Random();
		boolean startingPlayer = player.nextBoolean();
		this.player1 = player1;
		this.player2 = player2;

		if (startingPlayer == true) {
			this.currentPlayer = player1;
		} else {
			this.currentPlayer = player2;
		}
		System.out.println(b.toString());
		
		if (b.tmpColor != "") {
			if (player1.playerColor.getFarbe().toString() == b.tmpColor) {
				this.currentPlayer = player2;
			} else if (player2.playerColor.getFarbe().toString() == b.tmpColor) {
				this.currentPlayer = player1;
			}
		}
		currentPlayer.doTurn();
	}

}
