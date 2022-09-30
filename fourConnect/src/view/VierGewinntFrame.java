package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import model.Bord;
import model.Circle;
import model.CircleBord;
import model.GameObject;
import observer_observable.BordObserver;
import observer_observable.ObservableBord;

public class VierGewinntFrame extends JFrame implements BordObserver, ActionListener {

	protected JButton moveCircleButton1 = new JButton("0");
	protected JButton moveCircleButton2 = new JButton("1");
	protected JButton moveCircleButton3 = new JButton("2");
	protected JButton moveCircleButton4 = new JButton("3");
	protected JButton moveCircleButton5 = new JButton("4");
	protected JButton moveCircleButton6 = new JButton("5");
	protected JButton moveCircleButton7 = new JButton("6");
	public JButton computer = new JButton("Computer Player");
	private JMenuBar menu = new JMenuBar();
	private JMenu file = new JMenu("File");
	private JMenu help = new JMenu("Help");
	private JMenuItem loadGame = new JMenuItem("Load Game");
	private JMenuItem saveGame = new JMenuItem("Save Game");
	private JMenuItem newGame = new JMenuItem("Restart Game");
	private JMenuItem rules = new JMenuItem("Game rules");
	protected static boolean isDisposed;
//	public JMenuItem computer = new JMenuItem("Computer");
	
	private JLabel version = new JLabel(GameObject.versionNummber);
	public static JLabel centerPanelMessage = new JLabel();
	public static JLabel rightPanelMessage = new JLabel();
	private JPanel leftPanel = new JPanel();
	public static JPanel centerPanel = new JPanel();
	public static JPanel rightPanel = new JPanel();

	CircleBord circleDrawing = new CircleBord();
	Circle c = new Circle();

	public VierGewinntFrame(Bord b) {

//		setSize(365, 387);
//		setSize(365, 410);
		setSize(365, 440);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		isDisposed = false;
		setLocationRelativeTo(null);
		setLayout(null);
		
		JSplitPane sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, centerPanel);
        sp.setDividerLocation(50);
        sp.setPreferredSize(new Dimension(getWidth(), 25));
        JSplitPane sp2 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, sp, rightPanel);
        sp2.setDividerLocation(155);
        sp2.setBounds(0, 350, 365, 25);
        
        leftPanel.add(version);

        add(sp2, BorderLayout.SOUTH);

		file.add(saveGame);
		file.add(loadGame);
		file.add(newGame);
		file.add(help);

		help.add(rules);
		
		menu.add(file);
		menu.add(help);
//		menu.add(computer);	
//		computer.setToolTipText("Click this button to drop a color for computer");

		this.setJMenuBar(menu);

		this.setTitle("FourConnect");
		
//		addFileListener(this);
//		addButtonListener(this);
		circleDrawing.setBounds(0, 23, 365, 375);
		add(circleDrawing);

		moveCircleButton1.setBounds(0, 23, 50, 23);
		add(moveCircleButton1);

		moveCircleButton2.setBounds(50, 23, 50, 23);
		add(moveCircleButton2);

		moveCircleButton3.setBounds(100, 23, 50, 23);
		add(moveCircleButton3);

		moveCircleButton4.setBounds(150, 23, 50, 23);
		add(moveCircleButton4);

		moveCircleButton5.setBounds(200, 23, 50, 23);
		add(moveCircleButton5);

		moveCircleButton6.setBounds(250, 23, 50, 23);
		add(moveCircleButton6);

		moveCircleButton7.setBounds(300, 23, 50, 23);
		add(moveCircleButton7);
		
//		computer.setBounds(0, 0, 365, 23);				// Set in VierGewinntStartFrame
//		add(computer);
//		computer.setToolTipText("Click this button to drop a color for computer");
		
	}

	/**
	 * adds actionListeners
	 * 
	 * @param listener
	 */
	public void addButtonListener(ActionListener listener) {
		moveCircleButton1.addActionListener(listener);
		moveCircleButton1.setActionCommand("button0");

		moveCircleButton2.addActionListener(listener);
		moveCircleButton2.setActionCommand("button1");

		moveCircleButton3.addActionListener(listener);
		moveCircleButton3.setActionCommand("button2");

		moveCircleButton4.addActionListener(listener);
		moveCircleButton4.setActionCommand("button3");

		moveCircleButton5.addActionListener(listener);
		moveCircleButton5.setActionCommand("button4");

		moveCircleButton6.addActionListener(listener);
		moveCircleButton6.setActionCommand("button5");

		moveCircleButton7.addActionListener(listener);
		moveCircleButton7.setActionCommand("button6");

	}

	/**
	 * adds fileListeners
	 * 
	 * @param listener
	 */
	public void addFileListener(ActionListener listener) {
		loadGame.addActionListener(listener);
		loadGame.setActionCommand("Load Game");

		saveGame.addActionListener(listener);
		saveGame.setActionCommand("Save Game");

		newGame.addActionListener(listener);
		newGame.setActionCommand("Restart Game");

		help.addActionListener(listener);
		help.setActionCommand("Game Rule");

//		computer.addActionListener(listener);
//		computer.setActionCommand("Computer");

	}

	/**
	 * removes actionListeners
	 * 
	 * @param listener
	 */
	public void removeButtonListener(ActionListener listener) {
		moveCircleButton1.removeActionListener(listener);
		moveCircleButton1.setActionCommand("button0");

		moveCircleButton2.removeActionListener(listener);
		moveCircleButton2.setActionCommand("button1");

		moveCircleButton3.removeActionListener(listener);
		moveCircleButton3.setActionCommand("button2");

		moveCircleButton4.removeActionListener(listener);
		moveCircleButton4.setActionCommand("button3");

		moveCircleButton5.removeActionListener(listener);
		moveCircleButton5.setActionCommand("button4");

		moveCircleButton6.removeActionListener(listener);
		moveCircleButton6.setActionCommand("button5");

		moveCircleButton7.removeActionListener(listener);
		moveCircleButton7.setActionCommand("button6");

	}

	public void removeFileListener(ActionListener listener) {

		loadGame.removeActionListener(listener);

		saveGame.removeActionListener(listener);

		newGame.removeActionListener(listener);

		help.removeActionListener(listener);

//		computer.removeActionListener(listener);

	}
	
	@Override
	public void dispose(){
	     isDisposed = true;
	     super.dispose();       
	}
	

	@Override
	public void update(ObservableBord bord) {
		// TODO Auto-generated method stub
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand() == "button0") {

		} else if (e.getActionCommand() == "button1") {

		} else if (e.getActionCommand() == "button2") {

		} else if (e.getActionCommand() == "button3") {

		} else if (e.getActionCommand() == "button4") {

		} else if (e.getActionCommand() == "button5") {

		} else if (e.getActionCommand() == "button6") {

		}
		else if (e.getActionCommand() == "Save") {
		}
		else if (e.getActionCommand() == "Load") {

		}
	}
}
