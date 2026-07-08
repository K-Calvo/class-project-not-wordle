package view_controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import model.AccountCollection;
import model.WordleAccount;

/**
 * This class represents the leader board in the GUI of the Wordle game.
 * It provides a layout for displaying the leaderboard
 *
 * Author: KIANNY CALVO
 */

public class LeaderPane extends ScrollPane {

	private Label title;
	private VBox board;
	
	/**
	 * initializes the LeaderPane and sets up the layout
	 */
	public LeaderPane(AccountCollection accounts) {		
		board = new VBox();
		board.setAlignment(Pos.CENTER);
		board.setStyle("-fx-background-color: WHITE;");
		
		title = new Label("Leaderboard");
		title.setFont(Font.font("Calibri", FontWeight.BOLD, 40));
		title.setPadding(new Insets(15));
		
		updateLeaderBoard(accounts);
		
		this.setFitToHeight(true);
		this.setFitToWidth(true);
		this.setContent(board);		
	}

	/**
	 * updates the table according to display user rankings
	 * @param accounts an instance of AccountCollection that contains
	 * 	all the users
	 */
	public void updateLeaderBoard(AccountCollection accounts) {
		
		if(board.getChildren().size() != 0) {
			board.getChildren().clear();
		}		
		
		board.getChildren().add(title);

		// map of user rankings
		TreeMap<Double, ArrayList<WordleAccount>> rankings = new TreeMap<Double, ArrayList<WordleAccount>>();
		Collection<WordleAccount> userAccounts = accounts.getAccounts().values();
		for (WordleAccount account : userAccounts) {
			Double winRate = account.getStatistics().getWinRate();
			System.out.println("map wr " + winRate); //
			if (rankings.containsKey(winRate)) {
				rankings.get(winRate).add(account);
			} else {
				ArrayList<WordleAccount> users = new ArrayList<WordleAccount>();
				users.add(account);
				rankings.put(winRate, users);
			}
		}
		
		//style leader board
		int r = 1;
		for (Double key : rankings.descendingKeySet()) {

			System.out.println("key " + key);
			for (WordleAccount user : rankings.get(key)) {
				System.out.println("user " + user.getUsername());
				HBox entry = new HBox(15);
				entry.setStyle("-fx-background-color: WHITE; -fx-border-color: darkgrey;");
				entry.setAlignment(Pos.CENTER_LEFT);
				
				Label rank = new Label(String.valueOf(r));
				rank.setFont(Font.font("Times New Roman", FontWeight.EXTRA_BOLD, 70));
				rank.setAlignment(Pos.CENTER);
				rank.setMinWidth(100);
				rank.setMinHeight(100);
				
				VBox userStats = new VBox();
				userStats.setAlignment(Pos.CENTER_LEFT);
				userStats.setPadding(new Insets(7));
				
				Label name = new Label(user.getUsername());
				name.setFont(Font.font("Calibri", FontWeight.BOLD, 30));
				
				Label ratio = new Label("Win Ratio: " + String.valueOf(key) + "%");
				ratio.setFont(Font.font("Calibri", FontWeight.NORMAL, 20));
				
				Label streak = new Label("Longest Streak: " + String.valueOf(user.getStatistics().getLongestWinStreak()));
				streak.setFont(Font.font("Calibri", FontWeight.NORMAL, 20));
				
				userStats.getChildren().addAll(name, ratio, streak);				
				entry.getChildren().addAll(rank, userStats);				
				board.getChildren().add(entry);
			}
			r += 1;
		}
		
	}

}
