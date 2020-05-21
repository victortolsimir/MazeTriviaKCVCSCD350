package triviaMazeDevelopment;

public class PlayerFactory {
	public Player createPlayer(String name) {
			return new Player(1, 1, name);
		}
}