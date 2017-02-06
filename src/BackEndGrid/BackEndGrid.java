package BackEndGrid;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import Cells.Cell;
import Utils.FileReader;

public class BackEndGrid {
	private double canvasWidth=200;
	private double canvasHeight=200;
	private int sceneWidth=300;
	private int sceneHeight=300;
	private Cell[][] myCellGrid;
	private int columns;
	private int rows;
	private FileReader myFileReader;

	public BackEndGrid(int size) {
		myCellGrid = new Cell[size][size];
		//myFileReader = new FileReader();
		this.columns=size;
		this.rows=size;
	}

	public Cell getCell(int x,int y){
		return myCellGrid[x][y];
	}
	
	public int getRows(){
		return rows;
	}
	
	public int getColumns(){
		return columns;
	}

	
	public void switchCell(Cell cell1, Cell cell2){
		int cell2Row=cell2.getRow();
		int cell2Col=cell2.getCol();
		
		setCell(cell1.getRow(), cell1.getCol(), cell2);
		cell2.setRow(cell1.getRow());
		cell2.setCol(cell1.getCol());
		setCell(cell2Row, cell2Col, cell1);
		cell1.setRow(cell2Row);
		cell1.setCol(cell2Col);
	}

	public void setCell(int row, int col, Cell cell) {
		myCellGrid[row][col] = cell;
	}
	
	public List<Cell> getFourNeighbors(int row, int col) {
		List<Cell> neighborList = new ArrayList<Cell>();
		if (row>0) {
			neighborList.add(getCell(row-1,col));
		} if (row<myCellGrid.length) {
			neighborList.add(getCell(row+1,col));
		} if (col>0) {
			neighborList.add(getCell(row,col-1));
		} if (col<myCellGrid.length) {
			neighborList.add(getCell(row,col+1));
		}
		
		return neighborList;
	}
	
	
	public List<Cell> getEightNeighbors(int row, int col) {
		List<Cell> neighborList = new ArrayList<Cell>();
		neighborList.addAll(getFourNeighbors(row, col));
		if (row>0&&col>0) {
			neighborList.add(getCell(row-1,col-1));
		} if (row>0&&col<myCellGrid.length) {
			neighborList.add(getCell(row-1,col+1));
		} if (row<myCellGrid.length&&col>0) {
			neighborList.add(getCell(row+1,col-1));
		} if (row<myCellGrid.length&&col<myCellGrid.length) {
			neighborList.add(getCell(row+1,col+1));
		}
		return neighborList;
	}

}
