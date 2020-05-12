package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import triviaMaze.*;

import org.junit.jupiter.api.Test;

class MazeTests {
	
	private Maze maze;

	@Test
	void testMaze() {
		String r1, r2, r3, r4, r5, r6, r7, r8, r9;
		r1 = "* * *\n*   |\n* - *\n"; //north west
		r2 = "* * *\n|   |\n* - *\n"; //north
		r3 = "* * *\n|   *\n* - *\n"; //north east
		r4 = "* - *\n*   |\n* - *\n"; //west
		r5 = "* - *\n|   |\n* - *\n"; //center
		r6 = "* - *\n|   *\n* - *\n"; //east
		r7 = "* - *\n*   |\n* * *\n"; //south west
		r8 = "* - *\n|   |\n* * *\n"; //south
		r9 = "* - *\n|   *\n* * *\n"; //south east
		
		this.maze = new Maze(new Player(1,1));
		String testingStr = r1+r2+r2+r3+r4+r5+r5+r6+r4+r5+r5+r6+r7+r8+r8+r9;

		assertEquals(testingStr, maze.toString());
		//fail("Not yet implemented");
	}

}
