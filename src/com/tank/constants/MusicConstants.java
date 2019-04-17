package com.tank.constants;

import com.tank.musci.GameMusic;

public class MusicConstants {
	public static GameMusic star;
	public static GameMusic blast;
	public static GameMusic shoot;
	
	static {
		star = new GameMusic("img/start.wav");
		blast = new GameMusic("img/blast.wav");
		shoot = new GameMusic("img/shoot.wav");
	}
}
