package boggle.model.trie;

import java.util.ArrayList;

public class Trie {

	private Branch master;
	
	public Trie() {
		master = new Branch("");
	}
	
	public Trie(ArrayList<String> words) {
		this();
		
		for(String word : words) {
			char[] chars = word.toCharArray();
			
			Branch currentBranch = getMaster();
			for(int x = 0; x < chars.length-1; x++) {
				char currentChar = chars[x];
				
				currentBranch.addBranch(new Branch(currentChar + ""));
				currentBranch = currentBranch.getBranch(currentChar + "");
			}
			currentBranch.addBranch(new WordBranch(chars[chars.length-1] + ""));
		}
	}
	
	public Branch getMaster() {
		return master;
	}
	
	public Branch getBranch(String word) {
		char[] chars = word.toCharArray();
		
		String currentWord = "";
		Branch currentBranch = getMaster();
		for(char nextChar : chars) {
			Branch nextBranch = currentBranch.getBranch(nextChar + "");
			
			if(nextBranch != null) {
				currentBranch = nextBranch;
				currentWord += nextChar;
			} else {
				break;
			}
		}

		boolean isBranch = word.equals(currentWord);
		
		return isBranch ? currentBranch : null;
	}
	
	public boolean hasWord(String word) {
		char[] chars = word.toCharArray();
		
		Branch currentBranch = getMaster();
		for(char nextChar : chars) {
			Branch nextBranch = currentBranch.getBranch(nextChar + "");
			
			if(nextBranch != null) {
				currentBranch = nextBranch;
			} else {
				break;
			}
		}

		boolean isWord = currentBranch instanceof WordBranch;
		
		return isWord;
	}
}
