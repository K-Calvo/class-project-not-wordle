package model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * This class represents a collection of achievements for a player in a game. It
 * tracks various achievements and the player's current win streak. Achievements
 * are stored as a map with achievement names as keys.
 */
public class Achievements implements Serializable {

	private Map<String, Boolean> achievements; // tracks the state of different achievements.
	private int currentWinStreak; // stores the current consecutive win streak count.

	/**
	 * Constructor for Achievements. Initializes the map to track different
	 * achievements and sets up initial achievements.
	 */
	public Achievements() {
		achievements = new HashMap<>();
		initializeAchievements();
	}

	/**
	 * Initializes the achievements with default values. Sets all achievements to
	 * false initially.
	 */
	private void initializeAchievements() {
		achievements.put("First Win", false);
		achievements.put("10 Game Win Streak", false);
		achievements.put("100 Game Win Streak", false);
		achievements.put("Perfect Game", false);
		achievements.put("Scraping By", false);
		achievements.put("You Suck!", false);
		achievements.put("Played Wordle", false);
		achievements.put("Played Unwordle", false);
		achievements.put("Played Speed", false);
		achievements.put("10 Speedle", false);
		achievements.put("35 Speedle", false);
		achievements.put("60 Speed racer", false);
	}

	/**
	 * Updates achievements based on the active Users statistics.
	 * 
	 * @param stats The current user's statistics object.
	 */
	public void setAchievments(Statistics stats) {

		if (!achievements.get("First Win") && stats.getGamesWon() >= 1) {
			achievements.put("First Win", true);
		}

		// won on first guess
		if (!achievements.get("Perfect Game") && stats.getWinAtGuess()[0] >= 1) {
			achievements.put("Perfect Game", true);
		}

		// 10 game win streak
		if (!achievements.get("10 Game Win Streak") && stats.getCurrentWinStreak() >= 10) {
			achievements.put("10 Game Win Streak", true);
		}

		// 100 game win streak
		if (!achievements.get("100 Game Win Streak") && stats.getCurrentWinStreak() >= 100) {
			achievements.put("100 Game Win Streak", true);
		}

		// won on last guess
		if (!achievements.get("Scraping By") && stats.getWinAtGuess()[5] >= 1) {
			achievements.put("Scraping By", true);
		}

		// low winRatio
		if (!achievements.get("You Suck!") && stats.getGamesPlayed() >= 20 
				&& stats.getWinRate() <= 25.0) {
			achievements.put("You Suck!", true);
		}

		//max speedle wins of 10
		if(!achievements.get("10 Speedle") && stats.getSpeedleMax() >= 10) {
			achievements.put("10 Speedle", true);
		}
		//max speedle wins of 35
		if(!achievements.get("35 Speedle") && stats.getSpeedleMax() >= 35) {
			achievements.put("35 Speedle", true);
		}
		
		//max speedle wins of 60
		if(!achievements.get("60 Speed racer") && stats.getSpeedleMax() >= 60) {
			achievements.put("60 Speed racer", true);
		}
		
	}

	/**
	 * Records Wordle type and updates achievements based on game played.
	 * 
	 * @param string The game type and key.
	 */
	public void havePlayed(String gameType) {
		if (!achievements.get(gameType)) {
			achievements.put(gameType, true);
		}
	}

	/**
	 * Checks if a specific achievement has been earned.
	 * 
	 * @param achievement The name of the achievement to check.
	 * @return true if the achievement has been earned, false otherwise.
	 */
	public boolean checkAchievement(String achievement) {
		return achievements.getOrDefault(achievement, false);
	}

	/**
	 * Resets all achievements to their default state and resets the win streak.
	 */
	public void resetAchievements() {
		for (String key : achievements.keySet()) {
			achievements.put(key, false);
		}
		currentWinStreak = 0;
	}
}
