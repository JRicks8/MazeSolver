package MazeSolver;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Maze {
	public Cell startPosition;
	public Cell endPosition;
	public int width;
	public int height;
	//stores every value in the maze in a list as a 0 (pass-able) or 1 (blocked)
	public int[][] cells;
	
	//constructor with no parameters, all numbers start being 0.
	public Maze() {
		this.startPosition = new Cell();
		this.endPosition = new Cell();
		this.width = 0;
		this.height = 0;
		//stores all cells in the maze as [x][y].
		this.cells = new int[0][0];
	}
	
	//returns true if two cells have the same coordinates, false if not. 
	public boolean CompareCoordinates(Cell _cellA, Cell _cellB) {
		if(_cellA.x == _cellB.x && _cellA.y == _cellB.y) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public static Maze CreateMazeFromFile(String _filepath) {
		Maze returnMaze = new Maze();
		String line = "";   
		try   
		{  
			//parsing a CSV file into BufferedReader class constructor  
			BufferedReader br = new BufferedReader(new FileReader(_filepath)); 
			
			//i keeps track of which line is being processed.
			int i = 1;
		
			while ((line = br.readLine()) != null)   //returns a Boolean value  
			{  
				String[] splitLine = line.split(" ");   // use space as separator
				
				//coordinates are given as row,column: the y value of a cell gives the row and the x value gives the column. 
				//all functions in the program will use the (x,y) format. 
				
				//x and y dimensions of the maze.
				if(i == 1) {
					returnMaze.width = Integer.parseInt(splitLine[0]);
					returnMaze.height = Integer.parseInt(splitLine[1]);
					returnMaze.cells = new int[returnMaze.width][returnMaze.height];
				}
				//start point coordinates.
				else if(i == 2) {
					returnMaze.startPosition.y = Integer.parseInt(splitLine[0]);
					returnMaze.startPosition.x = Integer.parseInt(splitLine[1]);
				}
				//end point coordinates.
				else if(i == 3) {
					returnMaze.endPosition.y = Integer.parseInt(splitLine[0]);
					returnMaze.endPosition.x = Integer.parseInt(splitLine[1]);
				}
				//all other lines contain maze data.
				else if(i-4 < returnMaze.height){
					for(int j = 0; j < splitLine.length; j++) {
						int newInt = -1;
						
						try {
							newInt = Integer.parseInt(splitLine[j]);
						}
						catch(Exception e){
							
						}
						
						//i-4 is the row(y), j is the column(x).
						//only accept ones or zeros into the cells list.
						if(newInt == 1 || newInt == 0) {
							returnMaze.cells[j][i-4] = newInt;
						}
					}
				}
			
				//System.out.println(i); //useful for debugging to see which entry causes an error.
			
				i++;
			}  				
			br.close();
		}   
		catch (IOException e)   
		{  
		e.printStackTrace();  
		}  
		
		return returnMaze;
	}
	
	//prints the maze and relevant data to the console. 
	public void printMaze() {
		System.out.println("width: " + width);
		System.out.println("height: " + height);
		System.out.println("start coordinates: (" + startPosition.x + "," + startPosition.y + ")");
		System.out.println("end coordinates: (" + endPosition.x + "," + endPosition.y + ")");
		
		//for each row(y), print all values in the row.
		String stringToPrint = "";
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				stringToPrint += cells[j][i] + " ";
			}
			System.out.println(stringToPrint);
			stringToPrint = "";
		}
	}
	
	//prints the maze and relevant data to the console. Cells that are given in the list are represented as "P".  
	public void printMazePath(ArrayList<Cell> _pathList) {
		System.out.println("width: " + width);
		System.out.println("height: " + height);
		System.out.println("start coordinates: (" + startPosition.x + "," + startPosition.y + ")");
		System.out.println("end coordinates: (" + endPosition.x + "," + endPosition.y + ")");
		
		//for each row(y), print all values in the row. Print P where the coordinates match a Cell in the list. 
		String stringToPrint = "";
		Cell compareCell = new Cell();
		boolean isPath = false;
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				/*compare the current cell's coordinates to those found in the pathList. If a match is found, delete the
				matching cell from the pathList to reduce length of future searches. */
				compareCell.x = j;
				compareCell.y = i;
				
				isPath = false;
				for(Cell cell : _pathList) {
					if(CompareCoordinates(cell, compareCell)) {
						_pathList.remove(cell);
						isPath = true;
						break;
					}
				}
				
				if(isPath) {
					stringToPrint += "P ";
				}
				else {
					stringToPrint += cells[j][i] + " ";
				}
				
			}
			System.out.println(stringToPrint);
			stringToPrint = "";
		}
	}
	
	//will return a 1 or 0 based on which cell the coordinates give, -1 is returned if no cell is found. 
	public int GetCell(int _x, int _y) {
		int returnInt = -1;
		try {
			returnInt = cells[_x][_y];
		}
		catch (Exception e) {
		}
		
		return returnInt;
	}
	
}
