/*
 * Authors: Kayla Rubin, Victor Vargas, Caleb Stanley
 */
package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import TriviaMazeDevelopment.Tester;
import triviaMaze.Maze;
import triviaMaze.Player;

/*Authors: Kayla Rubin, Victor Vargas, Caleb Stanley
Description: This class tests the ability of SavingState class to save and restore game state*/

class SavingStateTests {

	//Test ability to save and restore
	@Test
	void saveGameTest() {
		
		Player player = new Player(1,1, "C");
		Maze maze = new Maze(player);
		
		Tester.saveGame(maze);
		
		Maze savedGame = Tester.loadGame().getMaze();
		String str = maze.toString();
		String rts = savedGame.toString();
		
		assertEquals(0, maze.toString().compareTo(savedGame.toString()));
	}

}
