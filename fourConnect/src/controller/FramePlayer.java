package controller;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

import model.Bord;
import model.GameObject;
import model.Token;
import model.Token.Winner;
import view.VierGewinntFrame;
import view.VierGewinntStartFrame;

public class FramePlayer extends Player implements java.awt.event.ActionListener {

	private VierGewinntFrame frame;

	public FramePlayer(Token playerColor, Bord b, VierGewinntFrame frame) {
		super(playerColor, b);
		this.frame = frame;
		frame.addFileListener(this);
//		frame.computer.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		frame.computer.removeActionListener(this);
//		int playedIndex; 
		int playedIndex;  //delete if not needed
		GameObject.dangerColor = playerColor.getFarbe();

		switch (e.getActionCommand()) {

		case ("button0"):
			frame.removeButtonListener(this);
			playedIndex = 0;
			doDrop(playedIndex);
			GameObject.dangerCol = playedIndex;
			break;

		case ("button1"):
			frame.removeButtonListener(this);
			playedIndex = 1;
			doDrop(playedIndex);
			GameObject.dangerCol = playedIndex;
			break;

		case ("button2"):
			frame.removeButtonListener(this);
			playedIndex = 2;
			doDrop(playedIndex);
			GameObject.dangerCol = playedIndex;
			break;

		case ("button3"):
			frame.removeButtonListener(this);
			playedIndex = 3;
			doDrop(playedIndex);
			GameObject.dangerCol = playedIndex;
			break;

		case ("button4"):
			frame.removeButtonListener(this);
			playedIndex = 4;
			doDrop(playedIndex);
			GameObject.dangerCol = playedIndex;
			break;

		case ("button5"):
			frame.removeButtonListener(this);
			playedIndex = 5;
			doDrop(playedIndex);
			GameObject.dangerCol = playedIndex;
			break;

		case ("button6"):
			frame.removeButtonListener(this);
			playedIndex = 6;
			doDrop(playedIndex);
			GameObject.dangerCol = playedIndex;
			break;

		case ("Restart Game"):
			this.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			b.restartGame();
			this.frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			GameObject.wrongPlayer = 0;
			break;

		case ("Save Game"):
			b.save();
			GameObject.wrongPlayer = 0;
			break;

		case ("Load Game"):
			if(GameObject.countLoaded == 0) {
				int result = JOptionPane.showConfirmDialog(this.frame, "Sure? You want to exit to load game?",
						"Load Game", JOptionPane.YES_NO_OPTION);
				
				if (result == JOptionPane.YES_OPTION) {
					this.frame.dispose();
					VierGewinntStartFrame.frame = null;
				} else if (result == JOptionPane.NO_OPTION) {
					
				}
				GameObject.countLoaded++;
			}
			GameObject.wrongPlayer = 0;
			break;


		default:
			GameObject.wrongPlayer++;
			if(GameObject.wrongPlayer == 1){
				JOptionPane.showMessageDialog(frame, "   Click A Number To Play ");
			}
			else if(GameObject.countRestarted == 1) {
				JOptionPane.showMessageDialog(frame, "   Click A Number To Play ");
			}
			break;
		}

		if (b.testVictory() != Winner.TIE && b.testVictory() != Winner.NONE) {
			
			frame.removeButtonListener(this);
			frame.computer.removeActionListener(this);
//			frame.removeFileListener(this);
			if (b.testVictory() == Winner.RED) {
				JOptionPane.showMessageDialog(frame, "              RED WON");
			} else
				JOptionPane.showMessageDialog(frame, "          YELLOW WON");
		} else if (b.testVictory() == Winner.TIE) {
			frame.removeButtonListener(this);
			frame.computer.removeActionListener(this);
//			frame.removeFileListener(this);
			JOptionPane.showMessageDialog(frame, "                   TIE");
		}
		
//		frame.repaint();	only needed if loading is done in game
	}

	@Override
	public void doTurn() {
		frame.addButtonListener(this);
		frame.computer.addActionListener(this);
		System.out.println(this.playerColor.getFarbe() + " ist dran");

		if (this.playerColor.getFarbe() == Token.Color.RED) {

			System.out.print("Spieler RED, was ist dein naechster Zug" + "\nEingabe: ");
			System.out.println();

		} else if (this.playerColor.getFarbe() == Token.Color.YELLOW) {

			System.out.print("Spieler YELLOW, was ist dein naechster Zug" + "\nEingabe: ");
			System.out.println();
		}

	}

}
