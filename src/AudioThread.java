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
	
	private volatile boolean muted = true;
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

/*


import util.Observer;
import util.Subject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Properties;

import javax.naming.ldap.Control;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.FloatControl; 

import javazoom.spi.PropertiesContainer;


public class AudioThread extends Thread implements Observer //extends TestCase
{
	private String filename="res\\audio\\zelda1.mp3";
	private Properties props = null;
	private AudioFormat decodedFormat;
	

	public AudioThread()
	{
	}
	
	
	public void update(Subject s) {
		//Here we can be updated if the Entity enters a new area of the map, enters a door, etc.
		setVolume(0);
	}
	
	public void setVolume(float amt) {
			SourceDataLine dataLine;
			try {
				dataLine = getLine(this.decodedFormat);
				if( dataLine.isControlSupported( FloatControl.Type.MASTER_GAIN ) ) {
					FloatControl gainControl =
				           (FloatControl) dataLine.getControl(FloatControl.Type.MASTER_GAIN);
			        float range = gainControl.getMaximum() - gainControl.getMinimum();
			        float gain = (range * amt) + gainControl.getMinimum();
			        gainControl.setValue(gain);
				}
			} catch (LineUnavailableException e) {
				e.printStackTrace();
			} 
	}
	

	protected void setUp() throws Exception
	{
		props = new Properties();
		InputStream pin = getClass().getClassLoader().getResourceAsStream("test.mp3.properties");
		props.load(pin);	
	}

	
	
	public void run()
	{
	 try
	 {
		File file = new File(filename);
		AudioInputStream in= AudioSystem.getAudioInputStream(file);
		AudioInputStream din = null;
		if (in != null)
		{
		  AudioFormat baseFormat = in.getFormat();
		  this.decodedFormat = new AudioFormat(
			  AudioFormat.Encoding.PCM_SIGNED,
			  baseFormat.getSampleRate(),
			  16,
			  baseFormat.getChannels(),
			  baseFormat.getChannels() * 2,
			  baseFormat.getSampleRate(),
			  false);
		  din = AudioSystem.getAudioInputStream(decodedFormat, in);
		  if (din instanceof PropertiesContainer)
		  {
			System.out.println("PropertiesContainer : OK");
		  }
		  else
		  {
			System.out.println("Wrong PropertiesContainer instance");
		  }
		  rawplay(decodedFormat, din);
		  in.close();		
		  
		}
	 }
	 catch (Exception e)
	 {
		System.out.println("AudioThread: "+e.getMessage());
	 }
	}

	private SourceDataLine getLine(AudioFormat audioFormat) throws LineUnavailableException
	{
	  SourceDataLine res = null;
	  DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
	  res = (SourceDataLine) AudioSystem.getLine(info);
	  res.open(audioFormat);
	  return res;
	}
	
	private void rawplay(AudioFormat targetFormat, AudioInputStream din) throws IOException, LineUnavailableException
	{
		byte[] data = new byte[4096];
		SourceDataLine line = getLine(targetFormat);		
		if (line != null)
		{
		  // Start
		  line.start();
		  int nBytesRead = 0, nBytesWritten = 0;
		  while (nBytesRead != -1)
		  {
			nBytesRead = din.read(data, 0, data.length);
			if (nBytesRead != -1) nBytesWritten = line.write(data, 0, nBytesRead);
		  }
		  setVolume(0);
		  // Stop
		  line.drain();
		  line.stop();
		  line.close();
		  din.close();
		}		
	}
}

*/
