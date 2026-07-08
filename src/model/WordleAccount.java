package model;

import java.io.Serializable;

/**
 * Represents an account for a player in the Wordle game.
 * This class stores the player's username, password, 
 * game instance, achievements, and statistics.
 */
public class WordleAccount implements Serializable {
	
	private final String username;
    private final String password;
    private Achievements achievements;
    private Statistics statistics;
    WordleGame game;
    
    /**
     * Constructor for WordleAccount.
     * Initializes the account with the specified 
     * username and password, and creates new instances 
     * of WordleGame, Achievements, and Statistics.
     *
     * @param username The username for the account.
     * @param password The password for the account.
     */
    public WordleAccount (String username, String password) {
    	this.username = username;
		this.password = password;
		this.game = new WordleGame();
        this.achievements = new Achievements();
        this.statistics = new Statistics();
    }
    
    /**
     * Gets the username of the account.
     * 
     * @return The username associated with this account.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets the password of the account.
     * 
     * @return The password associated with this account.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Gets the statistics associated with the account.
     * 
     * @return The Statistics object containing 
     * the player's game statistics.
     */
    public Statistics getStatistics() {
    	return statistics;
    }
    
    /**
     * Gets the current Wordle game instance
     *  associated with the account.
     * 
     * @return The current instance of WordleGame.
     */
    public WordleGame getGame() {
    	return game;
    }
    
    /**
     * Gets the achievements associated with the account.
     * 
     * @return The Achievements object 
     * containing the player's achievements.
     */
    public Achievements getAchievements() {
        return achievements;
    }
}
