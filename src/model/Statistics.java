package model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * This class represents the statistics of a player in a game.
 * It keeps track of various metrics such as games played, won, 
 * lost, guess distribution, win streaks, and calculates the win 
 * rate and average guesses.
 */
public class Statistics implements Serializable{

	private int gamesPlayed;
	private double gamesWon;
    private int gamesLost;
    private HashMap<Integer, Integer> guessDistribution; //tracks the number of guesses per game
    private int[] winAtGuess; //array to hold wins "locations"; 0 is 1st guess
    private int totalGuesses;
	private int averageGuesses;
	private int longestWinStreak;
	private int currentWinStreak;
	private int bestGame;			//fewest guesses in a winning game
	private int worstGame;			//most guesses in a losing game
	private Double winRate;
	private int speedleCount; //for speedle games won in a round
	private int speedleMax; //for most wins in a speedle round
	

	/**
     * Constructor for Statistics.
     * Initializes the statistics and 
     * sets default values.
     */
	public Statistics() {
        guessDistribution = new HashMap<>();
        bestGame = Integer.MAX_VALUE;
        worstGame = Integer.MIN_VALUE;
    	gamesPlayed = 0;
    	gamesWon = 0.0;
    	winRate = 0.0;
    	longestWinStreak = 0;
    	currentWinStreak = 0;
    	winAtGuess = new int[6];
    }
	
	/**
     * Updates post-game statistics based on 
     * the outcome and number of guesses.
     * 
     * @param isWin   Indicates if the game was won.
     * @param guesses The number of guesses taken in the game.
     */
	public void updatePostGameStats(boolean isWin, int guesses) {
		gamesPlayed++;
        totalGuesses += guesses;
        guessDistribution.put(guesses, guessDistribution.getOrDefault(guesses, 0) + 1);
        
        if (isWin) {
        	winAtGuess(guesses);
            gamesWon++;
            currentWinStreak++;
            bestGame = Math.min(bestGame, guesses);
            worstGame = Math.max(worstGame, guesses);
            if (currentWinStreak > longestWinStreak) {
                longestWinStreak = currentWinStreak;
            }
        } else {
            gamesLost++;
            currentWinStreak = 0;
        }
        computeAverageGuesses();
        getWinRate();
	}
	
	/**
     * Computes the average number of guesses per game.
     */
	public void computeAverageGuesses() {
		if (gamesPlayed > 0) {
            averageGuesses = totalGuesses / gamesPlayed;
        }
		
	}
	
	/**
     * Calculates and returns the win rate.
     * 
     * @return The win rate as a double.
     */
	public double getWinRate() {
		winRate = (double) gamesWon / gamesPlayed;
	    if (gamesPlayed > 0) {
	    	winRate = (gamesWon / gamesPlayed) * 100;
			winRate = Math.round(winRate*100)/100.0;
	        return winRate;
	    } else {
	        return 0;
	    }
	}

	/**
     * Gets the total number of games played.
     * 
     * @return The total number of games played.
     */
	public int getGamesPlayed() {
		return gamesPlayed;
	}
	
	/**
     * Gets the total number of games won.
     * 
     * @return The total number of games won.
     */
	public double getGamesWon() {
		return gamesWon;
	}
	
	/**
     * Gets the total number of guesses across all games.
     * 
     * @return The total number of guesses.
     */
	public int getTotalGuesses() {
		return totalGuesses;
	}
	
	/**
     * Gets the average number of guesses per game.
     * 
     * @return The average number of guesses.
     */
	public int getAverageGuesses() {
		return averageGuesses;
	}
	
	/**
     * Gets the longest win streak.
     * 
     * @return The longest win streak.
     */
	public int getLongestWinStreak() {
		return longestWinStreak;
	}
	
	/**
     * Gets the current win streak.
     * 
     * @return The current win streak.
     */
	public int getCurrentWinStreak() {
		return currentWinStreak;
	}
	
	/**
     * Gets the number of games lost.
     * 
     * @return The total number of games lost.
     */
    public int getGamesLost() {
        return gamesLost;
    }

    /**
     * Gets the best game (fewest guesses) score.
     * 
     * @return The fewest guesses taken in a winning game.
     */
    public int getBestGame() {
        return bestGame;
    }

    /**
     * Gets the worst game (most guesses) score.
     * 
     * @return The most guesses taken in a losing game.
     */
    public int getWorstGame() {
        return worstGame;
    }

    /**
     * Gets the distribution of guesses across games.
     * 
     * @return A map representing the number of times each guess count has occurred.
     */
    public Map<Integer, Integer> getGuessDistribution() {
        return guessDistribution;
    }
    
    /**
     * tracks the distribution of guesses across games.
     */
    private void winAtGuess(int guess) {
		winAtGuess[guess - 1] += 1;
	}
    
    /**
     * Gets the distribution of guesses across games.
     * 
     * @return An array representing the number of times each guess count has occurred.
     */
    public int[] getWinAtGuess() {
		return winAtGuess;
	}
    
    /**
     * Resets the speedleCount for a new speedle game
     */
    public void newSpeedle() {
    	speedleCount = 0;
    }
    
    /**
     * Updates the speedleCount and speedleMax
     */
    public void updateSpeedle() {
    	speedleCount++;
    	if(speedleCount > speedleMax) {
    		speedleMax = speedleCount;
    	}
    }
    
    /**
     * This method gets and returns the speedleCount
     * 
     * @return int speedleCount the number of wins in a speedle round.
     */
    public int getSpeedleCount() {
    	return speedleCount;
    }
    
    /**
     * This method gets and returns the speedleMax
     * 
     * @return int speedleMax the most wins in a round of speedle.
     */
    public int getSpeedleMax() {
    	return speedleMax;
    }
}
