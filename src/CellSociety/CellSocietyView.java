package CellSociety;

import Simulations.Simulation;
import UI_FrontEndGrid.BaseFrontEndGrid;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class CellSocietyView {
	public static final int DEFAULT_GRID_SIZE = 30;
	public static final double DEFAULT_SPEED = 150;

	private Simulation mySimulation;
	private BaseFrontEndGrid myDisplay;
	private Timeline cellAnimation;

	public CellSocietyView(Simulation newSimulation, BaseFrontEndGrid mainDisplay) {
		mySimulation = newSimulation;
		myDisplay = mainDisplay;
		initializeAnimation();
	}

	private void initializeAnimation() {
		KeyFrame gridFrame = new KeyFrame(Duration.millis(DEFAULT_SPEED), e -> getNextFrame());
		cellAnimation = new Timeline();
		cellAnimation.setCycleCount(Timeline.INDEFINITE);
		cellAnimation.getKeyFrames().add(gridFrame);
	}

	public void startOp() {
		cellAnimation.play();
	}

	public void stopOp() {
		cellAnimation.pause();
	}

	public void getNextFrame() {
		mySimulation.update();
		myDisplay.updateGrid();
	}
	
	public void setRate(double newRate) {
		cellAnimation.setRate(newRate);
	}
}
