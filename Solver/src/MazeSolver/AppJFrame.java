package MazeSolver;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.PublicKey;
import java.util.ArrayList;

import javax.swing.*;

public class AppJFrame extends JFrame{
	public int width;
	public int height;
	public int mazeFrameWidth;
	public int mazeFrameHeight;
	public Maze maze;
	public JFrame mazeFrame;
	public JButton importButton;
	public ActionListener importListener;
	public JCheckBox findPath;
	public JLabel findPathLabel;
	
	//used to create the AppJFrame.
	public AppJFrame(){
		this.width = 250;
		this.height = 150;
		this.mazeFrameWidth = 800;
		this.mazeFrameHeight = 800;
		this.maze = null;
		this.mazeFrame = new JFrame();
		
		//set up import listener.
		importListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				int r = fileChooser.showOpenDialog(null);
				 
	            // if the user selects a file
	            if (r == JFileChooser.APPROVE_OPTION)
	 
	            {
	                // set the label to the path of the selected file
	                DisplayMaze(fileChooser.getSelectedFile().getAbsolutePath());
	            }
			}
		};
				
		//set up import button.
		importButton = new JButton("Import Maze File");
		importButton.setBounds(45, 25, 150, 50);
		importButton.addActionListener(importListener);
		this.add(importButton);
		
		//set up  pathFind checkbox.
		findPath = new JCheckBox();
		findPath.setBounds(100, 80, 25, 25);
		this.add(findPath);
		findPathLabel = new JLabel("find path");
		findPathLabel.setBounds(45, 80, 100, 25);
		this.add(findPathLabel);
		
		//set frame parameters.
		this.setSize(width, height);
		this.setLayout(null);
		
	}
	
	//returns an AppJFrame class with all necessary components added. 
	public static AppJFrame CreateAppJFrame() {
		AppJFrame returnFrame = new AppJFrame();
		
		return returnFrame;
	}
	
	//import a maze and display it on a new JFrame using colored rectangles arranged in a grid layout. 
	public void DisplayMaze(String _filePath) {
		//if maze cannot be created display a popup saying so and stop the function. 
		try {
			maze = Maze.CreateMazeFromFile(_filePath);
		}
		catch(Exception e) {
			JDialog popup = new JDialog(this, "Invalid File", true);
			JButton okayButton = new JButton("OK");
			okayButton.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent E){popup.setVisible(false);}});
			okayButton.setBounds(120, 80, 60, 30);
			popup.add(okayButton);
			JLabel label = new JLabel("Maze file could not be processed");
			label.setHorizontalAlignment(SwingConstants.CENTER);
			popup.add(label);
			popup.setSize(300,150);
			popup.setVisible(true);
			
			return;
		}
		
		//get the path.
		ArrayList<Cell> path = Pathfind.findPath(maze);
		//create a couple variables used for finding which cells are in the path. 
		Cell compareCell = new Cell();
		boolean isPath = false;
		
		//create the mazeFrame
		JFrame mazeFrame = new JFrame();
		mazeFrame.setSize(mazeFrameWidth, mazeFrameHeight);
		
		/*add each cell to the frame. Green for start, red for finish, yellow for path, white for passable, black for blocked.
		some cells fit under multiple groups, such as the start and end being passable and part of the path. Start/end overrides
		path, and path overrides passable.*/
		for(int y = 0; y < maze.height; y++) {
			for(int x = 0; x < maze.width; x++) {
				JPanel cell = new JPanel();
				
				if(x == maze.startPosition.x && y == maze.startPosition.y) {
					cell.setBackground(Color.green);
				}
				else if(x == maze.endPosition.x && y == maze.endPosition.y) {
					cell.setBackground(Color.red);
				}
				else {
					if(findPath.isSelected()) {
						//check if the cell is in the path. 
						compareCell.x = x;
						compareCell.y = y;
						for(Cell pathCell : path) {
							//if the pathcell being checked has the came coordinates as the compareCell, the cell is in the path.
							if(maze.CompareCoordinates(pathCell, compareCell)) {
								isPath = true;
								//remove the pathCell to quicken future searches: no other cell will have the came coordinates. 
								path.remove(pathCell);
								break;
							}
						}
					}
					
					if(isPath) {
						cell.setBackground(Color.yellow);
						isPath = false;
					}
					else if(maze.cells[x][y] == 1) {
						cell.setBackground(Color.white);
					}
					else if(maze.cells[x][y] == 0) {
						cell.setBackground(Color.black);
					}
				}
				mazeFrame.add(cell);
			}
		}
		
		//set the layout to a grid depending on the maze dimensions (GridLayout take rows, columns as input).
		mazeFrame.setLayout(new GridLayout(maze.height, maze.width, 1, 1));
		
		//show the mazeFrame.
		mazeFrame.setVisible(true);
	}
	
}
