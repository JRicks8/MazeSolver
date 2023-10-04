package MazeSolver;

import java.util.ArrayList;

public class Pathfind {
	
	static ArrayList<Cell> findPath(Maze maze) {
		ArrayList<Cell> openList = new ArrayList<Cell>();
		boolean closedList[][] = new boolean[maze.width][maze.height];
		Cell cells[][] = new Cell[maze.width][maze.height];
		for (int i = 0; i < maze.width; i++) {
			for (int j = 0; j < maze.height; j++) {
				closedList[i][j] = false;
				cells[i][j] = new Cell();
				cells[i][j].x = i;
				cells[i][j].y = j;
			}
		}
		
		maze.startPosition.f = 0.0f;
		maze.startPosition.g = 0.0f;
		maze.startPosition.h = 0.0f;
		openList.add(maze.startPosition);
		
		boolean foundDestination = false;
		
		while (!openList.isEmpty()) {
			// find node with least f "q"
			int cellIndex = getBestCell(openList);
			Cell p = openList.get(cellIndex);
			
			// pop q off open list
			openList.remove(cellIndex);
			// add to closed list
			closedList[p.x][p.y] = true;
			
			// find q's 4 neighbors and set their parent to q
			ArrayList<Cell> result;
			
			result = evaluateNeighbor(maze, cells, openList, closedList, p.x, p.y, 1, 0);
			if (result != null) {
				foundDestination = true;
				return result;
			}
			result = evaluateNeighbor(maze, cells, openList, closedList, p.x, p.y, -1, 0);
			if (result != null) {
				foundDestination = true;
				return result;
			}
			result = evaluateNeighbor(maze, cells, openList, closedList, p.x, p.y, 0, 1);
			if (result != null) {
				foundDestination = true;
				return result;
			}
			result = evaluateNeighbor(maze, cells, openList, closedList, p.x, p.y, 0, -1);
			if (result != null) {
				foundDestination = true;
				return result;
			}
		}
		return null;
	}
	
	private static ArrayList<Cell> evaluateNeighbor(Maze maze, Cell[][] cells, ArrayList<Cell> openList, 
			boolean closedList[][], int x, int y, int dx, int dy) {
		float gNew, hNew, fNew;
		int isPassable;
		
		isPassable = maze.GetCell(x + dx, y + dy);
		if (isPassable == 1) {
			
			if (maze.endPosition.x == cells[x + dx][y + dy].x 
				&& maze.endPosition.y == cells[x + dx][y + dy].y) {
				cells[x + dx][y + dy].parent = cells[x][y];
				return tracePath(cells[x + dx][y + dy]);
			} else if (closedList[x + dx][y + dy] == false) {
				gNew = cells[x][y].g + 1;
				hNew = Math.abs(maze.endPosition.x - cells[x + dx][y + dy].x) + 
						Math.abs(maze.endPosition.y - cells[x + dx][y + dy].y);
				fNew = gNew + hNew;
				
				// if not in the open list or is in open list already, but has better f value
				if (cells[x + dx][y + dy].f == Float.MAX_VALUE
					|| cells[x + dx][y + dy].f > fNew) {
					openList.add(cells[x + dx][y + dy]);
					
					cells[x + dx][y + dy].parent = cells[x][y];
					cells[x + dx][y + dy].g = gNew;
					cells[x + dx][y + dy].h = hNew;
					cells[x + dx][y + dy].f = fNew;
				}
			}
		}
		return null;
	}

	private static ArrayList<Cell> tracePath(Cell endPoint) {
		ArrayList<Cell> path = new ArrayList<Cell>();
		Cell currentCell = endPoint;
		while (currentCell != null) {
			path.add(currentCell);
			currentCell = currentCell.parent;
		}
		path = reverseList(path);
		return path;
	}
	
	private static ArrayList<Cell> reverseList(ArrayList<Cell> list)
    {
        ArrayList<Cell> revList = new ArrayList<Cell>();
        for (int i = list.size() - 1; i >= 0; i--) {
        	revList.add(list.get(i));
        }
        return revList;
    }
	
	private static int getBestCell(ArrayList<Cell> openList) {
		int result = 0;
		float leastF = Float.MAX_VALUE;
		for (int i = 0; i < openList.size(); i++) {
			if (openList.get(i).f < leastF) {
				leastF = openList.get(i).f;
				result = i;
			}
		}
		
		return result;
	}
}
