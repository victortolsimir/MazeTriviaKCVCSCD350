package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import triviaMaze.Maze;
import triviaMaze.Player;
import triviaMaze.Tester;

/*Authors: Kayla Rubin, Victor Vargas, Caleb Stanley
Description: This class tests the ability of SavingState class to save and restore game state*/

class SavingStateTests {

	
	//Test ability to save and restore
	@Test
	void saveGameTest() {
		
		Player player = new Player(1,1,null);
		Maze maze = new Maze(player);
		
		Tester.saveGame(maze);
		
		Maze savedGame = Tester.loadGame().getMaze();
		
		assertEquals(0, maze.toString().compareTo(savedGame.toString()));
		//fail("Not yet implemented");
	}

}
