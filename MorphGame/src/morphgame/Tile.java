package morphgame;

import java.awt.Image;
import java.awt.Rectangle;

public class Tile {

	private int tileX, tileY, speedX, type;
	public Image tileImage;

	private MainCharacter maincharacter = StartingClass.getMainCharacter();
	private Background bg = StartingClass.getBg1();

	private Rectangle r;

	public Tile(int x, int y, int typeInt) {
		tileX = x * 40;
		tileY = y * 40;

		type = typeInt;

		r = new Rectangle();

		if (type == 5) {
			tileImage = StartingClass.tiledirt;
		} else if (type == 8) {
			tileImage = StartingClass.tilegrassTop;
		} else if (type == 4) {
			tileImage = StartingClass.tilegrassLeft;

		} else if (type == 6) {
			tileImage = StartingClass.tilegrassRight;

		} else if (type == 2) {
			tileImage = StartingClass.tilegrassMorph;
		} else {
			type = 0;
		}

	}

	public void update() {
		speedX = bg.getSpeedX() * 5;
		tileX += speedX;
		r.setBounds(tileX, tileY, 40, 40);

		if (r.intersects(MainCharacter.yellowRed) && type != 0) {
			checkVerticalCollision(MainCharacter.rect, MainCharacter.rect2);
			checkSideCollision(MainCharacter.rect3, MainCharacter.rect4,
					MainCharacter.footleft, MainCharacter.footright);
		}

	}

	public int getTileX() {
		return tileX;
	}

	public void setTileX(int tileX) {
		this.tileX = tileX;
	}

	public int getTileY() {
		return tileY;
	}

	public void setTileY(int tileY) {
		this.tileY = tileY;
	}

	public Image getTileImage() {
		return tileImage;
	}

	public void setTileImage(Image tileImage) {
		this.tileImage = tileImage;
	}
	public void checkVerticalCollision(Rectangle rtop, Rectangle rbot) {
		if (rtop.intersects(r)) {
			
		}

		if (rbot.intersects(r) && type == 8) {
			maincharacter.setJumped(false);
			maincharacter.setSpeedY(0);
			maincharacter.setCenterY(tileY - 63);
		}
	}

	public void checkSideCollision(Rectangle rleft, Rectangle rright, Rectangle leftfoot, Rectangle rightfoot) {
		if (type != 5 && type != 2 && type != 0){
			if (rleft.intersects(r)) {
				maincharacter.setCenterX(tileX + 102);
	
				maincharacter.setSpeedX(0);
	
			}else if (leftfoot.intersects(r)) {
				maincharacter.setCenterX(tileX + 85);
				maincharacter.setSpeedX(0);
			}
			
			if (rright.intersects(r)) {
				maincharacter.setCenterX(tileX - 62);
	
				maincharacter.setSpeedX(0);
			}
			
			else if (rightfoot.intersects(r)) {
				maincharacter.setCenterX(tileX - 45);
				maincharacter.setSpeedX(0);
			}
		}
	}
}
