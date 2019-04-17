package com.tank.ui;

import java.awt.Graphics;

import javax.swing.JPanel;

import com.tank.constants.ImageConstants;

public class GameOver {
	public static void draw(Graphics g,JPanel jp) {
		// TODO Auto-generated method stub
		g.drawImage(ImageConstants.over, 411,347, 667, 507, 0, 0, 256, 160, jp);
	}
}
