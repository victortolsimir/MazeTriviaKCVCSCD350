package triviaMaze;



public class Maze {

	private Room[][] maze;
	private Player player;
	private int size;
	
	public Maze(Player thePlayer) {
		//really a 3x3 maze
		this.maze = new Room[6][6];
		this.size = 3;
		createMaze();
	}
	
	public Player getPlayer() {
		return this.player;
	}
	
	public int getSize() {
		return this.size;
	}
	
	private void createMaze() {
		
		
		 for(int i = 1; i < this.maze.length - 1; i++) {
			 for(int j = 1; j < this.maze.length - 1; j++) {
				 this.maze[i][j] = new Room(i -1, j -1);
			 }
		 }
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
}
