package util;

/**
 * Centralized access point for resource files.
 */

import java.util.Hashtable;
import java.awt.Image;
import javax.imageio.ImageIO;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import java.io.InputStream;
import java.io.IOException;
import java.util.Scanner;

public class ResourceLoader {
	private final ClassLoader loader;
	private final Hashtable<String, Image> images;
	private final Hashtable<String, String> items;
	private final Hashtable<String, String> audios;
	private static ResourceLoader instance = null;
	
	/*
	static {
		getInstance().getAudioClip("");		
	}
	*/
	
	/**
	 * Constructs a new ResourceLoader. Since this is a singleton, this is
	 * only called once, from within a static method.
	 */
	private ResourceLoader() {
		this.loader = getClass().getClassLoader();
		this.images = new Hashtable<String, Image>();
		this.items = new Hashtable<String,String>(); //Modify this to set Item Images
		this.audios = new Hashtable<String,String>();
		items.put("Boulder", "img/terrain20px/Boulder.png");
		items.put("Cross Bow", "img/crossbow.png");
		items.put("Sword", "img/sword.png");
		items.put("Potion Life", "img/potionlife.png");
		items.put("Red Armor", "img/redarmor.png");
		items.put("Boots", "img/boots.png");
		items.put("Arrows", "img/arrows.png");
		items.put("Shield", "img/shield.png");
		items.put("Mana", "img/mana.png");
		
		audios.put("Instant Death", "audio/evilLaugh.wav");
		audios.put("Portal Item", "audio/elevator.wav");
		
		images.put("Intro Image",getImage("img/IntroOccupationTypes.png"));
		images.put("New Game Button",getImage("img/buttons/NewGame.png"));
		images.put("Load Game Button",getImage("img/buttons/LoadGame.png"));
		images.put("Quit Game Button",getImage("img/buttons/QuitGame.png"));
		images.put("Smasher Button",getImage("img/buttons/Smasher.png"));
		images.put("Summoner Button",getImage("img/buttons/Summoner.png"));
		images.put("Sneak Button",getImage("img/buttons/Sneak.png"));
	}
	
	
	/**
	 * Returns the singleton instance of this class.
	 */
	public static ResourceLoader getInstance() {
		if(instance == null)
			instance = new ResourceLoader();
		return instance;
	}
	
	/**
	 * Gives you the Image corresponding to the specified key. Currently the
	 * path is used as the key. When I think of a nicer way to do this, I'll do
	 * it. 
	 */
	public Image getImage(String key) {
		Image ret = images.get(key);
		if(ret == null) {
			try {
				ret = ImageIO.read(getStream(key));
			}
			catch(IOException e) {
			}
			images.put(key, ret);
		}
		return ret;
	}
	public Image getItemImage(String key){
		return getImage(items.get(key));
	}
	
	//Plays an audio clip, derrr
	public void playAudioClip(String key) {
		try {
			InputStream in = getStream(audios.get(key)); 
			AudioStream as = new AudioStream(in);
			AudioPlayer.player.start(as);
		}
		catch (Exception e) { System.out.println(e); }
	}
	
	/**
	 * Gives you an InputStream corresponding to the specified key. Currently
	 * the path is used as the key. When I think of a nicer way to do this,
	 * I'll do it.
	 */
	public InputStream getStream(String key) {
		InputStream ret = loader.getResourceAsStream("res/" + key);
		return ret;
	}

	public Scanner getScanner(String key) {
		return new Scanner(getStream(key));
	}
}
