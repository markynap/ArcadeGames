package SpaceRace;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import gameMain.*;

public class Timer {

	private SpaceRaceGame spaceGame;
	private int time;
	private int tempTime = 0;
	private int maxTime;
	
	public Timer(SpaceRaceGame spaceGame, int maxTime) {
		this.spaceGame = spaceGame;
		time = 0;
		this.maxTime = maxTime;
	}
	
	
	public void drawTime(Graphics g) {
		g.setColor(Color.white);
		g.drawLine(Game.WIDTH/2, time, Game.WIDTH/2, Game.HEIGHT+30);
		tempTime++;
		if (tempTime >= maxTime / 2) {
			time++;
			tempTime = 0;
		}
		if (time > maxTime) {
			spaceGame.gameOver();
		}
		g.setFont(new Font("Times New Roman", Font.BOLD, 20));
		g.drawString("Time: " + time, Game.WIDTH/2 - 30, 30);
	}
}
