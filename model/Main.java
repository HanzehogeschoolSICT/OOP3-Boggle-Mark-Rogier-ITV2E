package model;

import javafx.application.Application;
import javafx.stage.Stage;
import view.GUIHandler;

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
