package boggle.model;

import java.util.ArrayList;
import boggle.model.trie.Branch;
import boggle.model.trie.Trie;
import boggle.model.trie.WordBranch;
import boggle.view.Board;

public class WordSearch {

	private Trie trie;
	private ArrayList<Word> foundWords;
	private ArrayList<Vector2> usedCords;
	
	public WordSearch(Trie trie) {
		this.trie = trie;
		this.foundWords = new ArrayList<Word>();
		this.usedCords = new ArrayList<Vector2>();
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Word> findWords(Board board, Vector2 current) {
    	for(int x = -1; x <= 1; x++) {
    		for(int y = -1; y <= 1; y++) {
    			Vector2 newVec = new Vector2(current.getX() + x, current.getY() + y);
    			    			
    			if(!(x == 0 && y == 0) && newVec.inBounds(0, board.getSize(), 0, board.getSize()) && !usedCords.contains(newVec)) {    				
    				ArrayList<Vector2> oldUsedCords = (ArrayList<Vector2>) usedCords.clone();
    				ArrayList<Vector2> newUsedCords = (ArrayList<Vector2>) usedCords.clone();
    				newUsedCords.add(newVec);
    				
    				String currentWord = getWordFromCords(board, newUsedCords);
    				
    				Branch currentBranch = trie.getBranch(currentWord);
    				if(currentBranch != null) {
    					if(currentBranch instanceof WordBranch) {    
    						Word word = new Word(currentWord, newUsedCords);
    						
    						if(!foundWord(currentWord)) {
    							foundWords.add(word);
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
	
	public boolean foundWord(String word) {
		for(int x = 0; x < foundWords.size(); x++) {
			if(foundWords.get(x).getWord().equals(word)) {
				return true;
			}
		}
		return false;
	}
	
	public String getWordFromCords(Board board, ArrayList<Vector2> cords) {
    	String word = "";
    	
    	for(Vector2 vec : cords) {    		
    		word += board.toString().charAt(vec.getX() * board.getSize() + vec.getY());
    	}
    	
    	return word;
    }
}
