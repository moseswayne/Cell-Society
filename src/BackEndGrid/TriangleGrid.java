package BackEndGrid;

import java.util.ArrayList;
import java.util.List;

import Cells.Cell;

public class TriangleGrid extends BackEndGrid{

	public TriangleGrid(int size) {
		super(size);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Cell> getNeighbors(int row, int column) {
		// TODO Auto-generated method stub
		List<Cell> neighbors =new ArrayList<Cell>();
		if(row%4==0){
			neighbors.add(tryGetCell(row+1,column-1));
			neighbors.add(tryGetCell(row+1,column));
			neighbors.add(tryGetCell(row-1,column));
		}
		if(row%4==1){
			neighbors.add(tryGetCell(row-1,column));
			neighbors.add(tryGetCell(row-1,column+1));
			neighbors.add(tryGetCell(row+1,column));
		}
		if(row%4==2){
			neighbors.add(tryGetCell(row+1,column));
			neighbors.add(tryGetCell(row+1,column+1));
			neighbors.add(tryGetCell(row-1,column));
		}
		if(row%4==3){
			neighbors.add(tryGetCell(row-1,column-1));
			neighbors.add(tryGetCell(row-1,column));
			neighbors.add(tryGetCell(row+1,column));
		}
		return neighbors;
	}

}
