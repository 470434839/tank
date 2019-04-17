package com.tank.characters;

import com.tank.constants.ImageConstants;
import com.tank.ruler.TankMoveRuler;
import com.tank.ui.MyGamePanel;

public class EnemyTanks extends Character implements Runnable {
	private int toward;//上：0下：1左：2右：3
	private int type;
	private boolean boomed;

	public void attacked() {
		if (shining) {
			return;
		}
		switch (type) {
		case 1:
		case 3:
		case 5:
			type--;
			break;
		case 7:
			type = 5;
			break;
		case 4:
			type = 6;
			break;
		default:
			boomed = true;
			MyGamePanel.factory.AttackOneTank();
			MyGamePanel.ens.remove(this);
			break;
		}
		directiongX = (type % 4) * 2;
	}

	private void move() {
		switch ((int) (toward)) {
		case 0:
			if (TankMoveRuler.canMove(x, y - 1)) {
				y--;
				isAttacked(x, y);
			} else {
				toward = (int) (Math.random() * 4);
			}
			directiongY = (type / 4) * 4;
			break;
		case 1:
			if (TankMoveRuler.canMove(x, y + 1)) {
				y++;
				isAttacked(x, y);
			} else {
				toward = (int) (Math.random() * 4);
			}
			directiongY = 2 + (type / 4) * 4;
			break;
		case 2:
			if (TankMoveRuler.canMove(x - 1, y)) {
				x--;
				isAttacked(x, y);
			} else {
				toward = (int) (Math.random() * 4);
			}
			directiongY = 3 + (type / 4) * 4;
			break;
		case 3:
			if (TankMoveRuler.canMove(x + 1, y)) {
				x++;
				isAttacked(x, y);
			} else {
				toward = (int) (Math.random() * 4);
			}
			directiongY = 1 + (type / 4) * 4;
			break;

		default:
			break;
		}
	}

	public EnemyTanks(int x, int y, int type) {
		this.x = x;
		this.y = y;
		this.type = type;
		toward = (int) (Math.random() * 4);
		directiongY = 2 + (type / 4) * 4;
		directiongX = (type % 4) * 2;
		this.image = ImageConstants.enemyTank;
	}

	@Override
	public void run() {
		int speed = 3;
		while (true) {
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (shining) {
				continue;
			}
			if (boomed || Player.getPlayer().life < 1) {
				break;
			}
			directiongX++;
			if (directiongX > (type % 4) * 2 + 1) {
				directiongX = (type % 4) * 2;
			}
			speed++;
			if (speed > 3) {
				speed = 0;
				if (Math.random() > 0.95) {
					toward = (int) (Math.random() * 4);
				}
				synchronized (TankMoveRuler.lock) {
					move();
				}
			}
			if (Math.random() > 0.92) {
				attack(toward);
			}

		}
	}

	@Override
	public void attack(int toward) {
		{
			Boom boom = new EnemyBoom(x, y, TankMoveRuler.changeEnemyToward(toward));
			MyGamePanel.booms.add(boom);
		}
	}

	@Override
	protected void isAttacked(int x, int y) {
		for (int i = 0; i < MyGamePanel.booms.size(); i++) {
			Boom b = MyGamePanel.booms.get(i);
			if (b instanceof PlayerBoom) {
				if (b.x == x && b.y == y) {
					if (!b.boomed) {
						this.attacked();
						b.boomed = true;
					}
				}
			}
		}
	}
}
