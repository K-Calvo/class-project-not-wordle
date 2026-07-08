package view_controller;

import java.util.Arrays;
import java.util.Optional;

import javafx.animation.PauseTransition;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;
import model.AccountCollection;
import model.SoundEffects;
import model.WordleAccount;
import model.WordleGame;

/*
* Author:KIANNY CALVO
* 
*/


/**
 * The GamePane class represents the main game interface for the Wordle game.
 * It contains all UI elements related to the game such as the tile grid, keyboard, and notifications.
 */
public class GamePane extends VBox {

	private GridPane notificationPane;
	private Label notification;
	private TilePane tiles;
	private FlowPane keyboard;
	private Button[] letterButtons;
	private Button enter;
	private Button delete;
	private WordleGame game;
	private AccountCollection accounts;
	private WordleAccount currAccount;

	/**
	 * Constructs a new GamePane with the specified Wordle game.
	 * @param game The WordleGame instance to be used in this pane.
	 * @param accounts The AccountCollection instance to be used.
	 */
	public GamePane(WordleGame game, AccountCollection accounts) {
		notificationPane = new GridPane();
		notificationLayout();
		tiles = new TilePane();
		tileLayout();
		keyboard = new FlowPane();
		keyboardlayout();
		this.game = game;
		this.accounts = accounts;

		// vBox layout
		this.maxHeight(550.0);
		this.minHeight(500.0);
		this.minWidth(400.0);
		this.setSpacing(10.0);

		this.setFillWidth(false);

		this.setAlignment(Pos.CENTER);

		// add children
		this.getChildren().add(notificationPane);
		this.getChildren().add(tiles);
		this.getChildren().add(keyboard);

		// style
		this.setStyle("-fx-background-color: WHITE");
	}

	/**
	 * Sets up the layout and style for the keyboard in the game pane.
	 */
	private void keyboardlayout() {
		// layout
		keyboard.setAlignment(Pos.BOTTOM_CENTER);
		keyboard.setColumnHalignment(HPos.CENTER);
		keyboard.setHgap(8.0);
		keyboard.setVgap(8.0);
		keyboard.minHeight(200.0);
		keyboard.minWidth(400.0);
		keyboard.setPrefWrapLength(290.0);

		// style for button
		String buttonStyle = "-fx-background-color: lightgrey";

		// add children
		char c;
		letterButtons = new Button[26];
		for (c = 'A'; c <= 'Z'; ++c) {
			Button button = new Button(String.valueOf(c));
			letterButtons[c-65] = button;
			button.setOnAction(e -> {
				typeLetters(((Button) e.getSource()).getText());
			});
			button.prefHeight(50.0);
			button.prefWidth(50.0);
			button.setStyle(buttonStyle);
			button.setFont(Font.font("Calibri", FontWeight.BOLD, 15));
			keyboard.getChildren().add(button);
		}

		// enter button
		enter = new Button("ENTER");
		enter.setOnAction(e -> {
			typeLetters("enter");
		});
		enter.prefWidth(80);
		enter.setStyle(buttonStyle);
		enter.setFont(Font.font("Calibri", FontWeight.BOLD, 15));
		keyboard.getChildren().add(enter);

		// delete button
		delete = new Button("⌫");
		delete.setOnAction(e -> {
			typeLetters("delete");
		});
		delete.prefWidth(80);
		delete.setStyle(buttonStyle);
		delete.setFont(Font.font("Calibri", FontWeight.BOLD, 15));
		keyboard.getChildren().add(delete);
	}

	/**
	 * Sets up the layout and style for the tiles in the game pane.
	 */
	private void tileLayout() {
		// layout
		tiles.setPadding(new Insets(10, 10, 10, 10));
		tiles.setAlignment(Pos.CENTER);
		tiles.setHgap(4.0);
		tiles.setVgap(4.0);
		tiles.setPrefRows(6);
		tiles.setPrefColumns(5);
		tiles.setPrefTileHeight(50.0);
		tiles.setPrefTileWidth(55.0);
		tiles.setTileAlignment(Pos.CENTER);

		// add children
		for (int i = 0; i < 30; i++) {
			Label label = new Label("");
			label.setFont(Font.font("Calibri", FontWeight.BOLD, 25));
			label.setTextFill(Color.BLACK);
			label.setAlignment(Pos.CENTER);
			label.setStyle("-fx-border-color: darkgrey; -fx-background-color: WHITE");
			label.setMinWidth(45);
			label.setMinHeight(45);
			tiles.getChildren().add(label);
		}
	}

	/**
	 * Sets up the layout and style for the notification label in the game pane.
	 */
	private void notificationLayout() {
		
		// hidden label
		notification = new Label("Not a Word");
		notification.setFont(Font.font("Calibri", FontWeight.NORMAL, 25));
		notification.setTextFill(Color.WHITE);
		notification.setAlignment(Pos.CENTER);
		notification.setStyle("-fx-background-color: BLACK;");
		notification.setVisible(false);
		notification.toFront();

		// layout
		notificationPane.setPrefWidth(400.0);
		notificationPane.setAlignment(Pos.TOP_CENTER);
		notificationPane.setHgap(15.0);
		notificationPane.add(notification, 0, 0);
	}

	private int curLetter = 0;
	private int curTile = 0;

	/**
	 * Connects the computer keyboard to the game interface.
	 * @param scene The scene where keyboard events are to be captured.
	 */
	public void onType(Scene scene) { 
		scene.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent key) -> { 
			String keyStr = key.getText(); //temp for testing
			if(key.getCode() == KeyCode.ENTER) {
				enter.fire(); 
				key.consume();
				return;
			} else if(key.getCode() == KeyCode.DELETE || key.getCode() == KeyCode.BACK_SPACE) { 
				delete.fire(); 				 
				key.consume();
				return;
			} else if(Character.isLetter(keyStr.charAt(0)) && curTile != 30) { 
				int keyboardIndex = (int) keyStr.charAt(0);
				if (keyboardIndex >= 97) {
					((Button) keyboard.getChildren().get(keyboardIndex - 97)).fire();
				} else {
					((Button) keyboard.getChildren().get(keyboardIndex - 65)).fire();
				}
				System.out.println("Key pressed " + keyStr); 
				key.consume();
			}
		}); 
	}

	/**
	 * Handles the actions to be performed when the enter key is pressed.
	 * This includes displaying notifications and checking the guess.
	 * @param word The word that has been guessed.
	 */
	private void onEnter(String word) {
		// display notification temporarily
		PauseTransition delay = new PauseTransition(Duration.seconds(4));
		delay.setOnFinished(e -> notification.setVisible(false));

		// enter guess and check for validity of guess
		if (!(game.guess(word.toLowerCase()))) {
			notification.setVisible(true);
			delay.play();
			curLetter = 5;
			return;
		}

		// check each letter for level of accuracy and adjust tile pane
		// adjust keyboard coloring?
		int row = game.getTurn() - 1;
		for (int i = 0; i < 5; i++) {
			// determines color of tile + sound
			int color = game.checkLetter(row, i);
			char letter = game.getLetter(row, i);
			Label tile = (Label) tiles.getChildren().get((row * 5) + i);
			tile.setTextFill(Color.WHITE);
			if (color == 2) { // green
				tile.setStyle("-fx-background-color: forestgreen");
				letterButtons[letter-97].setStyle("-fx-background-color: forestgreen");
				// SoundEffects.playGreenTile();
			} else if (color == 1) {// yellow
				tile.setStyle("-fx-background-color: goldenrod");
				letterButtons[letter-97].setStyle("-fx-background-color: goldenrod");
				// SoundEffects.playYellowTile();
			} else {// dark grey
				tile.setStyle("-fx-background-color: darkgrey");
				letterButtons[letter-97].setStyle("-fx-background-color: darkgrey");
				// SoundEffects.playGreyTile();
			}
		}

		System.out.println(game.getTurn());
		// check for win or lose
		boolean win = game.checkWin();
		WordleAccount activeAccount = accounts.activeAccount();
		if (win) {
			System.out.println("winner");
			currAccount.getStatistics().updatePostGameStats(win, game.getTurn());
			SoundEffects.playWinSound();
			if(game.getState() == 2) {
				currAccount.getStatistics().updateSpeedle();
				game = new WordleGame(game.getState());
				this.resetGamePane(game);
			} else {
				gameOver(win);
				if(game.getState() > 0) {
					// update achievements
					activeAccount.getAchievements().havePlayed("Played Unwordle");
				} else {
					// update achievements
					activeAccount.getAchievements().havePlayed("Played Wordle");
				}
					
			}
		} else if (game.getTurn() == 6) {
			System.out.println("loser");			
			currAccount.getStatistics().updatePostGameStats(win, game.getTurn());
			SoundEffects.playLoseSound();
			if(game.getState() == 2) {
				game = new WordleGame(game.getState());
				this.resetGamePane(game);
			} else {
				gameOver(win);
			}
		} else {
			SoundEffects.playGuessSound();
		}

		curLetter = 0;
	}
	
	/**
	 * Alerts user that game has ended, their status, and the current word
	 * @param isWin boolean value after game win is checked
	 */
	private void gameOver(boolean isWin) {
		Alert alert;
		if(isWin) {
			alert = new Alert(Alert.AlertType.INFORMATION, 
		    		"Congradulations, You won!", 
		    		ButtonType.CLOSE);
		} else {
		String word = game.getWord();
	    alert = new Alert(Alert.AlertType.INFORMATION, 
	    		"Better Luck Next Time! The word was " + word, 
	    		ButtonType.CLOSE);
		}
		alert.setHeaderText("Game Over");
		alert.show();
	}

	/**
	 * Types letters into the game's tiles based on keyboard or on-screen button inputs.
	 * @param key The key or button text that was pressed.
	 */
	public void typeLetters(String key) {
		if(game.getTurn() == 6) {
			return;
		}
		if (curLetter == 5 && key.equals("enter")) {
			String guess = "";
			for (int i = 1; i <= 5; i++) {
				guess = ((Labeled) tiles.getChildren().get(curTile - i)).getText() + guess;
			}
			onEnter(guess);
			return;
		} else if (key.equals("delete") && curLetter != 0) {
			Label tile = (Label) tiles.getChildren().get(curTile - 1);
			tile.setText("");
			curTile -= 1;
			curLetter -= 1;
			return;
		} else if (key.equals("delete") && curLetter == 0) {
			return;
		} else if (curLetter != 5 && !(key.equals("enter")) && !(key.equals("delete"))) {
			Label tile = (Label) tiles.getChildren().get(curTile);
			tile.setText(key);
			curTile += 1;
			curLetter += 1;
			return;
		}
	}

	/**
	 * Resets the game pane with a new WordleGame instance.
	 * Clears all tiles and resets the keyboard.
	 * @param newGame The new WordleGame instance to be used.
	 */
	public void resetGamePane(WordleGame newGame) {
		this.game = newGame;
		
		for (int i = 0; i < 5; i++) {
			for (int col = 0; col < 6; col++) {
				Label tile = (Label) tiles.getChildren().get((col * 5) + i);
				tile.setText("");
				tile.setTextFill(Color.BLACK);
				tile.setStyle("-fx-border-color: darkgrey; -fx-background-color: WHITE");
				tile.setFont(Font.font("Calibri", FontWeight.BOLD, 25));
				curLetter = 0;
				curTile = 0;
			}
		}
		for (int i = 0; i < 26; i++) {
			letterButtons[i].setStyle("-fx-background-color: lightgrey");
		}
		
	}

	public void setCurrAccount(WordleAccount user) {
		currAccount = user;
	}

}
