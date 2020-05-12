package triviaMaze;

public class Maze {

	private Room[][] maze;
	private Player player;
	
	public Maze(Player thePlayer) {
		//really a 3x3 maze
		this.maze = new Room[6][6];
		createMaze();
	}
	
	public Player getPlayer() {
		return this.player;
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
	
	public Room[][] getMaze() {
		return this.maze;
	}
}
