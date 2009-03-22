package util;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import java.io.File;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;


import javazoom.jlgui.basicplayer.*;
//import javazoom.jlgui.player.amp.util.Config;
//import javazoom.spi.PropertiesContainer;



public class AudioThread extends Thread implements Observer {

	public static final int CLIP = 0;
	public static final int STREAM = 1;
	
	private BasicPlayer bp;
	public boolean soundFXMuted = false;
	//private BasicController theSoundPlayer = null;
	//private String initConfig = "jlgui.ini";
	
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
		try {
	    	File inFile = ResourceLoader.getInstance().getAudioFile(key);
	    	bp = new  BasicPlayer();
	    	bp.open(inFile);
	    	bp.play();
	    	bp.setGain(1f);
	    	//theSoundPlayer.open(inFile);//new File("F:\\--oop--\\rpgeeze_svn\\res\\audio\\zelda_13.wav"));
	    	//theSoundPlayer.play();
	    }
	    catch (Exception e) {e.printStackTrace();}
	}
	
	public void playAudioClip(String key) {
		try {
			if (!soundFXMuted) {
				InputStream in = ResourceLoader.getInstance().getStreamFromRoot(key); 
				AudioStream as = new AudioStream(in);
				AudioPlayer.player.start(as);
			}
		}
		catch (Exception e) { System.out.println(e); }
	}
	
	
	public void fadeDown() {
		try
        {
			float currGain = 1;		
			float amt = bp.getPrecision();
			if (amt == 0) amt = (float) 2e-63;
			while (currGain > 0) {				
				bp.setGain(currGain);
				currGain -= amt;
			}			
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }		
	}

	@Override
	public void update(Subject s) {
		//fadeDown();
	}
		
}


