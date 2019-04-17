package com.tank.constants;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageConstants {
	public static Image grass;
	public static Image wall;
	public static Image steels;
	public static Image ice;
	public static Image water;
	public static Image border;
	public static Image boss;
	public static Image player;
	public static Image icon;
	public static Image enemyTank;
	public static Image bullet;
	public static Image star;
	public static Image boom;
	public static Image over;
	public static Image win;

	public static final int FRESH = 40;
	static {
		try {
			grass = ImageIO.read(new File("img/grass.png"));
			wall = ImageIO.read(new File("img/wall.gif"));
			steels = ImageIO.read(new File("img/steels.png"));
			ice = ImageIO.read(new File("img/ice.png"));
			water = ImageIO.read(new File("img/water1.jpg"));
			border = ImageIO.read(new File("img/gray.png"));
			boss = ImageIO.read(new File("img/boss.gif"));
			player = ImageIO.read(new File("img/player.gif"));
			icon = ImageIO.read(new File("img/boss1.gif"));
			enemyTank = ImageIO.read(new File("img/tanks.bmp"));
			bullet = ImageIO.read(new File("img/bullet.png"));
			star = ImageIO.read(new File("img/star.png"));
			boom = ImageIO.read(new File("img/boom.png"));
			over = ImageIO.read(new File("img/gameover.bmp"));
			win = ImageIO.read(new File("img/win.bmp"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
