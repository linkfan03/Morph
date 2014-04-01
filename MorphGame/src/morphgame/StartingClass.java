package morphgame;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class StartingClass extends Applet implements Runnable, KeyListener {
	enum GameState {
		Running, Dead
	}

	GameState state = GameState.Running;

	private static MainCharacter mainCharacter;
	public static int score = 0;
	private Font font = new Font(null, Font.BOLD, 30);
	private Image image, character, characterDown, characterJumped,
			characterForward, characterBack, currentSprite, background;
	private Graphics second;
	private URL base;
	private static Background bg1, bg2;
	public static Image tilegrassTop, tilegrassMorph, tilegrassLeft,
			tilegrassRight, tiledirt;

	private ArrayList<Tile> tilearray = new ArrayList<Tile>();

	@Override
	public void init() {

		setSize(800, 480);
		setFocusable(true);
		setBackground(Color.BLACK);
		addKeyListener(this);
		Frame frame = (Frame) this.getParent().getParent();
		frame.setTitle("Morph");
		try {
			base = getDocumentBase();
		} catch (Exception e) {
			// TODO: handle exception
		}

		// Image Setups
		character = getImage(base, "data/square.png");
		characterDown = getImage(base, "data/squareDown.png");
		characterJumped = getImage(base, "data/squareJump.png");
		characterForward = getImage(base, "data/squareForward.png");
		characterBack = getImage(base, "data/squareBack.png");

		currentSprite = character;
		background = getImage(base, "data/backgroundMockUp1.png");

		tiledirt = getImage(base, "data/tiledirt.png");
		tilegrassTop = getImage(base, "data/tilegrasstop.png");
		tilegrassMorph = getImage(base, "data/tilegrassbot.png");
		tilegrassLeft = getImage(base, "data/tilegrassleft.png");
		tilegrassRight = getImage(base, "data/tilegrassright.png");
	}

	@Override
	public void start() {
		bg1 = new Background(0, 0);
		bg2 = new Background(2160, 0);
		mainCharacter = new MainCharacter();
		// Initialize Tiles
		try {
			loadMap("data/map1.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Thread thread = new Thread(this);
		thread.start();
	}

	private void loadMap(String filename) throws IOException {
		ArrayList lines = new ArrayList();
		int width = 0;
		int height = 0;

		BufferedReader reader = new BufferedReader(new FileReader(filename));
		while (true) {
			String line = reader.readLine();
			// no more lines to read
			if (line == null) {
				reader.close();
				break;
			}

			if (!line.startsWith("!")) {
				lines.add(line);
				width = Math.max(width, line.length());

			}
		}
		height = lines.size();

		for (int j = 0; j < 12; j++) {
			String line = (String) lines.get(j);
			for (int i = 0; i < width; i++) {

				if (i < line.length()) {
					char ch = line.charAt(i);
					Tile t = new Tile(i, j, Character.getNumericValue(ch));
					tilearray.add(t);
				}

			}
		}

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
		if (state == GameState.Running) {

			while (true) {
				mainCharacter.update();
				if (mainCharacter.isMovingLeft()) {
					currentSprite = characterBack;
				} else if (mainCharacter.isMovingRight()) {
					currentSprite = characterForward;
				} else if (mainCharacter.isJumped()) {
					currentSprite = characterJumped;
				} else if (mainCharacter.isJumped() == false
						&& mainCharacter.isDucked() == false) {
					currentSprite = character;
				}
				updateTiles();
				bg1.update();
				bg2.update();
				repaint();
				try {
					Thread.sleep(17);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (mainCharacter.getCenterY() > 500) {
					state = GameState.Dead;
				}
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
		if (state == GameState.Running) {

			g.drawImage(background, bg1.getBgX(), bg1.getBgY(), this);
			g.drawImage(background, bg2.getBgX(), bg2.getBgY(), this);
			paintTiles(g);

			g.drawImage(currentSprite, mainCharacter.getCenterX() - 61,
					mainCharacter.getCenterY() - 63, this);

			g.setFont(font);
			g.setColor(Color.WHITE);
			g.drawString(Integer.toString(score), 740, 30);

		} else if (state == GameState.Dead) {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, 800, 480);
			g.setColor(Color.WHITE);
			g.drawString("Dead", 360, 240);

		}
	}

	private void updateTiles() {

		for (int i = 0; i < tilearray.size(); i++) {
			Tile t = (Tile) tilearray.get(i);
			t.update();
		}

	}

	private void paintTiles(Graphics g) {
		for (int i = 0; i < tilearray.size(); i++) {
			Tile t = (Tile) tilearray.get(i);
			g.drawImage(t.getTileImage(), t.getTileX(), t.getTileY(), this);
		}
	}

	public void keyPressed(KeyEvent e) {

		switch (e.getKeyCode()) {

		case KeyEvent.VK_DOWN:
			currentSprite = characterDown;
			if (mainCharacter.isJumped() == false) {
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

	public static MainCharacter getMainCharacter() {
		return mainCharacter;
	}

}
