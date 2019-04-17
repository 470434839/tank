package com.tank.characters;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import com.tank.constants.ImageConstants;
import com.tank.constants.MusicConstants;
import com.tank.ruler.TankMoveRuler;
import com.tank.ui.MyGamePanel;

public class Player extends Character implements KeyListener {
	private static Player player;
	public int life;
	private int moveStep=4;
	Player(){
		this.x = 13;
		this.y = 23;
		this.image = ImageConstants.player;
	}
	
	public static Player getPlayer() {
		if (player == null) {
			player = new Player();
		}
		return player;	
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode()==KeyEvent.VK_R) {
			MyGamePanel.nextLevel();
		}
		if (shining) {
			return;
		}
		if (moveStep>2) {
			
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			if (TankMoveRuler.canMove(x , y-1)) {
				y--;
				isAttacked(x, y);
			}
			directiongY = 0;
			break;
		case KeyEvent.VK_DOWN:
			if (TankMoveRuler.canMove(x , y+1)) {
				y++;
				isAttacked(x, y);
			}

			directiongY = 2;
			break;
		case KeyEvent.VK_LEFT:
			if (TankMoveRuler.canMove(x-1, y)) {
				x--;
				isAttacked(x, y);
			}
			directiongY = 3;
			break;
		case KeyEvent.VK_RIGHT:
			if (TankMoveRuler.canMove(x+1, y)) {
				x++;
				isAttacked(x, y);
			}
			directiongY = 1;
			break;
		case KeyEvent.VK_A:
		case KeyEvent.VK_SPACE:
			if (life < 1) {
				return;
			}
			attack(directiongY);
			break;
			
		default:
			break;
		}
		moveStep =0;
		}
		moveStep++;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		moveStep = 4;
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}
	@Override
	public void attack(int toward) {
		Boom boom = new PlayerBoom(x, y, TankMoveRuler.changePlayerToward(toward));
		MyGamePanel.booms.add(boom);	
		new Thread(MusicConstants.shoot).start();
	}
	public void imageStep() {
		directiongX++;
		if (directiongX == 2) {
			directiongX = 0;
		}
		
	}
	@Override
	protected void isAttacked(int x, int y) {
		for (int i = 0; i < MyGamePanel.booms.size(); i++) {
			Boom b = MyGamePanel.booms.get(i);
			if (b instanceof EnemyBoom) {
				if (b.x==x&&b.y==y) {
				if (!b.boomed) {
				this.attacked();
				b.boomed = true;
				}
				}
			}
		}
	}
	public void attacked() {
		if (shining) {
			return;
		}
		life--;
		relife();
	}
	public void relife() {
		x = 13;
		y = 23;
		shining = true;
		shiningStep = 0;
		directiongY = 0;
	}
	
}
