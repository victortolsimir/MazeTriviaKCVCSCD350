package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import triviaMaze.Room;

class RoomTest {
	
	Room room;
	
	@BeforeEach
	void setUp() {
		room = new Room(1,1);
	}
	
	@Test
	void constructorTest() {
		Room r;
		String expected;
		
		//center room
		r = new Room(2,2);
		expected = "* - *\n|   |\n* - *\n";
		assertEquals(expected,r.toString());
		
		//north room
		r = new Room(1,2);
		expected = "* * *\n|   |\n* - *\n";
		assertEquals(expected,r.toString());
		
		//east room
		r = new Room(2,5);
		expected = "* - *\n|   *\n* - *\n";
		assertEquals(expected,r.toString());
		
		//south room
		r = new Room(4,2);
		expected = "* - *\n|   |\n* * *\n";
		assertEquals(expected,r.toString());
		
		//west room
		r = new Room(3,1);
		expected = "* - *\n*   |\n* - *\n";
		assertEquals(expected,r.toString());
					
		//north west corner room
		r = new Room(1,1);
		expected = "* * *\n*   |\n* - *\n";
		assertEquals(expected,r.toString());
		
		//north east corner room
		r = new Room(1,4);
		expected = "* * *\n|   *\n* - *\n";
		assertEquals(expected,r.toString());
		
		//south west corner room
		r = new Room(4,1);
		expected = "* - *\n*   |\n* * *\n";
		assertEquals(expected,r.toString());
		
		//south east corner room
		r = new Room(4,4);
		expected = "* - *\n|   *\n* * *\n";
		assertEquals(expected,r.toString());
			
	}
	
	@Test
	void setExitTest() {
		//if exit not set
		assertEquals(false,room.isExit());
		
		room.setExit();
		assertEquals(true,room.isExit());
	}
	
	@Test 
	void setEntranceTest() {
		//if entrance not set
		assertEquals(false,room.isEntrance());
		
		room.setEntrance();
		assertEquals(true,room.isEntrance());
	}
	
	
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
	
	@Test
	void setLockTest() {
		//ROOMS NOT ON EDGE OF MAZE Room(2,2)
		Room r;
		String expected;
		
		//no Locks
		r = new Room(2,2);
		expected = "* - *\n|   |\n* - *\n";
		assertEquals(expected,r.toString());
		
		//north Lock
		r = new Room(2,2);
		r.setLock(0);
		expected = "* * *\n|   |\n* - *\n";
		assertEquals(expected,r.toString());
		
		//south Lock
		r = new Room(2,2);
		r.setLock(2);
		expected = "* - *\n|   |\n* * *\n";
		assertEquals(expected,r.toString());
		
		//east Lock
		r = new Room(2,2);
		r.setLock(1);
		expected = "* - *\n|   *\n* - *\n";
		assertEquals(expected,r.toString());
		
		//west Lock
		r = new Room(2,2);
		r.setLock(3);
		expected = "* - *\n*   |\n* - *\n";
		assertEquals(expected,r.toString());
		
	}
	

}