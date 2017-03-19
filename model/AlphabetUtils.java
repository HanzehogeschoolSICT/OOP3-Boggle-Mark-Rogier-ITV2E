package model;

import java.util.Random;

public class AlphabetUtils {
	
	public char getRandomCharacter() {
		char[] alphabet = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 'd', 't', 'u', 'v', 'w', 'x', 'y', 'z' };
		
		return alphabet[new Random().nextInt(alphabet.length)];
	}
}
