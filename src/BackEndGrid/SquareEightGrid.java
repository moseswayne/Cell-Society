package BackEndGrid;

import java.util.ArrayList;
import java.util.List;

import Cells.Cell;

public class SquareEightGrid extends BackEndGrid{

	public SquareEightGrid(int size) {
		super(size);
		// TODO Auto-generated constructor stub
	}


	public List<Cell> getFourNeighbors(int row, int col) {
		List<Cell> neighborList = new ArrayList<Cell>();
		if (row>0) {
			neighborList.add(tryGetCell(row-1,col));
		} if (row<getRows()-1) {
			neighborList.add(tryGetCell(row+1,col));
		} if (col>0) {
			neighborList.add(tryGetCell(row,col-1));
		} if (col<getColumns()-1) {
			neighborList.add(tryGetCell(row,col+1));
		}
		return neighborList;
	}
	
	@Override
	public List<Cell> getNeighbors(int row, int col) {
		List<Cell> neighborList = new ArrayList<Cell>();
		neighborList.addAll(getFourNeighbors(row, col));
		if (row>0&&col>0) {
			neighborList.add(tryGetCell(row-1,col-1));
		} if (row>0&&col<getColumns()-1) {
			neighborList.add(tryGetCell(row-1,col+1));
		} if (row<getRows()-1&&col>0) {
			neighborList.add(tryGetCell(row+1,col-1));
		} if (row<getRows()-1&&col<getColumns()-1) {
			neighborList.add(tryGetCell(row+1,col+1));
		}
		return neighborList;
	}

}
