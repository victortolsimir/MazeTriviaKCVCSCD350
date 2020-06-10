/*  Authors: Victor, Kayla, Caleb 
 *  CSCD 350
*/

package triviaMaze;
import java.sql.*;
import java.util.ArrayList;

public class TriviaDatabase {
	
	Connection c = null;
	Statement stmt = null;
	ResultSet rs = null;
	private String currentTable;
	
	/* intitializes the trivia database and sets currentTable.
	*/
	public TriviaDatabase() throws SQLException{
		initialize();
		currentTable = "TriviaMazeQuestions";
	}
	
	//  connects the sqlite to the TriviaMazeQuestions.db database.
	private void initialize() throws SQLException{
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:TriviaMazeQuestions.db");
			//System.out.println("Opened database successfully");
			
		}
		catch(Exception e) {
			System.err.println(e.getClass().getName() +": "+e.getMessage());
			System.exit(0);
		}
		
		//System.out.println("Operation done successfully");
	}
	
	// changes current database table to the other database table, so if we are on AdminQuestions, we switch to TriviaMazeQuestions.
	public void changeTable() {
		if(currentTable.equals("TriviaMazeQuestions")) {
			currentTable = "AdminQuestion";
		}
		else {
			currentTable = "TriviaMazeQuestions";
		}
	}
	
	// getQuestion() executes a query using SQLite to select the question from the database with the same id as the one passed in. It returns a string of the question.
	public String getQuestion(int id) throws SQLException{
		String result = "";
		stmt = c.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM "+currentTable+" WHERE ID = "+id+";");
        
        while(rs.next()) {
        	result = rs.getString("Question");
        }
        
        return result;
	}
	
	// getAnswer() executes a query using SQLite to select the answer from the database with the same id as the one passed in. It returns a string of the answer.
	public String getAnswer(int id) throws SQLException{
		String result = "";
		stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM "+currentTable+" WHERE ID = "+id+";");
		while(rs.next()) {
			result = rs.getString("Answer");
		}
		return result;
	}
	
	// getOptions executes a query using SQLite to select the options from the database with the same id as the one passed in. It returns an ArrayList of the options.
	public ArrayList<String> getOptions(int id) throws SQLException{
		ArrayList<String> result = new ArrayList<String>();
		String temp = "";
		stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM "+currentTable+" WHERE ID = "+id+";");
		while(rs.next()) {
			temp = rs.getString("Options");
		}
		
		//split options
		String[] options = temp.split(";");
		for(String s: options) {
			result.add(s);
		}
		return result;
	}
	
	// getType executes a query using SQLite to select the type column with the same id as the one passed it. it returns a String of the type.
	public String getType(int id) throws SQLException{
		stmt = c.createStatement();
		String result = "";
		ResultSet rs = stmt.executeQuery("SELECT * FROM "+currentTable+" WHERE ID = "+id+";");
		while(rs.next()) {
			result = rs.getString("Type");
		}
		return result;
	}
	
	// addTableItem inserts the question, answer, options, and type into the database.
	public void addTableItem(String question, String answer, ArrayList<String> options, String type) throws SQLException {
		
		stmt = c.createStatement();
		
		String option = "";
		if(options.isEmpty()) {
			option = null;
		}
		else {
			for(int i = 0; i < options.size(); i++) {
				option+=options.get(i);
				if(i != options.size()-1) {
					option+=";";
				}
			}
		}
		
		int idCount = getIDCountTD();
		int id = idCount+1;
		
		
		String sql = "INSERT INTO TriviaMazeQuestions (Question,Answer,Options,ID,Type) "+
				"VALUES ("+" \""+question+"\", \""+answer+"\", \""+option+"\", "+id+", \""+type+"\" );";
		stmt.executeUpdate(sql);
		
	}
	
	// getIDCount executes a query using SQLite to select the type column with the same id as the one passed it. it returns a String of the type.
	public int getIDCount() throws SQLException {
		
		int result = 0;
		stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT COUNT(ID) FROM "+currentTable+";");
		while(rs.next()) {
			result = rs.getInt("COUNT(ID)");
		}
		return result;
        
	}
	
	//getIDCountTD executes a query using SQLite to select the IDCount from the TriviaMazeQuestions table specifically. It returns an Int of the IDCount.
	private int getIDCountTD() throws SQLException {
		
		int result = 0;
		stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT COUNT(ID) FROM TriviaMazeQuestions;");
		
		while(rs.next()) {
			result = rs.getInt("COUNT(ID)");
		}
		return result;
	}
	
}
