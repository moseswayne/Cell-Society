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
		neighbors.add(tryGetCell(row,column-1));
		neighbors.add(tryGetCell(row,column+1));
		if((row+column)%2==0){
			neighbors.add(tryGetCell(row-1,column));
		}else{
			neighbors.add(tryGetCell(row+1,column));
		}
		return neighbors;
	}

}
