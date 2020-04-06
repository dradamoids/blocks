import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Piece {
	
	
	private TType ttype;
	private int xcenter;  //the position of the piece on the grid
	private int ycenter;
	private int xpos;  // the position of the piece on the panel
	private int ypos;
	
	private Color color;
	private Color backcolor;
	private int [][] configuration;
	private Random rand;
	private BlockGrid grid;

	
	
	public Piece(BlockGrid grid,TType ttype) {

		this.grid = grid;
		this.ttype = ttype;
		backcolor = Color.GRAY;
		switch (ttype) {
			case LINE:
				configuration = new int[][] {{0,-1},{0,0},{0,1},{0,2}};
				color = Color.BLUE;
				break;
			case SQUARE:
				configuration = new int[][] {{-1,1},{0,1},{0,0},{-1,0}};
				color = Color.YELLOW;
				break;
			case SSHAPE:
				configuration = new int[][] {{1,1},{0,1},{0,0},{-1,0}};
				color = Color.GREEN;
				break;
			case ZSHAPE:
				configuration = new int[][] {{-1,1},{0,1},{0,0},{1,0}};
				color = Color.ORANGE;
				break;
			case R_LSHAPE:
				configuration = new int[][] {{1,1},{0,1},{0,0},{0,-1}};
				color = Color.MAGENTA;
				break;
			case L_LSHAPE:
				configuration = new int[][] {{-1,1},{0,1},{0,0},{0,-1}};
				color = Color.PINK;
				break;
			case TSHAPE:
				configuration = new int[][] {{0,1},{-1,0},{0,0},{1,0}};
				color = Color.RED;
				break;
			case NOSHAPE:
				configuration = new int[][] {{}};
				color = Color.BLACK;
		}
		xcenter=Global.GRIDSIZEX/2;
		ycenter=1;

	}
	
	public TType getType() {
		return this.ttype;
	}
	
	public void setPos(int xpos,int ypos) {
		this.xpos = xpos;
		this.ypos = ypos;
		
		int newcenterx=xpos/Global.SQUARESIZE;
		int newcentery=ypos/Global.SQUARESIZE;
		if (canMove(newcenterx, newcentery)) {

				this.xcenter = newcenterx;
				this.ycenter = newcentery;

			
		}

	}
	
	public int getCenterY() {
		return this.ycenter;
	}
	public int getCenterX() {
		return this.xcenter;
	}
	
	public TType getRandomShape() {
		
		int x = rand.nextInt(7);
		return TType.values()[x];
		
	}
	
	
	public void rotateLeft() {

		int xval=0;
		int yval=0;
		
		for (int[] vector: configuration) {
			xval = vector[0];
			yval = vector[1];
			vector[0] = -yval;
			vector[1] = xval;
		}
		
		
		
	}
	public void rotateRight() {

			int xval=0;
			int yval=0;
			unblit();
			for (int[] vector: configuration) {
				xval = vector[0];
				yval = vector[1];
				vector[0] = yval;
				vector[1] = -xval;
			
		}
		blit();
		
		
	}
	
	private boolean pieceCanRotate(int[][]configuration) {
		boolean canItMove = true;
		for (int [] vector: configuration) {
			if (!coorInPiece(xcenter+vector[0],ycenter+vector[1])) {
			if (!grid.canMove(xcenter+vector[0], ycenter+vector[1]))
				canItMove = false;
		}
		}
		return canItMove;	
	}
	public void moveDown() {
			unblit();
			ycenter +=1;
			blit();
		
	}
	
	public void moveLeft() {

			unblit();
			xcenter -=1;
			blit();
		
	}
	
	public void moveRight() {
			unblit();
			xcenter +=1;
			blit();
		
	}
	public void moveUp() {
		unblit();
		ycenter -= 1;
		blit();
	}
	
	private boolean canMove(int xcenter, int ycenter) {
		boolean canItMove = true;
		for (int [] vector: configuration) {
			if (!coorInPiece(xcenter+vector[0],ycenter+vector[1])) {
				if (!grid.canMove(xcenter+vector[0], ycenter+vector[1]))
					canItMove = false;
		}
		}
		return canItMove;
		
	}
	
	private boolean canPlace() {
		for (int [] vector: configuration) {
			
			if (!grid.canPlace(xcenter+vector[0], ycenter+vector[1]))
					return false;
		
		}
		return true;
	}
	
	public boolean place() {
		if (canPlace()) {
			blit();
			return true;
		} 
		return false;
		
	}

	
	private boolean coorInPiece(int x,int y) {
		for (int [] vector : configuration) {
			if ((x==(vector[0]+xcenter)) && (y==(vector[1]+ycenter))){
				return true;
			}
		}
		return false;
	}
	public void blit() {
		for (int [] vector: configuration) {
			grid.turnOn(vector[0]+xcenter, vector[1]+ycenter, color);
		}
		
		
	}
	
	public void unblit() {
		for (int [] vector: configuration) {
			grid.turnOff(vector[0]+xcenter, vector[1]+ycenter);
		}
		
		
	}
	
	public void draw(Graphics canvas) {
		for (int [] vector: configuration) {
			Square.draw((vector[0]+xcenter)*Global.SQUARESIZE, (vector[1]+ycenter)*Global.SQUARESIZE, canvas, backcolor);
		}	
		for (int [] vector: configuration) {
			Square.draw(vector[0]*Global.SQUARESIZE+xpos, vector[1]*Global.SQUARESIZE+ypos, canvas, color);
		}	
		
	}
}
