package model;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class SpielZustand {

	public SpielZustand() {

//		File folder1 = new File("viergewinnt_backup/src/ordner");
//		File file1 = new File("viergewinnt_backup/src/ordner/text.txt");

		File folder1 = new File("C://eclipse/gameSave");
		File file1 = new File("C://eclipse/gameSave/text.txt");

		if (folder1.exists()) {
			System.out.println("ordner existiert");
		} else {
			folder1.mkdirs();
			System.out.println("Folder wurde neu erstellt");
		}

		if (file1.exists()) {
			System.out.println("Datei existiert");
		} else {
			try {
				file1.createNewFile();
			} catch (IOException e) {

				e.printStackTrace();
			}
		}

		try {
			Scanner sc = new Scanner(file1);
			while (sc.hasNext()) {
				System.out.print(sc.next() + " ");
			}
			sc.close();
		} catch (IOException e) {
			System.out.println("Datei könnte nich gefunden werden");
			e.printStackTrace();
		}

//		try {
//			OutputStream stream = new FileOutputStream(file1);
//			String textInhalt = "Hallow welt \r\nDas ist ein Test";
//			try {
//				stream.write(textInhalt.getBytes());
//				stream.close();
//				
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}

	}

	public static void main(String[] args) {
		new SpielZustand();

	}

}
