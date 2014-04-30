package morphgame;

import java.applet.AudioClip;
import java.awt.Color;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import morphgame.StartingClass.GameState;

public class Menu {
	//TODO Add code for try again
	//private static Rectangle tryAgainButton = new Rectangle(StartingClass.getWindowWidth()/2 - 65, 205, 155, 50);
	private static Rectangle submitButton = new Rectangle(StartingClass.getWindowWidth()/2 - 130, 128, 350, 50);
	private static Rectangle playButton = new Rectangle(StartingClass.getWindowWidth()/2 - 85, 180, 200, 50);
	private static Rectangle quitButton = new Rectangle(StartingClass.getWindowWidth()/2 - 85, 230, 200, 50);
	private static Rectangle scoreButton = new Rectangle(StartingClass.getWindowWidth()/2 - 85, 280, 200, 50);
	
	private static Rectangle backButton = new Rectangle(StartingClass.getWindowWidth() / 2 - 85, 400, 200, 50);
	private static Rectangle instructButton = new Rectangle(StartingClass.getWindowWidth() / 2 - 85, 330, 200, 50);  
  
	private String name;
	private String score;
	
	private LeaderBoard leaderboard;
	
	public Menu(LeaderBoard l){
		leaderboard = l;
		
	}
	
	public void update(Graphics g, GameState state){
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, StartingClass.getWindowWidth(), StartingClass.getWindowHeight());
		g.setColor(Color.WHITE);
		Graphics2D g2d = (Graphics2D)g;
		
		
		if(state == GameState.Dead){
			score = leaderboard.getScore().toString();
			g.drawString("Submit Score: " + score, submitButton.x+5 , submitButton.y+35);
			g2d.draw(submitButton);
			g.drawString("Quit", quitButton.x + 60, quitButton.y + 35);
			g2d.draw(quitButton);
			
			g.drawString("Top Scores",scoreButton.x + 10, scoreButton.y + 35);
			g2d.draw(scoreButton);
		
			g.drawString("Try Again", playButton.x + 10, playButton.y + 35);
			g2d.draw(playButton);
		
		}

		else if(state == GameState.MainMenu){
			g.drawString("Morph", StartingClass.getWindowWidth()/2 - 30, 160);
			g.drawString("Play", playButton.x + 60, playButton.y + 35);
			g2d.draw(playButton);

			g.drawString("Instructions", instructButton.x + 10, instructButton.y + 45);                                           
                        g2d.draw(instructButton);             
			
			g.drawString("Quit", quitButton.x + 60, quitButton.y + 35);
			g2d.draw(quitButton);
			
			g.drawString("Top Scores",scoreButton.x + 10, scoreButton.y + 35);
			g2d.draw(scoreButton);
			
		}else if(state == GameState.Leaderboard){
			//displays top ten in leaderboard
			drawString(g,leaderboard.getScores(10),StartingClass.getWindowWidth()/2 - 125,0);
			g.drawString("Back", backButton.x + 60, backButton.y + 35);
			g2d.draw(backButton);
		}else if (state == GameState.InstructMenu){
			g.drawString("How to Play:", StartingClass.getWindowWidth() / 2 - 400, 60);
			g.drawString("Use the Right and Left Arrow Keys To Move", StartingClass.getWindowWidth() / 2 - 400, 100);
			g.drawString("Press the space bar to jump", StartingClass.getWindowWidth() / 2 - 400, 140);
			g.drawString("Back", backButton.x + 60, backButton.y + 35);
			g2d.draw(backButton);
		}
		
	}
	
	//my drawString method to detect newline characters and put in a newline
	 private void drawString(Graphics g, String text, int x, int y) {
         for (String line : text.split("\n"))
             g.drawString(line, x, y += g.getFontMetrics().getHeight());
     }
	 public void submitScore(){
		 // methods for input text in java applets and android are 
		 //very different so I am not going to bother implementing it in java
		 //-Greg 
		 
		 //this is where the code for the input text popup will be called
		 
		 name = "Some_Name";
		 leaderboard.insertScore(name);
	 }
	
//	public static Rectangle getTryAgainButton(){
//		return tryAgainButton;
//	}
	
	/*
	public static String getName(){
		return name;
	}
	*/
	public static Rectangle getPlayButton(){
		return playButton;
	}
	
	public static Rectangle getQuitButton(){
		return quitButton;
	}

	public static Rectangle getScoreButton() {
		return scoreButton;
	}

	public static Rectangle getSubmitButton() {
		return submitButton;
	}
	
	public static Rectangle getBackButton(){
		return backButton;
	}
	
	public static Rectangle getInstructButton(){
		return instructButton;
	}
}
