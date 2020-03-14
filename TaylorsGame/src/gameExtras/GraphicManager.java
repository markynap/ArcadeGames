package gameExtras;

import java.awt.Color;
import java.awt.Graphics;

import gameMain.Game;

public class GraphicManager {

	private Game game;
	
	public GraphicManager(Game game) {
		this.game = game;
	}
	
	public void renderBoxOutline(Graphics g, int x, int y, int width, int height, int thickness, Color lineColor) {
		g.setColor(lineColor);
		for (int i = 0; i < thickness; i++) {
			g.drawRect(x + i, y + i, width, height);
		}
	}
	
	public void renderBoxFilled(Graphics g, int x, int y, int width, int height, int thickness, Color lineColor, Color fillColor) {
		renderBoxOutline(g, x, y, width, height, thickness, lineColor);
		g.setColor(fillColor);
		g.fillRect(x + thickness, y + thickness, width - thickness, height - thickness);
	}
}
