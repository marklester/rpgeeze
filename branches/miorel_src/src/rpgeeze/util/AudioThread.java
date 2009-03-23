package rpgeeze.util;
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
    private volatile boolean STmuted = false;
    private volatile boolean FXmuted = false;
    private Player player;
	
	private AudioThread() {
	}
	
	public static AudioThread getInstance() {
		//returns a new instance each time
		AudioThread instance = new AudioThread();
		return instance;
	}
	
	public void run(String key, int type)
	{
		/*
		Config.getInstance().load(initConfig);
	    BasicPlayer bplayer = new BasicPlayer();
	    List mixers = bplayer.getMixers();
	    if (mixers != null)
	    {
	        Iterator it = mixers.iterator();
	        String mixer = Config.getInstance().getAudioDevice();
	        boolean mixerFound = false;
	        if ((mixer != null) && (mixer.length() > 0))
	        {
	            // Check if mixer is valid. 
	            while (it.hasNext())
	            {
	                if (((String) it.next()).equals(mixer))
	                {
	                    bplayer.setMixerName(mixer);
	                    mixerFound = true;
	                    break;
	                }
	            }
	        }
	        if (mixerFound == false)
	        {
	            // Use first mixer available.
	            it = mixers.iterator();
	            if (it.hasNext())
	            {
	                mixer = (String) it.next();
	                bplayer.setMixerName(mixer);
	            }
	        }
	    }
	    theSoundPlayer = bplayer;
	    */
		switch (type) {
		case CLIP :
			playAudioClip(key);
			break;
		case STREAM :
			play(key);
			break;
		}
	}
	
	public void play(String key) {
		if (!STmuted){
	        try {
	            InputStream is = ResourceLoader.getInstance().getIS(key);
	            BufferedInputStream bis = new BufferedInputStream(is);
	            player = new Player(bis);
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
				InputStream in = ResourceLoader.getInstance().getStreamFromRoot(key); 
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


