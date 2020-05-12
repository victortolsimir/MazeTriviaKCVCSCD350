package triviaMaze;

public class Player {
	
	private int[] position = new int[2];
	private int lives = 2;
	
	public Player(int row, int col) {
		this.position[0] = row;
		this.position[1] = col;
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
}
