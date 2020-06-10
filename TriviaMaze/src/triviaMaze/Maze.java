/*
 * Authors: Kayla Rubin, Victor Vargas, Caleb Stanley
 * Description: Contains a 2-D array to represent the maze and a Player that is within the maze.
 * Contains all methods to create a Maze object that is serializable, traversing the maze to see if there is a path to the exit, and
 * a string representation of the 2-D maze
 * 
 */
package triviaMaze;

import java.io.Serializable;
import java.util.ArrayList;

public class Maze implements Serializable {

	private Room[][] maze;
	private Player player;
	
	/*
	 * Creates a Maze object
	 */
	public Maze(Player thePlayer) {
		this.maze = new Room[6][6];
		this.player = thePlayer;
		createMaze();
	}
	
	/*
	 * Retrieves the player field and returns it
	 */
	public Player getPlayer() {
		return this.player;
	}
	
	/*
	 * Fills the 2-D representation of the Maze with rooms and sets the exit
	 */
	private void createMaze() {
		
		 int count = 1;
		 for(int i = 1; i < this.maze.length - 1; i++) {
			 for(int j = 1; j < this.maze.length - 1; j++) {
				 this.maze[i][j] = new Room(i, j, count);
				 count++;
			 }
		 }
		 this.maze[4][4].setExit();
	}
	
	/*
	 * Retrieves the 2-D array of Rooms 
	 */
	public Room[][] getMaze() {
		return this.maze;
	}
	
	public String toString() {
		String maze = "";
		for(int i = 1; i < this.maze.length - 1; i++) {
			 for(int j = 1; j < this.maze.length - 1; j++) {
				 maze += this.maze[i][j].toString();
			 }
		 }
		return maze;
	}
	
	/*
	 * Following 3 methods are used for separating the top, middle, and bottom levels of each room
	 * and storing them into an ArrayList to be used for printing @printMaze() in TriviaMaze
	 */
	public ArrayList<String> getMazeTopRow(int row) {
		
		ArrayList<String> list = new ArrayList<String>();
		for(int i = 1; i < 5; i++) {
			list.add(this.maze[row][i].getTopRow());
		}
		return list;
		
	}
	
	public ArrayList<String> getMazeMidRow(int row, Player player) {
		
		ArrayList<String> list = new ArrayList<String>();
		for(int i = 1; i < 5; i++) {
			int[] array = {row, i};
			list.add(this.maze[row][i].getMidRow(player, array));
		}
		return list;
		
	}
	
	public ArrayList<String> getMazeBotRow(int row) {
		
		ArrayList<String> list = new ArrayList<String>();
		for(int i = 1; i < 5; i++) {
			list.add(this.maze[row][i].getBotRow());
		}
		return list;
		
	}
	
	/*
	 * Traverses the maze to check if there is a possible path to the exit of the maze
	 */
	public boolean traverse(int curX, int curY, boolean[] visited, int currentIndex) {
		
		boolean success = false;
		Room room = this.maze[curX][curY];
		
		//if current room is the exit then there is a path
		if(room.isExit()) {
			return true;
		}
		
		//mark the current room as visited
		visited[currentIndex] = true;
		
		//going down first
		if(currentIndex + 4 < visited.length && !room.bottomLocked() && !visited[currentIndex + 4] && !success) {
			
			success = traverse(curX + 1, curY, visited, currentIndex + 4);
		}
		
		//going right when down doesn't work
		if(currentIndex + 1 < visited.length && !room.rightLocked() && !visited[currentIndex + 1] && !success) {
			
			success = traverse(curX, curY + 1, visited, currentIndex + 1);
		}
		
		
		//going left when down and right don't work
		if(currentIndex - 1 >= 0 && !room.leftLocked() && !visited[currentIndex - 1] && !success) {
			
			success = traverse(curX, curY - 1, visited, currentIndex - 1);
		}
		
		//going up when no other direction works
		if(currentIndex - 4 >= 0 && !room.topLocked() && !visited[currentIndex - 4] && !success) {
			
			success = traverse(curX - 1, curY, visited, currentIndex - 4);
		}
		
		return success;
		
	}
}
