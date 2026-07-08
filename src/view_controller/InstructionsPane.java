package view_controller;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * This class represents the instructions pane in the GUI of the Wordle game. It
 * provides a layout for displaying the rules and instructions on how to play
 * the game.
 *
 * Author: KIANNY CALVO
 */
public class InstructionsPane extends VBox {

	/**
	 * Constructor for InstructionsPane. Initializes the pane with components to
	 * display game rules and instructions.
	 */
	public InstructionsPane() {
		Label how1 = new Label("How To Play");
		how1.setFont(Font.font("Calibri", FontWeight.EXTRA_BOLD, 40));

		Label how2 = new Label("Guess the Wordle in 6 tries.");
		how2.setFont(Font.font("Calibri", FontWeight.LIGHT, 35));

		Label how3 = new Label("• Each guess must be a valid 5-letter word.");
		how3.setFont(Font.font("Calibri", FontWeight.NORMAL, 12));

		Label how4 = new Label("• The color of the tiles will change to show "
				+ "how close your guess was to the word.");
		how4.setFont(Font.font("Calibri", FontWeight.NORMAL, 12));
		
		Label example1 = new Label("Example");
		example1.setFont(Font.font("Calibri", FontWeight.BOLD, 15));
		
		HBox example = new HBox(5);
		for(String str: "GUESS".split("")) {
			Label label = new Label(str);
			label.setFont(Font.font("Calibri", FontWeight.BOLD, 18));
			label.setAlignment(Pos.CENTER);
			if(str.equals("G")) {
				label.setStyle("-fx-background-color: forestgreen");
				label.setTextFill(Color.WHITE);
			} else if(str.equals("U")) {
				label.setStyle("-fx-background-color: goldenrod");
				label.setTextFill(Color.WHITE);
			} else if(str.equals("E")) {
				label.setStyle("-fx-background-color: darkgrey");
				label.setTextFill(Color.WHITE);
			} else {
				label.setStyle("-fx-border-color: darkgrey; -fx-background-color: WHITE");
				label.setTextFill(Color.BLACK);
			}
			label.setMinWidth(23);
			label.setMinHeight(23);	
			example.getChildren().add(label);
		}		
		
		Label example2 = new Label("G is in the word and in the correct spot.");
		example2.setFont(Font.font("Calibri", FontWeight.NORMAL, 15));
		
		Label example3 = new Label("I is in the word but in the wrong spot.");
		example3.setFont(Font.font("Calibri", FontWeight.NORMAL, 15));
		
		Label example4 = new Label("U is not in the word in any spot.");
		example4.setFont(Font.font("Calibri", FontWeight.NORMAL, 15));
		
		Label unwordle = new Label("Unwordle");
		unwordle.setFont(Font.font("Calibri", FontWeight.BOLD, 25));
		
		Label unwordle1 = new Label("• Guess the random assortment of letters in 6 tries.");
		unwordle1.setFont(Font.font("Calibri", FontWeight.NORMAL, 12));
		
		Label speed = new Label("Speed");
		speed.setFont(Font.font("Calibri", FontWeight.BOLD, 25));
		
		Label speed1 = new Label("• Win as many rounds as posible within 15 minutes.");
		speed1.setFont(Font.font("Calibri", FontWeight.NORMAL, 12));
		
		this.setSpacing(10);
		this.setPadding(new Insets(5));
		this.getChildren().addAll(how1, how2, how3, how4, example1, example, example2, 
				example3, example4, unwordle, unwordle1, speed, speed1);
		this.setAlignment(Pos.CENTER_LEFT);
	}
}
