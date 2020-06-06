package triviaMaze;

import java.sql.SQLException;
import java.util.ArrayList;

import java.util.*;

public class DatabaseTester {
	public static void main(String[] args) throws SQLException {
		TriviaDatabase db = new TriviaDatabase();
		String type;
		String question;
		String answer;
		ArrayList<String> options;
		int idCount;
		
		idCount = db.getIDCount();
		type = db.getType(3);
		question = db.getQuestion(3);
		answer = db.getAnswer(3);
		options = db.getOptions(3);
		System.out.println("Number of items in table: "+idCount);
		System.out.println("Type: "+type);
		System.out.println("Question: "+question);
		System.out.println("Options: ");
		for(int i = 0; i < options.size(); i++) {
			System.out.println(i+1+". "+options.get(i));
		}
		System.out.println("Answer: "+answer);
		
		System.out.println("-----------------------------------------------------");
		db.changeTable();
		
		idCount = db.getIDCount();
		type = db.getType(1);
		question = db.getQuestion(1);
		answer = db.getAnswer(1);
		options = db.getOptions(1);
		System.out.println("Number of items in table: "+idCount);
		System.out.println("Type: "+type);
		System.out.println("Question: "+question);
		System.out.println("Options: ");
		for(int i = 0; i < options.size(); i++) {
			System.out.println(i+1+". "+options.get(i));
		}
		System.out.println("Answer: "+answer);
		
		System.out.println("-----------------------------------------------------");
		addTableItem(db);
	}

	private static void addTableItem(TriviaDatabase db) throws SQLException {
		Scanner sc = new Scanner(System.in);
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
