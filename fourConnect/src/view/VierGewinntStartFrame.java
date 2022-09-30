package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

import controller.ComputerPlayer;
import controller.FrameGame;
import controller.FramePlayer;
import model.Bord;
import model.Cell;
import model.GameObject;
import model.Token;

public class VierGewinntStartFrame extends JFrame implements ActionListener {

	public static JLabel message = new JLabel();
	private JButton startGame = new JButton("Start");
	private JButton deleteSave = new JButton("Delete");
	private JButton loadGame = new JButton("Load");
	private JRadioButton _1player = new JRadioButton("1Player");
	private JRadioButton _2player = new JRadioButton("2Players");
	private JRadioButton _3player = new JRadioButton("Computer");
	private JLabel version = new JLabel(GameObject.versionNummber);
	private JPanel leftPanel = new JPanel();
	private JPanel centerPanel = new JPanel();
    private JPanel rightPanel = new JPanel();
    private String comboBoxListe[] = {"English", "German", "French"};
    private JComboBox<String> languages = new JComboBox<String>(comboBoxListe);
	private ButtonGroup bg;

	private Bord b;
	public static VierGewinntFrame frame;
	private FrameGame game;
	private Cell c = new Cell();
	public static int counter;

	public VierGewinntStartFrame() {

		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(null);
		setSize(310, 190);

        JSplitPane sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, centerPanel);
        sp.setDividerLocation(50);
        sp.setPreferredSize(new Dimension(getWidth(), 25));
        JSplitPane sp2 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, sp, rightPanel);
        sp2.setDividerLocation(200);
        sp2.setBounds(0, 120, 300, 25);
        
        leftPanel.add(version);
        
        //JSplitPane wird Panel hinzugefügt
        add(sp2, BorderLayout.SOUTH);
 
        //JComboBox wird Panel hinzugefügt
        languages.setBounds(222, 0, 70, 20);
        add(languages);
		

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
		
		add(message);
		
		this.setTitle("GameSetting");
		GameObject.language = GameObject.languages.EN.toString();    // to adjust later on
//		languages.setSelectedItem(GameObject.languages.FR.toString());
	}

	public void addButtonListener(ActionListener listener) {

		_1player.addActionListener(listener);

		_2player.addActionListener(listener);

		_3player.addActionListener(listener);

		startGame.addActionListener(listener);

		deleteSave.addActionListener(listener);
		
		loadGame.addActionListener(listener);
		
		languages.addActionListener(listener);
		languages.setActionCommand("Language");  //actioncommand added manually for jcombobox because cant be added through constructor

	}

	public void removeButtonListener(ActionListener listener) {
		_1player.removeActionListener(listener);

		_2player.addActionListener(listener);

		_3player.addActionListener(listener);

		startGame.addActionListener(listener);

		deleteSave.removeActionListener(listener);
		
		loadGame.removeActionListener(listener);
		
		languages.removeActionListener(listener);

	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		message.setForeground(Color.BLACK);
		if(frame == null || VierGewinntFrame.isDisposed == true) {			//check if a game is going on before configuring another 
			if (e.getSource().equals(_1player)) {
				GameObject.state = 1;
				message.setText("Player vs Computer");
				centerPanel.add(message);
			} else if (e.getSource().equals(_2player)) {
				GameObject.state = 2;
				message.setText("Player vs Player");
				centerPanel.add(message);
			} else if (e.getSource().equals(_3player)) {
				GameObject.state = 3;
				message.setText("Computer vs Computer");
				centerPanel.add(message);
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
			centerPanel.add(message);
		}

		switch(e.getActionCommand()) {
			
			case("Start"):
				counter = 0;
				switch (message.getText()) {
					case ("Player vs Computer"):
						b = new Bord();
						frame = new VierGewinntFrame(b);
						
						frame.computer.setBounds(0, 0, 350, 23);
						frame.add(frame.computer);
						frame.computer.setToolTipText("Click this button to drop a color for computer");
						
						game = new FrameGame(b, frame);
						FramePlayer player1 = new FramePlayer(c.getRed(), b, frame);
						ComputerPlayer player2 = new ComputerPlayer(c.getYellow(), b, frame);
						game.doGame(player1, player2);
						message.setText("");
						centerPanel.add(message);
						break;
		
					case ("Player vs Player"):
						b = new Bord();
						frame = new VierGewinntFrame(b);
						game = new FrameGame(b, frame);
						FramePlayer player3 = new FramePlayer(c.getRed(), b, frame);
						FramePlayer player4 = new FramePlayer(c.getYellow(), b, frame);
						game.doGame(player3, player4);
						message.setText("");
						centerPanel.add(message);
						break;
		
					case ("Computer vs Computer"):
						b = new Bord();
						frame = new VierGewinntFrame(b);
						
						frame.remove(frame.moveCircleButton1);
						frame.remove(frame.moveCircleButton2);
						frame.remove(frame.moveCircleButton3);
						frame.remove(frame.moveCircleButton4);
						frame.remove(frame.moveCircleButton5);
						frame.remove(frame.moveCircleButton6);
						frame.remove(frame.moveCircleButton7);
						
						frame.computer.setBounds(0, 23, 350, 23);
						frame.add(frame.computer);
						frame.computer.setToolTipText("Click this button to drop a color for computer");
						
						game = new FrameGame(b, frame);
						ComputerPlayer player5 = new ComputerPlayer(c.getRed(), b, frame);
						ComputerPlayer player6 = new ComputerPlayer(c.getYellow(), b, frame);
						game.doGame(player5, player6);
						message.setText("");
						centerPanel.add(message);
						break;
		
					default:
						message.setForeground(Color.RED);
						message.setText("First select players");
						centerPanel.add(message);
						break;
					}
					GameObject.isLoaded = false;
					bg.clearSelection();
					break;
					
			case("Delete"):
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
							centerPanel.add(message);
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
							centerPanel.add(message);
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
							centerPanel.add(message);
						}
						break;
		
					default:
						message.setForeground(Color.RED);
						message.setText("First select players");
						centerPanel.add(message);
						break;
		
				}
				bg.clearSelection();
						break;
					
			case("Load"):
				this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				switch (message.getText()) {
					case ("Player vs Computer"):
						b = new Bord();
						frame = new VierGewinntFrame(b);
						
						frame.computer.setBounds(0, 0, 350, 23);
						frame.add(frame.computer);
						frame.computer.setToolTipText("Click this button to drop a color for computer");
						
						game = new FrameGame(b, frame);
						b.load();
						FramePlayer player1 = new FramePlayer(c.getRed(), b, frame);
						ComputerPlayer player2 = new ComputerPlayer(c.getYellow(), b, frame);
						game.doGame(player1, player2);
						message.setText("");
						centerPanel.add(message);
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
						centerPanel.add(message);
						break;
		
					case ("Computer vs Computer"):
						b = new Bord();
						frame = new VierGewinntFrame(b);
						
						frame.remove(frame.moveCircleButton1);
						frame.remove(frame.moveCircleButton2);
						frame.remove(frame.moveCircleButton3);
						frame.remove(frame.moveCircleButton4);
						frame.remove(frame.moveCircleButton5);
						frame.remove(frame.moveCircleButton6);
						frame.remove(frame.moveCircleButton7);
						
						frame.computer.setBounds(0, 23, 350, 23);
						frame.add(frame.computer);
						frame.computer.setToolTipText("Click this button to drop a color for computer");
						
						game = new FrameGame(b, frame);	
						b.load();
						ComputerPlayer player5 = new ComputerPlayer(c.getRed(), b, frame);
						ComputerPlayer player6 = new ComputerPlayer(c.getYellow(), b, frame);
						game.doGame(player5, player6);
						message.setText("");
						centerPanel.add(message);
						break;
		
					default:
						message.setForeground(Color.RED);
						message.setText("First select players");
						centerPanel.add(message);
						break;
				}
				GameObject.isLoaded = true;
				this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				bg.clearSelection();
				break;
					
			case("Language"):				
				GameObject.language = (String)languages.getSelectedItem();
				break;			
		}
	}

	public static void main(String[] args) {
		new VierGewinntStartFrame();
	}

}
