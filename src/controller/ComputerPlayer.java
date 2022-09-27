package controller;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;

import model.Bord;
import model.Cell;
import model.GameObject;
import model.Token;
import model.Token.Winner;
import view.VierGewinntFrame;
import view.VierGewinntStartFrame;

public class ComputerPlayer extends Player implements java.awt.event.ActionListener {

	public VierGewinntFrame frame;
	private boolean canRestart;
	Cell c = new Cell();

	public ComputerPlayer(Token playerColor, Bord b, VierGewinntFrame frame) {
		super(playerColor, b);
		this.frame = frame;
		frame.addFileListener(this);
//		frame.addButtonListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ArrayList<Integer>gameIndex = new ArrayList<>();
		
		
		frame.removeButtonListener(this);
	
		
		Random number = new Random();
		int playedIndex = number.nextInt(7);
		
		//TODO
		for (int j = 0; j < 7; j++) {
			boolean isAdded = false;
			for (int i = 5; i >= 0; i--) {
				if (isAdded == false) {
					if (Bord.bord[i][j] == null) {
						gameIndex.add(j);
						isAdded = true;
					}
				}
			
			}
			
		}


		Random num = new Random();
		playedIndex = gameIndex.get(num.nextInt(gameIndex.size()));

		gameIndex = null;
		
		GameObject.dangerColorComputer = playerColor.getFarbe();
		
		if(b.isColumnFramePlayerDanger(GameObject.dangerColorFrame) == true || b.isRowDangerFramePlayer(GameObject.dangerColorFrame) == true || b.isColumnFrameDiagonalDanger(GameObject.dangerColorFrame) == true || b.isRowDangerFrame(GameObject.dangerColorFrame) == true) {
			playedIndex = GameObject.dangerColIndexFrame;
		}
		if(b.isColumnComputerPlayerDanger(GameObject.dangerColorComputer) == true || b.isRowDangerComputerPlayer(GameObject.dangerColorComputer) == true || b.isColumnComputerDiagonalDanger(GameObject.dangerColorComputer) == true || b.isRowDangerComputer(GameObject.dangerColorComputer) == true) {
			playedIndex = GameObject.dangerColIndexComputer;
		}
				
		switch (e.getActionCommand()) {

		case ("Restart Game"):
//			if(GameObject.countRestarted == 0) {
//				if(GameObject.startColor.toString() != GameObject.currentColor.toString()) {
//					this.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
//					b.restartGame();
//					this.frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
//					GameObject.wrongPlayer = 0;;		
//				}
//				else {
//					JOptionPane.showMessageDialog(frame, "Another player needs to play for a restart ");
//					GameObject.countRestarted++;
//				}				
//			}
			canRestart = false;
			if (b.testVictory() != Winner.TIE && b.testVictory() != Winner.NONE) {
				canRestart = true;
			}
			else if(GameObject.countRestarted == 0) {
				if(GameObject.startColor.toString() != GameObject.currentColor.toString()) {
					canRestart = true;	
				}
				else {
					JOptionPane.showMessageDialog(frame, GameObject.nextColor + " needs to play for a restart ");
					GameObject.countRestarted++;
				}				
			}
			if(canRestart == true) {
				this.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				b.restartGame();
				this.frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				GameObject.wrongPlayer = 0;
			}
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

//		case ("Delete Save"):
//			b.deleteSave();
//			break;

		case ("Computer Player"):
			frame.computer.removeActionListener(this);
			GameObject.currentColor = playerColor.getFarbe();
			doDrop(playedIndex);
			break;

		default:
			GameObject.wrongPlayer++;
			if (GameObject.wrongPlayer == 1) {
				JOptionPane.showMessageDialog(frame, "  Click Computer To Play ");
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
		GameObject.dangerColIndexComputer = playedIndex;
		frame.repaint();		//frame refresh after a restart
	}

	@Override
	public void doTurn() {
		
		if(hasDropped() == true || VierGewinntStartFrame.counter == 0) {
		System.out.println("\n");
		frame.computer.addActionListener(this);
		frame.addButtonListener(this);
		System.out.println(this.playerColor.getFarbe() + " ist dran");
		}

		
		if (this.playerColor.getFarbe() == Token.Color.RED) {
			
			System.out.print("Spieler RED, was ist dein naechster Zug" + "\nEingabe: " + "\n");

		} else if (this.playerColor.getFarbe() == Token.Color.YELLOW) {

			System.out.print("Spieler YELLOW, was ist dein naechster Zug" + "\nEingabe: " + "\n");
		}
		
		VierGewinntStartFrame.counter ++;
	}
}
