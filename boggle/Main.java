package boggle;

import java.util.ArrayList;

import boggle.control.GUIHandler;
import boggle.model.Vector2;
import boggle.model.trie.Trie;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	
	private Trie trie;
	private GUIHandler guiHandler;
	
	private WordUtils wordUtils;
	private AlphabetUtils alphabetUtils;
	
	public static void main(String[] args) {
		launch(args);
	}
	 
	@Override
	public void start(Stage primaryStage) {
		trie = new Trie(FileUtils.readFile("dict.txt"));
		
		//Build utils
		wordUtils = new WordUtils(this);
		alphabetUtils = new AlphabetUtils();
		
		//Start GUI
		guiHandler = new GUIHandler(this, primaryStage);
		
		//Find words
		ArrayList<String> foundWords = new ArrayList<String>();
		for(int x = 0; x < guiHandler.getBoard().getRows(); x++) {
			for(int y = 0; y < guiHandler.getBoard().getColumns(); y++) {
				foundWords = wordUtils.findWords(foundWords, new ArrayList<Vector2>(), new Vector2(x, y));
			}
		}
	  
		//Print words
		for(String word : foundWords) {
			System.out.println(word);
		}
	}
	
	public Trie getTrie() {
		return trie;
	}
	
	public GUIHandler getGUIHandler() {
		return guiHandler;
	}
	
	public AlphabetUtils getAlphabetUtils() {
		return alphabetUtils;
	}
}
