/*
 * Authors: Kayla Rubin, Victor Vargas, Caleb Stanley
 * Classes need to implement the memento pattern for saving state
 */
package triviaMaze;

import java.io.Serializable;

public class MazeOriginator {

	private Maze maze;
	
	public void setMaze(Maze theMaze) {
		this.maze = theMaze;
	}
	
	public Maze getMaze() {
		return this.maze;
	}
	
	/*
	 * Returns a save-able object 
	 */
	public MazeMemento saveState() {
		return new MazeMemento(this.maze);
	}
	
	/*
	 * Retrieves the field state from a save-able object
	 */
	public void loadState(MazeMemento state) {
		this.maze = state.getMaze();
		
	}
	
	/*
	 * Class that can be Serialized for saving
	 */
	public static class MazeMemento implements Serializable {
		private Maze theMaze;
		public MazeMemento(Maze theMaze) {
			this.theMaze = theMaze;
		}
		
		public Maze getMaze() {
			if(this.theMaze != null)
				return this.theMaze;
			throw new IllegalArgumentException("Maze is null @MazeMemento");
		}
	}
}
