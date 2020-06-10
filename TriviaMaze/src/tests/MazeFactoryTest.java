package tests;
import triviaMaze.*;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/*Authors: Kayla Rubin, Victor Vargas, Caleb Stanley
Description: This test class checks mazeFactories ability to create a valid maze and return it */

class MazeFactoryTest {

	@Test
	void createMazeTest() {
		MazeFactory mazeFactory = new MazeFactory();
		Maze maze = mazeFactory.createMaze(new Player(1,1,null));
		
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
		String expected = r1+r2+r2+r3+r4+r5+r5+r6+r4+r5+r5+r6+r7+r8+r8+r9;

		assertEquals(expected, maze.toString());
	}
	

}
