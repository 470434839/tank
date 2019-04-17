package com.tank.characters;

import java.awt.Graphics;

import javax.swing.JPanel;

import com.tank.constants.ImageConstants;
import com.tank.constants.MapConstants;
import com.tank.constants.MusicConstants;
import com.tank.ui.MyGamePanel;

public class Boom {
	int x;
	int y;
	
	int toward; 
	boolean boomed = false;
	int boomsetp = 0;
	int movestep = 5; 
	public String type = this.getClass().getName();

	public Boom(int x, int y, int toward) {
		this.x = x;
		this.y = y;
		this.toward = toward;
	}

	public void draw(Graphics g, JPanel jp) {
		if (boomed) {
			g.drawImage(ImageConstants.boom, x << 5, y << 5, (x + 1) << 5, (y + 1) << 5, boomsetp * 192, 0,
					(boomsetp + 1) * 192, 192, jp);
			if (boomsetp == 0) {
				new Thread(MusicConstants.blast).start();
			}
			boomsetp++;
			if (boomsetp > 10) {
				MyGamePanel.booms.remove(this);
			}
		} else {
			movestep++;
			if (movestep > 2) {
				move();
				movestep = 0;
			}
			g.drawImage(ImageConstants.bullet, x << 5, y << 5, (x + 1) << 5, (y + 1) << 5, toward << 5, 0,
					(toward + 1) << 5, 32, jp);
		}
	}

	public void move() {
		switch ((int) (toward)) {
		case 3:
			y--;
			break;
		case 1:
			y++;
			break;
		case 0:
			x++;
			break;
		case 2:
			x--;
			break;
		default:
			break;
		}
		collision(x, y);
	}

	public void collision(int x, int y) {
		for (int i = 0; i < MyGamePanel.booms.size(); i++) {
			Boom b = MyGamePanel.booms.get(i);
			if (this.type!= b.type) {
				if (x==b.x&&y==b.y) {
					this.boomed=true;
					b.boomed =true;
				}
			}
		}
		switch (MapConstants.MAP[y][x]) {
		case 1:
			MapConstants.MAP[y][x] = 0;
			boomed = true;
			break;
		case 2:
			boomed = true;
			break;
		case 6:
			boomed = true;
			break;
		case 7:
			Player.getPlayer().life=0;
			boomed = true;
			break;
		default:
			break;
		}
	}
}
