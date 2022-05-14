package model;

import java.awt.Color;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

import view.JOptionFrame;
import view.VierGewinntStartFrame;

public abstract class GameObject {
	
	public static List<Token> lager;
	public static List<Cell> gameStand = new ArrayList<>();						//for game save	
	protected static List<String> test = new ArrayList<String>();     	 		//for game load
	protected static Cell[] saved = new Cell[42];
	Cell c = new Cell();
	private static String path1 = "C://eclipse/gameSave/playervscomputer.txt";     
	private static String path2 = "C://eclipse/gameSave/playervsplayer.txt";
	private static String path3 = "C://eclipse/gameSave/computervscomputer.txt";
	private static String path4 = "C://eclipse/gameSave/playervscomputertmp.txt";  //Extra for computerPlayer
	private static String path5 = "C://eclipse/gameSave/playervscomputertmp1.txt";  //Extra for framePlayer
	public static int state = 0;
	public static int countSaved = 0;
	public static int countLoaded = 0;
	public static int countRestarted = 0;
	public static int wrongPlayer = 0;
	private static File folder;
	private static File file;
	
	private static File file1;   //extra for player1
	private static File file2;   //extra for player1
	
//	private String ip = "Local horst";
//	private int port  = 2222;
	
//	private Socket clientSocket;
//	private ServerSocket serverSocket;
	
//	private DataOutputStream dos;
//	private DataInputStream dis;
	
	
	
	
	public String tmpColor = "";       				//the last played colour before a save
	
	public static Token.Color dangerColor;  		//vertical
	public static int dangerCol;					//vertical  used in bord
	
	public static int dangerCol2;					//vertical used in framePlayer	
	public static Token.Color dangerColor2; 		//vertical
	
	public abstract void dropToken(Token farbe, int columnIndex);
	
	@Override
	public String toString() {
		String board = "";
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				board += "[";
				if (Bord.bord[i][j] == null) {
					board += "_";
				} else if (Bord.bord[i][j].getTokenColor().getFarbe().toString() == "RED") {
					board += "X";
				} else if (Bord.bord[i][j].getTokenColor().getFarbe().toString() == "YELLOW") {
					board += "O";
				} else {
					board += " ";
				}
				board += "]";
			}
			board += "\n";
		}
		return board;
	}
	
	/**
	 * converts an colour enum to a colour java.awt
	 * 
	 * @param tokenColor
	 * @return colour
	 */
	public Color tokenToSwing(Token tokenColor) {
		if (tokenColor.getFarbe() == c.getRed().getFarbe()) {
			return Color.RED;
		} else if (tokenColor.getFarbe() == c.getYellow().getFarbe()) {
			return Color.YELLOW;
		}

		return Color.WHITE;
	}
	
	
	/**
	 * saves game path
	 * 
	 * @param path
	 */
	public void save() {
		String path = "";
		switch (state) {
			case (1):				
				path = path1;
				break;	
			case (2):
				path = path2;
				break;	
			case (3):
				path = path3;
				break;
		}

		folder = new File("C://eclipse/gameSave");
		file = new File(path);
		
		//TODO										//START
		file1 = new File(path4);
		file2 = new File(path5);
		if(state == 1) {
			if (file1.exists() == false && file2.exists()) {
				try {
					file1.createNewFile();
					file2.createNewFile();
				}catch(IOException e) {
					e.printStackTrace();
				}
				
			}
		}											//END
						
		if (folder.exists() == false && file.exists() == false) {
			try {				
				folder.mkdirs();
				file.createNewFile();
				System.out.println("Folder wurde neu erstellt");
			} catch (IOException e) {

				e.printStackTrace();
			}
		} else {
			System.out.println("ordner existiert");
			System.out.println("Datei existiert");
		}
		
		boolean saved = false;
		List<String> textInhalt = new ArrayList<String>();
		if (lager.size() < 42) {
			for (int i = 0; i < gameStand.size(); i++) {

				textInhalt.add(gameStand.get(i).getCol() + " " + gameStand.get(i).getRow() + " "
						+ gameStand.get(i).getTokenColor().getFarbe() + "\n");

			}
			try {
//				file.createNewFile();
				OutputStream stream = new FileOutputStream(path);
				for (int j = 0; j < textInhalt.size(); j++) {
					stream.write(textInhalt.get(j).getBytes());
					saved = true;
				}

				stream.close();
				
				OutputStream stream1 = new FileOutputStream(path4);		//START
				String tmp = "";
				tmp += dangerColor2.toString() + " " + dangerCol2;
				stream1.write(tmp.getBytes());
				stream1.close();	
				
				OutputStream stream2 = new FileOutputStream(path5);
				String tmp1 = "";
				tmp1 += dangerColor.toString() + " " + dangerCol;
				stream2.write(tmp1.getBytes());
				stream2.close();
																		//END

			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			countSaved++;
			if (countSaved == 1) {
				JOptionPane.showMessageDialog(new JOptionFrame(), "            Play to Save");
				System.out.println("You must play to save game ");
			}

		}

		if (saved == true) {
			countSaved++;
			if (countSaved == 1) {
				JOptionPane.showMessageDialog(new JOptionFrame(), "           Game Saved");
				System.out.println("Game saved");
			}

		}

	}
	

	/**
	 * loads game path
	 * 
	 * @param path
	 * @throws IOException
	 */
	public void load() {
		String path = "";
		switch (state) {
			case (1):				
				path = path1;
				break;	
			case (2):
				path = path2;
				break;	
			case (3):
				path = path3;
				break;
		}
				
		// TODO
		file1 = new File(path4); // START
		file2 = new File(path5);
		List<String> tmp = new ArrayList<String>();
		List<String> tmp1 = new ArrayList<String>();
		if (state == 1) {
			try {
				Scanner s = new Scanner(file1);
				while (s.hasNext()) {
					tmp.add(s.next());
				}
				s.close();

				Scanner s1 = new Scanner(file2);
				while (s1.hasNext()) {
					tmp1.add(s1.next());
				}
				s1.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

			if (tmp.get(0).equals("YELLOW")) {
				dangerColor2 = Token.Color.YELLOW;
			} else if (tmp.get(0).equals("RED")) {
				dangerColor2 = Token.Color.RED;
			}
			dangerCol2 = Integer.parseInt(tmp.get(1));

			if (tmp1.get(0).equals("YELLOW")) {
				dangerColor = Token.Color.YELLOW;
			} else if (tmp1.get(0).equals("RED")) {
				dangerColor = Token.Color.RED;
			}
			dangerCol = Integer.parseInt(tmp1.get(1)); // END

		}
		
		file = new File(path);
		try {
			Scanner sc = new Scanner(file);
			while (sc.hasNext()) {
				
				test.add(sc.next());
			}
			sc.close();
		} catch (IOException e) {
//			JOptionPane.showMessageDialog(new JOptionFrame(), "         " + e.getMessage());
			e.printStackTrace();
		}
		int count = 0;
		int count1 = 0;
		for (int j = 0; j < test.size(); j++) {
			if (count1 == 0) {
				saved[count] = new Cell();
			} else if (count1 == 3) {
				count++;
				saved[count] = new Cell();
				count1 = 0;
			}

			if (test.get(j).equals("RED")) {
				saved[count].setTokenColor(c.getRed());
				tmpColor = "RED";
			} else if (test.get(j).equals("YELLOW")) {
				saved[count].setTokenColor(c.getYellow());
				tmpColor = "YELLOW";
			}

			else if (Integer.parseInt(test.get(j)) >= 0 && Integer.parseInt(test.get(j)) <= 6) {
				if (count1 == 0) {
					saved[count].setCol(Integer.parseInt(test.get(j)));
				} else if (count1 == 1) {
					saved[count].setRow(Integer.parseInt(test.get(j)));
				}

			}
			count1++;
		}

		if (lager.size() == 42) {
			for (int i = 0; i < saved.length; i++) {
				if (saved[i] != null) {

					if (Bord.bord[saved[i].getRow()][saved[i].getCol()] == null) {
						dropToken(saved[i].getTokenColor(), saved[i].getCol());
						CircleBord.bord[saved[i].getRow()][saved[i].getCol()]
								.setColor(tokenToSwing(saved[i].getTokenColor()));

						countLoaded++;
						if (countLoaded == 1) {
//							JOptionPane.showMessageDialog(new JOptionFrame(), "          Game Loaded");
							System.out.println("Game loaded");
							System.out.println(toString());
						}
					}
				} else {
					countLoaded++;
					if (countLoaded == 1) {
						JOptionPane.showMessageDialog(new JOptionFrame(), "              No Saves");
						System.out.println("No saved game");
					}
				}
			}
		} else {
			countLoaded++;
			if (countLoaded == 1) {
				JOptionPane.showMessageDialog(new JOptionFrame(), "  Already Loaded/Started");
				System.out.println("Game already loaded or started");
			}
		}

	}

	/**
	 * deletes saved game
	 * 
	 * @param path
	 */
	public void deleteSave() {
		String path = "";
		switch (state) {
			case (1):				
				path = path1;
				break;	
			case (2):
				path = path2;
				break;	
			case (3):
				path = path3;
				break;
		}

		file = new File(path);
		if (file.exists()) {
			try {
				OutputStream stream = new FileOutputStream(file);
				String textInhalt = "";
				try {
					stream.write(textInhalt.getBytes());
					stream.close();

					VierGewinntStartFrame.message.setBounds(143, 10, 120, 30);
					VierGewinntStartFrame.message.setText("Save Deleted");
					System.out.println("Game deleted");

				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			
			VierGewinntStartFrame.message.setBounds(155, 10, 120, 30);
			VierGewinntStartFrame.message.setText("No Saves");
			System.out.println("No saved game");
		}
	}

	/**
	 * clears the game frame during a restart game
	 */
	public void clearFrame() {

		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {

				CircleBord.bord[i][j].setColor(Color.WHITE);

			}
		}
	}

	/**
	 * restarts game
	 */
	public void restartGame() {

		new Bord();
		gameStand = new ArrayList<>();
		test = new ArrayList<String>();
		saved = new Cell[42];
		clearFrame();
		countRestarted++;
		

		if (countRestarted == 1) {
//			JOptionPane.showMessageDialog(new JOptionFrame(), "       Game Restarted");
			System.out.println("Game Restarted");
		}

	}
}
