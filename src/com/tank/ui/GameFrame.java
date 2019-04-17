package com.tank.ui;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import com.tank.characters.Player;
import com.tank.constants.ImageConstants;
import com.tank.constants.MapConstants;

public class GameFrame extends JFrame {
	/**
	 * 
	 */
	private static MyGamePanel gp;
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		GameFrame aFrame = new GameFrame();
	}
	public static MyGamePanel getgp() {
		return gp;
	}

	public GameFrame() {
		setTitle("坦克大战");
		setIconImage(ImageConstants.icon);
		gp = new MyGamePanel();
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setBounds(((int) dim.getWidth() - MapConstants.WIDTH) / 2,
				((int) dim.getHeight() - MapConstants.HEIGHT) / 2, MapConstants.WIDTH, MapConstants.HEIGHT);
		this.setContentPane(gp);
		this.addKeyListener(Player.getPlayer());
		this.setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}