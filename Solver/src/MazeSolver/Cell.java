package MazeSolver;

public class Cell {
	public int x;
	public int y;
	public float g;
	public float h;
	public float f;
	
	public Cell parent;
	
	//constructor with no parameters, all numbers start being 0. 
	public Cell(){
		this.x = 0;
		this.y = 0;
		this.g = Float.MAX_VALUE;
		this.h = Float.MAX_VALUE;
		this.f = Float.MAX_VALUE;
		this.parent = null;
	}
}
