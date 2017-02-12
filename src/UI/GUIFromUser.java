package UI;

import java.lang.reflect.Method;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GUIFromUser extends UserInterface{
	public static final String METHOD_STUB = "Controls";
	private HBox myControls;

	public GUIFromUser(Stage mainStage, String resources, Scene previousScene, String simType) {
		super(mainStage, resources, previousScene);
		myControls = new HBox();
		setUserControls(simType);
		setBottomPane(myControls);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void extendToolBar(Pane toolBar) {
		// TODO Auto-generated method stub
		
	}
	
	public void setUserControls(String simType) {
		try{
			Method constructControls = this.getClass().getMethod(simType+METHOD_STUB);
			constructControls.setAccessible(true);
			constructControls.invoke(this, (Object[]) null);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void getNewSimulation() {
		// TODO Auto-generated method stub
		
	}
	
	public void fireControls() {
		Button trivial = new Button("I do nothing");
		trivial.setOnAction(e -> {
			System.out.println("Holy cow, I work");
		});
		myControls.getChildren().add(trivial);
	}
	
	public void gameoflifeControls() {
		Button trivial = new Button("I do something");
		trivial.setOnAction(e -> {
			System.out.println("wtf does this do");
		});
		myControls.getChildren().add(trivial);
	}
	
	public void predatorpreyControls() {
		Button trivial = new Button("predator prey");
		trivial.setOnAction(e -> {
			System.out.println("test #3");
		});
		myControls.getChildren().add(trivial);
	}
	
	public void segregationControls() {
		Button trivial = new Button("Segregation");
		trivial.setOnAction(e -> {
			System.out.println("Final test");
		});
		myControls.getChildren().add(trivial);
	}

}
