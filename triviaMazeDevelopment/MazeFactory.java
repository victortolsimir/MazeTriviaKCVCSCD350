  
package triviaMazeDevelopment;

public class MazeFactory {
	
	public Maze createMaze(Player player) {
		return new Maze(player);
	}
}