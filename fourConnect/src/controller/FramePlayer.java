package controller;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import model.Bord;
import model.Cell;
import model.GameObject;
import model.Token;
import model.Token.Winner;
import view.VierGewinntFrame;
import view.VierGewinntStartFrame;

public class FramePlayer extends Player implements java.awt.event.ActionListener {

	private VierGewinntFrame frame;
	Cell c = new Cell();
	private boolean canRestart;

	public FramePlayer(Token playerColor, Bord b, VierGewinntFrame frame) {
		super(playerColor, b);
		this.frame = frame;
		frame.addFileListener(this);
//		frame.computer.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		

		frame.computer.removeActionListener(this);

		int playedIndex;  //delete if not needed
		GameObject.dangerColorFrame = playerColor.getFarbe();
		
		//reset right panel 
		VierGewinntFrame.rightPanelMessage.setText("");
		VierGewinntFrame.rightPanel.add(VierGewinntFrame.rightPanelMessage);
		frame.validate();
		
		switch (e.getActionCommand()) {

		case ("button0"):
			frame.removeButtonListener(this);
			playedIndex = 0;
			doDrop(playedIndex);
			GameObject.dangerColIndexFrame = playedIndex;
			GameObject.currentColor = playerColor.getFarbe();
			break;

		case ("button1"):
			frame.removeButtonListener(this);
			playedIndex = 1;
			doDrop(playedIndex);
			GameObject.dangerColIndexFrame = playedIndex;
			GameObject.currentColor = playerColor.getFarbe();
			break;

		case ("button2"):
			frame.removeButtonListener(this);
			playedIndex = 2;
			doDrop(playedIndex);
			GameObject.dangerColIndexFrame = playedIndex;
			GameObject.currentColor = playerColor.getFarbe();
			break;

		case ("button3"):
			frame.removeButtonListener(this);
			playedIndex = 3;
			doDrop(playedIndex);
			GameObject.dangerColIndexFrame = playedIndex;
			GameObject.currentColor = playerColor.getFarbe();
			break;

		case ("button4"):
			frame.removeButtonListener(this);
			playedIndex = 4;
			doDrop(playedIndex);
			GameObject.dangerColIndexFrame = playedIndex;
			GameObject.currentColor = playerColor.getFarbe();
			break;

		case ("button5"):
			frame.removeButtonListener(this);
			playedIndex = 5;
			doDrop(playedIndex);
			GameObject.dangerColIndexFrame = playedIndex;
			GameObject.currentColor = playerColor.getFarbe();
			break;

		case ("button6"):
			frame.removeButtonListener(this);
			playedIndex = 6;
			doDrop(playedIndex);
			GameObject.dangerColIndexFrame = playedIndex;
			GameObject.currentColor = playerColor.getFarbe();
			break;

		case ("Restart Game"):
//			if(GameObject.countRestarted == 0) {
//				if(GameObject.startColor.toString() != GameObject.currentColor.toString()) {
//					this.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
//					b.restartGame();
//					this.frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
//					GameObject.wrongPlayer = 0;
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
		
		frame.repaint();	//frame refresh after a restart
		frame.validate();
	}

	@Override
	public void doTurn() {
		if(hasDropped() == true || VierGewinntStartFrame.counter == 0) {
			frame.addButtonListener(this);
			frame.computer.addActionListener(this);
			System.out.println(this.playerColor.getFarbe() + " ist dran");
		}
		//TODO else statement for either player 1 or 2

		if (this.playerColor.getFarbe() == Token.Color.RED) {

			VierGewinntFrame.centerPanelMessage.setText(Token.Color.RED.toString());
			System.out.print("Spieler RED, was ist dein naechster Zug" + "\nEingabe: ");
			System.out.println();

		} else if (this.playerColor.getFarbe() == Token.Color.YELLOW) {
			
			VierGewinntFrame.centerPanelMessage.setText(Token.Color.YELLOW.toString());
			System.out.print("Spieler YELLOW, was ist dein naechster Zug" + "\nEingabe: ");
			System.out.println();
		}
		
		VierGewinntFrame.centerPanel.add(VierGewinntFrame.centerPanelMessage);
		VierGewinntStartFrame.counter ++;
	}

}
