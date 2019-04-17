package com.tank.characters;


public class EnemyBoom extends Boom{

	public EnemyBoom(int x, int y, int toward) {
		super(x, y, toward);
	}
	
	public void collision(int x, int y) {
		if (Player.getPlayer().x== x&&Player.getPlayer().y==y) {
			Player.getPlayer().attacked();
			boomed = true;
		}
		super.collision(x, y);	
	}
}
