package UI;

import java.util.Map;

import CellSociety.CellSocietyView;
import Simulations.Fire;
import Simulations.GameOfLife;
import Simulations.PredatorPrey;
import Simulations.Segregation;
import Simulations.Simulation;
import UI_FrontEndGrid.BaseFrontEndGrid;
import Utils.ParameterParser;
import javafx.scene.Node;
import javafx.scene.paint.Color;

public class InitiateCS {

	private Simulation mySimulation;
	private ParameterParser myDataFile;
	private BaseFrontEndGrid myDisplay;
	private Color myColor;
	private double myWidth;
	private double myHeight;
	
	public InitiateCS(String sim, Map<String,String> params, Map<int[], String> cells, double gridWidth, double gridHeight, Color backColor) {
		myWidth = gridWidth;
		myHeight = gridHeight;
		myColor = backColor;
		instantiateSimulation(sim, params, cells);
		myDisplay = new BaseFrontEndGrid(mySimulation.getMyGrid(), myWidth, myHeight, myColor);
	}
	
	public void instantiateSimulation(String simType, Map<String,String> myParameters, Map<int[], String> myCells) {
		if (simType.equals("Fire")) {
			mySimulation = new Fire(myParameters, myCells);
		} else if (simType.equals("GameOfLife")) {
			mySimulation = new GameOfLife(myParameters, myCells);
		} else if (simType.equals("PredatorPrey")) {
			mySimulation = new PredatorPrey(myParameters, myCells);
		} else if (simType.equals("Segregation")) {
			mySimulation = new Segregation(myParameters, myCells);
		} else {
			System.exit(1);
		}
		mySimulation.initiateSimulation();
	}
	
	public Node getGridNode() {
		myDisplay.updateGrid();
		return myDisplay.returnDisplay();
	}
	
	public CellSocietyView getCellSociety() {
		return new CellSocietyView(mySimulation, myDisplay);
	}
}
