package com.tank.characters;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

import com.tank.constants.ImageConstants;
import com.tank.constants.MapConstants;

public abstract class Character {
	public int x;
	public int y;
	protected int directiongY;//上：0下：2左：3右：1
	protected int directiongX;
	protected boolean shining = true;
	protected int size = 28;
	protected Image image;
	protected int shiningStep=0;

	public abstract void attack(int toward) ;
	protected abstract void isAttacked(int x,int y);

	public void draw(Graphics g,JPanel jp) {
		if (shining) {
			g.drawImage(ImageConstants.star,x << 5, y << 5, (x + 1) << 5, (y + 1) << 5, 192*(shiningStep>>2), 0,
					192*((shiningStep>>2)+1), 192,jp);
			shiningStep++;
			if (shiningStep==40) {
				shining=false;
			}
	}else {
			
		g.drawImage(image, x << 5, y << 5, (x + 1) << 5, (y + 1) << 5, directiongX * size, directiongY * size,
				(directiongX + 1) * size, (directiongY + 1) * size, jp);
		if (MapConstants.MAP[y][x] == 4) {
			g.drawImage(ImageConstants.grass, 32 * x - 5, 32 * y - 5, 32 + 32 * x + 5, 32 + 32 * y + 5, 0, 0, 87, 83,
					jp);
			
		}
		}
	}

}
