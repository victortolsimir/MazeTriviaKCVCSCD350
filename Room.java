package triviaMaze;

public class Room {

	private int[] pos;
	public Room(int row, int col) {
		this.pos = new int[2];
		this.pos[0] = row;
		this.pos[1] = col;
	}
	
	public String toString() {
		return "*-*\n|E|\n*-*\n";
	}
}
