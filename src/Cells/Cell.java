package Cells;

//import java.awt.Color;
import javafx.scene.paint.Color;
import java.awt.Point;


public abstract class Cell {
	private String myState;
	private Color myColor; 
	private int row;
	private int col;
	
	public int getRow() {
		return row;
	}
	
	public int getCol() {
		return col;
	}
	
	public void setRow(int row) {
		this.row = row;
	}
	
	public void setCol(int col) {
		this.col = col;
	}
	
	public abstract void updateState(String newState);
	
	public Color getColor() {
		return myColor;
	}
	
	public String getState(){
		return myState;
	}
	
	public void setColor(Color color) {
		myColor = color;
	}
	
	public void setState(String state) {
		myState = state;
	}
	
	

}
