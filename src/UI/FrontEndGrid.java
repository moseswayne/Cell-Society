package UI;

import BackEndGrid.BackEndGrid;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * @author Daniel
 *	Stores a node that represents the grid of Cells in the BackEndGrid that can be returned when called.
 */
public class FrontEndGrid { 
	private double canvasWidth;
	private double canvasHeight;
	private Canvas gridPicture;
	private BackEndGrid myGrid;
	private Color defaultColor;

	public FrontEndGrid(BackEndGrid myGrid, double canvasWidth, double canvasHeight, Color defaultColor) {
		this.defaultColor=defaultColor;
		this.myGrid=myGrid;
		this.canvasHeight=canvasHeight;
		this.canvasWidth=canvasWidth;
		gridPicture=new Canvas(canvasWidth,canvasHeight);
	}

	public void updateGrid(){
		GraphicsContext gc=gridPicture.getGraphicsContext2D();
		gc.setFill(defaultColor);
		gc.fillRect(0, 0, canvasWidth, canvasHeight);
		
		int columns=myGrid.getColumns();
		int rows=myGrid.getRows();
		double cellWidth=canvasWidth/columns;
		double cellHeight=canvasHeight/rows;
		for(int i=0;i<columns;i++){
			for(int j=0;j<rows;j++){
				Color javaColor=myGrid.getCell(i,j).getColor();
				gc.setFill(javaColor);
				gc.fillRect(i*cellWidth, j*cellHeight, (i+1)*cellWidth, (j+1)*cellHeight);
			}
		}
	}
	
	public Node returnDisplay(){
		return gridPicture;		
	}

}