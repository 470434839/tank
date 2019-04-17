package com.tank.factory;

import com.tank.characters.EnemyTanks;
import com.tank.characters.Player;
import com.tank.ui.MyGamePanel;

public class Factory implements Runnable {
	private int winnum = 5;
	private int tankNum = 0;
	private int attackedNum = 0;
	public Factory(){
		
	}
	public int getLast() {
		return winnum -attackedNum;
	}
	
	public Factory(int x){
		winnum = x;
	}

	public int getWinnum() {
		return winnum;
	}

	public int getTankNum() {
		return tankNum;
	}

	public int getAttackedNum() {
		return attackedNum;
	}

	public void AttackOneTank() {
		tankNum--;
		attackedNum++;
	}


	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			if (attackedNum>=winnum) {
				Player.getPlayer().life=0;
				break;
			}
			if (attackedNum < winnum - 2 && tankNum < 3) {
				EnemyTanks enemyTanks = new EnemyTanks((int) (Math.random() * 30 + 1), 1, (int) (Math.random() * 8));
				new Thread(enemyTanks).start();
				MyGamePanel.ens.add(enemyTanks);
				tankNum++;
			}
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
