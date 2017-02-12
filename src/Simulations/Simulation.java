package Simulations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import BackEndGrid.BackEndGrid;
import Cells.Cell;
import Utils.ParameterParser;

public abstract class Simulation {
	//private final int screenSizeX = 400;
	//private final int screenSizeY = 400;
	
	private BackEndGrid myGrid;
	private int myGridSize;
	private String myTitle;
	private Map<String,String> myParameters;
	private Map<int[],String> myCells;
	
	public Simulation(Map<String,String> parameters, Map<int[],String> cells){
		myParameters = parameters;
		myCells = cells;
		myGridSize = Integer.parseInt(myParameters.get("size"));
		setMyGrid(new BackEndGrid(myGridSize));
		myTitle = myParameters.get("title");
	}
	
	public abstract void update();
	
	public abstract void initiateSimulation();
	
	public List<Cell> getStateSpecificSubset(List<Cell> cells, String state){
		List<Cell> sublist=new ArrayList<Cell>();
		for(Cell cell:cells){
			if(cell.getState().equals(state)){
				sublist.add(cell);
			}
		}
		return sublist;
	}
	
	public List<Cell> getClassSpecificSubcells(List<Cell> list,String className){
		List<Cell> sublist=new ArrayList<>();
		Class<?> cls;
		try {
			cls = Class.forName(className);
			for(Cell item:list){
				if(cls.isInstance(item)){	
					sublist.add(item);
				}
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sublist;
	}
	
	public void play() {
		
	}
	
	public void pause() {
		
	}
	
	public void stop() {
		
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
	
	public Map<String,String> getMyParameters() {
		return myParameters;
	}
	
	public Map<int[],String> getMyCells() {
		return myCells;
	}
	
	public void updateCellInMap(Cell cell) {
		int[] coordinates = new int[2];
		coordinates[0] = cell.getRow();
		coordinates[1] = cell.getCol();
		myCells.put(coordinates, cell.getState());
	}

	
}