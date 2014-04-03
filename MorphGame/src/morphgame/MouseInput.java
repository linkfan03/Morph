package morphgame;

import java.net.URL;

import java.awt.Rectangle;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import morphgame.StartingClass.GameState;

public class MouseInput implements MouseListener {
	
	private URL base;

	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		int mouseX = e.getX();
		int mouseY = e.getY();
		GameState state = StartingClass.getState();
		if(state == GameState.MainMenu){
			if(isButtonClicked(Menu.getPlayButton(), mouseX, mouseY)){
				//Clicked the play button
				StartingClass.setState(GameState.Running);
			}
		}
		if(state == GameState.MainMenu || state == GameState.Dead){
			if(isButtonClicked(Menu.getQuitButton(), mouseX, mouseY)){
				System.exit(1);
			}
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	private boolean isButtonClicked(Rectangle button, int mouseX, int mouseY){
		if(mouseX >= button.x && mouseX <= button.x + button.width && mouseY >= button.y && mouseY <= button.y + button.height){
			return true;
		}
		return false;
	}

}
