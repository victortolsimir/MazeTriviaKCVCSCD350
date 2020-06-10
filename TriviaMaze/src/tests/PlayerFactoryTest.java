package tests;
import triviaMaze.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/*Authors: Kayla Rubin, Victor Vargas, Caleb Stanley
Description: This test class checks PlayerFactories's ability to create a valid maze and return it */

class PlayerFactoryTest {
	
	

	@Test
	void createPlayerTest() {
		PlayerFactory playerFactory = new PlayerFactory();
		Player player = playerFactory.createPlayer("TestName");
		String expected = "TestName is at (1,1) Lives: 2";
		assertEquals(expected, player.toString());
	}

}
