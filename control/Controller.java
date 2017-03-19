package boggle.control;

import java.util.ArrayList;

import boggle.FileUtils;
import boggle.WordUtils;
import boggle.model.Vector2;
import boggle.model.trie.Trie;
import javafx.stage.Stage;

public class Controller {

	public Controller(Stage primaryStage) {
		Trie trie = new Trie(FileUtils.readFile("dict.txt"));
		
		//Start GUI
		GUIHandler guiHandler = new GUIHandler(primaryStage);
		
		//Find words
		ArrayList<String> foundWords = new ArrayList<String>();
		for(int x = 0; x < guiHandler.getBoard().getRows(); x++) {
			for(int y = 0; y < guiHandler.getBoard().getColumns(); y++) {
				foundWords = new WordUtils().findWords(trie, guiHandler.getBoard(), foundWords, new ArrayList<Vector2>(), new Vector2(x, y));
			}
		}
	  
		//Print words
		for(String word : foundWords) {
			System.out.println(word);
		}
	}
	
}
