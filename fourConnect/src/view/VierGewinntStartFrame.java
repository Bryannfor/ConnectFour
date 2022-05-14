package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

import controller.ComputerPlayer;
import controller.FrameGame;
import controller.FramePlayer;
import model.Bord;
import model.Cell;
import model.GameObject;

public class VierGewinntStartFrame extends JFrame implements ActionListener {

	public static JLabel message = new JLabel();
	private JButton startGame = new JButton("Start");
	private JButton deleteSave = new JButton("Delete");
	private JButton loadGame = new JButton("Load");
	private JRadioButton _1player = new JRadioButton("1Player");
	private JRadioButton _2player = new JRadioButton("2Players");
	private JRadioButton _3player = new JRadioButton("Computer");
	private ButtonGroup bg;

	private Bord b;
	public static VierGewinntFrame frame;
	private FrameGame game;
	private Cell c = new Cell();

	public VierGewinntStartFrame() {

		setSize(300, 180);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(null);

		bg = new ButtonGroup();
		bg.add(_1player);
		bg.add(_2player);
		bg.add(_3player);

		addButtonListener(this);

		_1player.setBounds(20, 20, 70, 30);
		_1player.setToolTipText("Choose this option for a player vs computer game");
		add(_1player);

		_2player.setBounds(20, 50, 80, 30);
		_2player.setToolTipText("Choose this option for a player vs player game");
		add(_2player);

		_3player.setBounds(20, 80, 85, 30);
		_3player.setToolTipText("Choose this option for a computer vs computer game");
		add(_3player);

		startGame.setBounds(150, 35, 72, 20);
		startGame.setToolTipText("Click this button to start the game");
		add(startGame);

		loadGame.setBounds(150, 55, 72, 20);
		loadGame.setToolTipText("Click this button to load a game save");
		add(loadGame);
		
		deleteSave.setBounds(150, 75, 72, 20);
		deleteSave.setToolTipText("Click this button to delete any game saves");
		add(deleteSave);
		
		message.setBounds(130, 10, 120, 30);
		message.setForeground(Color.RED);
		message.setFont(new Font(null, Font.BOLD, 13));
		add(message);

		this.setTitle("GameSetting");

	}

	public void addButtonListener(ActionListener listener) {

		_1player.addActionListener(listener);
		_1player.setActionCommand("1Player");

		_2player.addActionListener(listener);
		_2player.setActionCommand("2Players");

		_3player.addActionListener(listener);
		_3player.setActionCommand("Computer");

		startGame.addActionListener(listener);
		startGame.setActionCommand("Start");

		deleteSave.addActionListener(listener);
		deleteSave.setActionCommand("delete");
		
		loadGame.addActionListener(listener);
		loadGame.setActionCommand("load");

	}

	public void removeButtonListener(ActionListener listener) {
		_1player.removeActionListener(listener);

		_2player.addActionListener(listener);

		_3player.addActionListener(listener);

		startGame.addActionListener(listener);

		deleteSave.removeActionListener(listener);
		
		loadGame.removeActionListener(listener);

	}
	

	@Override
	public void actionPerformed(ActionEvent e) {

		if(frame == null || VierGewinntFrame.isDisposed == true) {						//check if a game is going on before configuring another 
			if (e.getSource().equals(_1player)) {
				GameObject.state = 1;
				message.setBounds(125, 10, 160, 30);
				message.setText("Player vs Computer");
			} else if (e.getSource().equals(_2player)) {
				GameObject.state = 2;
				message.setBounds(135, 10, 160, 30);
				message.setText("Player vs Player");
			} else if (e.getSource().equals(_3player)) {
				GameObject.state = 3;
				message.setBounds(115, 10, 160, 30);
				message.setText("Computer vs Computer");
			}
		}
		else {
			int result = JOptionPane.showConfirmDialog(this, "Sure? You want to end current game?",
					"Game Setting", JOptionPane.YES_NO_OPTION);
			
			if (result == JOptionPane.YES_OPTION) {
				frame.dispose();
				frame = null;
			} 
			bg.clearSelection();
			message.setText("");
		}

		if (e.getActionCommand() == "Start") {
			
			switch (message.getText()) {
			case ("Player vs Computer"):
				b = new Bord();
				frame = new VierGewinntFrame(b);
				game = new FrameGame(b, frame);
				FramePlayer player1 = new FramePlayer(c.getRed(), b, frame);
				ComputerPlayer player2 = new ComputerPlayer(c.getYellow(), b, frame);
				game.doGame(player1, player2);
				message.setText("");
				break;

			case ("Player vs Player"):
				b = new Bord();
				frame = new VierGewinntFrame(b);
				game = new FrameGame(b, frame);
				FramePlayer player3 = new FramePlayer(c.getRed(), b, frame);
				FramePlayer player4 = new FramePlayer(c.getYellow(), b, frame);
				game.doGame(player3, player4);
				message.setText("");
				break;

			case ("Computer vs Computer"):
				b = new Bord();
				frame = new VierGewinntFrame(b);
				game = new FrameGame(b, frame);
				ComputerPlayer player5 = new ComputerPlayer(c.getRed(), b, frame);
				ComputerPlayer player6 = new ComputerPlayer(c.getYellow(), b, frame);
				game.doGame(player5, player6);
				message.setText("");
				break;

			default:
				message.setBounds(130, 10, 120, 30);
				message.setText("First select players");
				break;
			}

			bg.clearSelection();

		} else if (e.getActionCommand() == "delete") {
			int result;
			switch (message.getText()) {
			case ("Player vs Computer"):
				b = new Bord();
				result = JOptionPane.showConfirmDialog(this, "Sure? You want to delete current saved game?",
						"Game Setting", JOptionPane.YES_NO_OPTION);
				
				if (result == JOptionPane.YES_OPTION) {
					b.deleteSave();
					bg.clearSelection();
				} else if (result == JOptionPane.NO_OPTION) {
					bg.clearSelection();
					message.setText("");
				}
				break;

			case ("Player vs Player"):
				b = new Bord();
				result = JOptionPane.showConfirmDialog(this, "Sure? You want to delete current saved game?",
						"Game Setting", JOptionPane.YES_NO_OPTION);
				
				if (result == JOptionPane.YES_OPTION) {
					b.deleteSave();
					bg.clearSelection();
				} else if (result == JOptionPane.NO_OPTION) {
					bg.clearSelection();
					message.setText("");
				}
				break;

			case ("Computer vs Computer"):
				b = new Bord();
				result = JOptionPane.showConfirmDialog(this, "Sure? You want to delete current saved game?",
						"Game Setting", JOptionPane.YES_NO_OPTION);
				
				if (result == JOptionPane.YES_OPTION) {
					b.deleteSave();
					bg.clearSelection();
				} else if (result == JOptionPane.NO_OPTION) {
					bg.clearSelection();
					message.setText("");
				}
				break;

			default:
				message.setBounds(130, 10, 120, 30);
				message.setText("First select players");
				break;

		}
		bg.clearSelection();


			
		}else if (e.getActionCommand() == "load") {
			this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			switch (message.getText()) {
				case ("Player vs Computer"):
					b = new Bord();
					frame = new VierGewinntFrame(b);
					game = new FrameGame(b, frame);
					b.load();
					FramePlayer player1 = new FramePlayer(c.getRed(), b, frame);
					ComputerPlayer player2 = new ComputerPlayer(c.getYellow(), b, frame);
					game.doGame(player1, player2);
					message.setText("");
					break;
	
				case ("Player vs Player"):
					b = new Bord();
					frame = new VierGewinntFrame(b);
					game = new FrameGame(b, frame);	
					b.load();
					FramePlayer player3 = new FramePlayer(c.getRed(), b, frame);
					FramePlayer player4 = new FramePlayer(c.getYellow(), b, frame);
					game.doGame(player3, player4);
					message.setText("");
					break;
	
				case ("Computer vs Computer"):
					b = new Bord();
					frame = new VierGewinntFrame(b);
					game = new FrameGame(b, frame);	
					b.load();
					ComputerPlayer player5 = new ComputerPlayer(c.getRed(), b, frame);
					ComputerPlayer player6 = new ComputerPlayer(c.getYellow(), b, frame);
					game.doGame(player5, player6);
					message.setText("");
					break;
	
				default:
					message.setBounds(130, 10, 120, 30);
					message.setText("First select players");
					break;

			}
			this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			bg.clearSelection();

		}

	}

	public static void main(String[] args) {
		new VierGewinntStartFrame();
	}

}
