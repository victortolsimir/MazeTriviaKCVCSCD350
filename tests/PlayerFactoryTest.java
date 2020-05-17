package tests;
import triviaMaze.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PlayerFactoryTest {

	@Test
	void createPlayerTest() {
		PlayerFactory playerFactory = new PlayerFactory();
		Player player = playerFactory.createPlayer("TestName");
		String expected = "Name: TestName Position: (1,1) Lives: 2";
		assertEquals(expected, player.toString());
	}

}
