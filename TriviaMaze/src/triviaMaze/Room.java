/*
 * Authors: Kayla Rubin, Victor Vargas, Caleb Stanley
 * Description: A Room possibly has four walls or doors, an exit or entrance, a room number that is a unique
 * identifier, and coordinates. Room locks the doors when they are needed to be and 
 * checks all doors for them being locked. Sets entrance, exit, and all walls.
 * 
 */
package triviaMaze;

import java.io.Serializable;

public class Room implements Serializable{
	
	final int UNLOCKED = 0;
	final int LOCKED = 1;
	final int WALL = 2;		
	private int[] walls;	// North, East, South, West
	private boolean isExit;
	private boolean isEntrance;
	private int[] coordinates = new int[2];
	private int roomNum;
	
	//Used to construct the Room by filling out the walls array appropriately as well as the coordinates array.
	public Room(int xPos, int yPos, int num) {
		
		this.walls = new int[4];
		this.coordinates[0] = xPos;
		this.coordinates[1] = yPos;
		this.isEntrance = false;
		this.isExit = false;
		this.roomNum = num; 
		
		if(xPos == 1 && yPos == 1) {
			this.walls[0] = WALL;
			this.walls[1] = UNLOCKED;
			this.walls[2] = UNLOCKED;
			this.walls[3] = WALL;
		}
		
		else if(xPos == 4 && yPos == 1) {
			this.walls[0] = UNLOCKED;
			this.walls[1] = UNLOCKED;
			this.walls[2] = WALL;
			this.walls[3] = WALL;
		}
		
		else if(xPos == 1 && yPos == 4) {
			this.walls[0] = WALL;
			this.walls[1] = WALL;
			this.walls[2] = UNLOCKED;
			this.walls[3] = UNLOCKED;
		}
		
		else if(xPos == 4 && yPos == 4) {
			this.walls[0] = UNLOCKED;
			this.walls[1] = WALL;
			this.walls[2] = WALL;
			this.walls[3] = UNLOCKED;
		}
		
		else if(xPos == 1 && yPos > 1 && yPos < 4) {
			this.walls[0] = WALL;
			this.walls[1] = UNLOCKED;
			this.walls[2] = UNLOCKED;
			this.walls[3] = UNLOCKED;
		}
		
		else if(xPos == 4 && yPos > 1 && yPos < 4) {
			this.walls[0] = UNLOCKED;
			this.walls[1] = UNLOCKED;
			this.walls[2] = WALL;
			this.walls[3] = UNLOCKED;
		}
		
		else if(xPos > 1 && xPos < 4 && yPos == 1) {
			this.walls[0] = UNLOCKED;
			this.walls[1] = UNLOCKED;
			this.walls[2] = UNLOCKED;
			this.walls[3] = WALL;
		}
		
		else if(xPos > 1 && xPos < 4 && yPos == 4) {
			this.walls[0] = UNLOCKED;
			this.walls[1] = WALL;
			this.walls[2] = UNLOCKED;
			this.walls[3] = UNLOCKED;
		}
		
		else if(xPos > 1 && xPos < 4 && yPos > 1 && yPos < 4) {
			this.walls[0] = UNLOCKED;
			this.walls[1] = UNLOCKED;
			this.walls[2] = UNLOCKED;
			this.walls[3] = UNLOCKED;
		}
	}
	
	@Override
	public String toString() {
		
		String str = "";
		
		if(this.walls[0] == UNLOCKED)
			str += "* - *\n";
		
		else if(this.walls[0] == LOCKED)
			str += "* * *\n";
		
		else
			str += "* * *\n";
		
		if(this.walls[1] == UNLOCKED && this.walls[3] == UNLOCKED)
			str += "|   |\n";
		
		else if((this.walls[1] == LOCKED || this.walls[1] == WALL) && this.walls[3] == UNLOCKED)
			str += "|   *\n";
		
		else if(this.walls[1] == UNLOCKED && (this.walls[3] == LOCKED || this.walls[3] == WALL))
			str += "*   |\n";
		
		else
			str += "*   *\n";
		
		if(walls[2] == UNLOCKED)
			str += "* - *\n";
		else
			str += "* * *\n";
		
		return str;
	}
	
	public int getRoomNum() {
		return this.roomNum;
	}
	
	public boolean isExit() {
		return this.isExit;
	}
	
	public boolean isEntrance() {
		return this.isEntrance;
	}
	
	public void setExit() {
		this.isExit = true;
	}
	
	public void setEntrance() {
		this.isEntrance = true;
	}
	
	/*
	 * following 3 methods all return a single part of the room so top, middle, or bottom
	 * and returns a String representation of each part
	 */
	public String getTopRow() {
		
		String str = "";
		if(this.walls[0] == UNLOCKED)
			str += "* - *";
		else 
			str += "* * *";
		
		return str;
		
	}

	public String getMidRow(Player player, int[] position) {
		
		String str = "";
		if(this.walls[1] == UNLOCKED && this.walls[3] == UNLOCKED) 
			
			if(player.getCoordinates()[0] == position[0] && player.getCoordinates()[1] == position[1])
				str += "| P |";
			else 
				str += "|   |";
		
		else if((this.walls[1] == LOCKED || this.walls[1] == WALL) && this.walls[3] == UNLOCKED) 
			
			if(player.getCoordinates()[0] == position[0] && player.getCoordinates()[1] == position[1])
				str += "| P *";
			else if(position[0] == 4 && position[1] == 4)
				str += "| E *";
			else 
				str += "|   *";
		
		
		else if(this.walls[1] == UNLOCKED && (this.walls[3] == LOCKED || this.walls[3] == WALL))
			
			if(player.getCoordinates()[0] == position[0] && player.getCoordinates()[1] == position[1])
				str += "* P |";
			else 
				str += "*   |";
		
		else 
			
			if(player.getCoordinates()[0] == position[0] && player.getCoordinates()[1] == position[1])
				str += "* P *";
			else 
				str += "*   *";
		
		return str; 
	}
	
	public String getBotRow() { 
		
		String str = "";
		if(this.walls[2] == UNLOCKED)
			str += "* - *";
		else
			str += "* * *";
		
		return str;
	}
	
	
	
	//Checks to see if all the doors in the wall array are either locked or are a wall, if they are it returns true, else returns false.
	public boolean allDoorsLocked() {
		
		if((this.walls[0] == LOCKED || this.walls[0] == WALL) && (this.walls[1] == LOCKED || this.walls[1] == WALL) && (this.walls[2] == LOCKED || this.walls[2] == WALL) && (this.walls[3] == LOCKED || this.walls[3] == WALL))
			return true;
		else
			return false;
	}
	
	public boolean topLocked() {
		return this.walls[0] == LOCKED || this.walls[0] == WALL;
	}
	
	public boolean rightLocked() {
		return this.walls[1] == LOCKED || this.walls[1] == WALL;
	}
	
	public boolean bottomLocked() {
		return this.walls[2] == LOCKED || this.walls[2] == WALL;
	}
	
	public boolean leftLocked() {
		return this.walls[3] == LOCKED || this.walls[3] == WALL;
	}
	
	public void setLock(int direction) {
		this.walls[direction] = LOCKED;
	}
	
	public int[] getCoordinates() {
		return this.coordinates;
	}
	
	public int[] getWalls() {
		return this.walls;
	}
}
