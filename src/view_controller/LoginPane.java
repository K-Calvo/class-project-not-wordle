package view_controller;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import model.AccountCollection;
import model.WordleAccount;

/**
 * This class represents the login pane for the Wordle game.
 * It provides UI components for users to log in or create an account.
 */
public class LoginPane extends GridPane{

	private TextField usernameField;
    private PasswordField passwordField;
    public Button loginButton;
    private Button createAccountButton;
    private Label statusLabel;
    private AccountCollection accounts;
    private Label userLabel;
    private Label passwordLabel;
    public static WordleAccount currAccount;
    private static GamePane gamePane;
	
    /**
     * Constructor for LoginPane. Sets up the layout, styles, and event handlers.
     * 
     * @param WordleAccount The account collection to be used for login and account creation.
     */
    public LoginPane(AccountCollection WordleAccounts, GamePane gamePane) {
    	this.accounts = WordleAccounts;
    	this.gamePane = gamePane;
    	setAlignment(Pos.CENTER);
        setHgap(10);
        setVgap(10);
        setPadding(new Insets(25, 25, 25, 25));

        loginButton = new Button("Login");
        statusLabel = new Label("Login First");
        createAccountButton = new Button("Create Account"); 

        usernameField = new TextField();
        passwordField = new PasswordField();
        
        userLabel = new Label("Username:");
        passwordLabel = new Label("Password:");

        HBox accountNameBox = new HBox(10);
        accountNameBox.setAlignment(Pos.CENTER);
        accountNameBox.getChildren().addAll(userLabel, usernameField, createAccountButton);

        HBox passwordBox = new HBox(10);
        passwordBox.setAlignment(Pos.CENTER);
        passwordBox.getChildren().addAll(passwordLabel, passwordField, loginButton);

        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(accountNameBox, passwordBox, statusLabel);

        add(vbox, 0, 0);
        
    	style();
    	this.setStyle("-fx-background-color: WHITE");
    	
        loginButton.setOnAction(e -> login());
        createAccountButton.setOnAction(e -> createAccount());
    }
    
    /**
     * Handles the login process. Validates user credentials and manages the UI accordingly.
     */
    public void login() {
    	String username = usernameField.getText();
        String password = passwordField.getText();
        if (accounts.login(username, password)) {
            statusLabel.setText("Logged in as " + username);
            loginButton.setDisable(true);            
            
            ((Stage) loginButton.getScene().getWindow()).close();
            
        } else {
            statusLabel.setText("Invalid username or password!");
        }	
    	currAccount = accounts.activeAccount();
    	gamePane.setCurrAccount(currAccount);
    }
    
    public static WordleAccount getActiveAccount() {
    	return currAccount;
    }
    
    /**
     * Handles the creation of a new account. Validates the input and updates the account collection.
     */
    private void createAccount() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username == null || username.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            statusLabel.setText("Both fields must be filled out!");
            return;
        }

        if (accounts.accountExists(username)) {
            statusLabel.setText("Account already exists!");
        } else {
            accounts.createNewAccount(username, password);
            statusLabel.setText("Account created successfully!");
            login();
        }
    }
    
    /**
     * Applies styling to UI components.
     */
    public void style() {
    	//style buttons
    	String buttonStyle = "-fx-background-color: lightgrey";
    	loginButton.setStyle(buttonStyle);
    	createAccountButton.setStyle(buttonStyle);
    	loginButton.setFont(Font.font("Calibri", FontWeight.BOLD, 13));
    	createAccountButton.setFont(Font.font("Calibri", FontWeight.BOLD, 13));
    
    	//style text fields
    	usernameField.setFont(Font.font("Calibri", FontWeight.BOLD, 13));
    	passwordField.setFont(Font.font("Calibri", FontWeight.BOLD, 13));
    	usernameField.setStyle("-fx-border-color: darkgrey; -fx-background-color: WHITE");
    	passwordField.setStyle("-fx-border-color: darkgrey; -fx-background-color: WHITE");
    	
    	//style labels
    	statusLabel.setFont(new Font("Calibri", 15));
    	userLabel.setFont(new Font("Calibri", 15));
    	passwordLabel.setFont(new Font("Calibri", 15));
    }     
    
    public WordleAccount getUser() {
    	return currAccount;
    }
    
    public void setCurrAccount(WordleAccount user) {
    	currAccount = user;
    }
}