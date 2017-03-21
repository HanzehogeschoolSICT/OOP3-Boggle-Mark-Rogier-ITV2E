package boggle.model;

import boggle.view.GUIHandler;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
		
	public static void main(String[] args) {
		launch(args);
	}
	 
	@Override
	public void start(Stage primaryStage) {
		new GUIHandler(primaryStage);

		//new Test(foundWords);
	}
}
