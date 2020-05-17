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
	
	public MazeMemento saveState() {
		return new MazeMemento(this.maze);
	}
	
	public void loadState(MazeMemento state) {
		this.maze = state.getMaze();
		
	}
	
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
