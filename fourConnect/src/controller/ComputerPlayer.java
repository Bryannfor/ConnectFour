package controller;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JLabel;
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
		playedIndex = gameIndex.get(num.nextInt(gameIndex.size()));  //todo check for winner before picking index

		gameIndex = null;
		
		GameObject.dangerColorComputer = playerColor.getFarbe();
		
		if(b.isColumnFramePlayerDanger(GameObject.dangerColorFrame) == true || b.isRowDangerFramePlayer(GameObject.dangerColorFrame) == true || b.isDiagonalFrameDanger(GameObject.dangerColorFrame) == true || b.isRowDangerFrame(GameObject.dangerColorFrame) == true) {
			playedIndex = GameObject.dangerColIndexFrame;
		}
		if(b.isColumnComputerPlayerDanger(GameObject.dangerColorComputer) == true || b.isRowDangerComputerPlayer(GameObject.dangerColorComputer) == true || b.isDiagonalComputerDanger(GameObject.dangerColorComputer) == true || b.isRowDangerComputer(GameObject.dangerColorComputer) == true) {
			playedIndex = GameObject.dangerColIndexComputer;
		}
			
		//reset right panel
		VierGewinntFrame.rightPanelMessage.setText("");
		VierGewinntFrame.rightPanel.add(VierGewinntFrame.rightPanelMessage);
		frame.validate();
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
					else if(GameObject.nextColor == null) {
							JOptionPane.showMessageDialog(frame, GameObject.startColor + " needs to play for a restart ");
							GameObject.countRestarted++;
					}
					else {
						JOptionPane.showMessageDialog(frame, GameObject.loadColor + " needs to play for a restart ");
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
	
			case ("Computer Player"):
				frame.computer.removeActionListener(this);
				GameObject.currentColor = playerColor.getFarbe();
				doDrop(playedIndex);
				break;
	
			default:
				VierGewinntFrame.rightPanelMessage.setFont(new Font(null, Font.BOLD, 14));
				VierGewinntFrame.rightPanelMessage.setForeground(Color.RED);
				VierGewinntFrame.rightPanelMessage.setText("Invalid Move");
				VierGewinntFrame.rightPanel.add(VierGewinntFrame.rightPanelMessage);
				break;
		}
		
		if (b.testVictory() != Winner.TIE && b.testVictory() != Winner.NONE) {
			frame.removeButtonListener(this);
			frame.computer.removeActionListener(this);
//			frame.removeFileListener(this);
			if (b.testVictory() == Winner.RED) {
				JOptionPane.showMessageDialog(frame, "              RED WON");
				VierGewinntFrame.centerPanelMessage.setText("RED WON");
			} else {
				JOptionPane.showMessageDialog(frame, "          YELLOW WON");
				VierGewinntFrame.centerPanelMessage.setText("YELLOW WON");
			}
		} else if (b.testVictory() == Winner.TIE) {
			frame.removeButtonListener(this);
			frame.computer.removeActionListener(this);
//			frame.removeFileListener(this);
			JOptionPane.showMessageDialog(frame, "                   TIE");
			VierGewinntFrame.centerPanelMessage.setText("TIE");
		}
		GameObject.dangerColIndexComputer = playedIndex;
		VierGewinntFrame.centerPanel.add(VierGewinntFrame.centerPanelMessage);
		frame.validate();
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
			
			VierGewinntFrame.centerPanelMessage.setText(Token.Color.RED.toString());
			System.out.print("Spieler RED, was ist dein naechster Zug" + "\nEingabe: " + "\n");

		} else if (this.playerColor.getFarbe() == Token.Color.YELLOW) {
			
			VierGewinntFrame.centerPanelMessage.setText(Token.Color.YELLOW.toString());
			System.out.print("Spieler YELLOW, was ist dein naechster Zug" + "\nEingabe: " + "\n");
		}
		
		VierGewinntFrame.centerPanel.add(VierGewinntFrame.centerPanelMessage);
		VierGewinntStartFrame.counter++;
	}
}
