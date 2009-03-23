package rpgeeze.util;
import rpgeeze.GameProperties;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;


//import javazoom.jlgui.basicplayer.*;
import javazoom.jl.player.Player;



public class AudioThread extends Thread implements Observer {

	public static final int CLIP = 0;
	public static final int STREAM = 1;
    private volatile boolean STmuted = true;
    private volatile boolean FXmuted = false;
    private int type;
    private String key;
    private Player player;
	
	private AudioThread() {
		super();
	}
	
	public static AudioThread getInstance() {
		//returns a new instance each time
		AudioThread instance = new AudioThread();
		return instance;
	}
	
	public void setKeyType(String key, int type)
	{
		this.key = key;
		this.type = type;
	}
	
	public void run()
	{
		switch (type) {
		case CLIP :
			playAudioClip(key);
			break;
		case STREAM :
			playStream(key);
			break;
		}
	}
	
	public void playStream(String key) {
		if (!STmuted){
	        try {
	            InputStream is = ResourceLoader.getInstance().getAudio( GameProperties.getInstance().getProperty("audio.game") );
	            BufferedInputStream bis = new BufferedInputStream(is);
	            player = new Player(bis);
	            player.play();
	        }
	        catch (Exception e) {
	            System.out.println("Problem playing file " + key);
	            System.out.println(e);
	        }
		}
	}
	
	public void playAudioClip(String key) {
		try {
			if (!FXmuted) {
				key = key.toLowerCase().replace(" ", ".");
				InputStream in = ResourceLoader.getInstance().getAudio( GameProperties.getInstance().getProperty("audio." +key) );
				//InputStream in = ResourceLoader.getInstance().getAudio(key); 
				AudioStream as = new AudioStream(in);
				AudioPlayer.player.start(as);
			}
		}
		catch (Exception e) { System.out.println(e); }
	}
	
    public void close() {
        if (player != null)
                player.close();
    }	
	
	@Override
	public void update(Subject s) {
		//fadeDown();
	}
		
}


