/*
 * Authors: Kayla Rubin, Victor Vargas, Caleb Stanley
 */
package triviaMaze;

import java.io.Serializable;
import java.util.ArrayList;

public class Maze implements Serializable {

	private Room[][] maze;
	private Player player;
	
	public Maze(Player thePlayer) {
		//really a 3x3 maze
		this.maze = new Room[6][6];
		this.player = thePlayer;
		createMaze();
	}
	
	public Player getPlayer() {
		return this.player;
	}
	
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
	
	public String toString() {
		String maze = "";
		for(int i = 1; i < this.maze.length - 1; i++) {
			 for(int j = 1; j < this.maze.length - 1; j++) {
				 maze += this.maze[i][j].toString();
			 }
		 }
		return maze;
	}
	
	public Room[][] getMaze() {
		return this.maze;
	}
	
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
