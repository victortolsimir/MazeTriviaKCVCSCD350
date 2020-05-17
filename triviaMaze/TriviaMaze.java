package triviaMaze;
import java.util.Scanner;

public class TriviaMaze {
	
	//private Scanner sc;
	//private MazeFactory mazeFactory;
	//private PlayerFactory playerFactory;
	private Maze triviaMaze;
    private Player player;
	
	public TriviaMaze() {
		
		this.player = new Player(0, 0);
		this.triviaMaze = new Maze(player);
	}
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		TriviaMaze newMaze = new TriviaMaze();
		Player player = newMaze.player;
		Room[][] maze = newMaze.triviaMaze.getMaze();
		int x = 1, y = 1;
		
		while(!(maze[x][y].allDoorsLocked()) && player.getLives() > 0) {
			
			int[] results = newMaze.menu(maze[x][y], x, y, player); 
			x = results[0];
			y = results[1];
		}
		
		
	}
	
	private int[] menu(Room room, int x, int y, Player player) {
		
		int optionReturn = 0;
		Scanner scanner = new Scanner(System.in);
		int[] position = room.getCoordinates();
		int[] results = new int[2];
		
		while(optionReturn == 0) {
			
			if(position[1] > 0) {
				System.out.println("Type \"up\" or \"north\" to move 1 room up");
			}
			if(position[0] < 3) {
				System.out.println("Type \"right\" or \"east\" to move 1 room right");
			}
			if(position[1] < 3) {
				System.out.println("Type \"down\" or \"south\" to move 1 room down");
			}
			if(position[0] > 0) {
				System.out.println("Type \"left\" or \"west\" to move 1 room left");
			}
			
			String option = scanner.next();
	
			if((option.equalsIgnoreCase("up") || option.equalsIgnoreCase("north")) && position[1] > 0) {
				optionReturn = 1;
				results[0] = y - 1;
				results[1] = x;
				player.setCoordinates(x,  y-1);
			}
			else if((option.equalsIgnoreCase("right") || option.equalsIgnoreCase("east")) && position[0] < 4) {
				optionReturn = 2;
				results[0] = x + 1;
				results[1] = y;
				player.setCoordinates(x+1,  y);
			}
			else if((option.equalsIgnoreCase("down") || option.equalsIgnoreCase("south")) && position[1] < 4) {
				optionReturn = 3;
				results[1] = y + 1;
				results[0] = x;
				player.setCoordinates(x, y+1);
			}
			else if((option.equalsIgnoreCase("left") || option.equalsIgnoreCase("west")) && position[0] > 0) {
				optionReturn = 4;
				results[0] = x - 1;
				results[1] = y;
				player.setCoordinates(x-1, y);
			}
			else {
				System.out.println("Invalid Option.");
			}
		}
		
		return results; // results[0] is going to be x coordinates, results[1] is going to be y coordinate;
	}
}
