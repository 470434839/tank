package com.tank.ruler;

import java.util.Arrays;
import java.util.List;

import com.tank.characters.EnemyTanks;
import com.tank.characters.Player;
import com.tank.constants.MapConstants;
import com.tank.ui.MyGamePanel;

public class TankMoveRuler {
	public static List<Integer> allowList = Arrays.asList(0, 3, 4);
	public static Object lock = new Object();

	public static boolean canMove(int x, int y) {
		if (!allowList.contains(MapConstants.MAP[y][x])) {//检测地形
			return false;
		}
		for (int i = 0; i < MyGamePanel.ens.size(); i++) {
			EnemyTanks en =MyGamePanel.ens.get(i);
			synchronized (en) {
				if (x == en.x && y == en.y) {
					return false;
				}
			}
			
		}
		if (Player.getPlayer().x == x && Player.getPlayer().y == y) {//检测玩家
			return false;
		}
		return true;
	}
	public static int changePlayerToward(int toward) {
		switch (toward) {
		case 0:
			return 3;
		case 1:
			return 0;
		case 2:
			return 1;
		case 3:
			return 2;
		}
		return toward;
	}
	public static int changeEnemyToward(int toward) {
		switch (toward) {
		case 0:
			return 3;
		case 3:
			return 0;
		}
		return toward;
	}

}
