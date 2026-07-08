package model;

import java.io.Serializable;
import java.util.ArrayList;

import view_controller.LoginPane;

/**
 * The WordleGame class models the logic of a Wordle game.
 * It handles game states, player's guesses, and tracks the current game progress.
 */
public class WordleGame implements Serializable {

	char[][] wordleGame;
	String word;
	int curAttempt = 0;
	public final int WORD_LENGTH = 5;
	public final int NUM_ATTEMPTS = 6;
	WordRepository wordRepository;
	public boolean isUnwordle;
	public int gameState;
	private WordleAccount currentPlayerAccount;

	/**
	 * Default constructor for WordleGame.
	 * Initializes a standard Wordle game with default settings.
	 */
	public WordleGame() {
		isUnwordle = false;
		gameState = 0;
		wordRepository = new WordRepository(WORD_LENGTH, false);
		word = wordRepository.getWord();
		initArray(word);
		currentPlayerAccount = LoginPane.getActiveAccount();
	}
	
	/**
	 * Constructor for WordleGame with a specific game state.
	 * @param state An integer representing the state of the game (0 for original, 1 for Unwordle, etc.).
	 */
	public WordleGame(int state) {
		gameState = state;
		isUnwordle = gameState == 1;
		wordRepository = new WordRepository(WORD_LENGTH, isUnwordle);
		word = wordRepository.getWord();
		initArray(word);
	}

	/**
	 * Checks the letter at a specific row and column against the target word.
	 * Returns an integer indicating the status of the letter (0 = incorrect, 1 = wrong spot, 2 = correct).
	 * @param row The row index of the letter.
	 * @param col The column index of the letter.
	 * @return An integer indicating the status of the guessed letter.
	 */
	public int checkLetter(int row, int col) {
		char letter = getLetter(row, col);
		if (word.contains(Character.toString(letter))) {
			if (word.charAt(col) == letter) {
				return 2;
			}
			return 1;
		}
		return 0;
	}

	/**
	 * Retrieves the letter from the wordle game grid at specified row and column.
	 * @param row The row index from which to retrieve the letter.
	 * @param col The column index from which to retrieve the letter.
	 * @return The character at the specified row and column.
	 */
	public char getLetter(int row, int col) {
		return wordleGame[row][col];
	}

	/**
	 * Gets the current turn number in the game.
	 * @return The current attempt number (turn).
	 */
	public int getTurn() {
		return this.curAttempt;
	}

	/**
	 * Initializes the game array with placeholder characters.
	 * @param word The word to be used in the game setup.
	 */
	private void initArray(String word) {
		wordleGame = new char[NUM_ATTEMPTS][WORD_LENGTH];
		for (int i = 0; i < NUM_ATTEMPTS; i++) {
			for (int j = 0; j < WORD_LENGTH; j++) {
				wordleGame[i][j] = '.'; // place holder for empty space
			}
		}
	}

	/**
	 * Adds a guess to the game board and checks if it leads to a win.
	 * Updates player's statistics based on the result of the guess.
	 * @param word The guessed word.
	 * @return True if the word is valid and added, false otherwise.
	 */
	public boolean guess(String word) {
		if (wordRepository.isWord(word)) {
			int col = 0;
			for (char c : word.toCharArray()) {
				wordleGame[curAttempt][col] = c;
				col++;
			}
			
			curAttempt++;
			boolean isWin = checkWin();
			
			// Update statistics based on the game outcome
			if (isWin) {
				if (currentPlayerAccount != null) {
					currentPlayerAccount.getStatistics().updatePostGameStats(true, curAttempt);
					currentPlayerAccount.getAchievements().setAchievments(currentPlayerAccount.getStatistics());
				}
	            return true;
			}
			if (curAttempt == NUM_ATTEMPTS && !isWin) {
				if (currentPlayerAccount != null) {
					currentPlayerAccount.getStatistics().updatePostGameStats(false, curAttempt);
					currentPlayerAccount.getAchievements().setAchievments(currentPlayerAccount.getStatistics());
				}
	        }
			return true;
		}
		return false;
	}

	/**
	 * Checks if the current guess leads to a win.
	 * @return True if the current guess is a winning guess, false otherwise.
	 */
	public boolean checkWin() {
		for (int col = 0; col < WORD_LENGTH; col++) {
			if (checkLetter(curAttempt - 1, col) <= 1) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Resets the game to its initial state with a new word.
	 */
	public void resetGame() {
		word = wordRepository.getWord();
		curAttempt = 0;
		initArray(word);
	}
	
	/**
	 * Checks if the game is in Unwordle mode.
	 * @return True if the game is in Unwordle mode, false otherwise.
	 */
	public boolean isUnwordle() {
		return isUnwordle;
	}
	
	/**
	 * Gets the current state of the game.
	 * The states are: 0 for original Wordle, 1 for Unwordle, 2 for Wordle with a timer (Speed Wordle).
	 * @return An integer representing the current game state.
	 */
	public int getState() {
		return gameState;
	}
	
	/**
	 * get the current word
	 * @return returns a String, word.
	 */
	public String getWord() {
		return word;
	}
	
}
