package control;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.stage.Stage;
import model.FileUtils;
import model.Vector2;
import model.WordUtils;
import model.trie.Trie;
import view.GUIHandler;

public class Main extends Application {
	
	public static Trie trie;
	
	public static void main(String[] args) {
		trie = new Trie(FileUtils.readFile("dict.txt"));
		
		launch(args);
	}
	 
	@Override
	public void start(Stage primaryStage) {
		//Start GUI
		GUIHandler.startGUI(primaryStage);
	    
		//Find words
		ArrayList<String> foundWords = new ArrayList<String>();
		for(int x = 0; x < GUIHandler.getBoard().getRows(); x++) {
			for(int y = 0; y < GUIHandler.getBoard().getColumns(); y++) {
				foundWords = WordUtils.findWords(foundWords, new ArrayList<Vector2>(), new Vector2(x, y));
			}
		}
	  
		//Print words
		for(String word : foundWords) {
			System.out.println(word);
		}
	}
}
