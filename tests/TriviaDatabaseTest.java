package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import triviaMaze.TriviaDatabase;

/*Authors: Kayla Rubin, Victor Vargas, Caleb Stanley
Description: This class tests the ability of TriviaDatabase class to interact with the database*/

class TriviaDatabaseTest {
	
	TriviaDatabase db;
	
	@BeforeEach
	void setUp() throws SQLException {
		db = new TriviaDatabase();
	}
	
	//test ability to grab question based on id
	@Test
	void getQuestion() throws SQLException{
		String question = db.getQuestion(10);
		assertEquals("What does Lilo put in Stitch’s bottle in Lilo & Stitch?",question);
		
		//get question for id that doesn't exist
		question = db.getQuestion(0);
		assertEquals("",question);
	}
	
	//Test ability to alternate between tables
	@Test
	void changeTable() throws SQLException{
		//test change to admin table
		db.changeTable();
		String question = db.getQuestion(1);
		assertEquals("This is admin mode?",question);
		
		//test change to TriviaMazeQuestions table
		db.changeTable();
		question = db.getQuestion(1);
		assertEquals("What color is Alice’s dress in Disney’s Alice in Wonderland?",question);
		
	}
	
	//Test ability to get answer based on id
	@Test
	void getAnswer() throws SQLException{
		String answer = db.getAnswer(8);
		assertEquals("A llama",answer);
		
		//get answer for id that doesn't exist
		answer = db.getAnswer(0);
		assertEquals("",answer);
		
	}
	
	//Test ability to get options based on id
	@Test
	void getOptions() throws SQLException{
		ArrayList<String> temp = db.getOptions(3);
		String options = "";
		
		for(int i = 0; i < temp.size(); i++) {
			if(i == temp.size()-1) {
				options += temp.get(i);
			}
			else {
				options += temp.get(i)+";";
			}
		}
		
		assertEquals("Gaston;Maurice;Geppetto;Pierre",options);
	}
	
	@Test
	//Test ability to grab type by id
	void getType() throws SQLException{
		String type = db.getType(1);
		assertEquals("multipleChoice",type);
	}
	
	@Test
	//Test ability to get the total number of items in table
	void getIDCount() throws SQLException{
		db.changeTable();
		int count = db.getIDCount();
		assertEquals(1,count);
	}
	
	

}
