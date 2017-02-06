package Simulations;

import java.util.ArrayList;
import java.util.List;

import Cells.Cell;
import Cells.FireCell;

public class Fire extends Simulation {

	private double probCatch;

	private boolean burningTreesLeft;

	private static final String EMPTY = "empty";
	private static final String TREE = "tree";
	private static final String BURNING = "burning";
	
	private List<FireCell> burningList;

	public Fire(int size, double probCatch,String title) {
		super(size,title);
		this.probCatch = probCatch;
		burningList = new ArrayList<FireCell>();
	}

	@Override
	public void update() {
		burningTreesLeft = false;
		for (int i = 0; i<getGridSize();i++) {
			for (int j = 0; j<getGridSize();j++) {
				applyRules(i,j);
			}
		}
		if (burningTreesLeft) {
			updateBurningTrees();
			calculateStatus();
			getMyGrid().displayGrid();
		} else {
			stop();
		}
	}

	private void applyRules(int row, int col) {
		FireCell cell = (FireCell) getMyGrid().getCell(row, col);
		if (cell.getState().equals(TREE)) {
			if (existsBurningNeighbor(getMyGrid().getFourNeighbors(row,col))) {
				calculateNewStateOfTree((FireCell) cell);
			}
		} if (cell.getState().equals(BURNING)) {
			burningList.add(cell);
		}
	}

	private boolean existsBurningNeighbor(List<Cell> neighbors) {
		for (Cell neighborCell : neighbors) {
			if (neighborCell.getState().equals(TREE)) {
				return true;
			} 
		}
		return false;
	}

	private void calculateNewStateOfTree(FireCell cell) {
		double random = Math.random();
		if (random<probCatch) {
			cell.updateState(BURNING);
		}
	}
	
	private void updateBurningTrees() {
		for (FireCell cell : burningList) {
			cell.updateState(EMPTY);
		}
		burningList.clear();
	}

	@Override
	public void initiateSimulation() {
		int gridSize = getGridSize();
		for (int i=0; i<gridSize;i++) {
			for (int j = 0; j<gridSize;j++) {
				if (isBorderCell(i,j,gridSize)) {
					setEmptyCell(i,j);
				} else if (isMiddleCell(i,j,gridSize)){
					setBurningCell(i,j);
				} else {
					setTreeCell(i,j);
				}
			}

		}
	}
	
	private boolean isBorderCell(int row, int col, int gridSize) {
		int gridEdge = gridSize -1;
		if (row==0 || col==gridEdge || row==0 || col==gridEdge) return true;
		else return false;
	}
	
	private void setEmptyCell(int row, int col) {
		getMyGrid().setCell(row, col, new FireCell(EMPTY));
	}
	
	private boolean isMiddleCell(int row, int col, int gridSize) {
		int mid = gridSize/2;
		if (col==mid && row==mid) return true;
		else return false;
	}
	
	private void setBurningCell(int i, int j) {
		getMyGrid().setCell(i, j, new FireCell(BURNING));
	}
	
	private void setTreeCell(int row, int col) {
		getMyGrid().setCell(row, col, new FireCell(TREE));
	}

	@Override
	public void calculateStatus() {
		// TODO Auto-generated method stub
		
	}

}