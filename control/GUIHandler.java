package boggle.control;

import boggle.view.Board;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class GUIHandler {
	
	private Board board;
	
	public GUIHandler(Stage primaryStage) {
		primaryStage.setTitle("Boggle fun!");

		//Close program on GUI close
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
		    @Override
		    public void handle(WindowEvent event) {
		        System.exit(0);
		    }
		});
		
		//Load board
		board = new Board(4, 4, null);
        
        BorderPane mainPanel = new BorderPane();
        mainPanel.setCenter(board.getDisplay());
        
        //Load window
        Scene scene = new Scene(mainPanel, board.getElementSize() * board.getColumns() + 100, board.getElementSize() * board.getRows() + 100);
        primaryStage.setScene(scene);
        primaryStage.show();
	}
	
	public Board getBoard() {
		return board;
	}
}
