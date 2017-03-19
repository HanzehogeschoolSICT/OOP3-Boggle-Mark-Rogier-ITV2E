package boggle;

import java.util.ArrayList;

import boggle.control.GUIHandler;
import boggle.model.Vector2;
import boggle.model.trie.Trie;
import boggle.view.Board;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
		
	public static void main(String[] args) {
		launch(args);
	}
	 
	@Override
	public void start(Stage primaryStage) {
		Trie trie = new Trie(FileUtils.readFile("dict.txt"));
		
		//Start GUI
		GUIHandler guiHandler = new GUIHandler(primaryStage);
		
		//Find words
		Board board = guiHandler.getBoard();
		ArrayList<String> foundWords = new ArrayList<String>();
		WordSearch wordSearch = new WordSearch(trie);
		
		for(int x = 0; x < board.getRows(); x++) {
			for(int y = 0; y < board.getColumns(); y++) {
				foundWords = wordSearch.findWords(board, new Vector2(x, y));
			}
		}
	  
		//Print words
		for(String word : foundWords) {
			System.out.println(word);
		}
	}
}
