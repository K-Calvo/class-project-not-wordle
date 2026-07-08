package view_controller;

import java.util.Arrays;

import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import model.Statistics;
import model.WordleAccount;

/**
 * This class represents the statistics pane in the GUI of the Wordle game.
 * It provides a layout for displaying user game statistics
 *
 * Author: KIANNY CALVO
 */
public class StatsPane extends VBox {
	
	private HBox bar;
	private Label playedValue;
	private Label played;
	private Label rateValue;
	private Label winRate;
	private Label curStreakValue;
	private Label curStreak;
	private Label maxValue;
	private Label maxStreak;
	
	private BarChart<Number, String> bc;
	XYChart.Series<Number, String> series;
	
	private Statistics stats;
	
	/**
     * Constructor for StatsPane.
     * Initializes the pane with tabs for statistics and leaderboard.
     */
	public StatsPane () { 
		 this.setStyle("-fx-background-color: WHITE; -fx-border-color: darkgrey;");
		 
		 Label title = new Label("Statistics");
		 title.setFont(Font.font("Calibri", FontWeight.BOLD, 30));
		 
		 statsBar();
		 
		 Label chartTitle = new Label("Guess Distribution");
		 chartTitle.setFont(Font.font("Calibri", FontWeight.EXTRA_BOLD, 25));
		 
		 guessGraph();
		
		 this.setSpacing(10);
		 this.getChildren().addAll(title, bar, chartTitle, bc);
		 this.setAlignment(Pos.CENTER); 
	}
	
	/**
	 * sets they layout for the HBox statistic bar
	 */
	private HBox statsBar() {
		bar = new HBox(30);
		
		//games played
		VBox gamesPlayed = new VBox();
		playedValue = new Label("0");
		playedValue.setFont(Font.font("Calibri", FontWeight.BOLD, 40));
		played = new Label("Played");
		played.setFont(Font.font("Calibri", FontWeight.LIGHT, 10));	
		gamesPlayed.setAlignment(Pos.CENTER); 
		gamesPlayed.getChildren().addAll(playedValue, played);
		
		//win%
		VBox wins = new VBox();
		rateValue = new Label("00%");
		rateValue.setFont(Font.font("Calibri", FontWeight.BOLD, 40));
		winRate = new Label("Win%");
		winRate.setFont(Font.font("Calibri", FontWeight.LIGHT, 10));	
		wins.setAlignment(Pos.CENTER); 
		wins.getChildren().addAll(rateValue, winRate);
		
		//current streak
		VBox streak = new VBox();
		curStreakValue = new Label("0");
		curStreakValue.setFont(Font.font("Calibri", FontWeight.BOLD, 40));
		curStreak = new Label("Current Streak");
		curStreak.setFont(Font.font("Calibri", FontWeight.LIGHT, 10));
		streak.setAlignment(Pos.CENTER); 
		streak.getChildren().addAll(curStreakValue, curStreak);
		
		//max streak
		VBox max = new VBox();
		maxValue= new Label("0");
		maxValue.setFont(Font.font("Calibri", FontWeight.BOLD, 40));
		maxStreak = new Label("Max Streak");
		maxStreak.setFont(Font.font("Calibri", FontWeight.LIGHT, 10));
		max.setAlignment(Pos.CENTER); 
		max.getChildren().addAll(maxValue, maxStreak);
		
		bar.setAlignment(Pos.CENTER);
		bar.getChildren().addAll(gamesPlayed, wins, streak, max);
		return bar;
	}
	
	/**
	 * sets they layout for the statistics bar chart
	 */
	private void guessGraph() {
		CategoryAxis yAxis = new CategoryAxis();          
		yAxis.setCategories(FXCollections.<String>observableArrayList(Arrays.asList(
		   "1", "2", "3", "4", "5", "6"))); 
		
		NumberAxis xAxis = new NumberAxis();
		
		bc = new BarChart<>(xAxis, yAxis);
		
		series = new XYChart.Series<>();
		series.getData().add(new XYChart.Data<Number, String>(3, "1"));
		series.getData().add(new XYChart.Data<Number, String>(0, "2"));
		series.getData().add(new XYChart.Data<Number, String>(0, "3"));
		series.getData().add(new XYChart.Data<Number, String>(1, "4"));
		series.getData().add(new XYChart.Data<Number, String>(0, "5"));
		series.getData().add(new XYChart.Data<Number, String>(0, "6"));
		
		bc.getData().add(series);
		
		bc.setLegendVisible(false);
		styleBars();     
		bc.lookup(".chart-plot-background").setStyle("-fx-background-color: transparent;");
	}
	
	/**
	 * updates the statistics bar and bar chart to display user statistics
	 * @param activeAccount the current user account, an WordleAccount object
	 */
	public void updateDisplay(WordleAccount activeAccount) {
		stats = activeAccount.getStatistics();
		
		//update bar
		playedValue.setText(String.valueOf(stats.getGamesPlayed()));
		rateValue.setText(String.valueOf(Math.round(stats.getWinRate())) + "%");
		curStreakValue.setText(String.valueOf(stats.getCurrentWinStreak()));
		maxValue.setText(String.valueOf(stats.getLongestWinStreak()));		
		
		//update graph
		series.getData().clear();
		int[] temp = stats.getWinAtGuess();
		for(int i = 0; i < 6; i++) {
			int curValue = temp[i];
			String guess = String.valueOf(i + 1);
			series.getData().add(new XYChart.Data<Number, String>(curValue, guess));
		}
		styleBars();
	}
	
	/**
	 * Styles bars in bar chart
	 */
	private void styleBars() {
		for(Data data : series.getData())
        {
            data.getNode().setStyle("-fx-bar-fill: forestgreen;");
        }     
	}

	
}
