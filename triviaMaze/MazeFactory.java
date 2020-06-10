/*
 * Authors: Kayla Rubin, Victor Vargas, Caleb Stanley
 */
package triviaMaze;

public class MazeFactory {
	public Maze createMaze(Player player) {
		return new Maze(player);
	}
}
