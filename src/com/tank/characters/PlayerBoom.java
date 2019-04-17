package com.tank.characters;

import com.tank.ruler.TankMoveRuler;
import com.tank.ui.MyGamePanel;

public class PlayerBoom extends Boom {

	public PlayerBoom(int x, int y, int toward) {
		super(x, y, toward);
	}

	@Override
	public void collision(int x, int y) {
		synchronized (TankMoveRuler.lock) {
			for (int i = 0; i < MyGamePanel.ens.size(); i++) {
				EnemyTanks en = MyGamePanel.ens.get(i);
				if (en.x == x && en.y == y) {
					if (!boomed) {
						en.attacked();
						boomed = true;
						return;
					}
				}
			}
		}
		super.collision(x, y);
	}

}
