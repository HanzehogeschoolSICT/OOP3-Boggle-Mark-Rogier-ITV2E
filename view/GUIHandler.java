package boggle.view;

import java.util.ArrayList;

import boggle.model.AlphabetUtils;
import boggle.model.FileUtils;
import boggle.model.Vector2;
import boggle.model.Word;
import boggle.model.WordSearch;
import boggle.model.trie.Trie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class GUIHandler {
		
	BorderPane mainPanel;
	Board board;
		
	int screenSize = 800;
	int listWidth = 250;
	int inputSize = 30;
	
	public GUIHandler(Stage primaryStage) {		
		primaryStage.setTitle("Boggle fun!");

		//Close program on GUI close
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
		    @Override
		    public void handle(WindowEvent event) {
		        System.exit(0);
		    }
		});
		
		loadGUI(primaryStage);
	}
	
	public void loadGUI(Stage primaryStage) {		
		mainPanel = new BorderPane();
		
        TextField sizeField = new TextField("10");
        sizeField.setMaxWidth(35);
        
        Button make = new Button();
        make.setText("Make board");
        make.setOnAction(new EventHandler<ActionEvent>() {
        	public void handle(ActionEvent event) {
        		int size = 4;
        		try {
        			int input = Integer.parseInt(sizeField.getText());
        			if(input > 0) {
        				size = input;
        			} else {
            			sizeField.setText(size + "");
        			}
        		} catch(Exception e) {
        			sizeField.setText(size + "");
        		}
        		String str = new AlphabetUtils().makeRandomString(size*size);

        		loadBoard(size, str);
        		
        		//Search words
        		WordSearch wordSearch = new WordSearch(new Trie(FileUtils.readFile("dict.txt")));
        		ArrayList<Word> foundWords = null;
        		
        		for(int x = 0; x < size; x++) {
        			for(int y = 0; y < size; y++) {	
        				foundWords = wordSearch.findWords(board, new Vector2(x, y));
        			}
        		}
        		
        		setWordList(board, foundWords);
			}
		});
            
        HBox hBox = new HBox();
		hBox.setSpacing(10);
        hBox.getChildren().addAll(make, sizeField);
        
        mainPanel.setTop(hBox);
        
        //Load window
        Scene scene = new Scene(mainPanel, screenSize + listWidth, inputSize + screenSize);
        primaryStage.setScene(scene);
        primaryStage.show();
	}
	
	public void loadBoard(int size, String boardStr) {		
		int elementSize = screenSize / size;
		
		Board board = new Board(size, size, boardStr, elementSize);
		
		mainPanel.getChildren().remove(mainPanel.getLeft());
        mainPanel.setLeft(board.getDisplay());
        
		this.board = board;
	}
	
	public void setWordList(Board board, ArrayList<Word> words) {
		ObservableList<Word> wordsList = FXCollections.observableArrayList(words);
		ListView<Word> listView = new ListView<Word>(wordsList);
		
		listView.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				Word word = listView.getSelectionModel().getSelectedItem(); 
				
				if(word != null && word.getPath() != null) {
					board.resetColors();
							
					for(Vector2 cord : word.getPath()) {
						Canvas canvas = board.getCanvas(cord);
						
						board.setColor(canvas, Color.DARKGREEN);
						board.setCharacter(canvas, board.toString().charAt(cord.asIndex(board.getSize())));
					}
				}
			}
		});
		
		mainPanel.getChildren().remove(mainPanel.getCenter());
		mainPanel.setCenter(listView);
	}
}
