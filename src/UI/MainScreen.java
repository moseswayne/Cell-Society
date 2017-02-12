package UI;

import java.util.ArrayList;
import Simulations.Simulation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainScreen {
	public static int BUTTON_SPACING = 10;
	public static final String RESOURCE_TAG = "resources/";

	private String usedResource = "English";

	private Stage myStage;
	private String[] mySimulations;
	private ArrayList<Button> myButtons;
	private Scene myScene;
	private VBox myVBox;
	private double myHeight;
	private double myWidth;

	public MainScreen(Stage initStage, String[] possibleSimulations, double height, double width) {
		myStage = initStage;
		mySimulations = possibleSimulations;
		myVBox = new VBox(BUTTON_SPACING);
		myHeight = height;
		myWidth = width;
		myScene = new Scene(myVBox, myHeight, myWidth);
		setUpMainScreen();
	}

	public void setUpMainScreen() {
		myStage.setScene(myScene);
		this.getButtons();
		for (Button currentButton : myButtons) {
			myVBox.getChildren().add(currentButton);
		}
	}

	private void getButtons() {
		myButtons = new ArrayList<>();
		for (String currentSimulation : mySimulations) {
			// Button currentButton = new Button(currentSimulation.getTitle());
			// currentButton.setOnAction(e ->
			// currentSimulation.initiateSimulation());
			Button currentButton = new Button(currentSimulation);
			currentButton.setOnAction(e -> openUserSim(currentSimulation));
			myButtons.add(currentButton);
		}
		Button fileButton = new Button("Read from File");
		fileButton.setOnAction(e -> openFileSim());
		myButtons.add(fileButton);
	}

	private void openFileSim() {
		UserInterface myUI = new GUIFromFile(myStage, RESOURCE_TAG + usedResource, myScene);
		myUI.setUIScreen(myWidth, myHeight);
	}
	
	private void openUserSim(String simType) {
		String mySim = simType.toLowerCase().replaceAll("\\s+","");
		UserInterface myUI = new GUIFromUser(myStage, RESOURCE_TAG + usedResource, myScene, mySim);
		myUI.setUIScreen(myWidth, myHeight);
	}
}