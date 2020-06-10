/*
 * Authors: Kayla Rubin, Victor Vargas, Caleb Stanley
 */
package triviaMaze;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import TriviaMazeDevelopment.Tester;
import triviaMaze.Maze;
import triviaMaze.Player;

class SavingStateTests {

	@Test
	void saveGameTest() {
		
		Player player = new Player(1,1, "C");
		Maze maze = new Maze(player);
		
		Tester.saveGame(maze);
		
		Maze savedGame = Tester.loadGame().getMaze();
		
		assertEquals(0, maze.toString().compareTo(savedGame.toString()));
		//fail("Not yet implemented");
	}

}
