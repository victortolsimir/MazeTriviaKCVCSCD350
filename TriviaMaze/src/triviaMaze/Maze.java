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
		
		
		 for(int i = 1; i < this.maze.length - 1; i++) {
			 for(int j = 1; j < this.maze.length - 1; j++) {
				 this.maze[i][j] = new Room(i, j);
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
}
