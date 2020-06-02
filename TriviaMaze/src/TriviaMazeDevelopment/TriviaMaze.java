package TriviaMazeDevelopment;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.sql.*;

import triviaMaze.Maze;
import triviaMaze.MazeFactory;
import triviaMaze.MazeOriginator;
import triviaMaze.Player;
import triviaMaze.PlayerFactory;
import triviaMaze.Room;
import triviaMaze.TriviaDatabase;

public class TriviaMaze {
	
	private static Scanner sc;
	private static MazeFactory mf;
	private static PlayerFactory pf;
	private static ArrayList<Integer> questions;
	private static boolean adminMode;
	private static int questionNum;
	private static Maze triviaMaze;
	
	private static void gameInitialize() {
		pf = new PlayerFactory();
		mf = new MazeFactory();
		Player player = pf.createPlayer(getName());
		triviaMaze = mf.createMaze(player);
		questions = new ArrayList<Integer>();
		if(player.getName().equalsIgnoreCase("admin"))
			adminMode = true;
		else {
			adminMode = false;
		}
		
		for(int i = 0; i < 25; i++)
			questions.add(new Integer(i));
		
		Collections.shuffle(questions);
		questionNum = 1;
	}
	
	public static void main(String[] args) throws SQLException {
		sc = new Scanner(System.in);
		gameInitialize();
		menu();
		sc.close();
	}
	
	private static void menu() throws SQLException {
		
		Room[][] maze = triviaMaze.getMaze();
		Player player = triviaMaze.getPlayer();
		String playAgain;
		int x = 1;
		int y = 1;
		System.out.println("\nWelcome to the Trivia Maze \n");
		
		while(!(maze[x][y].allDoorsLocked()) && player.getLives() > 0) {
			
			//re-initialize stored values
			player = triviaMaze.getPlayer();
			maze = triviaMaze.getMaze();
			int[] coords = player.getCoordinates();
			x = coords[0];
			y = coords[1];
			
			
			System.out.println("Current Maze: \n");
			printMaze();
			System.out.println(player.toString() + "\n");
			
			String option = getPlayerOption(maze[x][y]);
			int[] results = menuDirection(maze[x][y],x, y, player,option);
			
			x = results[0];
			y = results[1];
			
	
			if(x == 4 && y == 4) {
				System.out.println("\n*******************************************");
				System.out.println("\n Congratulations you have beaten the maze!");
				System.out.println("\n*******************************************");
				System.out.println("\nPlay Again? type \"yes\" or \"no\"");
				playAgain = sc.nextLine();
				if(playAgain.trim().equalsIgnoreCase("yes")) {
					gameInitialize();
					menu();
				}
				else {
					System.exit(0);
				}
			}
		}
		
		System.out.println("\n-----------------------------------------");
		System.out.println("\n               You lose!");
		System.out.println("\n-----------------------------------------");
		System.out.println("\nPlay Again? type \"yes\" or \"no\"");
		playAgain = sc.nextLine();
		if(playAgain.trim().equalsIgnoreCase("yes")) {
			gameInitialize();
			menu();
		}
		
		else {
			System.exit(0);
		}
	}
	
	private static String getPlayerOption(Room room) {
		int[] position = room.getCoordinates();
		String option;
		
		if(position[0] > 1 && room.getWalls()[0] == 0) {
			System.out.println("Type \"up\" or \"north\" to move 1 room up");
		}
		if(position[1] < 4 && room.getWalls()[1] == 0) {
			System.out.println("Type \"right\" or \"east\" to move 1 room right");
		}
		if(position[0] < 4 && room.getWalls()[2] == 0) {
			System.out.println("Type \"down\" or \"south\" to move 1 room down");
		}
		if(position[1] > 1 && room.getWalls()[3] == 0) {
			System.out.println("Type \"left\" or \"west\" to move 1 room left");
		}
		System.out.println("Type \"options\" to open the options menu");
		option = sc.nextLine();
		
		return option;
	}

	private static int[] menuDirection(Room room, int x, int y, Player player, String option) throws SQLException {
		
		int optionReturn = 0;
		int[] position = room.getCoordinates();
		int[] results = {x,y};
		boolean answer = false;
		while(optionReturn == 0) {

			/*//used for testing purposes
			if(option.equalsIgnoreCase("quit")){
				player.subtractLife();
				player.subtractLife();
				optionReturn = 1;
			}*/
	
			if((option.equalsIgnoreCase("up") || option.equalsIgnoreCase("north")) && position[0] > 1 && room.getWalls()[0] == 0) {
				
				optionReturn = 1;
				answer = askQuestion();
				if(answer == true) {
					results[1] = y;
					results[0] = x - 1;
					triviaMaze.getPlayer().setCoordinates(x - 1,  y);
				}
				else {
					triviaMaze.getMaze()[x][y].setLock(0);
					triviaMaze.getMaze()[x-1][y].setLock(2);
					if(adminMode == false)
						player.subtractLife();
				}
			}
			else if((option.equalsIgnoreCase("right") || option.equalsIgnoreCase("east")) && position[1] < 4 && room.getWalls()[1] == 0) {
				
				optionReturn = 2;
				answer = askQuestion();
				if(answer == true) {
					results[0] = x;
					results[1] = y + 1;
					triviaMaze.getPlayer().setCoordinates(x,  y + 1);
				}
				else {
					triviaMaze.getMaze()[x][y].setLock(1);
					triviaMaze.getMaze()[x][y+1].setLock(3);
					if(adminMode == false)
						player.subtractLife();
				}
			}
			else if((option.equalsIgnoreCase("down") || option.equalsIgnoreCase("south")) && position[0] < 4 && room.getWalls()[2] == 0) {
				
				optionReturn = 3;
				answer = askQuestion();
				if(answer == true) {
					results[0] = x + 1;
					results[1] = y;
					triviaMaze.getPlayer().setCoordinates(x + 1, y);
				}
				else {
					triviaMaze.getMaze()[x][y].setLock(2);
					triviaMaze.getMaze()[x+1][y].setLock(0);
					if(adminMode == false)
						player.subtractLife();
				}
			}
			else if((option.equalsIgnoreCase("left") || option.equalsIgnoreCase("west")) && position[1] > 1 && room.getWalls()[3] == 0) {
				
				optionReturn = 4;
				answer = askQuestion();
					if(answer == true) {
						results[0] = x;
						results[1] = y - 1;
						triviaMaze.getPlayer().setCoordinates(x, y - 1);
				}
				else {
					triviaMaze.getMaze()[x][y].setLock(3);
					triviaMaze.getMaze()[x][y-1].setLock(1);
					if(adminMode == false)
						player.subtractLife();
				}
			}
			else if(option.equalsIgnoreCase("options")) {
				optionReturn = 5;
				optionsMenu();
			}
			else {
				System.out.println("Invalid Option.");
				option = getPlayerOption(room);
			}
		}
		
		return results; // results[0] is going to be x coordinates, results[1] is going to be y coordinate;
	}
	
	private static String getName() {
		
		System.out.print("Please Enter your name: ");
		String name = sc.nextLine();
		
		while(name.length() > 20) {
			System.out.print("\nName must be less than 20 characters!\nPlease Enter your name: ");
			name = sc.nextLine();
		}
		
		if(name.trim().equalsIgnoreCase("admin"))
			System.out.println("Admin mode is now enabled\nAll doors ask one question and life is no longer reduced on missed questions");
		
		return name;
	}
	
	private static void printMaze() {
		
		
		int i = 1;
		
		
		while( i < 5) {
			
			ArrayList<String> topRow = triviaMaze.getMazeTopRow(i); 
			ArrayList<String> midRow = triviaMaze.getMazeMidRow(i, triviaMaze.getPlayer());
			ArrayList<String> botRow = triviaMaze.getMazeBotRow(i);
	
			for(String top : topRow) {
				System.out.print(top + " ");
			}
		
			System.out.println();
			for(String mid : midRow) {
				System.out.print(mid + " ");
			}
		
			System.out.println();
			
			for(String bot : botRow) {
				System.out.print(bot + " ");
			}
			
			System.out.println();
			i++;
		}
	}
	
	private static void optionsMenu() {
		
		System.out.println("Press 1. to save game\nPress 2. to load game\nPress 3. to quit");
		int option = sc.nextInt();
		sc.nextLine();
		
		if(option == 1) {
			saveGame(triviaMaze);
		}
		
		else if(option == 2)
			triviaMaze = loadGame().getMaze();
			
		else {
			System.exit(0);
		}
	}
	
	private static void saveGame(Maze triviaMaze) {
		
		MazeOriginator originator = new MazeOriginator();
		originator.setMaze(triviaMaze);
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
		}
		
		catch (IOException e) {
			e.printStackTrace();
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
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return savedGame;
	}
	
	private static boolean askQuestion() throws SQLException {
		
		int questionIndex = questions.get(questionNum);
		TriviaDatabase db = new TriviaDatabase();
		String question;
		String answer;
		ArrayList<String> options;
		
		if(adminMode == true) {
			db.changeTable();
			question = db.getQuestion(questionNum);
			answer = db.getAnswer(questionNum);
			int idCount = db.getIDCount();
			options = db.getOptions(questionNum); 
		}
		else {
			question = db.getQuestion(questionIndex);
			answer = db.getAnswer(questionIndex);
			int idCount = db.getIDCount();
			options = db.getOptions(questionIndex);
			questionNum++;
		}
		
		System.out.println("Question: " + question);
		
		for(int i = 0; i < options.size(); i++) {
			
			System.out.println(i + 1 + ". " + options.get(i));
			
		}
		
		int userAnswer = 0;
		try {
			userAnswer = Integer.parseInt(sc.nextLine()) - 1;
			while(userAnswer >= options.size() || userAnswer < 0) {
				System.out.println("Please enter valid number");
				userAnswer = Integer.parseInt(sc.nextLine()) - 1;
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		
		
		if(options.get(userAnswer).equals(answer)) {
			System.out.println("Answer is Correct!\n");
			return true;
		}
		else {
			System.out.println("Answer is Incorrect!\n");
			return false;
		}
	}

	
	
}
