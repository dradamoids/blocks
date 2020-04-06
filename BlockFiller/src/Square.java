import java.awt.Color;
import java.awt.Graphics;

public class Square {
	
	private Color color;
	private int x;
	private int y;
	private boolean isVisible;
	
	public Square(int x, int y) {
		this.x=x;
		this.y=y;
		isVisible=false;
	}
	
	public Square(int x,int y,Color color) {
		this.x=x;
		this.y=y;
		this.color=color;
		isVisible = true;
		
	}
	
	public void setX(int x) {
		this.x = x;
	}
	public int getX() {
		return this.x;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getY() {
		return this.y;
	}
	
	public boolean isOn() {
		return isVisible;
	}
	
	public void turnOn(Color color) {
		this.color=color;
		isVisible=true;
	}
	
	public void turnOff() {
		isVisible=false;
	}
	
	
	public void draw(Graphics canvas) {
		
		
		canvas.setColor(color.darker());
		canvas.fillRect(x*Global.SQUARESIZE, y*Global.SQUARESIZE, Global.SQUARESIZE, Global.SQUARESIZE);
		
		canvas.setColor(color);
		canvas.fillRect(x*Global.SQUARESIZE+2, y*Global.SQUARESIZE+2, Global.SQUARESIZE-4, Global.SQUARESIZE-4);

		
	}
	public static void draw(int x, int y, Graphics canvas, Color color) {
		
		canvas.setColor(color.darker());
		canvas.fillRect(x, y, Global.SQUARESIZE, Global.SQUARESIZE);
		

		canvas.setColor(color);
		canvas.fillRect(x+2, y+2, Global.SQUARESIZE-4, Global.SQUARESIZE-4);

		
	}

}
