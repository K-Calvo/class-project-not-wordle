package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 * This class represents a collection of accounts for the Wordle game.
 * It handles account management functions such as creating new accounts,
 * logging in, and saving account information.
 */
public class AccountCollection implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Map<String, WordleAccount> accounts; 	//stores Wordle accounts keyed by username.
	private static WordleAccount activeAccount = null; 	//current active account in the session.
	public ArrayList<String> leaderBoard; 			//list of leaders in the Wordle game.

	/**
     * Constructs a new AccountCollection instance.
     * Initializes the leader board and account map, 
     * and adds default accounts.
     */
	public AccountCollection() {
		leaderBoard = new ArrayList<>();
		this.accounts = new HashMap<>();
		addDefaultAccounts();

	}

	/**
	 * Adds default accounts for grading and testing.
	 */
	private void addDefaultAccounts() {
		this.addAccount("kianny", "1");
		this.addAccount("vincent", "2");
		this.addAccount("leah", "3");
		this.addAccount("kenneth", "4");
	}

	/**
     * Adds a new account to the collection if it doesn't already exist.
     *
     * @param username The username for the new account.
     * @param password The password for the new account.
     */
	private void addAccount(String username, String password) {
		if (!accounts.containsKey(username)) {
			accounts.put(username, new WordleAccount(username, password));
		} else {
			System.out.println("Account already exists!");
		}
	}
	
	/**
     * Checks if an account with the specified username exists.
     *
     * @param username The username to check.
     * @return true if an account with the given username exists, false otherwise.
     */
	public boolean accountExists(String username) {
	    return accounts.containsKey(username);
	}

	/**
     * Creates a new account and adds it to the collection.
     *
     * @param username The username for the new account.
     * @param password The password for the new account.
     */
	public void createNewAccount(String username, String password) {
	    accounts.put(username, new WordleAccount(username, password));
	}

	/**
     * Attempts to log in with the specified username and password.
     *
     * @param username The username for login.
     * @param password The password for login.
     * @return true if the login is successful, false otherwise.
     */
	public boolean login(String username, String password) {
		if (isUsername(username)) {
			WordleAccount user = accounts.get(username);
			if(user.getPassword().equals(password)) {
				activeAccount = user;
				return true;
			}
		}
		return false;
	}
	
	/**
     * Checks if the specified username is associated with an account.
     *
     * @param username The username to check.
     * @return true if the username is associated with an account, false otherwise.
     */
	public boolean isUsername(String username) {
		if (accounts.get(username) != null) {
			return true;
		}
		return false;
	}
	
	/**
     * Logs out the currently active account and saves its state.
     */
	public void logout() {
		this.saveAccount();
		if(activeAccount != null) {
			activeAccount = null;
		}
	}
	
	/**
     * Retrieves the currently active account.
     *
     * @return The currently active WordleAccount, or null if no account is active.
     */
	public WordleAccount activeAccount() {
		return activeAccount;
	}
	
	/**
     * Saves the current state of the AccountCollection to a file.
     */
	public void saveAccount() {
	    try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("WordleAccounts.ser"))) {
	        out.writeObject(this);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	/**
     * Loads the AccountCollection from a file if it 
     * exists, otherwise returns a new instance.
     *
     * @return The loaded AccountCollection if successful, 
     * or a new instance if no saved data is found.
     */
	public AccountCollection loadAccount() {
	    File accountFile = new File("WordleAccounts.ser");
	    if (accountFile.exists() && confirmRestoreSession()) {
	        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("WordleAccounts.ser"))) {
	            return (AccountCollection) in.readObject();
	        } catch (IOException | ClassNotFoundException e) {
	            e.printStackTrace();
	            return null;
	        }
	    } else {
	        return new AccountCollection();
	    }
	}

	 /**
     * Prompts the user to confirm whether 
     * to restore the saved session.
     *
     * @return true if the user confirms to 
     * restore the session, false otherwise.
     */
	private boolean confirmRestoreSession() {
	    Alert startupAlert = new Alert(Alert.AlertType.CONFIRMATION);
	    startupAlert.setTitle("Startup Option");
	    startupAlert.setHeaderText("Read saved data?");
	    startupAlert.setContentText("Press cancel while system testing.");
	    Optional<ButtonType> result = startupAlert.showAndWait();
	    return result.isPresent() && result.get() == ButtonType.OK;
	}

	public Map<String, WordleAccount> getAccounts() {
		return accounts;
	}
}
