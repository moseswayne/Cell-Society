package BackEndGrid;

import java.util.ArrayList;
import java.util.List;

import Cells.Cell;

public class HexagonGrid extends BackEndGrid{
	public HexagonGrid(int size) {
		super(size);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Cell> getNeighbors(int row, int column) {
		// TODO Auto-generated method stub
		List<Cell> neighbors=new ArrayList<Cell>();
		neighbors.add(tryGetCell(row-2,column));
		neighbors.add(tryGetCell(row+2,column));
		if(row%2==0){
			neighbors.add(tryGetCell(row-1,column-1));
			neighbors.add(tryGetCell(row+1,column-1));	
			neighbors.add(tryGetCell(row-1,column));
			neighbors.add(tryGetCell(row+1,column));
		}
		if(row%2==1){
			neighbors.add(tryGetCell(row-1,column));
			neighbors.add(tryGetCell(row+1,column));
			neighbors.add(tryGetCell(row-1,column+1));
			neighbors.add(tryGetCell(row+1,column+1));
		}
		
		return null;
	}
}
