package view_controller;

import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.AccountCollection;
import model.WordleAccount;
import model.WordleGame;

/**
 * The main GUI class for the Wordle game application. It sets up the primary
 * stage and handles the layout and interaction of different game panes.
 */
public class WordleGUI extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	private BorderPane everything;
	private AccountCollection accounts = new AccountCollection();
	private WordleGame game = new WordleGame();
	private LoginPane loginPane; // GridPane ~~~ popup overlay
	private GamePane gamePane;
	private InstructionsPane instructionsPane; // GridPane ~~~ menu option
	private StatsPane statsPane;
	private LeaderPane leaderBoard;
	private AchievementsPane achievementsPane; // BorderPane ~~~ menu option
	private Button help;
	private MenuButton menu;
	private WordleAccount user;
	private Stage primaryStage;

	@Override
	public void start(Stage primaryStage) throws Exception {
		accounts = accounts.loadAccount();
		accounts.loadAccount();
		layoutGUI(primaryStage);
		popup(primaryStage);

		Scene scene = new Scene(everything, 440, 600);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Wordle");
		primaryStage.show();
		this.primaryStage = primaryStage;

		primaryStage.setOnCloseRequest((e) -> {
			// automatically logs user off
			accounts.saveAccount();
			accounts.logout();
			Platform.exit();
			System.exit(0);
		});

		// keyboard press
		gamePane.onType(scene);
	}

	/**
	 * Sets up the layout for the primary game GUI including all the necessary
	 * panes.
	 * 
	 * @param primaryStage The primary stage for GUI
	 */
	private void layoutGUI(Stage primaryStage) {
		everything = new BorderPane();
		everything.setPadding(new Insets(15, 15, 15, 15));
		everything.setStyle("-fx-background-color: WHITE");

		gamePane = new GamePane(game, accounts);
		BorderPane.setAlignment(gamePane, javafx.geometry.Pos.CENTER);
		everything.setCenter(gamePane);

		help = new Button("?");
		help.setTextFill(Color.WHITE);
		help.setFont(Font.font("Calibri", FontWeight.BOLD, 20));
		help.setStyle("-fx-background-radius: 10em; " + "-fx-min-width: 30px; " + "-fx-min-height: 30px; "
				+ "-fx-max-width: 30px; " + "-fx-max-height: 30px; " + "-fx-background-color: DARKGREY;"
				+ "-fx-background-insets: 0px; " + "-fx-padding: 0px;");
		help.setOnAction(e -> {
			Instructions.popup(instructionsPane);
		});
		BorderPane.setAlignment(help, javafx.geometry.Pos.CENTER_RIGHT);
		everything.setBottom(help);

		menuBar(primaryStage);
		BorderPane.setAlignment(menu, javafx.geometry.Pos.CENTER_LEFT);
		everything.setTop(menu);

		loginPane = new LoginPane(accounts, gamePane);

		instructionsPane = new InstructionsPane();

		statsPane = new StatsPane();

		leaderBoard = new LeaderPane(accounts);

		achievementsPane = new AchievementsPane();
	}

	/**
	 * Sets up the menu bar with options for game modes, statistics, achievements,
	 * etc.
	 * 
	 * @param primaryStage The primary stage for this application.
	 */
	private void menuBar(Stage primaryStage) {
		// title

		// menu
		menu = new MenuButton("Menu ☰");

		// menu items
		MenuItem statistics = new MenuItem("Statistics");
		statistics.setOnAction((e) -> {
			BorderPane.setAlignment(menu, javafx.geometry.Pos.CENTER_LEFT);
			everything.setTop(menu);

			BorderPane.setAlignment(statsPane, javafx.geometry.Pos.CENTER);
			everything.setCenter(statsPane);

			statsPane.updateDisplay(accounts.activeAccount());
			help.setVisible(false);
		});
		menu.getItems().add(statistics);

		MenuItem leaderboard = new MenuItem("Leaderboard");
		leaderboard.setOnAction((e) -> {
			BorderPane.setAlignment(menu, javafx.geometry.Pos.CENTER_LEFT);
			everything.setTop(menu);

			BorderPane.setAlignment(leaderBoard, javafx.geometry.Pos.CENTER);
			everything.setCenter(leaderBoard);

			leaderBoard.updateLeaderBoard(accounts);
			help.setVisible(false);
		});
		menu.getItems().add(leaderboard);

		MenuItem achievements = new MenuItem("Achievements");
		achievements.setOnAction((e) -> {
			WordleAccount activeUser = accounts.activeAccount();
			activeUser.getAchievements().setAchievments(activeUser.getStatistics());
			achievementsPane.resetLayout(activeUser.getAchievements());

			BorderPane.setAlignment(menu, javafx.geometry.Pos.CENTER_LEFT);
			everything.setTop(menu);

			BorderPane.setAlignment(achievementsPane, javafx.geometry.Pos.CENTER);
			everything.setCenter(achievementsPane);

			help.setVisible(false);
		});
		menu.getItems().add(achievements);

		Menu mode = new Menu("Game Mode");
		MenuItem original = new MenuItem("Original");
		original.setOnAction((e) -> {
			switchToOriginal();
			primaryStage.setTitle("Wordle");
			help.setVisible(true);
		});
		MenuItem speedle = new MenuItem("Speedle");
		speedle.setOnAction((e) -> {
			switchToSpeed();
			primaryStage.setTitle("Speedle");
			help.setVisible(true);
		});
		MenuItem unwordle = new MenuItem("Unwordle");
		unwordle.setOnAction((e) -> {
			primaryStage.setTitle("Unwordle");
			switchToUnwordle();
			help.setVisible(true);
		});

		mode.getItems().add(speedle);
		mode.getItems().add(original);
		mode.getItems().add(unwordle);
		menu.getItems().add(mode);

		MenuItem newGame = new MenuItem("New Game");
		newGame.setOnAction((e) -> startNewGame());
		menu.getItems().add(newGame);

		MenuItem exit = new MenuItem("Exit");
		exit.setOnAction((e) -> {
			accounts.saveAccount();
			accounts.logout();
			Platform.exit();
			System.exit(0);
		});
		menu.getItems().add(exit);

		// style
		menu.setTextFill(Color.DARKGREY);
		menu.setFont(Font.font("Calibri", FontWeight.LIGHT, 15));
		menu.setStyle("-fx-background-color: WHITE;");
	}

//	private void showLoginPane(Stage primaryStage) {
//	    if (loginPane == null) {
//	        loginPane = new LoginPane(accounts);
//	    }
//	    Scene scene = new Scene(loginPane, 440, 200);
//	    primaryStage.setScene(scene);
//	    primaryStage.show();
//	}

	/**
	 * Displays the login popup window when the application starts.
	 * 
	 * @param primaryStage The primary stage for this application.
	 */
	private void popup(Stage primaryStage) {
		primaryStage.setOnShown((e) -> {
			accounts.saveAccount();
			Login.loginPopup(loginPane, accounts, gamePane);
		});
	}

	/**
	 * Starts a new game. This is triggered by the 'New Game' menu option.
	 */
	private void startNewGame() {
		// see if the user really wants to start a new game
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
				"Start a new Original game? Your current progress will be lost.", ButtonType.YES, ButtonType.NO);
		alert.setHeaderText("Confirm New Game");
		Optional<ButtonType> result = alert.showAndWait();

		if (result.isPresent() && result.get() == ButtonType.YES) {
			// reset the game
			game = new WordleGame(game.getState());
			gamePane.resetGamePane(game);
		}
	}

	/**
	 * Switches the game to Unwordle mode.
	 */
	private void switchToUnwordle() {
		if (!game.isUnwordle()) {
			// see if the user really wants to start a new game
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
					"Switch to Unwordle? Your current progress will be lost.", ButtonType.YES, ButtonType.NO);
			alert.setHeaderText("Confirm Unwordle");
			Optional<ButtonType> result = alert.showAndWait();

			if (result.isPresent() && result.get() == ButtonType.YES) {
				// reset the game
				game = new WordleGame(1);
				gamePane.resetGamePane(game);

				// update achievements
				accounts.activeAccount().getAchievements().havePlayed("Played Unwordle");

				BorderPane.setAlignment(menu, javafx.geometry.Pos.CENTER_LEFT);
				everything.setTop(menu);

				BorderPane.setAlignment(gamePane, javafx.geometry.Pos.CENTER);
				everything.setCenter(gamePane);
			}
		}
	}

	/**
	 * Switches the game to Speed mode and starts timer.
	 */
	private void switchToSpeed() {
		if (game.getState() != 2) {
			// see if the user really wants to start a new game
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
					"Switch to Speed? Your current progress will be lost. " + "Your time will start when you click YES",
					ButtonType.YES, ButtonType.NO);
			alert.setHeaderText("Confirm Speed Wordle");
			Optional<ButtonType> result = alert.showAndWait();

			if (result.isPresent() && result.get() == ButtonType.YES) {
				// reset the game
				game = new WordleGame(2);
				gamePane.resetGamePane(game);
				
				//update reset speedle statistics
				accounts.activeAccount().getStatistics().newSpeedle();

				BorderPane.setAlignment(menu, javafx.geometry.Pos.CENTER_LEFT);
				everything.setTop(menu);

				BorderPane.setAlignment(gamePane, javafx.geometry.Pos.CENTER);
				everything.setCenter(gamePane);

				// game timer for 5 minutes
				TimerTask task = new TimerTask() {
					public void run() {
						System.out.println("times up!");
						Platform.runLater(() -> {
							Alert alert;
							alert = new Alert(Alert.AlertType.NONE,
									"You won "
											+ String.valueOf(accounts.activeAccount().getStatistics().getSpeedleCount())
											+ " games!",
									ButtonType.CLOSE);
							alert.setHeaderText("Times Up!");
							alert.setTitle("Game Over");
							alert.show();
							// update achievements
							accounts.activeAccount().getAchievements().havePlayed("Played Speed");
						});
					}
				};
				Timer timer = new Timer("Timer");

				timer.schedule(task, TimeUnit.MINUTES.toMillis(15));
			}
		}
	}

	/**
	 * Switches the game to the Original Wordle mode.
	 */
	private void switchToOriginal() {
		if (game.isUnwordle()) {
			// see if the user really wants to start a new game
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
					"Switch to Original Wordle? Your current progress will be lost.", ButtonType.YES, ButtonType.NO);
			alert.setHeaderText("Confirm Original Wordle");
			Optional<ButtonType> result = alert.showAndWait();

			if (result.isPresent() && result.get() == ButtonType.YES) {
				// reset the game
				game = new WordleGame(0);
				gamePane.resetGamePane(game);

				// update achievements
				accounts.activeAccount().getAchievements().havePlayed("Played Wordle");

				BorderPane.setAlignment(menu, javafx.geometry.Pos.CENTER_LEFT);
				everything.setTop(menu);

				BorderPane.setAlignment(gamePane, javafx.geometry.Pos.CENTER);
				everything.setCenter(gamePane);
			}
		}
	}

	/**
	 * A nested class for managing the login popup.
	 */
	public class Login {

		/**
		 * Displays the login popup window.
		 * 
		 * @param loginPane The login pane to be displayed in the popup.
		 * 
		 * @param accounts  The account collection used for login validation.
		 */
		public static void loginPopup(LoginPane loginPane, AccountCollection accounts, GamePane gamePane) {
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);

			Scene scene = new Scene(loginPane, 440, 200);
			stage.setTitle("Login");
			stage.setScene(scene);
			stage.show();

//			stage.setOnCloseRequest((e) -> {
//				// popup gameLayout
//				// close upon login and alert user they must log in first
//				Alert exitAlert = new Alert(Alert.AlertType.CONFIRMATION);
//				exitAlert.setTitle("Exit Confirmation");
//				exitAlert.setHeaderText("Click cancel to not save any changes");
//				exitAlert.setContentText("Save account data?");
//				Optional<ButtonType> result = exitAlert.showAndWait();
//
//				if (result.isPresent() && result.get() == ButtonType.OK) {
//					accounts.saveAccount();
//				}
//
//			});
			// exits system if user chooses not to login
			stage.setOnCloseRequest((e) -> {
				accounts.saveAccount();
				Platform.exit();
				System.out.println(loginPane.getUser());
				gamePane.setCurrAccount(loginPane.getUser());
				System.exit(0);
			});
		}
	}

	/**
	 * A nested class for managing the instructions popup.
	 */
	public class Instructions {

		/**
		 * Displays the instructions popup window.
		 * 
		 * @param instructionsPane The instructions pane to be displayed in the popup.
		 */
		public static void popup(InstructionsPane instructionsPane) {
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);

			Scene scene = new Scene(instructionsPane, 420, 580);
			stage.setTitle("Login");
			stage.setScene(scene);
			stage.show();
		}

	}

}
