package UI;

import java.io.File;
import java.util.Map;

import Utils.ParameterParser;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class GUIFromFile extends UserInterface{
	private String mySimType;
	private Map<String,String> mySimParameters;
	private Map<int[], String> mySimCells;
	private FileChooser fileBrowse;

	public GUIFromFile(Stage mainStage, String resources, Scene previousScene) {
		super(mainStage, resources, previousScene);
		fileBrowse = new FileChooser();
	}

	private void openFileBrowser() {
		File readFile = fileBrowse.showOpenDialog(getStage());
		if (readFile != null) {
			ParameterParser myParameterParser = new ParameterParser(readFile);
			mySimType = myParameterParser.getSimType();
			mySimParameters = myParameterParser.getParameters();
			mySimCells = myParameterParser.getCells();
			getNewSimulation();
		} else {
			return;
		}
	}

	@Override
	public void extendToolBar(Pane toolBar) {
		Button openFileButton = new Button(getResources("OpenFile"));
		openFileButton.setOnAction(e -> openFileBrowser());
		toolBar.getChildren().add(openFileButton);
	}

	@Override
	public void getNewSimulation() {
		InitiateCS myInitializer = new InitiateCS(mySimType, mySimParameters, mySimCells, GRID_SIZE_RATIO * getWidth(), GRID_SIZE_RATIO * getHeight(),
				DEFAULT_COLOR);
		setCellSociety(myInitializer.getCellSociety());
		setCenterNode(myInitializer.getGridNode());
	}
}
