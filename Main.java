package boggle;

import java.util.ArrayList;

import boggle.model.BoardState;
import boggle.model.Vector2;
import boggle.model.WordSearch;
import boggle.model.trie.Trie;
import boggle.view.GUIHandler;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
		
	public static void main(String[] args) {
		launch(args);
	}
	 
	@Override
	public void start(Stage primaryStage) {
		//Initialize
		int size = 4;
		String str = new AlphabetUtils().makeRandomString(size*size);
		BoardState boardState = new BoardState(size, str);
		
		WordSearch wordSearch = new WordSearch(new Trie(FileUtils.readFile("dict.txt")));
		ArrayList<String> foundWords = new ArrayList<String>();		

		//Start GUI
		GUIHandler guiHandler = new GUIHandler(primaryStage);
		guiHandler.loadBoard(size, str);

		//Find words
		for(int x = 0; x < boardState.getSize(); x++) {
			for(int y = 0; y < boardState.getSize(); y++) {				
				foundWords = wordSearch.findWords(boardState, new Vector2(x, y));
			}
		}
	  
		//Print words
		for(String word : foundWords) {
			System.out.println(word);
		}
	}
}
