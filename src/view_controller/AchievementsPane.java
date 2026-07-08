package view_controller;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import model.Achievements;

/**
 * This class represents the achievements pane in the GUI.
 * It displays the achievements in the form of labels and images,
 * indicating locked and unlocked achievements.
 */
public class AchievementsPane extends VBox {
	
	private GridPane grid;
	private VBox box1;
	private VBox box2;
	private VBox box3;
	private VBox box4;
	private VBox box5;
	private VBox box6;
	private VBox box7;
	private VBox box8;
	private VBox box9;
	private VBox box10;
	private VBox box11;
	private VBox box12;
	
	/**
     * Constructor for AchievementsPane.
     * Initializes the pane with components to display achievements.
     */
	public AchievementsPane() {
		Label title = new Label("Achievements");
		title.setFont(Font.font("Calibri", FontWeight.EXTRA_BOLD, 40));
		
		grid = new GridPane();	
		grid.setHgap(10); 
		grid.setVgap(10); 
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setAlignment(Pos.CENTER);

		box1 =  new VBox(5);
		Label lock1 = new Label("🔒");
		lock1.setFont(Font.font("Calibri", FontWeight.NORMAL, 40));
		Label caption1 = new Label("First Win");
		caption1.setFont(Font.font("Calibri", FontWeight.EXTRA_BOLD, 10));
		box1.setAlignment(Pos.CENTER);
		box1.getChildren().addAll(lock1, caption1);
		grid.add(box1, 0, 0); 
		
		box2 =  new VBox(5);
		Label lock2 = new Label("🔒");
		lock2.setFont(Font.font("Calibri", FontWeight.NORMAL, 40));
		Label caption2 = new Label("10 Game Win Streak");
		caption2.setFont(Font.font("Calibri", FontWeight.EXTRA_BOLD, 10));
		box2.setAlignment(Pos.CENTER);
		box2.getChildren().addAll(lock2, caption2);
		grid.add(box2, 1, 0); 
		
		box3 =  new VBox(5);
		Label lock3 = new Label("🔒");
		lock3.setFont(Font.font("Calibri", FontWeight.NORMAL, 40));
		Label caption3 = new Label("100 Game Win Streak");
		caption3.setFont(Font.font("Calibri", FontWeight.EXTRA_BOLD,10));
		box3.setAlignment(Pos.CENTER);
		box3.getChildren().addAll(lock3, caption3);
		grid.add(box3, 2, 0); 
		
		box4 =  new VBox(5);
		Label lock4 = new Label("🔒");
		lock4.setFont(Font.font("Calibri", FontWeight.NORMAL, 40));
		Label caption4 = new Label("Perfect Game");
		caption4.setFont(Font.font("Calibri", FontWeight.EXTRA_BOLD, 10));
		box4.setAlignment(Pos.CENTER);
		box4.getChildren().addAll(lock4, caption4);
		grid.add(box4, 0, 1); 
		
		box5 =  new VBox(5);
		Label lock5 = new Label("🔒");
		lock5.setFont(Font.font("Calibri", FontWeight.NORMAL, 40));
		Label caption5 = new Label("Scraping By");
		caption5.setFont(Font.font("Calibri", FontWeight.EXTRA_BOLD, 10));
		box5.setAlignment(Pos.CENTER);
		box5.getChildren().addAll(lock5, caption5);
		grid.add(box5, 1, 1); 
		
		box6 =  new VBox(5);
		Label lock6 = new Label("🔒");
		lock6.setFont(Font.font("Calibri", FontWeight.NORMAL, 40));
		Label caption6 = new Label("You Suck!");
		caption6.setFont(Font.font("Calibri", FontWeight.EXTRA_BOLD, 10));
		box6.setAlignment(Pos.CENTER);
		box6.getChildren().addAll(lock6, caption6);
		grid.add(box6, 2, 1); 
		
		box7 =  new VBox(5);
		Label lock7 = new Label("🔒");
		lock7.setFont(Font.font("Calibri", FontWeight.NORMAL, 40));
		Label caption7 = new Label("Played Wordle");
		caption7.setFont(Font.font("Calibri", FontWeight.EXTRA_BOLD, 10));
		box7.setAlignment(Pos.CENTER);
		box7.getChildren().addAll(lock7, caption7);
		grid.add(box7, 0, 2); 
		
		box8 =  new VBox(5);
		Label lock8 = new Label("🔒");
		lock8.setFont(Font.font("Calibri", FontWeight.NORMAL, 40));
		Label caption8 = new Label("Played Unwordle");
		caption8.setFont(Font.font("Calibri", FontWeight.EXTRA_BOLD, 10));
		box8.setAlignment(Pos.CENTER);
		box8.getChildren().addAll(lock8, caption8);
		grid.add(box8, 1, 2); 		

		box9 =  new VBox(5);
		Label lock9 = new Label("🔒");
		lock9.setFont(Font.font("Calibri", FontWeight.NORMAL, 40));
		Label caption9 = new Label("Played Speed");
		caption9.setFont(Font.font("Calibri", FontWeight.EXTRA_BOLD, 10));
		box9.setAlignment(Pos.CENTER);
		box9.getChildren().addAll(lock9, caption9);
		grid.add(box9, 2, 2); 
		
		box10 =  new VBox(5);
		Label lock10 = new Label("🔒");
		lock10.setFont(Font.font("Calibri", FontWeight.NORMAL, 40));
		Label caption10 = new Label("10 Speedle");
		caption10.setFont(Font.font("Calibri", FontWeight.EXTRA_BOLD, 10));
		box10.setAlignment(Pos.CENTER);
		box10.getChildren().addAll(lock10, caption10);
		grid.add(box10, 0, 3); 
		
		box11 = new VBox(5);
		Label lock11 = new Label("🔒");
		lock11.setFont(Font.font("Calibri", FontWeight.NORMAL, 40));
		Label caption11 = new Label("35 Speedle");
		caption11.setFont(Font.font("Calibri", FontWeight.EXTRA_BOLD, 10));
		box11.setAlignment(Pos.CENTER);
		box11.getChildren().addAll(lock11, caption11);
		grid.add(box11, 1, 3); 
		
		box12 = new VBox(5);
		Label lock12 = new Label("🔒");
		lock12.setFont(Font.font("Calibri", FontWeight.NORMAL, 40));
		Label caption12 = new Label("60 Speed racer");
		caption12.setFont(Font.font("Calibri", FontWeight.EXTRA_BOLD, 10));
		box12.setAlignment(Pos.CENTER);
		box12.getChildren().addAll(lock12, caption12);
		grid.add(box12, 2, 3); 
		
		this.setSpacing(10);
		this.getChildren().addAll(title, grid);
		this.setAlignment(Pos.CENTER);
	}
	
	/**
	 * updates the achievement object and pane when called.
	 * 
	 * @param achievements the active account's achievement object.
	 */
	public void resetLayout(Achievements achievements) {
		Node temp = null;
		
		for(Node child: box1.getChildren()) {
			String text = ((Labeled) child).getText();
			if(text.equals("🔒")) {
				temp = child;
			}
			if((!text.equals("🔒") || !text.equals("🏆")) && achievements.checkAchievement(text)) {
				((Labeled) temp).setText("🏆");
			}
		}
		
		for(Node child: box2.getChildren()) {
			String text = ((Labeled) child).getText();
			if(text.equals("🔒")) {
				temp = child;
			}
			if((!text.equals("🔒") || !text.equals("🏆")) && achievements.checkAchievement(text)) {
				((Labeled) temp).setText("🏆");
			}
		}
		
		for(Node child: box3.getChildren()) {
			String text = ((Labeled) child).getText();
			if(text.equals("🔒")) {
				temp = child;
			}
			if((!text.equals("🔒") || !text.equals("🏆")) && achievements.checkAchievement(text)) {
				((Labeled) temp).setText("🏆");
			}
		}
		
		for(Node child: box4.getChildren()) {
			String text = ((Labeled) child).getText();
			if(text.equals("🔒")) {
				temp = child;
			}
			if((!text.equals("🔒") || !text.equals("🏆")) && achievements.checkAchievement(text)) {
				((Labeled) temp).setText("🏆");
			}
		}
		
		for(Node child: box5.getChildren()) {
			String text = ((Labeled) child).getText();
			if(text.equals("🔒")) {
				temp = child;
			}
			if((!text.equals("🔒") || !text.equals("☹")) && achievements.checkAchievement(text)) {
				((Labeled) temp).setText("☹");
			}
		}
		
		for(Node child: box6.getChildren()) {
			String text = ((Labeled) child).getText();
			if(text.equals("🔒")) {
				temp = child;
			}
			if((!text.equals("🔒") || !text.equals("🏆")) && achievements.checkAchievement(text)) {
				((Labeled) temp).setText("🏆");
			}
		}
		
		for(Node child: box7.getChildren()) {
			String text = ((Labeled) child).getText();
			if(text.equals("🔒")) {
				temp = child;
			}
			if((!text.equals("🔒") || !text.equals("🏆")) && achievements.checkAchievement(text)) {
				((Labeled) temp).setText("🏆");
			}
		}
		
		for(Node child: box8.getChildren()) {
			String text = ((Labeled) child).getText();
			if(text.equals("🔒")) {
				temp = child;
			}
			if((!text.equals("🔒") || !text.equals("🏆")) && achievements.checkAchievement(text)) {
				((Labeled) temp).setText("🏆");
			}
		}
		
		for(Node child: box9.getChildren()) {
			String text = ((Labeled) child).getText();
			if(text.equals("🔒")) {
				temp = child;
			}
			if((!text.equals("🔒") || !text.equals("🏆")) && achievements.checkAchievement(text)) {
				((Labeled) temp).setText("🏆");
			}
		}
		
		for(Node child: box10.getChildren()) {
			String text = ((Labeled) child).getText();
			if(text.equals("🔒")) {
				temp = child;
			}
			if((!text.equals("🔒") || !text.equals("🏆")) && achievements.checkAchievement(text)) {
				((Labeled) temp).setText("🏆");
			}
		}
		
		for(Node child: box11.getChildren()) {
			String text = ((Labeled) child).getText();
			if(text.equals("🔒")) {
				temp = child;
			}
			if((!text.equals("🔒") || !text.equals("🏆")) && achievements.checkAchievement(text)) {
				((Labeled) temp).setText("🏆");
			}
		}
		
		for(Node child: box12.getChildren()) {
			String text = ((Labeled) child).getText();
			if(text.equals("🔒")) {
				temp = child;
			}
			if((!text.equals("🔒") || !text.equals("🏎")) && achievements.checkAchievement(text)) {
				((Labeled) temp).setText("🏎");
			}
		}
	}

}
