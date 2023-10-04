package MazeSolver;

public class JacksonTesting {
	public static void main(String[] args) {
		Maze testMaze;
		
		testMaze = Maze.CreateMazeFromFile("C:\\maze.txt");
		testMaze.printMaze();
		testMaze.printMazePath(Pathfind.findPath(testMaze));	}
}
