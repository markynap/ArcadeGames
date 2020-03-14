package gameExtras;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import gameMain.*;
import gameMain.Game.STATE;

public class KeyInput extends KeyAdapter{
	
	private Game game;
	private char key;
	private int keyCode;

	public KeyInput(Game game) {
		this.game = game;
	}
	
	public void keyPressed(KeyEvent e) {
		key = e.getKeyChar();
		keyCode = e.getExtendedKeyCode();
		
		if (game.gameState() == STATE.Menu) {
			
			if (keyCode == KeyEvent.VK_RIGHT) {
				game.menu().incSelectedIndex(1);
			} else if (keyCode == KeyEvent.VK_LEFT) {
				game.menu().incSelectedIndex(-1);
			} else if (keyCode == KeyEvent.VK_UP) {
				game.menu().incSelectedIndex(-3);
			} else if (keyCode == KeyEvent.VK_DOWN) {
				game.menu().incSelectedIndex(3);
			}
			
			if (key == 'a' || keyCode == KeyEvent.VK_ENTER) {
				switch (game.menu().selectedIndex()) {
			//	case 0: game.setFallingCirclesGame();
			//		break;
				case 1: game.setSpaceRaceGame();
					break;
				default: System.out.println("Have no data to set for occurance of Game Index #" + game.menu().selectedIndex());
				}
			}
			
			
			
		} else if (game.gameState() == STATE.SpaceRaceGame) {
			
			if (key == 'w') {
				game.spaceRaceGame.playerOne.setAcceleration(true);
			} else if (key == 's') {
				game.spaceRaceGame.playerOne.setAcceleration(false);
			} else if (key == 'a' || key == 'd' ) {
				game.spaceRaceGame.playerOne.stop();
			}
			
			if (keyCode == KeyEvent.VK_UP) {
				game.spaceRaceGame.playerTwo.setAcceleration(true);
			} else if (keyCode == KeyEvent.VK_DOWN) {
				game.spaceRaceGame.playerTwo.setAcceleration(false);
			} else if (keyCode == KeyEvent.VK_RIGHT || keyCode == KeyEvent.VK_LEFT) {
				game.spaceRaceGame.playerTwo.stop();
			}
			
			
		}
	}
}
