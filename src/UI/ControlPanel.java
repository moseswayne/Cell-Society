package UI;

import java.util.ResourceBundle;

import Simulations.Simulation;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class ControlPanel extends VBox{

	private Button startButton;
	private Button stepButton;
	private Button stopButton;
	private Button resetButton;
	private ResourceBundle myResources;
	private Simulation mySimulation;
	private boolean runSimulation;
	
	public ControlPanel(String resources, Simulation currSimulation) {
		myResources = ResourceBundle.getBundle(resources);
		mySimulation = currSimulation;
		runSimulation = false;
	}
	
	private void initiateStartButton() {
		startButton = new Button(myResources.getString("StartButton"));
		startButton.setOnAction(e -> startOp());
	}
	
	private void startOp() {
		runSimulation = true;
		
	}
	
	private void enableButtons() {
		startButton.setDisable(mySimulation==null && runSimulation);
		stepButton.setDisable(mySimulation==null);
		stopButton.setDisable(mySimulation==null && !runSimulation);
		resetButton.setDisable(mySimulation==null);
	}
	
	public void setSimulation(Simulation newSimulation) {
		mySimulation = newSimulation;
	}
}
