/*  Authors: Victor, Kayla, Caleb 
 *  CSCD 350 
*/

package triviaMaze;

public class PlayerFactory {
	public Player createPlayer(String name) {
			return new Player(1, 1, name);
		}
}

