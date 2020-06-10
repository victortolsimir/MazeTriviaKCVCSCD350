package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import triviaMaze.Room;

/*Authors: Kayla Rubin, Victor Vargas, Caleb Stanley
  Description: This class tests the behaviors of the room object class*/

class RoomTest {
	
	Room room;
	
	@BeforeEach
	void setUp() {
		room = new Room(1,1,0);
	}
	
	//Test construction of room object
	@Test
	void constructorTest() {
		Room r;
		String expected;
		
		//center room
		r = new Room(2,2,0);
		expected = "* - *\n|   |\n* - *\n";
		assertEquals(expected,r.toString());
		
		//north room
		r = new Room(1,2,0);
		expected = "* * *\n|   |\n* - *\n";
		assertEquals(expected,r.toString());
		
		//east room
		r = new Room(2,4,0);
		expected = "* - *\n|   *\n* - *\n";
		assertEquals(expected,r.toString());
		
		//south room
		r = new Room(4,2,0);
		expected = "* - *\n|   |\n* * *\n";
		assertEquals(expected,r.toString());
		
		//west room
		r = new Room(3,1,0);
		expected = "* - *\n*   |\n* - *\n";
		assertEquals(expected,r.toString());
					
		//north west corner room
		r = new Room(1,1,0);
		expected = "* * *\n*   |\n* - *\n";
		assertEquals(expected,r.toString());
		
		//north east corner room
		r = new Room(1,4,0);
		expected = "* * *\n|   *\n* - *\n";
		assertEquals(expected,r.toString());
		
		//south west corner room
		r = new Room(4,1,0);
		expected = "* - *\n*   |\n* * *\n";
		assertEquals(expected,r.toString());
		
		//south east corner room
		r = new Room(4,4,0);
		expected = "* - *\n|   *\n* * *\n";
		assertEquals(expected,r.toString());
			
	}
	
	//test setting exit 
	@Test
	void setExitTest() {
		//if exit not set
		assertEquals(false,room.isExit());
		
		room.setExit();
		assertEquals(true,room.isExit());
	}
	
	//test setting entrance
	@Test 
	void setEntranceTest() {
		//if entrance not set
		assertEquals(false,room.isEntrance());
		
		room.setEntrance();
		assertEquals(true,room.isEntrance());
	}
	
	//test all doors locked
	@Test
	void allDoorsLockedTest() {
		//no doors locked
		assertEquals(false,room.allDoorsLocked());
		
		//one door locked
		room.setLock(0);//north
		assertEquals(false,room.allDoorsLocked());
		
		//all doors locked
		room.setLock(1);//east
		room.setLock(2);//south
		room.setLock(3);//west
		assertEquals(true,room.allDoorsLocked());
		
	}
	
	//test setting locks
	@Test
	void setLockTest() {
		
		//ROOMS NOT ON EDGE OF MAZE Room(2,2)
		Room r;
		String expected;
		
		//no Locks
		r = new Room(2,2,0);
		expected = "* - *\n|   |\n* - *\n";
		assertEquals(expected,r.toString());
		
		//north Lock
		r = new Room(2,2,0);
		r.setLock(0);
		expected = "* * *\n|   |\n* - *\n";
		assertEquals(expected,r.toString());
		
		//south Lock
		r = new Room(2,2,0);
		r.setLock(2);
		expected = "* - *\n|   |\n* * *\n";
		assertEquals(expected,r.toString());
		
		//east Lock
		r = new Room(2,2,0);
		r.setLock(1);
		expected = "* - *\n|   *\n* - *\n";
		assertEquals(expected,r.toString());
		
		//west Lock
		r = new Room(2,2,0);
		r.setLock(3);
		expected = "* - *\n*   |\n* - *\n";
		assertEquals(expected,r.toString());
		
	}
	

}
