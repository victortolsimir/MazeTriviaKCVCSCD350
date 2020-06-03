package triviaMaze;
import java.sql.*;
import java.util.ArrayList;

public class TriviaDatabase {
	Connection c = null;
	Statement stmt = null;
	ResultSet rs = null;
	private String currentTable;
	
	TriviaDatabase() throws SQLException{
		initialize();
		currentTable = "TriviaMazeQuestions";
	}
	
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
	
	public void changeTable() {
		if(currentTable.equals("TriviaMazeQuestions")) {
			currentTable = "AdminQuestion";
		}
		else {
			currentTable = "TriviaMazeQuestions";
		}
	}
	
	public String getQuestion(int id) throws SQLException{
		String result = "";
		stmt = c.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM "+currentTable+" WHERE ID = "+id+";");
        
        while(rs.next()) {
        	result = rs.getString("Question");
        }
        
        return result;
	}
	
	public String getAnswer(int id) throws SQLException{
		String result = "";
		stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM "+currentTable+" WHERE ID = "+id+";");
		while(rs.next()) {
			result = rs.getString("Answer");
		}
		return result;
	}
	
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
	
	public String getType(int id) throws SQLException{
		stmt = c.createStatement();
		String result = "";
		ResultSet rs = stmt.executeQuery("SELECT * FROM "+currentTable+" WHERE ID = "+id+";");
		while(rs.next()) {
			result = rs.getString("Type");
		}
		return result;
	}
	
	public void addTableItem(String question, String answer, ArrayList<String> options, String type) throws SQLException{
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
	
	public int getIDCount() throws SQLException {
		int result = 0;
		stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT COUNT(ID) FROM "+currentTable+";");
		while(rs.next()) {
			result = rs.getInt("COUNT(ID)");
		}
		return result;
        
	}
	
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
