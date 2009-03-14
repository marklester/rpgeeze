//import javax.sound.midi.*;
//import java.io.*;
import java.io.BufferedInputStream;
import java.io.InputStream;
import util.ResourceLoader;
import javazoom.jl.player.Player;
import model.Entity;
import model.Model;
import util.Observer;
import util.Subject;

public class AudioThread extends Thread implements Observer {
	
	private volatile boolean muted = false;
	private Player player; 	

	
	public AudioThread() {
		super();
	}
	
	public void run() {
        setAudio();
        if (!muted){
        	try { player.play(); }
        	catch (Exception e) { System.out.println(e); }
        }        	
	}	
	
    public void setAudio() {
    	setAudio("Intro");
    }
    
    
    // play the MP3 file to the sound card
    public void setAudio(String key) {
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
	
	public void close() { 
    	if (player != null) 
    		player.close();
    }
		
	public void setMute(boolean b) {
		muted = b;
	}
	
	public boolean isMuted() {
		return muted;
	}
	
	public void update(Subject s) {
		//Here we can be updated if the Entity enters a new area of the map, enters a door, etc.
	}
	
	
}
