/*
 * Authors: Kayla Rubin, Victor Vargas, Caleb Stanley
 */
package triviaMaze;

public class PlayerFactory {
	public Player createPlayer(String name) {
			return new Player(1, 1, name);
		}
}

