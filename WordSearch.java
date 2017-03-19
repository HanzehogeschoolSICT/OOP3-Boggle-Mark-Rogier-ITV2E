package boggle;

import java.util.ArrayList;

import boggle.model.Vector2;
import boggle.model.trie.Branch;
import boggle.model.trie.Trie;
import boggle.model.trie.WordBranch;
import boggle.view.Board;

public class WordSearch {

	private Trie trie;
	private ArrayList<String> foundWords;
	private ArrayList<Vector2> usedCords;
	
	public WordSearch(Trie trie) {
		this.trie = trie;
		this.foundWords = new ArrayList<String>();
		this.usedCords = new ArrayList<Vector2>();
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<String> findWords(Board board, Vector2 current) {		
    	for(int x = -1; x <= 1; x++) {
    		for(int y = -1; y <= 1; y++) {
    			Vector2 newVec = new Vector2(current.getX() + x, current.getY() + y);
    			
    			if(!(x == 0 && y == 0) && newVec.inBounds(0, board.getRows(), 0, board.getColumns()) && !usedCords.contains(newVec)) {    				
    				ArrayList<Vector2> oldUsedCords = (ArrayList<Vector2>) usedCords.clone();
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
    					
    					usedCords = newUsedCords;
    					findWords(board, newVec);
    					usedCords = oldUsedCords;
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
