package Simulations;

import Cells.GameOfLifeCell;

public class GameOfLife extends Simulation{
	
	private static final double probDead = 0.2;
	
	private static final String ALIVE = "alive";
	private static final String DEAD = "dead";

	public GameOfLife(int size,String title) {
		super(size,title);
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
		GameOfLifeCell cell = (GameOfLifeCell) getMyGrid().getCell(row, col);
		int numNeighbors = getMyGrid().getEightNeighbors(row, col).size();
		if (cell.getState().equals(ALIVE)) {
			if (numNeighbors<2||numNeighbors>3) {
				cell.updateState(DEAD);
				return;
			}
		} else if (cell.getState().equals(DEAD)) {
			if (numNeighbors==3) {
				cell.updateState(ALIVE);
				return;
			}
		}
	}

	@Override
	public void initiateSimulation() {
		for (int i=0; i<getGridSize();i++) {
			for (int j = 0; j<getGridSize();j++) {
				double rand = Math.random();
				if (rand<probDead) {
					getMyGrid().setCell(i, j, new GameOfLifeCell(DEAD));
				} else {
					getMyGrid().setCell(i, j, new GameOfLifeCell(ALIVE));
				}
			}
		}
	}


	@Override
	public void calculateStatus() {
		// TODO Auto-generated method stub
		
	}

}