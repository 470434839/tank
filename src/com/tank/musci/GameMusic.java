package com.tank.musci;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
/**
 * 播放音乐
 * 
 * @author Administrator
 *
 */
public class GameMusic implements Runnable{

	private AudioClip ac;
	
	
	public GameMusic(String path) {
		URI url;
		try {
			url = new File(path).toURI();
			ac=Applet.newAudioClip(url.toURL());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {	
		//播放音乐
		if (ac != null) {
			ac.play();
		}
	}
	
	
	
		

}

