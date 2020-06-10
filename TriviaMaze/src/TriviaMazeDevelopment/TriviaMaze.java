/* Cheat Documentation:
 *  To enable cheats, enter "admin" as your name when prompted. When this is activated,
 *  the maze will only ask one true/false question. To move through type true, and to fail chose false. 
 *  Admin mode also does not reduce health when failing to answer a question correctly. This will be used for 
 *  testing the maze and it's features as well as testing the algorithm to check if there is a path open to the exit.
 *  if there is no path to the exit, then the game will end.
 *  
 *  This class was made for the sole purpose of testing.
 * 
*/

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
	
	public static Scanner sc;
	public static MazeFactory mf;
	public static PlayerFactory pf;
	public static ArrayList<Integer> questions;
	public static boolean adminMode;
	public static int questionNum;
	public static Maze triviaMaze;
	public static TriviaDatabase db;
	public static Player player;
	
	public static void gameInitialize() throws SQLException {
		sc = new Scanner(System.in);
		pf = new PlayerFactory();
		mf = new MazeFactory();
		player = pf.createPlayer(getName());
		triviaMaze = mf.createMaze(player);
		questions = new ArrayList<Integer>();
		db = new TriviaDatabase();
		
		if(player.getName().equalsIgnoreCase("admin")) {
			db.changeTable();
			adminMode = true;
			questions.add(new Integer(1));
			questionNum = 1;
		}
		else {
			adminMode = false;
			shuffle();
		}
		
	}
	
	public static void shuffle() throws SQLException {

		int count = db.getIDCount();
		for(int i = 1; i < count+1; i++)
			questions.add(new Integer(i));
		Collections.shuffle(questions);
		
		questionNum = 1;
	}

	public static void main(String[] args) throws SQLException {
		gameInitialize();
		menu();
		sc.close();
	}
	
	public static void menu() throws SQLException {
		
		Room[][] maze = triviaMaze.getMaze();
		Player player = triviaMaze.getPlayer();
		String playAgain;
		int x = 1;
		int y = 1;
		System.out.println("\n***********************************************************************************************");
		System.out.println("\nWelcome to the Trivia Maze!\n\nYou need to escape the maze by reaching the exit.\nEach door you try to pass will ask you a trivia question, answer correctly and you may pass.\nMiss two questions and you will lose.");
		System.out.println("\n***********************************************************************************************\n");
		boolean path = true;
		
		while(!(maze[x][y].allDoorsLocked()) && player.getLives() > 0 && path) {
			
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
			
			path = triviaMaze.traverse(x,y, new boolean[16], triviaMaze.getMaze()[x][y].getRoomNum() - 1);
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
					sc.close();
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
			sc.close();
			System.exit(0);
		}
	}
	
	public static String getPlayerOption(Room room) {
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

	public static int[] menuDirection(Room room, int x, int y, Player player, String option) throws SQLException {
		
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
	
	public static String getName() {
		
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
	
	public static void printMaze() {
		
		
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
	
	public static void optionsMenu() throws SQLException {
		
		System.out.println("Press 1. to save game\nPress 2. to load game\nPress 3. add your own question\nPress 4. to quit");
		int option = sc.nextInt();
		sc.nextLine();
		
		if(option == 1) {
			saveGame(triviaMaze);
		}
		
		else if(option == 2)
			triviaMaze = loadGame().getMaze();
			
		else if(option == 3) {
			addTableItem();
			if(adminMode == false)
				shuffle();
			
		}
		else {
			System.exit(0);
		}
	}
	
	public static void saveGame(Maze triviaMaze) {
		
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
	
	public static boolean askQuestion() throws SQLException {
		
		int questionIndex = questions.get(questionNum - 1);
		String question;
		String answer;
		ArrayList<String> options;
		String type;
		
		if(adminMode == true) {
			question = db.getQuestion(questionNum);
			answer = db.getAnswer(questionNum);
			//int idCount = db.getIDCount();
			options = db.getOptions(questionNum); 
			type = db.getType(questionNum);
		}
		else {
			question = db.getQuestion(questionIndex);
			answer = db.getAnswer(questionIndex);
			//int idCount = db.getIDCount();
			options = db.getOptions(questionIndex);
			type = db.getType(questionIndex);
			questionNum++;
		}
		
		System.out.println("Question: " + question);
		
		//int userAnswer = 0;
		int userAnswer = 0;
		if(type.equals("multipleChoice") || type.equals("trueFalse")) {
			
			for(int i = 0; i < options.size(); i++) {
				System.out.println(i + 1 + ". " + options.get(i));
			}
			
			try {
				userAnswer = Integer.parseInt(sc.nextLine()) - 1;

				while(userAnswer >= options.size() || userAnswer < 0) {
					System.out.println("Please enter valid number");
					userAnswer = Integer.parseInt(sc.nextLine()) - 1;
				}
			} catch (NumberFormatException e) {
				System.out.println("Invalid option. ");
				System.out.println("Answer is Incorrect!\n");
				return false;
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
		
		else {
			String userAnswerShort = sc.nextLine().trim();
			if(userAnswerShort.equalsIgnoreCase(answer)) {
				System.out.println("Answer is Correct!\n");
				return true;
			}
			else {
				System.out.println("Answer is Incorrect!\n");
				return false;
			}
		}
		
	}
	
	public static void resetScanner() {
		sc = new Scanner(System.in);
	}

	public static void addTableItem() throws SQLException {
		
		//Scanner sc = new Scanner(System.in);
		String question;
		String answer;
		String type = "null";
		String option;
		int numOptions;
		ArrayList<String> options = new ArrayList<String>();
	
		//get question type
		System.out.println("What 'Type' of question is this?");
		System.out.println("1. True/False");
		System.out.println("2. Multiple Choice");
		System.out.println("3. Short answer");
		option = sc.nextLine();
		while(!option.equals("1") && !option.equals("2") && !option.equals("3")) {
			System.out.println("Invalid input. Please enter 1, 2, or 3.");
			option = sc.nextLine();
		}
		if(option.equals("1")) {type = "trueFalse";}
		if(option.equals("2")) {type = "multipleChoice";}
		if(option.equals("3")) {type = "shortAnswer";}
		
		//get question
		System.out.println("\nEnter a question: ");
		question = sc.nextLine();
		question = question.replaceAll("\"", "\'"); 
		question = question.replaceAll(";", ",");
		
		//get answer
		System.out.println("Enter an answer: ");
		answer = sc.nextLine();
		answer = answer.replaceAll("\"", "\'"); 
		answer = answer.replaceAll(";", ",");
		if(type.equals("trueFalse")) {
			answer = answer.substring(0,1).toUpperCase() + answer.substring(1,answer.length()) ;
			while(!answer.equalsIgnoreCase("True") && !answer.equalsIgnoreCase("False")) {
				System.out.println("Invalid input. Please enter either 'True' or 'False'.");
				System.out.println("Enter an answer: ");
				answer = sc.nextLine();
				answer = answer.substring(0,1).toUpperCase() + answer.substring(1,answer.length()) ;
				answer = answer.replaceAll("\"", "\'"); 
				answer = answer.replaceAll(";", ",");
			}
		}
		
		//set options
		if(type.equals("trueFalse")) {
			options.add("True");
			options.add("False");
		}
		else if(type.equals("multipleChoice")) {
			System.out.println("How many additional options would you like to add? (1-4)");
			String input = sc.nextLine();
			while( !(input.equals("1") || input.equals("2")|| input.equals("3")|| input.equals("4"))) {
				System.out.println("Invalid integer, please enter a number from 1 to 4.");
				input = sc.nextLine();
			}
			numOptions = Integer.parseInt(input);
			
			for(int i = 1; i <= numOptions; i++) {
				System.out.println("Enter option "+i+":");
				option = sc.nextLine();
				option = option.replaceAll("\"", "\'"); 
				option = option.replaceAll(";", ",");
				options.add(option);
			}
			options.add(answer);
			Collections.shuffle(options);
		}
		
		//review table item
		
		System.out.println("\nReview your table item:");
		System.out.println("--------------------------");
		System.out.println("Question: "+question);
		System.out.println("Answer: "+answer);
		if(!options.isEmpty()) {
			System.out.println("Options: ");
			for(int i = 0; i < options.size(); i++) {
				System.out.println((i+1)+". "+options.get(i));
			}
		}
		
		
		System.out.println("Would you like to add this item to the question database? (Y/N)");
		String confirmation = sc.nextLine();
		boolean confirmed = false;
		while(confirmed == false) {
			if(confirmation.equalsIgnoreCase("y")) {
				db.addTableItem(question, answer, options,type);
				System.out.println("Item added to database!");
				confirmed = true;
			}
			else if(confirmation.equalsIgnoreCase("n")) {
				System.out.println("Item not added to database.");
				confirmed = true;
			}
			else {
				System.out.println("Invalid input.");
				System.out.println("Would you like to add this item to the question database? (Y/N)");
				confirmation = sc.nextLine();
			}
			
		}	
	}
	
}
