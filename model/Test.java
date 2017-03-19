package boggle.model;

import java.util.ArrayList;

public class Test {

	public Test(ArrayList<String> foundWords) {
		ArrayList<String> dict = FileUtils.readFile("dict.txt");
		
		for(String foundWord: foundWords) {
			assert dict.contains(foundWord);
		}
	}
	
}
