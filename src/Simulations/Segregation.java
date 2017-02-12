package Simulations;

import java.util.ArrayList;	
import java.util.List;
import java.util.Map;
import java.util.Random;

import Cells.Cell;
import Cells.SegregationCell;

public class Segregation extends Simulation {
	private final String EMPTY = "empty"; // duplicated code
	private final String TYPE1 = "type1";
	private final String TYPE2 = "type2";
	private double satisfactionRequirement=0.5;

	public Segregation(Map<String,String> parameters,Map<int[],String> cells) {
		super(parameters,cells);
	}

	@Override
	public void update() {
		List<SegregationCell> occupiedCells = new ArrayList<>();
		List<SegregationCell> emptyCells = new ArrayList<>();
		for (int i = 0; i < getMyGrid().getRows(); i++) {
			for (int j = 0; j < getMyGrid().getColumns(); j++) {
				SegregationCell cell = (SegregationCell) getMyGrid().tryGetCell(i, j);
				if (cell.getState().equals(EMPTY)) {
					emptyCells.add(cell);
				} else {
					occupiedCells.add(cell);
				}
			}
		}

		for (SegregationCell cell : occupiedCells) {
			if (!cell.alwaysSatisfied()) {

				List<Cell> cellNeighbors = getMyGrid().getEightNeighbors(cell.getRow(), cell.getCol());
				List<Cell> emptyNeighbors = getStateSpecificSubset(cellNeighbors, EMPTY);
				List<Cell> similarNeighbors = getStateSpecificSubset(cellNeighbors, cell.getState());
				double occupiedNeighbors = cellNeighbors.size() - emptyNeighbors.size();
				double numberOfSimilarNeighbors = similarNeighbors.size();
				double similarityIndex = numberOfSimilarNeighbors / occupiedNeighbors;

				if (similarityIndex < satisfactionRequirement) {
					Random rn = new Random();
					int index = rn.nextInt(emptyCells.size());
					getMyGrid().switchCell(cell, emptyCells.get(index));
				}
			}
		}
	}

	@Override
	public void initiateSimulation() {
		for (int[] coordinates : getMyCells().keySet()) {
			String cellType = getMyCells().get(coordinates);
			SegregationCell cell = null;
			if (cellType.equals(TYPE1)) {
				cell = new SegregationCell(TYPE1);
			} else if (cellType.equals(TYPE2)) {
				cell = new SegregationCell(TYPE2);
			} else if (cellType.equals(EMPTY)) {
				cell = new SegregationCell(EMPTY);
			}
			getMyGrid().setCell(coordinates[0], coordinates[1], cell);
			cell.setRow(coordinates[0]);
			cell.setCol(coordinates[1]);
		}
	}

}