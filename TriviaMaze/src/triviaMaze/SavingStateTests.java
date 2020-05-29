package triviaMaze;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import triviaMaze.Maze;
import triviaMaze.Player;
import triviaMaze.Tester;

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
