package triviaMaze;

import java.sql.SQLException;
import java.util.ArrayList;

public class DatabaseTester {
	public static void main(String[] args) throws SQLException {
		TriviaDatabase db = new TriviaDatabase();
		String question;
		String answer;
		ArrayList<String> options;
		int idCount;
		
		idCount = db.getIDCount();
		question = db.getQuestion(3);
		answer = db.getAnswer(3);
		options = db.getOptions(3);
		System.out.println("Number of items in table: "+idCount);
		System.out.println("Question: "+question);
		System.out.println("Options: ");
		for(int i = 0; i < options.size(); i++) {
			System.out.println(i+1+". "+options.get(i));
		}
		System.out.println("Answer: "+answer);
		
		System.out.println("-----------------------------------------------------");
		db.changeTable();
		
		idCount = db.getIDCount();
		question = db.getQuestion(1);
		answer = db.getAnswer(1);
		options = db.getOptions(1);
		System.out.println("Number of items in table: "+idCount);
		System.out.println(question);
		System.out.println("Options: ");
		for(int i = 0; i < options.size(); i++) {
			System.out.println(i+1+". "+options.get(i));
		}
		System.out.println("Answer: "+answer);
	}
}
