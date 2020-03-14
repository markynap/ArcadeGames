package gameMain;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import FallingCircles.FallingCirclesGame;
import SpaceRace.*;
import gameExtras.*;
import threads.*;

public class Game extends Canvas implements Runnable{
	/** Standard Game Startup variables */
	private static final long serialVersionUID = 12345L;
	public final static int WIDTH = 900;
	public final static int HEIGHT = WIDTH/12 * 9;
	public final static int UNIT = ((WIDTH + HEIGHT)/2)/75; //10 with (900x675)
	public static ImageManager IM = new ImageManager();
	public GraphicManager GM = new GraphicManager(this);
	private boolean running;
	private Handler handler;
	private Menu menu;
	private ThreadPool pool;
	private SFXPlayer SFX;
	private MusicPlayer MP;
	/** Actual Game Variables declared below here */
	public FallingCirclesGame circlesGame;
	public SpaceRaceGame spaceRaceGame;
	private SpaceJunkGenerator spaceGenerator;
	
	public enum STATE {
		FallingCirclesGame,
		SpaceRaceGame,
		Menu
	}
	private STATE gameState = STATE.Menu;
	
	public Game() {
		handler = new Handler(this);
		this.addKeyListener(new KeyInput(this));
		menu = new Menu(this);
		new Window(WIDTH, HEIGHT, "Sacred Stones", this);
	}
	
	public void setFallingCirclesGame() {
		handler.clearObjects();
		circlesGame = new FallingCirclesGame(this);
		gameState = STATE.FallingCirclesGame;
	}
	
	public void setSpaceRaceGame() {
		handler.clearObjects();
		spaceRaceGame = new SpaceRaceGame(this);
		spaceGenerator = new SpaceJunkGenerator(this);
		pool.runTask(spaceGenerator);
		gameState = STATE.SpaceRaceGame;
	}
	
	public void render(Graphics g) {
		
		if (gameState == STATE.Menu) {
			menu.render(g);
		} else if (gameState == STATE.SpaceRaceGame ){
			renderBackGround(g);
			handler.render(g);
			spaceRaceGame.timer.drawTime(g);
		}
		
	}
	
	public void tick() {
		handler.tick();
	}
	
	public void renderBackGround(Graphics g) {
		if (gameState == STATE.SpaceRaceGame) {
			g.setColor(Color.black);
			g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
		}
	}

	public void run() {
		running = true;
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		// int frames = 0;
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				delta--;
			}
			if (running) {
				renderGame();
			}
			// frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				// System.out.println("FPS: " + frames);
				// frames = 0;
			}
		}
		stop();
	}
	public void renderGame() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		
		render(g);
		
		g.dispose();
		bs.show();
	}

	public synchronized void start() {
		 pool = new ThreadPool(3);
		 pool.runTask(this);
		// MP = new MusicPlayer("FireEmblemTheme", "FireEmblemHomeTune");
		// pool.runTask(MP);
		 SFX = new SFXPlayer();
		 pool.runTask(SFX);
		 running = true;
	}

	public synchronized void stop() {
		try {
			pool.join();
			running = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static int clamp(int var, int min, int max) {
		if (var >= max) {
			return var = max;
		} else if (var <= min) {
			return var = min;
		} else {
			return var;
		}
	}
	
	public static void main(String[] args) {
		new Game();
	}
	
	public STATE gameState() {
		return gameState;
	}
	
	public void setGameState(STATE state) {
		gameState = state;
	}
	/** Remove all GameObjects from storage */
	public void clearHandler() {
		handler.clearObjects();
	}
	public Menu menu() {
		return menu;
	}
	/** Add a GameObject to the current Game*/
	public void addGameObject(GameObject object) {
		if (object == null) {
			System.out.println("Cannot add null game Objects!");
			return;
		}
		handler.addObject(object);
	}
	
	/** Remove a GameObject from the current Game */
	public void removeGameObject(GameObject object) {
		if (object == null) {
			System.out.println("Cannot remove null GameObjects!");
			return;
		}
		handler.removeObject(object);
	}
}
