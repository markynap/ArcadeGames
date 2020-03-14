package gameMain;

import java.awt.Graphics;
import java.awt.Image;

import gameMain.Game;

public abstract class GameObject {

	protected int x, y;
	protected Image img;
	private Game game;
	
	public GameObject(Game game, int x, int y) {
		this.game = game;
		this.x = x;
		this.y = y;
		game.addGameObject(this);
	}
	
	public abstract void render(Graphics g);
	public abstract void tick();
	
	/**
	 * Moves object ahead by x and y amounts
	 * @param xAmount
	 * @param yAmount
	 */
	public void moveObject(int xAmount, int yAmount) {
		this.x += xAmount;
		this.y += yAmount;
	}
	
	/** Sets the x and y values*/
	public void setXandY(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
