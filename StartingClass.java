package kiloboltgame;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;

public class StartingClass extends Applet implements Runnable, KeyListener {
	
	private MainCharacter mainCharacter;
	private Image image, character,characterDown,characterJumped,currentSprite, background;
	private Graphics second;
	private URL base;
	private static Background bg1,bg2;
	
	@Override
	public void init() {

		setSize(800, 480);
		setBackground(Color.BLACK);
		setFocusable(true);
		addKeyListener(this);
		Frame frame = (Frame) this.getParent().getParent();
		frame.setTitle("Morph");
		try {
			base = getDocumentBase();
			System.out.println(getDocumentBase());
		} catch (Exception e) {
			// TODO: handle exception
		}

		// Image Setups
		character = getImage(base, "data/character.png");
		characterDown = getImage(base, "data/down.png");
		characterJumped = getImage(base, "data/jump.png");
		currentSprite = character;
		background = getImage(base, "data/background.png");
	}

	@Override
	public void start() {
		bg1 = new Background(0, 0);
		bg2 = new Background(2160, 0);
		mainCharacter = new MainCharacter();
		Thread thread = new Thread(this);
		thread.start();
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	@Override
	public void run() {
		while (true) {
			mainCharacter.update();
			if (mainCharacter.isJumped()){
				currentSprite = characterJumped;
			}else if (mainCharacter.isJumped() == false && mainCharacter.isDucked() == false){
				currentSprite = character;
			}
			bg1.update();
			bg2.update();
			repaint();
			try {
				Thread.sleep(17);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void update(Graphics g) {
		if (image == null) {
			image = createImage(this.getWidth(), this.getHeight());
			second = image.getGraphics();
		}

		second.setColor(getBackground());
		second.fillRect(0, 0, getWidth(), getHeight());
		second.setColor(getForeground());
		paint(second);

		g.drawImage(image, 0, 0, this);

	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(background, bg1.getBgX(), bg1.getBgY(), this);
		g.drawImage(background, bg2.getBgX(), bg2.getBgY(), this);
		g.drawImage(currentSprite, mainCharacter.getCenterX() - 61, mainCharacter.getCenterY() - 63, this);
	}
	
	public void keyPressed(KeyEvent e) {
	
        switch (e.getKeyCode()) {
        case KeyEvent.VK_UP:
            System.out.println("Move up");
            break;

        case KeyEvent.VK_DOWN:
            currentSprite = characterDown;
            if (mainCharacter.isJumped() == false){
                mainCharacter.setDucked(true);
                mainCharacter.setSpeedX(0);
            }
            break;

        case KeyEvent.VK_LEFT:
            mainCharacter.moveLeft();
            mainCharacter.setMovingLeft(true);
            break;

        case KeyEvent.VK_RIGHT:
            mainCharacter.moveRight();
            mainCharacter.setMovingRight(true);
            break;

        case KeyEvent.VK_SPACE:
            mainCharacter.jump();
            break;

        }

    }

	    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
        case KeyEvent.VK_UP:
            System.out.println("Stop moving up");
            break;

        case KeyEvent.VK_DOWN:
            currentSprite = character;
            mainCharacter.setDucked(false);
            break;

        case KeyEvent.VK_LEFT:
            mainCharacter.stopLeft();
            break;

        case KeyEvent.VK_RIGHT:
            mainCharacter.stopRight();
            break;

        case KeyEvent.VK_SPACE:
            break;

        }

    }

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}
	
	public static Background getBg1() {
        return bg1;
    }

    public static Background getBg2() {
        return bg2;
    }

}