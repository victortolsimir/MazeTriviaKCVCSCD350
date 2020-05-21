package triviaMazeDevelopment;
import java.util.Scanner;

public class TriviaMaze {
	
	private static Scanner sc;
	//private MazeFactory mazeFactory;
	//private PlayerFactory playerFactory;
	private Maze triviaMaze;
    private Player player;
	
	public TriviaMaze() {
		this.player = new Player(0, 0, getName());
		this.triviaMaze = new Maze(player);
	}
	
	public static void main(String[] args) {
		sc = new Scanner(System.in);
		TriviaMaze newMaze = new TriviaMaze();
		Player player = newMaze.player;
		Room[][] maze = newMaze.triviaMaze.getMaze();
		int x = 1, y = 1;
		System.out.println("\nWelcome to the Trivia Maze\n");
		
		while(!(maze[x][y].allDoorsLocked()) && player.getLives() > 0) {
			
			System.out.println("Current Room:\n");
			System.out.println(maze[x][y].toString());
			String option = newMaze.getPlayerOption(maze[x][y]);
			int[] results = newMaze.menu(maze[x][y],x, y, player,option); 
			x = results[0];
			y = results[1];
		}
	}
	
	
	private String getPlayerOption(Room room) {
		int[] position = room.getCoordinates();
		String option;
		
		if(position[0] > 1) {
			System.out.println("Type \"up\" or \"north\" to move 1 room up");
		}
		if(position[1] < 4) {
			System.out.println("Type \"right\" or \"east\" to move 1 room right");
		}
		if(position[0] < 4) {
			System.out.println("Type \"down\" or \"south\" to move 1 room down");
		}
		if(position[1] > 1) {
			System.out.println("Type \"left\" or \"west\" to move 1 room left");
		}
		option = sc.nextLine();
		
		return option;
	}

	public int[] menu(Room room, int x, int y, Player player, String option) {
		
		int optionReturn = 0;
		int[] position = room.getCoordinates();
		int[] results = {x,y};
		
		while(optionReturn == 0) {

			/*//used for testing purposes
			if(option.equalsIgnoreCase("quit")){
				player.subtractLife();
				player.subtractLife();
				optionReturn = 1;
			}*/
	
			if((option.equalsIgnoreCase("up") || option.equalsIgnoreCase("north")) && position[0] > 1) {
				optionReturn = 1;
				results[1] = y;
				results[0] = x - 1;
				player.setCoordinates(x - 1,  y);
			}
			else if((option.equalsIgnoreCase("right") || option.equalsIgnoreCase("east")) && position[1] < 4) {
				optionReturn = 2;
				results[0] = x;
				results[1] = y + 1;
				player.setCoordinates(x,  y + 1);
			}
			else if((option.equalsIgnoreCase("down") || option.equalsIgnoreCase("south")) && position[0] < 4) {
				optionReturn = 3;
				results[0] = x + 1;
				results[1] = y;
				player.setCoordinates(x + 1, y);
			}
			else if((option.equalsIgnoreCase("left") || option.equalsIgnoreCase("west")) && position[1] > 1) {
				optionReturn = 4;
				results[0] = x;
				results[1] = y - 1;
				player.setCoordinates(x, y - 1);
			}
			else {
				System.out.println("Invalid Option.");
				option = getPlayerOption(room);
			}
		}
		
		return results; // results[0] is going to be x coordinates, results[1] is going to be y coordinate;
	}
	
	private String getName() {
		System.out.print("Please Enter your name: ");
		String name = sc.nextLine();
		while(name.length() > 20) {
			System.out.print("\nName must be less than 20 characters!\nPlease Enter your name: ");
			name = sc.nextLine();
		}
		return name;
	}
}
