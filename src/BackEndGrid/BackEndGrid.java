package BackEndGrid;


import java.util.ArrayList;
import java.util.List;

import Cells.Cell;

public class BackEndGrid {
	private Cell[][] myCellGrid;
	private int maxColumns;
	private int maxRows;
	private final int growthRange=5;
	private boolean infinite;
	private boolean toiroidal;

	public BackEndGrid(int size) {
		myCellGrid = new Cell[size][size];
		//myFileReader = new FileReader();//commented this because it caused compile errors, but may be put back in later
		this.maxColumns=size;
		this.maxRows=size;
	}
	//returns null and no exceptions
	public Cell tryGetCell(int x,int y){
		if(toiroidal) return myCellGrid[x%maxRows][y%maxColumns];
		if(x>=0&&x<maxRows&&y>=0&&y<maxColumns) return myCellGrid[x][y];
		return null;
	}
	//exceptions can occur
	public Cell getCell(int x,int y){
		if(toiroidal) return myCellGrid[x%maxRows][y%maxColumns];
		else return myCellGrid[x][y];
	}
	
	public int getRows(){
		return maxRows;
	}
	
	public int getColumns(){
		return maxColumns;
	}

	
	public void switchCell(Cell cell1, Cell cell2){
		int cell2Row=cell2.getRow();
		int cell2Col=cell2.getCol();
		
		setCell(cell1.getRow(), cell1.getCol(), cell2);
		setCell(cell2Row, cell2Col, cell1);
	}

	public void setCell(int row, int col, Cell cell) {
		myCellGrid[row][col] = cell;
		cell.setCol(col);
		cell.setRow(row);
		if(infinite){
			expandIfNeeded(row, col, cell);
		}
	}
	private void expandIfNeeded(int row, int col, Cell cell) {
		if(cell.getState().equals("empty")){
			if(col<=growthRange) growArray(0,-maxColumns,cell);
			if(col>=maxColumns-growthRange) growArray(0,maxColumns,cell);
			if(row<=growthRange) growArray(-maxRows,0,cell);
			if(row>=maxRows-growthRange) growArray(maxRows,0,cell);
		}
	}
	
	public void growArray(int rowDisplacement,int columnDisplacement, Cell toolCell){
		Cell[][] newCellGrid=new Cell[maxRows+rowDisplacement][maxColumns+columnDisplacement];
		for(int i=0;i<newCellGrid.length;i++){
			for(int j=0;j<newCellGrid[0].length;j++){
				Cell emptyCell=toolCell.getEmptyCell();
				newCellGrid[i][j]=emptyCell;
				emptyCell.setRow(i);
				emptyCell.setCol(j);
			}
		}
		
		int startRow=0;
		int startColumn=0;
		if(rowDisplacement<0) startRow=Math.abs(rowDisplacement);
		if(columnDisplacement<0) startColumn=Math.abs(columnDisplacement);
		for(int m=0;m<myCellGrid.length;m++){
			for(int n=0;m<myCellGrid[0].length;n++){
				newCellGrid[m+startRow][n+startColumn]=myCellGrid[m][n];
				newCellGrid[m+startRow][n+startColumn].setRow(m+startRow);
				newCellGrid[m+startRow][n+startColumn].setRow(n+startColumn);
			}
		}
		myCellGrid=newCellGrid;
		maxRows+=Math.abs(rowDisplacement);
		maxColumns+=Math.abs(columnDisplacement);
	}
	
	//these neighbour functions will be implemented by subclasses.
	public List<Cell> getFourNeighbors(int row, int col) {
		List<Cell> neighborList = new ArrayList<Cell>();
		neighborList.add(tryGetCell(row-1,col));
		neighborList.add(tryGetCell(row+1,col));
		neighborList.add(tryGetCell(row,col-1));
		neighborList.add(tryGetCell(row,col+1));
		
		return neighborList;
	}
	
	public List<Cell> getEightNeighbors(int row, int col) {
		List<Cell> neighborList = new ArrayList<Cell>();
		neighborList.addAll(getFourNeighbors(row, col));
		neighborList.add(tryGetCell(row-1,col-1));
		neighborList.add(tryGetCell(row-1,col+1));
		neighborList.add(tryGetCell(row+1,col-1));
		neighborList.add(tryGetCell(row+1,col+1));
		
		return neighborList;
	}
	//to be overriden depending on each simulation
	public List<Cell> getNeighbors(int row, int column){
		return null;
	}
	
	
}
