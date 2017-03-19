package boggle;

import java.util.ArrayList;

import boggle.model.Vector2;
import boggle.model.trie.Branch;
import boggle.model.trie.Trie;
import boggle.model.trie.WordBranch;
import boggle.view.Board;

public class WordUtils {
	
	@SuppressWarnings("unchecked")
	public ArrayList<String> findWords(Trie trie, Board board, ArrayList<String> foundWords, ArrayList<Vector2> usedCords, Vector2 current) {    	
    	for(int x = -1; x <= 1; x++) {
    		for(int y = -1; y <= 1; y++) {
    			Vector2 newVec = new Vector2(current.getX() + x, current.getY() + y);
    			
    			if(!(x == 0 && y == 0) && newVec.inBounds(0, board.getRows(), 0, board.getColumns()) && !usedCords.contains(newVec)) {    				
    				ArrayList<Vector2> newUsedCords = (ArrayList<Vector2>) usedCords.clone();
    				newUsedCords.add(newVec);
    				
    				String currentWord = getWordFromCords(board, newUsedCords);
    				
    				Branch currentBranch = trie.getBranch(currentWord);
    				if(currentBranch != null) {
    					if(currentBranch instanceof WordBranch) {
    						if(!foundWords.contains(currentWord)) {
    							foundWords.add(currentWord);
    						}
    					}
    					
    					findWords(trie, board, foundWords, newUsedCords, newVec);
    				}
    			}
    		}
    	}
    	
    	return foundWords;
    }
    
    
    public String getWordFromCords(Board display, ArrayList<Vector2> cords) {
    	String word = "";
    	
    	for(Vector2 vec : cords) {
    		word += display.getCharacterField(vec.getX(), vec.getY()).getCharacter();
    	}
    	
    	return word;
    }
	
}
