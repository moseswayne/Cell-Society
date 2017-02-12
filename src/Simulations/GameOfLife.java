package Simulations;

import java.util.List;
import java.util.Map;

import Cells.Cell;
import Cells.GameOfLifeCell;

public class GameOfLife extends Simulation{
	
	private static final String ALIVE = "alive";
	private static final String DEAD = "dead";

	private int numDead; 
	
	public GameOfLife(Map<String,String> parameters,Map<int[],String> cells) {
		super(parameters,cells);
		numDead = 0;
	}
	
	@Override
	public void update(){
		for (int i=0; i<getGridSize();i++) {
			for (int j = 0; j<getGridSize();j++) {
				applyRulesToCell(i,j);
			}
		}
	}
	
	private void applyRulesToCell(int row, int col) {
		Cell cell = getMyGrid().tryGetCell(row, col);
		List<Cell> neighbors = getMyGrid().getEightNeighbors(row, col);
		int numAliveNeighbors=0;
		for (Cell gameCell : neighbors) {
			if (gameCell.getState().equals(ALIVE)) {
				numAliveNeighbors++;
			}
		}
		if (cell.getState().equals(ALIVE)) {
			if (numAliveNeighbors<2||numAliveNeighbors>3) {
				cell.updateState(DEAD);
				numDead++;
				return;
			}
		} else if (cell.getState().equals(DEAD)) {
			if (numAliveNeighbors==3) {
				cell.updateState(ALIVE);
				numDead--;
				return;
			}
		}
	}

	@Override
	public void initiateSimulation() {
		for (int[] coordinates : getMyCells().keySet()) {
			String cellType = getMyCells().get(coordinates);
			if (cellType.equals(DEAD)) numDead++;
			GameOfLifeCell cell = new GameOfLifeCell(cellType);
			getMyGrid().setCell(coordinates[0],coordinates[1],cell);
		}
	}

	public int getNumDead() {
		return numDead;
	}
	
	public int getNumAlive() {
		return getGridSize()*getGridSize()-numDead;
	}


}