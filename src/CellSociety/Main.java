package CellSociety;

import java.util.HashMap;
import java.util.Map;

import Simulations.Fire;
import Simulations.Simulation;
import UI.MainScreen;
import UI.UserInterface;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	public static final double APPLICATION_HEIGHT = 750;
	public static final double APPLICATION_WIDTH = 750;

	private UserInterface myUI;
	private MainScreen myScreen;
	private String resourceType = "English";
	private String[] mySims = {"Fire", "Game of Life", "Predator Prey", "Segregation"};
	private Map<String, Simulation> mySimulations;

	@Override
	public void start(Stage primaryStage) throws Exception {
		//primaryStage.setTitle("Cell Society");
		//myUI = new UserInterface(primaryStage, RESOURCE_TAG + resourceType);
		//primaryStage.show();
		myScreen = new MainScreen(primaryStage, mySims, APPLICATION_HEIGHT, APPLICATION_WIDTH);
		primaryStage.show();
	}
	/*
	private void getSimulationList() {
		mySimulations = new HashMap<String, Simulation>();
		//mySimulations.put("Fire", new Fire());
		//mySimulations.put("Game of Life", new GameOfLife());
		//mySimulations.put("Predator Prey", new PredatorPrey());
		//mySimulations.put("Segregation", new Segregation());
	}
	*/

	public static void main(String[] args) {
		launch(args);
	}

}
