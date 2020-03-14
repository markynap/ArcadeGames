package SpaceRace;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

import gameMain.*;

public class Racer extends GameObject {

	private final int imgHeight = 75, imgWidth = 40;
	private final int MAXVELOCITY = 3;
	private int	currentVelocity = 0;
	private boolean accelerating = false, decelerating = false;
	private int score = 0;
	private int startScoreX = Game.WIDTH/5;
	private int playerNumber;
	private Color scoreColor;
	
	public Racer(Game game, int x, int y, int playerNumber) {
		super(game, x, y);
		this.playerNumber = playerNumber;
		if (playerNumber == 1) {
			scoreColor = Color.BLUE;
			img = Game.IM.getImage("/racer1.png");
		} else {
			img = Game.IM.getImage("/racer2.png");
			scoreColor = Color.RED;
		}
		
	}

	public void render(Graphics g) {
		g.drawImage(img, x, y, imgHeight, imgWidth, null);
		g.setColor(scoreColor);
		g.setFont(new Font("Times New Roman", Font.ITALIC, 28));
		g.drawString("Score: " + score, startScoreX + (Game.WIDTH/2)*(playerNumber-1), 50);
	}

	public void tick() {
		if (accelerating) accelerate();
		else if (decelerating) decelerate();
		y -= currentVelocity;
		
		if (y <= -30) {
			score++;
			y = SpaceRaceGame.yStart;
		}
	}
	
	public void setAcceleration(boolean trueForUp) {
		if (trueForUp) {
			decelerating = false;
			accelerating = true;
		} else {
			accelerating = false;
			decelerating = true;
		}
	}
	
	public void stop() {
		currentVelocity = 0;
		accelerating = false;
		decelerating = false;
	}
	
	private int decelerate() {
		if (currentVelocity <= ((-1) * MAXVELOCITY)) return -1 * MAXVELOCITY;
		return currentVelocity--;
	}
	
	private int accelerate() {
		if (currentVelocity >= MAXVELOCITY) return MAXVELOCITY;
		return currentVelocity++;
	}

	public Rectangle getBounds() {
		return new Rectangle(x+1, y+1, imgWidth-1, imgHeight-1);
	}
	
	public void kill() {
		y = SpaceRaceGame.yStart;
		stop();
	}
	
}
