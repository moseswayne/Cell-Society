package Cells_Wator;

import javafx.scene.paint.Color;
import Cells.Cell;
/**
 * The only reason this class exists is because it saves me the trouble of dealing with nonexistent cells when trying to
 * switch two cells.
 */
public class WatorEmpty extends Cell{
	private Color WATOREMPTY_COLOR=Color.BLUE;
	
	public WatorEmpty(){
		setColor(WATOREMPTY_COLOR);
		setState("WatorEmpty");
	}
	
	@Override
	public void updateState(String newState) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Cell getEmptyCell() {
		// TODO Auto-generated method stub
		return new WatorEmpty();
	}

}
