package tests;

import model.WordleGame;

/**
 * This class is used for testing the WordleGame class.
 * It contains a main method that runs several tests on the WordleGame functionalities
 * such as guessing words, checking letters, and determining win conditions.
 */
public class WordleGameTest {
	
	/**
     * Main method for testing the WordleGame class.
     * 
     * @param args Command line arguments (not used in this test).
     */
	public static void main(String args[]) {
		WordleGame game = new WordleGame();
		game.guess("queue");
		printGrid(game);
		game.guess("axple");
		printGrid(game);
		System.out.println(game.checkWin());
		System.out.print(game.checkLetter(1, 0));
		System.out.print(game.checkLetter(1, 1));
		System.out.print(game.checkLetter(1, 2));
		System.out.print(game.checkLetter(1, 3));
		System.out.println(game.checkLetter(1, 4));
		game.guess("pearl");
		System.out.print(game.checkLetter(2, 0));
		System.out.print(game.checkLetter(2, 1));
		System.out.print(game.checkLetter(2, 2));
		System.out.print(game.checkLetter(2, 3));
		System.out.println(game.checkLetter(2, 4));
		
		printGrid(game);
		System.out.println(game.checkWin());
		
	}
	
	/**
     * Prints the current state of the game grid to the console.
     * 
     * @param g The WordleGame instance whose grid is to be printed.
     */
	public static void printGrid(WordleGame g) {
		for (int i = 0; i < g.NUM_ATTEMPTS; i++) {
			for (int j = 0; j < g.WORD_LENGTH;j++) {
				System.out.print(g.getLetter(i, j));
			}
			System.out.println();
		}
		System.out.println();
	}
	
	
}
