package Cells;

import javafx.scene.paint.Color;

/**
 * 
 * @author Daniel
 *
 */
public class SegregationCell extends Cell{
	private boolean alwaysBeSatisfied=false; //You will never be satisfied, satisfied, satisfieeeeeed....
	private final String EMPTY="empty";
	private final String TYPE1="type1";
	private final String TYPE2="type2";//we can have more types if we want
	private final Color EMPTY_COLOR=Color.WHITE;
	private final Color TYPE1_COLOR=Color.RED;
	private final Color TYPE2_COLOR=Color.BLUE;
	
	public SegregationCell(String state){
		updateState(state);
	}
	
	public SegregationCell(String state, boolean alwaysBeSatisfied){
		this.alwaysBeSatisfied=alwaysBeSatisfied;
		updateState(state);
	}
	
	@Override
	public void updateState(String newState) {
		if (newState.equals(TYPE1)) {
			setState(TYPE1);
			setColor(TYPE1_COLOR);
		} else if (newState.equals(TYPE2)) {
			setState(TYPE2);
			setColor(TYPE2_COLOR);
		} else if (newState.equals(EMPTY)) {
			setState(EMPTY);
			setColor(EMPTY_COLOR);
		} /*else{
			System.out.print("State Not Found!");//throw exception ideally
		}*/
	}
	
	public boolean alwaysSatisfied(){
		return alwaysBeSatisfied;
	}

	@Override
	public Cell getEmptyCell() {
		// TODO Auto-generated method stub
		return new SegregationCell("empty");
	}

}
