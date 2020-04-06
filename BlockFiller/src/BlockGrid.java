import java.awt.Color;
import java.awt.Graphics;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class BlockGrid {

	private Square grid[][];
	private int level=0;
	private ArrayList<String> result;
	
	public BlockGrid() {
		
		grid = new Square [Global.GRIDSIZEY][Global.GRIDSIZEX];
		for (int i = 0; i<Global.GRIDSIZEX; i++) {
			for (int j=0;j<Global.GRIDSIZEY; j++) {
				grid[j][i] = new Square(i,j);
				
			}
		}
		try {
			load();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		init();
	
	}
	
	public void load() throws FileNotFoundException, IOException {
		result = new ArrayList<>();
		 
		try (FileReader f = new FileReader("data.txt")) {
		    StringBuffer sb = new StringBuffer();
		    while (f.ready()) {
		        char c = (char) f.read();
		        if (c == '\n') {
		            result.add(sb.toString());
		            sb = new StringBuffer();
		        } else {
		            sb.append(c);
		        }
		    }
		    if (sb.length() > 0) {
		        result.add(sb.toString());
		    }
		}  
	}
	
	public void init() {
	
		for (int i=0;i<Global.GRIDSIZEY;i++) {
			for (int j=0; j<Global.GRIDSIZEX; j++) {
		
				if (result.get(i+level*Global.GRIDSIZEY).charAt(j) == '*') {
					grid[i][j].turnOn(Color.LIGHT_GRAY);
				}
				else {
					grid[i][j].turnOff();
				}
			}
		}
		level = (level+1)%3;
	}
	
	public boolean isFull() {
		for (int i = 0; i<Global.GRIDSIZEX; i++) {
			for (int j=0;j<Global.GRIDSIZEY; j++) {
				if (!grid[j][i].isOn()) {
					return false;
				}
			}
		}
		return true;
	}
	public void draw(Graphics canvas) {
		for (Square [] line : grid) {
			for (Square square : line) {
				if (square.isOn()) {
					square.draw(canvas);
				}
			}
		}
	}
	
	public void turnOn(int x, int y, Color color) {
		grid[y][x].turnOn(color);
	}
	public void turnOff(int x, int y) {
		grid[y][x].turnOff();
	}
	
	// check that a particular position is in the bounds of the grid and that the spot
	// is not already occupied.
	public boolean canPlace(int x, int y) {
		if (canMove(x,y)) {
		
			return !grid[y][x].isOn();
		}
		return false;
	}
	
	// check that a particular position is in the bounds of the grid
	public boolean canMove(int x, int y) {
		if ((x<0) || (x>=Global.GRIDSIZEX)) {
	 		return false;
		}
		if ((y<0) || (y>=Global.GRIDSIZEY)) {
			return false;
		}
		return true;
		
	}
	
	private void reset() {
		for (Square [] line : grid) {
			for (Square square : line) {
				square.turnOff();

			}
		}
		
	}
	


}

