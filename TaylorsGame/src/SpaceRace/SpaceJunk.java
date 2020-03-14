package SpaceRace;

import java.awt.Graphics;
import java.awt.Rectangle;

import gameMain.*;

public class SpaceJunk extends GameObject{
	
	private int projectileSpeed;
	private Game game;
	private double projectileAcceleration;
	private int radius = 8;
	private double tracker = 0;
	private boolean rightToLeft = false;
	
	public SpaceJunk(Game game, int x, int y, int projectileSpeed, double projectileAcceleration) {
		super(game, x, y);
		this.game = game;
		img = Game.IM.getImage("/asteroid.png");
		this.projectileSpeed = projectileSpeed;
		this.projectileAcceleration = Math.pow(projectileAcceleration, 3);
		if (x > 20) rightToLeft = true;
	}

	public void render(Graphics g) {
		g.drawImage(img, x, y, 2*radius-1, 2*radius-1, null);
	}

	public void tick() {
		if (rightToLeft) {
			x -= projectileSpeed;
			if (x < 0) game.removeGameObject(this);
		} else {
			x += projectileSpeed;
			if (x > Game.WIDTH) game.removeGameObject(this);
		}
		tracker += projectileAcceleration;
		if (tracker >= 1.5) {
			projectileSpeed++; 
			tracker = 0;
		}
		
		if (getBounds().intersects(game.spaceRaceGame.playerOne.getBounds())) game.spaceRaceGame.playerOne.kill();
		if (getBounds().intersects(game.spaceRaceGame.playerTwo.getBounds())) game.spaceRaceGame.playerTwo.kill();
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x+1, y+1, 2*radius-1, 2*radius-1);
	}

}
