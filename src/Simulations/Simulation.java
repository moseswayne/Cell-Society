package Simulations;

import BackEndGrid.BackEndGrid;

public abstract class Simulation {
	//private final int screenSizeX = 400;
	//private final int screenSizeY = 400;
	
	private BackEndGrid myGrid;
	private int myGridSize;
	private String myTitle;
	
	public Simulation(int size,String title){
		myGridSize = size;
		setMyGrid(new BackEndGrid(size));
		myTitle = title;
	}
	
	public abstract void update();
	
	public abstract void initiateSimulation();
	
	public abstract void calculateStatus();
	
	public void play() {
		
	}
	
	public void pause() {
		
	}
	
	public void stop() {
		
	}
	
	public void switchCell(){
		
	}
	
	public void moveToAndReplaceCell(){
		
	}

	public BackEndGrid getMyGrid() {
		return myGrid;
	}

	public void setMyGrid(BackEndGrid myGrid) {
		this.myGrid = myGrid;
	}
	
	public int getGridSize() {
		return myGridSize;
	}
	
	public String getTitle() {
		return myTitle;
	}
	
}