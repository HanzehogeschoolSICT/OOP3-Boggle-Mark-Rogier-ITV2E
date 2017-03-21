package boggle.model;

import java.util.ArrayList;

public class Word {

	private String word;
	private ArrayList<Vector2> path;
	
	public Word(String word, ArrayList<Vector2> path) {
		this.word = word;
		this.path = path;
	}
	
	public String getWord() {
		return word;
	}
	
	public ArrayList<Vector2> getPath() {
		return path;
	}
	
	public String toString() {
		return word;
	}
}
