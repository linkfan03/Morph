package morphgame;

import java.awt.Color;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import morphgame.StartingClass.GameState;


public class Menu {
	//TODO Add code for try again
	//private static Rectangle tryAgainButton = new Rectangle(StartingClass.getWindowWidth()/2 - 65, 205, 155, 50);
	private static Rectangle playButton = new Rectangle(StartingClass.getWindowWidth()/2 - 25, 205, 80, 50);
	private static Rectangle quitButton = new Rectangle(StartingClass.getWindowWidth()/2 - 25, 285, 80, 50);
	private static Rectangle backButton = new Rectangle(StartingClass.getWindowWidth() / 2 - 25, 200, 80, 50);
	private static Rectangle instructButton = new Rectangle(StartingClass.getWindowWidth() / 2 - 25, 365, 190, 50);
	
	public void update(Graphics g, GameState state){
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, StartingClass.getWindowWidth(), StartingClass.getWindowHeight());
		g.setColor(Color.WHITE);
		Graphics2D g2d = (Graphics2D)g;
		if(state == GameState.Dead){
			g.drawString("Dead", StartingClass.getWindowWidth()/2 - 25, 160);
			//g.drawString("Try Again", tryAgainButton.x + 10, tryAgainButton.y + 35);
			//g2d.draw(tryAgainButton);
		}
		else if (state == GameState.MainMenu) {
			g.drawString("Morph", StartingClass.getWindowWidth() / 2 - 30, 160);
			g.drawString("Play", playButton.x + 10, playButton.y + 35);
			g2d.draw(playButton);
			g.drawString("Instructions", instructButton.x + 10, instructButton.y + 45);
			g2d.draw(instructButton);
			
		} 
		else if (state == GameState.InstructMenu){
			g.drawString("How to Play:", StartingClass.getWindowWidth() / 2 - 400, 60);
			g.drawString("Use the Right and Left Arrow Keys To Move", StartingClass.getWindowWidth() / 2 - 400, 100);
			g.drawString("Press the space bar to jump", StartingClass.getWindowWidth() / 2 - 400, 140);
			g.drawString("Back", backButton.x + 10, backButton.y + 35);
			g2d.draw(backButton);
		}
		g.drawString("Quit", quitButton.x + 10, quitButton.y + 35);
		g2d.draw(quitButton);
	}
	
//	public static Rectangle getTryAgainButton(){
//		return tryAgainButton;
//	}
	
	public static Rectangle getPlayButton(){
		return playButton;
	}
	
	public static Rectangle getQuitButton(){
		return quitButton;
	}
	
	public static Rectangle getBackButton(){
		return backButton;
	}
	
	public static Rectangle getInstructButton(){
		return instructButton;
	}
}
