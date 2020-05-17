package triviaMaze;

public class MazeFactory {

	
	public Maze createMaze(Player player) {
		
		Maze maze = new Maze(player);
		
		return maze;
	}
}
