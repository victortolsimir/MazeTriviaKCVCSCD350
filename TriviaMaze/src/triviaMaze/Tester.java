package triviaMaze;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Tester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Player player = new Player(0, 0, "C");
		Maze maze = new Maze(player);
		
		System.out.print(maze);
		
		saveGame(maze);
		
		MazeOriginator ori = loadGame();
		
		maze = ori.getMaze();
		

	}
	
	public static void saveGame(Maze maze) {
		MazeOriginator originator = new MazeOriginator();
		originator.setMaze(maze);
		MazeOriginator.MazeMemento memento = originator.saveState();
		String filename = "saveFile.ser";
		
		try {
			FileOutputStream outputFile = new FileOutputStream(filename);
			ObjectOutputStream out = new ObjectOutputStream(outputFile);
			out.writeObject(memento);
			out.close();
			System.out.println("Game was saved!");
		}
		
		catch (FileNotFoundException e) {
			e.printStackTrace();
			//System.out.println("File not foud");
		}
		
		catch (IOException e) {
			e.printStackTrace();
			//System.out.println("IO exception in saveGame");
		}
		
	}
	
	public static MazeOriginator loadGame() {
		
		String fileName = "saveFile.ser";
		MazeOriginator savedGame = new MazeOriginator();
		
		try {
			FileInputStream inf = new FileInputStream(fileName);
			ObjectInputStream in = new ObjectInputStream(inf);
			MazeOriginator.MazeMemento memento = (MazeOriginator.MazeMemento) in.readObject();
			savedGame.loadState(memento);
			in.close();
			System.out.println("Game was loaded!");
		}
		
		catch (FileNotFoundException e) {
			System.out.println("File does not exist");
			return null;
		}
		catch (IOException e) {
			e.printStackTrace();
			//System.out.println("IO Exception while loading game");
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			//System.out.println("Class not found");
		}
		
		return savedGame;
		
	}

}
