package tests;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import triviaMaze.Player;

/*Authors: Kayla Rubin, Victor Vargas, Caleb Stanley
Description: This class tests behaviors of player object class */

class PlayerTest {
	
	private Player player;
	
	@BeforeEach
	void setUp(){
		player = new Player(1,1,null);
	}

	//Test ability to set coordinates for player
	@Test
	void setCoordinates() {
		player.setCoordinates(3, 4);
		int[] coordinates = player.getCoordinates();
		int[] compare = {3,4};
		assertArrayEquals(compare,coordinates);
		
	}
	
	//Test ability to subtract lives for player
	@Test
	void subtractLives() {
		int lives;
		
		player.subtractLife();
		lives = player.getLives();
		assertEquals(1,lives);
		
		player.subtractLife();
		lives = player.getLives();
		assertEquals(0,lives);
		
	}

}
