package SpaceRace;

import gameMain.Game;

public class SpaceRaceGame {

	private Game game;
	public Racer playerOne, playerTwo;
	public static final int yStart = Game.HEIGHT - 75;
	public Timer timer;
	
	public SpaceRaceGame(Game game) {
		this.game = game;
		playerOne = new Racer(game, Game.WIDTH/5, yStart, 1);
		playerTwo = new Racer(game, Game.WIDTH - 250, yStart, 2);
		timer = new Timer(this, 120);
	}
	
	public void gameOver() {
		
	}

}
