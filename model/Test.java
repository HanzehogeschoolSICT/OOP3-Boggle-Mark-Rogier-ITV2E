package model;

import java.util.ArrayList;

public class Test {

	public Test(ArrayList<Word> foundWords) {
		ArrayList<String> dict = FileUtils.readFile("dict.txt");
		
		for(Word foundWord: foundWords) {
			assert dict.contains(foundWord);
		}
	}
	
}
