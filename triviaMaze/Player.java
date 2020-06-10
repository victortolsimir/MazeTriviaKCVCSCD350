package triviaMaze;
public class Player {
	
	private int[] position;
	private int lives;
	private String name;
	
	public Player(int row, int col, String name) {
		position = new int[2];
		lives = 2;
		this.position[0] = row;
		this.position[1] = col;
		this.name = name;
	}
	

	public void setCoordinates(int row, int col) {
		this.position[0] = row;
		this.position[1] = col;
	}
	
	public int[] getCoordinates() {
		return position;
	}
	
	public void subtractLife() {
		this.lives--;
	}
	
	public int getLives() {
		return this.lives;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String toString() {
		return "Name: "+this.name+" Position: ("+this.position[0]+","+this.position[1]+") Lives: "+this.lives;
	}
	
}