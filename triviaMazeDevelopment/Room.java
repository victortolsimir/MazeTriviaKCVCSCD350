package triviaMazeDevelopment;
/* Caleb Stanley TriviaMaze Room class iteration 1
 * CSCD 350 Victor, Kayla, Caleb
 */
public class Room {
	
	final int UNLOCKED = 0;
	final int LOCKED = 1;
	final int WALL = 2;		
	private int[] walls;	// North, East, South, West
	private boolean isExit;
	private boolean isEntrance;
	private int[] coordinates = new int[2];
	
	//Used to construct the Room by filling out the walls array appropriately as well as the coordinates array.
	public Room(int xPos, int yPos) {
		
		this.walls = new int[4];
		this.coordinates[0] = xPos;
		this.coordinates[1] = yPos;
		this.isEntrance = false;
		this.isExit = false;
		
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
		
		else if(this.walls[1] == LOCKED || this.walls[1] == WALL && this.walls[3] == UNLOCKED)
			str += "|   *\n";
		
		else if(this.walls[1] == UNLOCKED && this.walls[3] == LOCKED || this.walls[3] == WALL)
			str += "*   |\n";
		
		else
			str += "*   *\n";
		
		if(walls[2] == UNLOCKED)
			str += "* - *\n";
		else
			str += "* * *\n";
		
		return str;
	}
	
	//Checks to see if all the doors in the wall array are either locked or are a wall, if they are it returns true, else returns false.
	public boolean allDoorsLocked() {
		
		if((this.walls[0] == LOCKED || this.walls[0] == WALL) && (this.walls[1] == LOCKED || this.walls[1] == WALL) && (this.walls[2] == LOCKED || this.walls[2] == WALL) && (this.walls[3] == LOCKED || this.walls[3] == WALL))
			return true;
		else
			return false;
	}
	
	public void setLock(int direction) {
		this.walls[direction] = LOCKED;
	}
	
	public int[] getCoordinates() {
		return this.coordinates;
	}
}
