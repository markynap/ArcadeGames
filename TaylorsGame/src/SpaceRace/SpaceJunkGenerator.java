package SpaceRace;

import java.util.Random;

import gameMain.*;

public class SpaceJunkGenerator implements Runnable{

	private Game game;
	private Random r;
	/** The higher this number the less frequently junk spawns */
	private int spawnRate = 45;
	private int maxProjectileSpeed = 5;
	private int minProjectileSpeed = 1;
	private int rng;
	private boolean running;
	
	public SpaceJunkGenerator(Game game) {
		this.game = game;
		r = new Random();
		running = true;
	}
	
	@Override
	public void run() {
		running = true;
		while (running) {
			rng = r.nextInt(spawnRate);
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (rng == 1) {
				//spawn new junk
				int sideRNG = r.nextInt(2);
				int heightRNG = r.nextInt(Game.HEIGHT - 200);
				int projectileSpeed = r.nextInt(maxProjectileSpeed - minProjectileSpeed) + minProjectileSpeed;
				double acceleration = Math.pow(Math.abs(r.nextDouble()), 3)/2;
				new SpaceJunk(game, Game.WIDTH*sideRNG, heightRNG, projectileSpeed, acceleration);
			}
			rng = r.nextInt(spawnRate);
		}
		
		
	}
	
	public void join() {
		running = false;
	}
	
}
