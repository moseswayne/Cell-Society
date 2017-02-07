package Cells_Wator;

import javafx.scene.paint.Color;

public class WatorPredator extends WatorCreature{
	private final Color PREDATOR_COLOR=Color.RED;
	private final int PREDATOR_GESTATION_PERIOD=10;
	private final int MAX_HUNGER_PERIOD=6;
	private int timeSinceAte=0;
	
	public WatorPredator(){
		setColor(PREDATOR_COLOR);
		setGestationPeriod(PREDATOR_GESTATION_PERIOD);
	}
	
	public WatorPredator makeChild(){
		return new WatorPredator();
	}
	
	public void incrementTimeSinceAte(){
		timeSinceAte++;
	}
	
	public void resetTimeSinceAte(){
		timeSinceAte=0;
	}
	
	public int getTimeSinceAte(){
		return timeSinceAte;
	}
	
	public int getMaxHungerPeriod(){
		return MAX_HUNGER_PERIOD;
	}

}
