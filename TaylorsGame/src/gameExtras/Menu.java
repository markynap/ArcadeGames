package gameExtras;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import gameMain.Game;

public class Menu {

	private Game game;
	private int boxW, boxH, boxT, numGames, startX, startY, distBetween;
	/**The current Game Choice we are on */
	private int selectedIndex;
	private Color boxColor = Color.PINK;
	private static final String[] gameNames = {"Falling Circles", "Space Race", "Game 3", "Game 4", "Game 5", "Game 6"}; 
	
	
	public Menu(Game game) {
		this.game = game;
		boxW = 175;
		boxH = 125;
		boxT = 5;
		numGames = 6;
		startX = 15 * Game.UNIT;
		startY = Game.HEIGHT/3 - (5*Game.UNIT);
		distBetween = 20 * Game.UNIT;
	}
	
	public void render(Graphics g) {
		g.setColor(Color.DARK_GRAY);
		g.setFont(new Font("Times New Roman", Font.BOLD, 30));
		g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
		g.setColor(Color.pink);
		g.drawString("Welcome To Taylor's Arcade!",23* Game.UNIT, 9*Game.UNIT);		
		g.setFont(new Font("Times New Roman", Font.BOLD, 22));
		for (int i = 0; i < numGames; i++) {
			game.GM.renderBoxFilled(g, startX + (i%3)*distBetween, startY + (i/3)*distBetween, boxW, boxH, boxT, Color.black, boxColor);
			g.setColor(Color.black);
			g.drawString(gameNames[i], startX + (i%3)*distBetween + (boxW/3 - 2*Game.UNIT), startY + (i/3)*distBetween + boxH/2 + Game.UNIT/2);
			game.GM.renderBoxOutline(g, startX + (selectedIndex%3)*distBetween, startY + (selectedIndex/3)*distBetween, boxW, boxH, boxT + 1, Color.blue);
		}
		
	}
	
	public int selectedIndex() {
		return selectedIndex;
	}
	
	public void incSelectedIndex(int val) {
		selectedIndex += val;
		if (selectedIndex < 0) selectedIndex = numGames -1;
		if (selectedIndex >= numGames) selectedIndex = 0;
	}
}
