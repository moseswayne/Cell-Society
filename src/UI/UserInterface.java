package UI;

import java.util.Map;
import java.util.ResourceBundle;

import CellSociety.CellSocietyView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public abstract class UserInterface {
	public static final int BUTTON_SPACING = 10;
	public static final Color DEFAULT_COLOR = Color.WHITE;
	public static final double GRID_SIZE_RATIO = .80;

	private CellSocietyView myCellSociety;
	private Stage myStage;
	private BorderPane myScreen;
	private Button startButton;
	private Button stepButton;
	private Button stopButton;
	private Button resetButton;
	private Slider mySpeedSlider;
	private boolean runSimulation;
	private Scene myScene;
	private Scene backScene;
	private ResourceBundle myResources;
	private double myWidth;
	private double myHeight;

	public UserInterface(Stage mainStage, String resources, Scene previousScene) {
		myResources = ResourceBundle.getBundle(resources);
		backScene = previousScene;
		myStage = mainStage;
		myScreen = new BorderPane();
		myScreen.setTop(setUpToolBar());
		myScreen.setLeft(setControlPanel());
		enableButtons();
	}

	private Node setUpToolBar() {
		HBox toolBar = new HBox();
		toolBar.setAlignment(Pos.TOP_RIGHT);
		Button returnToMainMenu = new Button(myResources.getString("ReturnMainMenu"));
		returnToMainMenu.setOnAction(e -> goBackToMenu());
		toolBar.getChildren().add(returnToMainMenu);
		extendToolBar(toolBar);
		toolBar.getChildren().add(testAdd());
		return toolBar;
	}
	
	public abstract void extendToolBar(Pane toolBar);

	private Node setControlPanel() {
		VBox controlPanel = new VBox();
		controlPanel.setAlignment(Pos.CENTER_LEFT);
		controlPanel.setSpacing(BUTTON_SPACING);
		initiateControlButtons();
		Label sliderLabel = new Label(myResources.getString("SliderLabel"));
		controlPanel.getChildren().addAll(startButton, stepButton, stopButton, resetButton, sliderLabel, mySpeedSlider);
		return controlPanel;
	}

	private void initiateControlButtons() {
		startButton = initiateButton(myResources.getString("StartButton"), new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				runSimulation = true;
				enableButtons();
				myCellSociety.startOp();
			}
		});
		stopButton = initiateButton(myResources.getString("StopButton"), new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				runSimulation = false;
				enableButtons();
				myCellSociety.stopOp();
			}
		});
		stepButton = initiateButton(myResources.getString("StepButton"), new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				myCellSociety.getNextFrame();
			}
		});
		resetButton = initiateButton(myResources.getString("ResetButton"), new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				runSimulation = false;
				myCellSociety.stopOp();
				getNewSimulation();
				enableButtons();
			}
		});
		mySpeedSlider = new Slider(0, 5, 1);
		mySpeedSlider.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				myCellSociety.setRate(newValue.doubleValue());
			}

		});
	}
	
	private Node testAdd() {
		Button testButton = new Button("lol");
		Button otherTest = new Button("lmao");
		VBox newVBox = new VBox();
		newVBox.getChildren().addAll(testButton,otherTest);
		return newVBox;
	}

	private Button initiateButton(String resourceString, EventHandler<ActionEvent> handler) {
		Button controlButton = new Button(resourceString);
		controlButton.setOnAction(handler);
		controlButton.setMaxWidth(Double.MAX_VALUE);
		return controlButton;
	}
	/*
	public void getNewSimulation() {
		InitiateCS myInitializer = new InitiateCS(mySimType, mySimParameters, mySimCells, GRID_SIZE_RATIO * getWidth(), GRID_SIZE_RATIO * getHeight(),
				DEFAULT_COLOR);
		setCellSociety(myInitializer.getCellSociety());
		setCenterNode(myInitializer.getGridNode());
	}*/
	
	public abstract void getNewSimulation();
	
	public void setParameterMaps(String sim, Map<String,String> params, Map<int[],String> cells) {
		
	}
	
	public void setCellSociety(CellSocietyView newView) {
		myCellSociety = newView;
		enableButtons();
	}
	
	public void setCenterNode(Node gridNode) {
		myScreen.setCenter(gridNode);
	}
	
	public void setBottomPane(Node gridNode) {
		myScreen.setBottom(gridNode);
	}
	
	public Stage getStage() {
		return myStage;
	}
	
	public String getResources(String resourceKey) {
		return myResources.getString(resourceKey);
	}
	
	public double getWidth() {
		return myWidth;
	}
	
	public double getHeight() {
		return myHeight;
	}

	public void setUIScreen(double screenWidth, double screenHeight) {
		myWidth = screenWidth;
		myHeight = screenHeight;
		myScene = new Scene(myScreen, myWidth, myHeight);
		myScene.getStylesheets().add("resources/gui_screen.css");
		myStage.setScene(myScene);
	}
	
	private void goBackToMenu() {
		myStage.setScene(backScene);
	}

	private void enableButtons() {
		startButton.setDisable(myCellSociety == null || runSimulation);
		stepButton.setDisable(myCellSociety == null);
		stopButton.setDisable(myCellSociety == null || !runSimulation);
		resetButton.setDisable(myCellSociety == null);
	}
}
