package com.tank.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.List;
import java.util.Vector;

import javax.swing.JPanel;

import com.tank.characters.Boom;
import com.tank.characters.EnemyTanks;
import com.tank.characters.Player;
import com.tank.constants.ImageConstants;
import com.tank.constants.MapConstants;
import com.tank.constants.MusicConstants;
import com.tank.factory.Factory;

public class MyGamePanel extends JPanel implements Runnable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int bossImageY = 0;
	public static List<EnemyTanks> ens = new Vector<EnemyTanks>();
	private static Player player = null;
	public static List<Boom> booms = new Vector<Boom>();
	public static Factory factory;

	public MyGamePanel() {
		setBackground(Color.BLACK);
		factory = new Factory();
		new Thread(factory).start();
		player = Player.getPlayer();
		player.life = 3;
		new Thread(this).start();
		setFont(new Font("宋体", 0, 32));
		new Thread(MusicConstants.star).start();
	}
	private void drawLife(Graphics g) {
		g.drawString("剩余生命:"+player.life+"    剩余敌人"+factory.getLast()+"    第"+(factory.getWinnum()-3)/2+"关", 50,28);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (player.life < 1) {
			if (factory.getAttackedNum()>= factory.getWinnum()) {
				Win.draw(g, this);
			}else {
				GameOver.draw(g, this);
			}
		} else {
			drawMap(g);
			drawLife(g);
			for (int i = 0; i < ens.size(); i++) {
				ens.get(i).draw(g,this);
			}
			player.draw(g,this);
			for (int i = 0; i < booms.size(); i++) {
				booms.get(i).draw(g, this);
			}
		}
	}

	public void drawMap(Graphics graphics) {
		for (int y = 0; y < MapConstants.MAP.length; y++) {
			for (int x = 0; x < MapConstants.MAP[y].length; x++) {
				int num = MapConstants.MAP[y][x];
				switch (num) {
				case 0:// null
					break;
				case 1:// 砖头
					graphics.drawImage(ImageConstants.wall, 32 * x, 32 * y, 32 + 32 * x, 32 + 32 * y, 0, 0, 60, 60,
							this);
					break;
				case 2:// 铁
					graphics.drawImage(ImageConstants.steels, 32 * x, 32 * y, 32 + 32 * x, 32 + 32 * y, 0, 0, 32, 32,
							this);
					break;
				case 3:// 冰
					graphics.drawImage(ImageConstants.ice, 32 * x, 32 * y, 32 + 32 * x, 32 + 32 * y, 0, 0, 64, 64,
							this);
					break;
				case 4:// 草
					graphics.drawImage(ImageConstants.grass, 32 * x, 32 * y, 32 + 32 * x, 32 + 32 * y, 0, 0, 87, 83,
							this);
					break;
				case 5:// 水
					graphics.drawImage(ImageConstants.water, 32 * x, 32 * y, 32 + 32 * x, 32 + 32 * y, 0, 0, 122, 122,
							this);
					break;
				case 6:// 边框
					graphics.drawImage(ImageConstants.border, 32 * x, 32 * y, 32 + 32 * x, 32 + 32 * y, 0, 0, 74, 78,
							this);
					break;
				case 7:// 老家
					graphics.drawImage(ImageConstants.boss, 32 * x, 32 * y, 32 + 32 * x, 32 + 32 * y, 0,
							bossImageY * 34, 41, 34 + bossImageY * 34, this);
					break;

				default:
					break;
				}
			}
		}
	}

	@Override
	public void run() {
		int bossSpeed = 0;
		while (true) {
			if (player.life < 1) {
				over();
				break;
			}
			player.imageStep();
			bossSpeed++;
			if (bossSpeed > 5) {
				bossImageY++;
				bossSpeed = 0;
			}
			if (bossImageY == 12) {
				bossImageY = 0;
			}
			try {
				Thread.sleep(ImageConstants.FRESH);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			repaint();
		}
	}
	
	public static void nextLevel() {
		if (factory.getAttackedNum()>= factory.getWinnum()) {
			player.life =3;
			player.relife();
			GameFrame.getgp().setBackground(Color.black);
			factory = new Factory(factory.getWinnum()+2);
			MapConstants.drawMap();
			booms = new Vector<Boom>();
			new Thread(factory).start();
			new Thread(GameFrame.getgp()).start();
			new Thread(MusicConstants.star).start();
		}else if (player.life==0) {
			player.life =3;
			player.relife();
			GameFrame.getgp().setBackground(Color.black);
			factory = new Factory(factory.getWinnum());
			MapConstants.drawMap();
			booms = new Vector<Boom>();
			ens = new Vector<EnemyTanks>();
			new Thread(factory).start();
			new Thread(GameFrame.getgp()).start();
			new Thread(MusicConstants.star).start();
		}
	}

	private void over() {
		setBackground(Color.RED);
		repaint();
	}

}
