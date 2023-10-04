package MazeSolver;

import java.util.ArrayList;

public class Pathfind {
	
	static void findPath(Maze maze) {
		ArrayList<Cell> openList = new ArrayList<Cell>();
		ArrayList<Cell> closedList = new ArrayList<Cell>();
		
		maze.startPosition.f = 0.0f;
		maze.startPosition.g = 0.0f;
		maze.startPosition.h = 0.0f;
		openList.add(maze.startPosition);
		
		boolean foundDestination = false;
		
		while (!openList.isEmpty()) {
			// find node with least f "q"
			Cell p = openList.get(0);
			
			// pop q off open list
			openList.remove(0);
			closedList.add(p);
			
			// find q's 4 neighbors and set their parent to q
			float gNew, hNew, fNew;
			boolean isWalkable;
			
			// for each neighbor
				// if neighbor is the goal, break;
			
				// else, compute g and h for this neighbor
				// neighbor.g = q.g + distance between neighbor and q
				// neighbor.h = distance from goal to neighbor (Manhattan distance)
			
				// neighbor.f = g + h
				
				// if a cell with the same position as this neighbor is in the open list
				// that also has a lower f than this neighbor, continue;
			
				// if a cell with the same position as neighbor is in the closed list
				// that also has a lower f than this neighbor, continue;
			
				// add neighbor to open list
			
			// push q to closed list
		}
	}
}
