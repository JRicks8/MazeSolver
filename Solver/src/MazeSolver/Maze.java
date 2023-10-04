package MazeSolver;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Maze {
	public Coordinates startPosition;
	public Coordinates endPosition;
	public int width;
	public int height;
	//stores every value in the maze in a list as a 0 (pass-able) or 1 (blocked)
	private ArrayList<Integer> cells;
	
	//constructor with no parameters, all numbers start being 0.
	public Maze() {
		this.startPosition = new Coordinates();
		this.endPosition = new Coordinates();
		this.width = 0;
		this.height = 0;
		this.cells = new ArrayList<Integer>();
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
				
				//x and y dimensions of the maze.
				if(i == 1) {
					returnMaze.width = Integer.parseInt(splitLine[0]);
					returnMaze.height = Integer.parseInt(splitLine[1]);
				}
				//start point coordinates.
				else if(i == 2) {
					returnMaze.startPosition.x = Integer.parseInt(splitLine[0]);
					returnMaze.startPosition.y = Integer.parseInt(splitLine[1]);
				}
				//end point coordinates.
				else if(i == 3) {
					returnMaze.endPosition.x = Integer.parseInt(splitLine[0]);
					returnMaze.endPosition.y = Integer.parseInt(splitLine[1]);
				}
				//all other lines contain maze data.
				else {
					for(String string : splitLine) {
						int newInt = Integer.parseInt(string);
						//only accept ones or zeros into the cells list.
						if(newInt == 1 || newInt == 0) {
							returnMaze.cells.add(newInt);
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
		System.out.println("start coordinates: (" + startPosition.x + "," + startPosition.y + "0");
		System.out.println("end coordinates: (" + endPosition.x + "," + endPosition.y + "0");
		
		//print the cells list, make a new row when the width is reached and stop after
		//for()
	}
	
	public int getCell(int _x, int _y) {
		return 0;
	}
}
