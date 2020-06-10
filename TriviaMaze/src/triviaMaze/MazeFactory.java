/*
 * Authors: Kayla Rubin, Victor Vargas, Caleb Stanley
 * Sole purpose is to create a Maze object
 */
package triviaMaze;

public class MazeFactory {
	
	public Maze createMaze(Player player) {
		return new Maze(player);
	}
}
