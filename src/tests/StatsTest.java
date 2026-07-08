package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import model.Statistics;
import model.WordleGame;

class StatsTest {
	
	Statistics stats = new Statistics();

	@Test
	void testConstruction() {
		assertEquals(0, stats.getCurrentWinStreak());
		assertEquals(0, stats.getGamesPlayed());
		assertEquals(0, stats.getGamesWon());
		assertEquals(0, stats.getLongestWinStreak());
		assertEquals(0, stats.getWinRate());
		int[] test = {0, 0, 0, 0, 0, 0};
		assertArrayEquals(test,stats.getWinAtGuess());
	}
	
	@Test
	void testUpdate() {
		stats.updatePostGameStats(true, 1);
		stats.updatePostGameStats(true, 6);
		stats.updatePostGameStats(false, 6);
		stats.updatePostGameStats(true, 3);
		
		assertEquals(1, stats.getCurrentWinStreak());
		assertEquals(4, stats.getGamesPlayed());
		assertEquals(3, stats.getGamesWon());
		assertEquals(2, stats.getLongestWinStreak());
		assertEquals(75.0, stats.getWinRate());
		int[] test = {1, 0, 1, 0, 0, 1};
		assertArrayEquals(test,stats.getWinAtGuess());		
		
		//test with wordle game
		WordleGame game = new WordleGame();
		game.guess("hello");
		game.guess("hello");
		game.guess("hello");
		game.guess("hello");
		game.guess("hello");
		game.guess("hello");
		stats.updatePostGameStats(game.checkWin(), game.getTurn());
		assertFalse(4 == stats.getGamesPlayed());
		assertTrue(75.0 != stats.getWinRate());
		System.out.println(Arrays.toString(stats.getWinAtGuess()));
	}
	

}
