package model;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.io.File;
import java.io.Serializable;

/**
 * This class manages a repository of words for the Wordle game.
 * It includes functionality to check if a word is valid and to 
 * retrieve new words. It supports a standard mode and an Unwordle 
 * mode, which has different word generation logic.
 */
public class WordRepository implements Serializable {
	
	private ArrayList<String> dictionary;	// Contains all words
	private ArrayList<String> available;	// Contains all the available words
	private ArrayList<String> usedWords;	// Contains all the words already used (for hidden word)
	private boolean isUnwordle;
	private int length;
	
	/**
     * Constructs a WordRepository for words of a specified length.
     *
     * @param len The length of words to be included in the repository.
     * @param isUnwordle Flag to indicate if the repository is for Unwordle mode.
     */
	public WordRepository(int len, boolean isUnwordle){
		// length of the word
		length = len;
		this.isUnwordle = isUnwordle;
		dictionary = new ArrayList<String>();
		available = new ArrayList<String>();
		usedWords = new ArrayList<String>();
		if (!isUnwordle) {
			if (len == 5) {
				try {
					File file = new File("fiveLetterWords.txt");
					Scanner input = new Scanner(file);
					while (input.hasNextLine()) {
						String word = input.nextLine().strip();
						dictionary.add(word);
						available.add(word);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
     * Checks if a specified word is in the dictionary.
     *
     * @param word The word to check.
     * @return true if the word is in the dictionary, false otherwise.
     */
	public boolean isWord(String word) {
		if (isUnwordle) {
			return true;
		}
		if (word.length() == length) {
			return dictionary.contains(word);
		}
		return false;
	}
	
	/**
     * Retrieves a new word from the repository to be used as the hidden word.
     * In Unwordle mode, generates a random word.
     * Removes the chosen word from the available list and adds it to used words.
     *
     * @return A new word for the game.
     */
	public String getWord() {
		Random rand = new Random();
		String word = "";
		if (isUnwordle) {
			for (int i = 0; i < length; i++) {
				char c = (char)(97 + rand.nextInt(27));
				word += c;
			}
			
		} else {
			int num = rand.nextInt(available.size());
			word = available.get(num);
			available.remove(num);
		}
		usedWords.add(word);
		return word;
		
	}
}
	
