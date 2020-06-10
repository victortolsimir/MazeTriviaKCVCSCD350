/*  Authors: Victor, Kayla, Caleb 
 *  CSCD 350 
*/

package triviaMaze;

public class MazeFactory {
	public Maze createMaze(Player player) {
		return new Maze(player);
	}
}
