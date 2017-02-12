package Utils;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class CellsMapGenerator {
	
	private final static String PROBABILITY = "probability";
	private final static String NUMBER = "number";
	
	private final static String FIRE = "probability";
	private final static String GAME_OF_LIFE = "number";
	private final static String SEGREGATION = "probability";
	private final static String PREDATOR_PREY = "number";
	
	private static final String EMPTY = "empty";
	private static final String TREE = "tree";
	private static final String BURNING = "burning";
	
	private static final String ALIVE = "alive";
	private static final String DEAD = "dead";
	private static final String PROBABILITY_DEAD = "probDead";
//	private static final String NUMBER_DEAD = "numDead";
	
	private static final String TYPE_1 = "type1";
	private static final String TYPE_2 = "type2";
	private static final String PROBABILITY_TYPE_1 = "probType1";
	private static final String PROBABILITY_TYPE_2 = "probType2";
//	private static final String NUMBER_TYPE_1 = "probDead";
//	private static final String NUMBER_TYPE_2 = "numDead";
	
	private static final String PREDATOR = "predator";
	private static final String PREY = "prey";
	private static final String PROBABILITY_PREDATOR = "probPredator";
	private static final String PROBABILITY_PREY = "numPredator";
	
	private int gridSize;
	
	public CellsMapGenerator() {
	}
	
	public Map<int[],String> generateMap(String method,String simType,int gridSize,Map<String,String> userParameters) {
		this.gridSize = gridSize;
		if (simType.equals(FIRE)) {
			return generateFireMap();
		} else if (simType.equals(GAME_OF_LIFE)) {
			return generateGameOfLifeMap(method,userParameters);
		} else if (simType.equals(PREDATOR_PREY)) {
			return generatePredatorPreyMap(method,userParameters);
		} else if (simType.equals(SEGREGATION)) {
			return generateSegregationMap(method,userParameters);
		} else {
			return null;
		}
	}
	
	private Map<int[], String> generateFireMap() {
		Map<int[],String> cells = new HashMap<int[],String>();
		for (int i=0; i<gridSize;i++) {
			for (int j = 0; j<gridSize;j++) {
				int[] coordinates = {i,j};
				if (isBorderCell(i,j)) {
					cells.put(coordinates,EMPTY);
				} else if (isMiddleCell(i,j)){
					cells.put(coordinates, BURNING);
				} else {
					cells.put(coordinates, TREE);
				}
			}
		}
		return cells;
	}
	
	private Map<int[], String> generateGameOfLifeMap(String method, Map<String,String> userParameters) {	
		Map<int[],String> cells = new HashMap<int[],String>();
		if (method.equals(PROBABILITY)) {
			double probDead = Double.parseDouble(PROBABILITY_DEAD);
			for (int i=0; i<gridSize;i++) {
				for (int j = 0; j<gridSize;j++) {
					int[] coordinates = {i,j};
					double rand = Math.random();
					if (rand<probDead) {
						cells.put(coordinates,DEAD);
					} else {
						cells.put(coordinates, ALIVE);
					}
					
				}
			}
		}
//		} else if (method.equals(NUMBER)) {
//			//throw error when userNumDead>cellsLeft (total cells in grid available)
//			int userNumDead = Integer.parseInt(NUMBER_DEAD);
//			int numDead = 0;
//			int cellsLeft = (int) Math.pow(gridSize,2);
//			for (int i=0; i<gridSize;i++) {
//				for (int j = 0; j<gridSize;j++) {
//					int[] coordinates = {i,j};
//					if (cellsLeft==userNumDead-numDead) {
//						cells.put(coordinates,DEAD);
//						numDead++;
//					}
//					else {
//						double rand = Math.random();
//						if (rand<0.5) {
//							cells.put(coordinates,DEAD);
//							numDead++;
//						} else {
//							cells.put(coordinates, ALIVE);
//						}
//					}
//					
//				}
//			}
//		}
		return cells;
	}
	
	private Map<int[], String> generateSegregationMap(String method, Map<String,String> userParameters) {
		Map<int[],String> cells = new HashMap<int[],String>();
		if (method.equals(PROBABILITY)) {
			//throw error probType1+probType2>1
			double probType1 = Double.parseDouble(PROBABILITY_TYPE_1);
			double probType2 = Double.parseDouble(PROBABILITY_TYPE_2);
			for (int i=0; i<gridSize;i++) {
				for (int j = 0; j<gridSize;j++) {
					int[] coordinates = {i,j};
					double rand = Math.random();
					if (rand<probType1) {
						cells.put(coordinates,TYPE_1);
					} else if (rand<probType1+probType2){
						cells.put(coordinates, TYPE_2);
					} else {
						cells.put(coordinates, EMPTY);
					}
				}
			}
		}
//		} else if (method.equals(NUMBER)) {
//			//throw error when userNumType1+userNumType2>cellsLeft (total cells in grid available)
//			int userNumType1 = Integer.parseInt(NUMBER_TYPE_1);
//			int userNumType2 = Integer.parseInt(NUMBER_TYPE_2);
//			int num = 0;
//			int cellsLeft = (int) Math.pow(gridSize,2);
//			for (int i=0; i<gridSize;i++) {
//				for (int j = 0; j<gridSize;j++) {
//					int[] coordinates = {i,j};
//					if (cellsLeft==userNumDead-numDead) {
//						cells.put(coordinates,DEAD);
//						numDead++;
//					}
//					else {
//						double rand = Math.random();
//						if (rand<0.5) {
//							cells.put(coordinates,DEAD);
//							numDead++;
//						} else {
//							cells.put(coordinates, ALIVE);
//						}
//					}
//					
//				}
//			}
		//		}
		return cells;
	}
	
	private Map<int[], String> generatePredatorPreyMap(String method, Map<String,String> userParameters) {
		Map<int[],String> cells = new HashMap<int[],String>();
		if (method.equals(PROBABILITY)) {
			if (method.equals(PROBABILITY)) {
				//throw error probType1`+probType2>1
				double probPrey = Double.parseDouble(PROBABILITY_PREY);
				double probPredator = Double.parseDouble(PROBABILITY_PREDATOR);
				for (int i=0; i<gridSize;i++) {
					for (int j = 0; j<gridSize;j++) {
						int[] coordinates = {i,j};
						double rand = Math.random();
						if (rand<probPrey) {
							cells.put(coordinates,PREY);
						} else if (rand<probPrey+probPredator){
							cells.put(coordinates, PREDATOR);
						} else {
							cells.put(coordinates, EMPTY);
						}
					}
				}
			}
		} 
		return cells;
	}
	
	private boolean isBorderCell(int row, int col) {
		int gridEdge = gridSize -1;
		if (row==0 || col==gridEdge || row==0 || col==gridEdge) return true;
		else return false;
	}
	
	private boolean isMiddleCell(int row, int col) {
		int mid = gridSize/2;
		if (col==mid && row==mid) return true;
		else return false;
	}
	

}
