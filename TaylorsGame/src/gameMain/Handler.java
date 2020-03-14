package gameMain;

import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {

	private Game game;
	private LinkedList<GameObject> objects;
	
	public Handler(Game game) {
		this.game = game;
		objects = new LinkedList<>();
	}
	
	public void render(Graphics g) {
		for (int i = 0; i < objects.size(); i++) {
			objects.get(i).render(g);
		}
	}
	
	public void tick() {
		for (int i = 0; i < objects.size(); i++) {
			objects.get(i).tick();
		}
	}
	
	public void clearObjects() {
		objects.clear();
	}
	
	public void addObject(GameObject obj) {
		objects.add(obj);
	}
	
	public void removeObject(GameObject obj) {
		objects.remove(obj);
	}
}
