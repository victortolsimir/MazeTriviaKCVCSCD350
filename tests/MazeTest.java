package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

import triviaMaze.*;

import org.junit.jupiter.api.Test;

/*Authors: Kayla Rubin, Victor Vargas, Caleb Stanley
  Description: Maze Test checks that maze object can be successfully initialized
  and that toString output is accurate to intended maze design. Also tests maze path finding algorithm used in class*/

class MazeTest{
	
	private Maze maze;
	private Room[][] rooms;
	private final int NORTH = 0;
	private final int EAST = 1;
	private final int SOUTH = 2;
	private final int WEST = 3;
	
	@BeforeEach
	void setUp() {
		this.maze = new Maze(new Player(1,1,null));
		rooms = maze.getMaze();
	}
	
	//Test maze creation 
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
		
		String testingStr = r1+r2+r2+r3+r4+r5+r5+r6+r4+r5+r5+r6+r7+r8+r8+r9;

		assertEquals(testingStr, maze.toString());
	}
	
	//Test Find Path to exit algorithm
	@Test
	void testTraverse() {
		boolean expected;
		boolean test;
		
		//paths to exit exist
		expected = true;
		test = maze.traverse(1, 1, new boolean[16], rooms[1][1].getRoomNum()-1);
		assertEquals(expected,test);
		
		//Lock one door
		rooms[1][1].setLock(SOUTH);
		rooms[2][1].setLock(NORTH);
		expected = true;
		test = maze.traverse(1, 1, new boolean[16], rooms[1][1].getRoomNum()-1);
		assertEquals(expected,test);
		
		//No path exists
		rooms[1][2].setLock(SOUTH);
		rooms[2][2].setLock(NORTH);
		rooms[1][3].setLock(SOUTH);
		rooms[2][3].setLock(NORTH);
		rooms[1][4].setLock(SOUTH);
		rooms[2][4].setLock(NORTH);
		expected = false;
		test = maze.traverse(1, 1, new boolean[16], rooms[1][1].getRoomNum()-1);
		assertEquals(expected,test);
		
		
	}

}
